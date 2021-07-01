package org.gamedevs.clashroyale.controller.menu.main;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.utils.console.Console;

public class MainBattle {

    @FXML
    private Button battleBtn;



    private final ImageView backgroundCover = new ImageView(new Image(
            getClass().getResource("./../../../view/img/menu/menu_background_cover_v2.png").toExternalForm()));

    @FXML
    private void bringBattlePopup(Event event){
        try{
            Group popupRoot = new Group();
            BattlePopup.setPopupMenuGroup(popupRoot);
            AnchorPane popupMenu = FXMLLoader.load(getClass().getResource(
                    "./../../../view/fxml/menu/battle_menu_popup.fxml"
            ));
            popupRoot.getChildren().add(backgroundCover);
            popupMenu.setTranslateX(48.5);
            popupMenu.setTranslateY(202.5);
            popupRoot.getChildren().add(popupMenu);
//            Scene scene = new Scene(popupMenu);
//            Stage stage = new Stage();
            Scene mainBattleMenuScene = battleBtn.getScene();
            AnchorPane mainBattleMenu = (AnchorPane) mainBattleMenuScene.getRoot();
            mainBattleMenu.getChildren().add(popupRoot);

//            stage.setScene(scene);
//            stage.show();
        }catch (Exception e){
            Console.getConsole().printTracingMessage("Failed to pop up battle menu");
        }
    }

}
