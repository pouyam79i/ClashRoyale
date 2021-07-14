package org.gamedevs.clashroyale.model.game.player;

import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.game.battle.field.Map;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * This class contains player structure.
 * What ever a player need to interact with the game.
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
     * Gets main game tools to be able to play.
     * @param map of game
     * @param playerSide side of player (TOP/DOWN)
     * @param elixir counter of elixir
     */
    protected Player(Map map, Side playerSide, Elixir elixir){
        this.playerSide = playerSide;
        this.map = map;
        this.elixir = elixir;
    }

    /**
     * Translate x and y according to player side!
     * Tells the map engine to drop player card on that!
     * Also no need to translate x and y for bot,
     * Because it knows all the map by reading it!
     */
    public void drop(int x, int y, Card card){

    }

}
