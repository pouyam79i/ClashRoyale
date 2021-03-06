package org.gamedevs.clashroyale.controller.menu.main;

import animatefx.animation.BounceIn;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.launcher.OfflineBattleLauncher;

/**
 * Battle popup handler
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.1
 */
public class BattlePopup {

    // fx:id
    @FXML
    private Button singleOnlineBtn;
    @FXML
    private Button doubleOnlineBtn;
    @FXML
    private Button easyOfflineBtn;
    @FXML
    private Button hardOfflineBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Bringing battle in single online mode (official match)
     */
    @FXML
    private void bringSingleOnlineBattle(){
        new BounceIn(singleOnlineBtn).play();
    }

    /**
     * Bringing battle in double online mode
     */
    @FXML
    private void bringDoubleOnlineBattle(){
        new BounceIn(doubleOnlineBtn).play();
    }

    /**
     * Bringing battle in single offline mode (training with easy mode)
     */
    @FXML
    private void bringEasyOfflineBtn(){
        Thread thread = (new Thread(() -> {
            new BounceIn(easyOfflineBtn).play();
            try {
                Thread.sleep(MainConfig.STD_BUTTON_ANIMATION_LATENCY);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                AnchorPane battleMainRoot = (AnchorPane) easyOfflineBtn.getScene().getRoot();
                battleMainRoot.getChildren().remove(MenuDataContainer.getMenuDataContainer().getBattlePopupMenu());
            });
            new OfflineBattleLauncher(false).start();
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Bringing battle in double offline mode
     */
    @FXML
    private void bringHardOfflineBtn(){
        Thread thread = (new Thread(() -> {
            new BounceIn(hardOfflineBtn).play();
            try {
                Thread.sleep(MainConfig.STD_BUTTON_ANIMATION_LATENCY);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                AnchorPane battleMainRoot = (AnchorPane) hardOfflineBtn.getScene().getRoot();
                battleMainRoot.getChildren().remove(MenuDataContainer.getMenuDataContainer().getBattlePopupMenu());
            });
            new OfflineBattleLauncher(true).start();
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Returns back to battle menu,
     * also removes the popup menu from root scene.
     */
    @FXML
    private void cancel(){
        Thread thread = (new Thread(() -> {
            new BounceIn(cancelBtn).play();
            try {
                Thread.sleep(MainConfig.STD_BUTTON_ANIMATION_LATENCY);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                AnchorPane battleMainRoot = (AnchorPane) cancelBtn.getScene().getRoot();
                battleMainRoot.getChildren().remove(MenuDataContainer.getMenuDataContainer().getBattlePopupMenu());
            });
        }));
        thread.setDaemon(true);
        thread.start();
    }

}
