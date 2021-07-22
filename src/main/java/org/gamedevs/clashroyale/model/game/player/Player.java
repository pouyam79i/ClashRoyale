package org.gamedevs.clashroyale.model.game.player;

import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.tools.CardGenerator;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.battle.tools.GameResult;
import org.gamedevs.clashroyale.model.game.droppable.CardFactory;
import org.gamedevs.clashroyale.model.game.droppable.Droppable;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.KingTower;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.PrincessTower;
import org.gamedevs.clashroyale.model.game.droppable.spell.Spell;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.console.ConsoleColor;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.util.ArrayList;

/**
 * This class contains player structure.
 * What ever a player need to interact with the game.
 *
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public abstract class Player extends Runnable {

    /**
     * Map of game (also has map engine code -> drop)
     */
    protected final Map map;
    /**
     * Player side! (TOP/DOWN)
     */
    protected final Side playerSide;
    /**
     * Elixir counter
     */
    protected final Elixir elixir;
    /**
     * Player card generator gives current usable cards.
     */
    protected final CardGenerator cardGenerator;
    /**
     * level of player
     */
    protected final int level;

    // Player main towers:
    /**
     * tower of king!
     */
    protected KingTower kingTower;
    /**
     * tower of left princess
     */
    protected PrincessTower leftPrincessTower;
    /**
     * tower of right princess
     */
    protected PrincessTower rightPrincessTower;

    /**
     * Gets main game tools to be able to play.
     *
     * @param map           of game
     * @param playerSide    side of player (TOP/DOWN)
     * @param elixir        counter of elixir
     * @param cardGenerator player card generator
     * @param level         level of player
     */
    protected Player(Map map, Side playerSide, Elixir elixir, CardGenerator cardGenerator, int level) {
        threadName = "Player";
        this.playerSide = playerSide;
        this.map = map;
        this.elixir = elixir;
        this.cardGenerator = cardGenerator;
        this.level = level;
        kingTower = new KingTower(level, playerSide);
        leftPrincessTower = new PrincessTower(level, playerSide);
        rightPrincessTower = new PrincessTower(level, playerSide);
    }

    /**
     * drops main tower of player
     */
    public void dropMainTowers() {
        map.setMainTower(kingTower, playerSide, 0);
        map.setMainTower(leftPrincessTower, playerSide, -1);
        map.setMainTower(rightPrincessTower, playerSide, 1);
    }

    /**
     * bind game result to life of main tower!
     */
    public void bindGameResult(GameResult gameResult){
        kingTower.setGameResult(gameResult);
        leftPrincessTower.setGameResult(gameResult);
        rightPrincessTower.setGameResult(gameResult);
    }

    /**
     * Translate x and y according to player side!
     * Tells the map engine to drop player card on that!
     * Also no need to translate x and y for bot,
     * Because it knows all the map by reading it!
     *
     * @param x    of drop
     * @param y    of drop
     * @param card of drop
     */
    public boolean drop(double x, double y, Card card) {
        if(card == null){
            Console.getConsole().printTracingMessage(ConsoleColor.RED_BOLD + "Null card input");
            return false;
        }
//        Console.getConsole().printTracingMessage("x, y init : " + x + " ," + y);
        int tileX = MouseTilePosition.TranslatePixelToTileX(x);
        int tileY = MouseTilePosition.TranslatePixelToTileY(y);
        if(tileX < 0 || tileX > 17 || tileY < 0 || tileY > 29){
            Console.getConsole().printTracingMessage(ConsoleColor.RED_BOLD + "wrong tile position at player drop: " +
                    tileX + "," + tileY);
            return false;
        }
//        Console.getConsole().printTracingMessage("x,y: " + tileX + ", " + tileY);

        if (card.getCardName() != CardName.RAGE &&
                card.getCardName() != CardName.FIREBALL &&
                card.getCardName() != CardName.ARROWS) {
            ArrayList<Droppable> droppables = CardFactory.buildDroppableItems(card.getCardName(), level, playerSide);
            if (!map.dropGameObject(tileX, tileY, (GameObject) droppables.get(0))) {
                return false;
            } else {
                for (int i = 1; i < droppables.size(); i++)
                    map.dropGameObject(tileX, tileY, (GameObject) droppables.get(i));
                return true;
            }

        } else
            return map.dropSpell(tileX, tileY, (Spell) CardFactory.buildDroppableItems(card.getCardName(), level, playerSide).get(0));
    }

    /**
     * Translate x and y according to player side!
     * Tells the map engine to drop player card on that!
     * Also no need to translate x and y for bot,
     * Because it knows all the map by reading it!
     *
     * @param x        of drop
     * @param y        of drop
     * @param cardName of drop
     */
    public boolean drop(int x, int y, CardName cardName) {
        return false; // TODO: change when code is completed
    }

    // Getters
    public Map getMap() {
        return map;
    }

    public Side getPlayerSide() {
        return playerSide;
    }

    public Elixir getElixir() {
        return elixir;
    }

    public CardGenerator getCardGenerator() {
        return cardGenerator;
    }

    public int getLevel() {
        return level;
    }

    public KingTower getKingTower() {
        return kingTower;
    }

    public PrincessTower getLeftPrincessTower() {
        return leftPrincessTower;
    }

    public PrincessTower getRightPrincessTower() {
        return rightPrincessTower;
    }
}
