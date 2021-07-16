package org.gamedevs.clashroyale.model.launcher;

import javafx.application.Platform;
import org.gamedevs.clashroyale.controller.battle.effects.GameTimer;
import org.gamedevs.clashroyale.controller.battle.main.CardDeckGame;
import org.gamedevs.clashroyale.model.container.gamedata.PlayerContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.BattleFieldContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.game.battle.engine.GameManager;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;
import org.gamedevs.clashroyale.model.media.MusicPlayer;
import org.gamedevs.clashroyale.model.media.Musics;
import org.gamedevs.clashroyale.model.updater.battle.ViewManager;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * This class launches the offline battle sequence,
 * which brings the game until battle is started!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class OfflineBattleLauncher extends Runnable {

    /**
     * if true launches the game in double mode
     * else it launches the game in single mode!
     */
    private final boolean isDoubleBattle;
    /**
     * Constructor of OfflineBattleLauncher
     * Sets requirements!
     */
    public OfflineBattleLauncher(boolean isDoubleBattle){
        this.isDoubleBattle = isDoubleBattle;
        threadName = "OfflineBattleLauncher";
    }

    /**
     * This thread brings the game until battle is started!
     */
    @Override
    public void run() {
        // Removing background panes which are used in main menu
        Platform.runLater(() -> {
            OnWaitLoader.getOnWaitLoader().displayBattleLoadingScreen(
                    MenuDataContainer.getMenuDataContainer().getRootPane()
            );
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().remove(
                    MenuDataContainer.getMenuDataContainer().getMainMenuRootGroup()
            );
        });
        // Initializing game engines
        GameManager gameManager = new GameManager();
        gameManager.getMap().setViewManager(new ViewManager(Side.DOWN));
        gameManager.buildOfflineSingleGame(UserAccountContainer.getUserAccountContainer().getAccount(), false);
        // Setting player to player container
        PlayerContainer.getPlayerContainer().setPlayer(gameManager.getTopPlayer());
        // Binding game timer to view
        GameTimer.getGameTimer().bindTimerLabel(gameManager.getClock().clockStringProperty());
        // Initializing card deck of player
        new CardDeckGame().init();
        // Playing battle music
        MusicPlayer.getMusicPlayer().play(Musics.BATTLE_SECOND_PHASE);
        // Adding battle field group to root pane!
        Platform.runLater(() -> {
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().add(
                    BattleFieldContainer.getBattleFieldContainer().getMainBattleGroup()
            );
            OnWaitLoader.getOnWaitLoader().disappear();
        });
        // Starting the game!
        gameManager.start();
        // TODO: replace this code with end of game checker()!
        try {
            Thread.sleep(30000); //TODO: Just testing :)
        } catch (InterruptedException ignored) {}
        // Killing launcher
        this.shutdown();
    }

}
