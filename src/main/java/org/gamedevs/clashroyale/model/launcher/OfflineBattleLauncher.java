package org.gamedevs.clashroyale.model.launcher;

import javafx.application.Platform;
import org.gamedevs.clashroyale.controller.battle.effects.GameTimer;
import org.gamedevs.clashroyale.model.container.scene.BattleFieldContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.game.battle.engine.GameManager;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;
import org.gamedevs.clashroyale.model.media.MusicPlayer;
import org.gamedevs.clashroyale.model.media.Musics;
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
        GameManager gameManager = new GameManager();
        GameTimer.getGameTimer().bindTimerLabel(gameManager.getClock().clockStringProperty());
        Platform.runLater(() -> {
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().add(
                    BattleFieldContainer.getBattleFieldContainer().getMainBattleGroup()
            );
        });
        gameManager.start();
        MusicPlayer.getMusicPlayer().play(Musics.BATTLE_SECOND_PHASE);
        Platform.runLater(() -> {
            OnWaitLoader.getOnWaitLoader().disappear();
        });
        try {
            Thread.sleep(30000); //TODO: Just testing :)
        } catch (InterruptedException ignored) {}
        // Killing launcher
        this.shutdown();
    }

}
