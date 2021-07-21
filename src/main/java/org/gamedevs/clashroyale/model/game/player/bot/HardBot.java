package org.gamedevs.clashroyale.model.game.player.bot;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.container.gamedata.PlayerContainer;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.battle.engine.map.path.PathSide;
import org.gamedevs.clashroyale.model.game.battle.tools.CardGenerator;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Player;
import org.gamedevs.clashroyale.model.game.player.Side;

import java.util.Random;

/**
 * a class which handle hard bot
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
public class HardBot extends Bot {

    /**
     * Gets main game tools to be able to play
     *
     * @param map           of game
     * @param playerSide    side of player (TOP/DOWN)
     * @param elixir        counter of elixir
     * @param cardGenerator player card generator
     * @param level         level of player
     */
    public HardBot(Map map, Side playerSide, Elixir elixir, CardGenerator cardGenerator, int level) {
        super(map, playerSide, elixir, cardGenerator, level);
    }

    /**
     * Applies the algorithm of bot! (Hard bot)
     * Decides what to do for next!
     */
    @Override
    protected void algorithm() {
        pickCard();
    }

    /**
     * pick a card and put it in battle field
     */
    @Override
    protected void pickCard() {
        Thread pickCard = new Thread() {
            @Override
            public void start() {
                while (true) {
                    DeckContainer deckContainer = new DeckContainer();
                    Card card;
                    do {
                        deckContainer.setDeck(gameDeck.getUnlockCards().getDeck());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } while (deckContainer.getDeck().size() == 0);
                    card = deckContainer.getRandomCard();
                    int[] coordinate = findBestXY(card);
                    if (drop(MouseTilePosition.TranslateTileToPixelX(coordinate[0]),
                            MouseTilePosition.TranslateTileToPixelY(coordinate[1]), card))
                        removeCard(card);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        pickCard.start();
    }

    /**
     * find side in which enemy's G.O. are more
     *
     * @return side in which enemy's G.O. are more
     */
    private PathSide findSideWithMoreEnemy() {
        int rightCnt = 0;
        int leftCnt = 0;
        for (GameObject gameObject : map.getDownSideAliveObj())
            if (gameObject.getHeadPixel().getX() < MainConfig.STD_BATTLE_FIELD_X_TILE)
                rightCnt++;
            else
                leftCnt++;

        if (rightCnt > leftCnt)
            return PathSide.RIGHT;
        else
            return PathSide.LEFT;
    }

    /**
     * find side whose tower has less HP
     *
     * @return side whose tower has less HP
     */
    private PathSide findSideWithLessTowerHp() {
        PathSide side;
        if (leftPrincessTower.getHp() > 0 && rightPrincessTower.getHp() > 0) {
            if (leftPrincessTower.getHp() < rightPrincessTower.getHp())
                side = PathSide.LEFT;
            else
                side = PathSide.RIGHT;
        } else {
            if (leftPrincessTower.getHp() > 0)
                side = PathSide.LEFT;
            else if (rightPrincessTower.getHp() > 0)
                side = PathSide.RIGHT;
            else
                side = PathSide.MIDDLE;
        }
        return side;
    }

    /**
     * find side whose enemy's tower has less hp
     *
     * @return side whose enemy's tower has less hp
     */
    private PathSide findSideWithLessTowerHpEnemy() {
        PathSide side;
        Player player = PlayerContainer.getPlayerContainer().getPlayer();
        if (player.getLeftPrincessTower().getHp() > 0 && player.getRightPrincessTower().getHp() > 0) {
            if (player.getLeftPrincessTower().getHp() < player.getRightPrincessTower().getHp())
                side = PathSide.LEFT;
            else
                side = PathSide.RIGHT;
        } else {
            if (player.getLeftPrincessTower().getHp() > 0)
                side = PathSide.LEFT;
            else if (player.getRightPrincessTower().getHp() > 0)
                side = PathSide.RIGHT;
            else
                side = PathSide.MIDDLE;
        }
        return side;
    }

    /**
     * find best xy for putting card
     * if card is range put it on enemy tower with least hp
     * else put it where two factor of
     * 1.enemy tower with least hp
     * 2.my tower with least hp
     * 3.side with more enemy
     *
     * @param card card to put
     * @return x, y
     */
    private int[] findBestXY(Card card) {
        if (card.getCardName() == CardName.RAGE ||
                card.getCardName() == CardName.ARROWS ||
                card.getCardName() == CardName.FIREBALL) {
            int[] coordinate = new int[2];
            coordinate[0] = findSideWithLessTowerHpEnemy().getX();
            coordinate[0] = 26;
            return coordinate;
        } else {
            return bestXYForSoldier();
        }
    }

    /**
     * find best xy for putting card
     * put it where two factor of
     * 1.enemy tower with least hp
     * 2.my tower with least hp
     * 3.side with more enemy
     *
     * @return x, y
     */
    private int[] bestXYForSoldier() {
        int[] coordinate = new int[2];
        PathSide towerHp = findSideWithLessTowerHp();
        PathSide enemy = findSideWithMoreEnemy();
        PathSide enemyTower = findSideWithLessTowerHpEnemy();
        Random random = new Random();

        coordinate[1] = random.nextInt(6) + 16;

        if (enemy == towerHp && towerHp == enemyTower) {
            coordinate[0] = towerHp.getX();
//            coordinate[1] = 19;
        } else if (towerHp == enemy) {
            coordinate[0] = towerHp.getX();
//            coordinate[1] = 19;
        } else if (enemy == enemyTower) {
            coordinate[0] = enemy.getX();
//            coordinate[1] = 16;
        } else if (towerHp == enemyTower) {
            coordinate[0] = towerHp.getX();
//            coordinate[1] = 19;
        } else {
            coordinate[0] = enemyTower.getX();
//            coordinate[1] = 16;
        }


        return coordinate;
    }
}
