package org.gamedevs.clashroyale.model.game.battle.engine.manager;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.account.Account;
import org.gamedevs.clashroyale.model.game.battle.engine.GameType;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.tools.CardGenerator;
import org.gamedevs.clashroyale.model.game.battle.tools.Clock;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.battle.tools.GameResult;
import org.gamedevs.clashroyale.model.game.player.Human;
import org.gamedevs.clashroyale.model.game.player.Player;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.game.player.bot.EasyBot;
import org.gamedevs.clashroyale.model.game.player.bot.HardBot;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * Game manager is the engine of game builder!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class GameManager extends Runnable {

    // Game main tools:
    /**
     * Map of game
     */
    private final Map map;
    /**
     * Clock of game (reverse timer)
     */
    private final Clock clock;
    // Players:
    /**
     * Top side player
     */
    private Player topPlayer;
    /**
     * Down side player
     */
    private Player downPlayer;
    // Result:
    /**
     * Top side player game result
     */
    private GameResult gameResult;


    /**
     * Constructor of game manager,
     * builds main game requirements
     */
    public GameManager() {
        threadName = "GameManager";
        map = new Map(MainConfig.STD_BATTLE_FIELD_X_TILE, MainConfig.STD_BATTLE_FIELD_Y_TILE);
        clock = new Clock();
        gameResult = null;
    }

    /**
     * Build game for offline single player mode
     */
    public void buildOfflineSingleGame(Account account, boolean hardBot){
        // human player elixir
        Elixir elixirTopPlayer = new Elixir(clock);
        // bot elixir
        Elixir elixirDownPlayer = new Elixir(clock);
        // This is used for human player
        CardGenerator cardGeneratorTopPlayer = new CardGenerator(account.getDeckContainer(), elixirTopPlayer);
        // This is used for bot
        CardGenerator cardGeneratorDownPlayer = new CardGenerator(account.getDeckContainer(), elixirDownPlayer);
        // Setting human player requirements
        downPlayer = new Human(map, Side.DOWN, elixirDownPlayer, cardGeneratorDownPlayer, account.getLevel());
        // Setting bot player requirements
        if(hardBot)
            topPlayer = new HardBot(map, Side.TOP, elixirTopPlayer, cardGeneratorTopPlayer, account.getLevel());
        else
            topPlayer = new EasyBot(map, Side.TOP, elixirTopPlayer, cardGeneratorTopPlayer, account.getLevel());
        gameResult = new GameResult(GameType.SINGLE_OFFLINE, "Bot", account.getUsername());
    }

    /**
     * Build game for offline double player mode
     */
    public void buildOfflineDoublePlayer(){

    }

    /**
     * runs the game
     */
    @Override
    public void run() {
        clock.start();
        topPlayer.start();
        downPlayer.start();
        // When we have still tile
        while (!clock.isEndOfTime()){
            // When we have a winner!
            if(gameResult.checkWinner()){
                // TODO: complete wining process
                return;
            }
        }
        // TODO: complete time process
    }

    // Getters
    public Map getMap() {
        return map;
    }
    public Clock getClock() {
        return clock;
    }
    public Player getTopPlayer() {
        return topPlayer;
    }
    public Player getDownPlayer() {
        return downPlayer;
    }
    public GameResult getGameResult() {
        return gameResult;
    }

}
