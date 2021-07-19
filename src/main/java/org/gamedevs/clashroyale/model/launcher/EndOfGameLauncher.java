package org.gamedevs.clashroyale.model.launcher;

import javafx.application.Platform;
import org.gamedevs.clashroyale.controller.battle.effects.GameResultController;
import org.gamedevs.clashroyale.controller.menu.main.MainBattle;
import org.gamedevs.clashroyale.model.container.gamedata.PlayerContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.BattleFieldContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.game.battle.tools.GameResult;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * This launcher is used to end the game and gets rewards for player
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class EndOfGameLauncher extends Runnable {

    /**
     * game result
     */
    private final GameResult gameResult;

    /**
     * setting requirements of end of game launcher
     * @param gameResult will be displayed
     */
    public EndOfGameLauncher(GameResult gameResult){
        threadName = "EndOfGameLauncher";
        this.gameResult = gameResult;
    }

    /**
     * runs the launcher
     */
    public void run(){
        // Displaying game result view
        GameResultController.getResultController().init();
        Platform.runLater(() -> {
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().add(
                    BattleFieldContainer.getBattleFieldContainer().getGameResult()
            );
            GameResultController.getResultController().display(gameResult,
                    PlayerContainer.getPlayerContainer().getPlayer().getPlayerSide());
        });
        // Wait to get back to main menu
        while (!GameResultController.getResultController().isChecked()){
            try {
                Thread.sleep(300);
            } catch (InterruptedException ignored) {}
        }
        // Getting back to main menu
        boolean winner = PlayerContainer.getPlayerContainer().getPlayer().getPlayerSide() == gameResult.getWinnerSide();
        // Adding player xp
        if(winner)
            UserAccountContainer.getUserAccountContainer().getAccount().increaseXP(300);
        else
            UserAccountContainer.getUserAccountContainer().getAccount().increaseXP(70);
        // removing battle field
        Platform.runLater(() -> {
            OnWaitLoader.getOnWaitLoader().display(
                    MenuDataContainer.getMenuDataContainer().getRootPane()
            );
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().remove(
                    BattleFieldContainer.getBattleFieldContainer().getGameResult()
            );
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().remove(
                    BattleFieldContainer.getBattleFieldContainer().getMainBattleGroup()
            );
        });
        try {
            Thread.sleep(1000);     // TODO: this is a test - remove it later
        } catch (InterruptedException ignored) {}
        // returning to main menu
        Platform.runLater(() -> {
            MenuDataContainer.getMenuDataContainer().getRootPane().getChildren().add(
                    MenuDataContainer.getMenuDataContainer().getMainMenuRootGroup()
            );
            OnWaitLoader.getOnWaitLoader().disappear();
            if(winner)
                MainBattle.getMainBattle().updateXP(300);
            else
                MainBattle.getMainBattle().updateXP(70);
        });
        // resetting player container!
        PlayerContainer.getPlayerContainer().setPlayer(null);
        this.shutdown();
    }

}
