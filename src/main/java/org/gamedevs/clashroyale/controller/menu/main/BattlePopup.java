package org.gamedevs.clashroyale.controller.menu.main;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.beans.EventHandler;

public class BattlePopup {

    @FXML
    private Button cancelBtn;

    private static Group popupMenu = null;

    @FXML
    public void cancel(Event Event){
        AnchorPane anchorPane = (AnchorPane) cancelBtn.getScene().getRoot();
        anchorPane.getChildren().remove(popupMenu);
    }

    public static void setPopupMenuGroup(Group p){
        popupMenu = p;
    }

}
