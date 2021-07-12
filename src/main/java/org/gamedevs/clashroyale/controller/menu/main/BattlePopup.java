package org.gamedevs.clashroyale.controller.menu.main;

import animatefx.animation.BounceIn;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;

/**
 * Battle popup handler
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.1
 */
public class BattlePopup {

    /**
     * With this button we return to main menu,
     */
    @FXML
    private Button cancelBtn;

    /**
     * Returns back to battle menu,
     * also removes the popup menu from root scene.
     */
    @FXML
    public void cancel(){
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
