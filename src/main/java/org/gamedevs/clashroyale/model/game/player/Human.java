package org.gamedevs.clashroyale.model.game.player;

import org.gamedevs.clashroyale.model.game.battle.field.Map;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;

/**
 * This class contains human player information.
 * It enables player to have interaction with game.
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class Human extends Player {

    /**
     * Gets main game tools to be able to play.
     * @param map of game
     * @param playerSide side of player (TOP/DOWN)
     * @param elixir counter of elixir
     */
    protected Human(Map map, Side playerSide, Elixir elixir) {
        super(map, playerSide, elixir);
    }

    /**
     * Updates human information
     */
    @Override
    public void run() {

    }

}
