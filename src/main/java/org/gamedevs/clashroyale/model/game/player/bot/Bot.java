package org.gamedevs.clashroyale.model.game.player.bot;

import org.gamedevs.clashroyale.model.game.battle.field.Map;
import org.gamedevs.clashroyale.model.game.battle.tools.CardGenerator;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.player.Player;
import org.gamedevs.clashroyale.model.game.player.Side;

/**
 * This class contains main structure of bot!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public abstract class Bot extends Player {

    /**
     * Gets main game tools to be able to play
     * @param map of game
     * @param playerSide side of player (TOP/DOWN)
     * @param elixir counter of elixir
     * @param cardGenerator player card generator
     * @param level level of player
     */
    protected Bot(Map map, Side playerSide, Elixir elixir, CardGenerator cardGenerator, int level) {
        super(map, playerSide, elixir, cardGenerator, level);
    }

    /**
     * Runs algorithm of bot!
     * Also updates bot information.
     */
    @Override
    public void run() {
        elixir.start();
        algorithm();
    }

    /**
     * Applies the algorithm of bot!
     * Decides what to do for next!
     */
    protected abstract void algorithm();

}
