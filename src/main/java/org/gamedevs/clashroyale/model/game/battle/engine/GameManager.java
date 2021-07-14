package org.gamedevs.clashroyale.model.game.battle.engine;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.game.battle.field.Map;
import org.gamedevs.clashroyale.model.game.battle.tools.Clock;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.player.Player;

/**
 * Game manager is the engine of game builder!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class GameManager {

    // Game main tools:
    /**
     * Map of game
     */
    private final Map map;
    /**
     * Clock of game (reverse timer)
     */
//    private final Clock clock;
    // Players:
    /**
     * Top side player
     */
    private Player topPlayer;
    /**
     * Down side player
     */
    private Player downPlayer;
    /**
     * top side player elixir counter
     */
    private Elixir elixirTopPlayer;
    /**
     * down side player elixir counter
     */
    private Elixir elixirDownPlayer;

    /**
     * Constructor of game manager,
     * builds main game requirements
     */
    public GameManager() {
        map = new Map(MainConfig.STD_BATTLE_FIELD_WIDTH, MainConfig.STD_BATTLE_FIELD_HEIGHT);
//        clock = new Clock();
    }

    /**
     * Build game for offline single player mode
     */
    public void buildOfflineSingleGame(){

    }

    /**
     * Build game for offline double player mode
     */
    public void buildOfflineDoublePlayer(){

    }

    /**
     * Runs the game
     */
    public void start(){

    }

}
