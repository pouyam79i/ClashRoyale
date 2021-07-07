package org.gamedevs.clashroyale.controller.menu.main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.container.scene.MainMenuSceneContainer;

/**
 * Profile view popup handler
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class ProfilePopup {

    /**
     * With this button we return to main menu.
     */
    @FXML
    private Button backBtn;

    /**
     * Returns back to battle menu,
     * also removes the popup menu from root scene.
     */
    @FXML
    public void backToMain(){
        AnchorPane battleMainRoot = (AnchorPane) backBtn.getScene().getRoot();
        battleMainRoot.getChildren().remove(MainMenuSceneContainer.getMenuData().getProfilePopupMenu());
    }

}
