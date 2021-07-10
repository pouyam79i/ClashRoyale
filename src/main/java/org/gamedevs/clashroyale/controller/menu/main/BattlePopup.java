package org.gamedevs.clashroyale.controller.menu.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import org.gamedevs.clashroyale.model.container.scene.MenuSceneContainer;

/**
 * Battle popup handler
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
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
        AnchorPane battleMainRoot = (AnchorPane) cancelBtn.getScene().getRoot();
        battleMainRoot.getChildren().remove(MenuSceneContainer.getMenuData().getBattlePopupMenu());
    }

}
