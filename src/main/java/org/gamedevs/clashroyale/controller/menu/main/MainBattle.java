package org.gamedevs.clashroyale.controller.menu.main;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;

/**
 * Main battle menu controller
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MainBattle {

    /**
     * Battle button,
     * used to bring battle popup
     */
    @FXML
    private Button battleBtn;
    /**
     * Profile button,
     * using to bring profile popup view
     */
    @FXML
    private Button profileBtn;

    /**
     * Brings battle popup
     */
    @FXML
    private void bringBattlePopup(){
        Scene mainBattleMenuScene = battleBtn.getScene();
        AnchorPane mainBattleMenu = (AnchorPane) mainBattleMenuScene.getRoot();
        mainBattleMenu.getChildren().add(MenuDataContainer.getMenuDataContainer().getBattlePopupMenu());
    }

    /**
     * Brings profile view popup
     */
    @FXML
    private void bringProfilePopup(){
        Scene mainBattleMenuScene = profileBtn.getScene();
        AnchorPane mainBattleMenu = (AnchorPane) mainBattleMenuScene.getRoot();
        mainBattleMenu.getChildren().add(MenuDataContainer.getMenuDataContainer().getProfilePopupMenu());
    }

}
