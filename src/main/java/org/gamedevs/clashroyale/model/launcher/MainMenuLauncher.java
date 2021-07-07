package org.gamedevs.clashroyale.model.launcher;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import org.gamedevs.clashroyale.model.container.scene.MainMenuSceneContainer;
import org.gamedevs.clashroyale.model.utils.console.Console;

/**
 * This class launches main menu.
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MainMenuLauncher{

    /**
     * Loads fxml files for main menu!
     * @throws IOException if failed to load files.
     */
    public void launch() throws IOException{
        // menu container
        MainMenuSceneContainer menuData = MainMenuSceneContainer.getMenuData();
        // Loading root pane
        AnchorPane mainRoot = FXMLLoader.load(getClass().getResource(
                "../../view/fxml/menu/main_root.fxml"
        ));
        mainRoot.setLayoutX(0);
        mainRoot.setLayoutY(0);
        menuData.setRootPane(mainRoot);
        // Loading battle menu requirements.
        AnchorPane battleMenu = FXMLLoader.load(getClass().getResource(
                "../../view/fxml/menu/main_battle.fxml"
        ));
        battleMenu.setLayoutX(0);
        battleMenu.setLayoutY(0);
        menuData.setBattleMenu(battleMenu);
        // Building deck menu requirements
        AnchorPane deckMenu = FXMLLoader.load(getClass().getResource(
                "../../view/fxml/menu/deck_scene_menu.fxml"
        ));
        deckMenu.setLayoutX(-405);
        deckMenu.setLayoutY(0);
        menuData.setDeckMenu(deckMenu);
        // Loading slider bar
        AnchorPane sliderBar = FXMLLoader.load(getClass().getResource(
                "../../view/fxml/menu/slider_bar.fxml"
        ));
        sliderBar.setLayoutX(0);
        sliderBar.setLayoutY(620);
        menuData.setSliderBar(sliderBar);
        // Building a root scene
        mainRoot.getChildren().add(battleMenu);
        mainRoot.getChildren().add(deckMenu);
        mainRoot.getChildren().add(sliderBar);
        Scene rootScene = new Scene(mainRoot);
        MainMenuSceneContainer.getMenuData().setRootScene(rootScene);
        // Displaying message
        Console.getConsole().printTracingMessage("Loading menu fxml files is done!");
    }

}
