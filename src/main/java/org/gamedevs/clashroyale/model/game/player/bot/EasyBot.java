package org.gamedevs.clashroyale.model.game.player.bot;

import org.gamedevs.clashroyale.model.game.battle.field.Map;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.player.Side;

/**
 *
 */
public class EasyBot extends Bot{

    /**
     * Gets main game tools to be able to play
     * @param map of game
     * @param playerSide side of player (TOP/DOWN)
     * @param elixir counter of elixir
     */
    protected EasyBot(Map map, Side playerSide, Elixir elixir) {
        super(map, playerSide, elixir);
    }

    /**
     * Applies the algorithm of bot! (Easy bot)
     * Decides what to do for next!
     */
    @Override
    protected void algorithm() {

    }

}
