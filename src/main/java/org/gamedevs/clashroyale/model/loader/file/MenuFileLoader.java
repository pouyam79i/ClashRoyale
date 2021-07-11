package org.gamedevs.clashroyale.model.loader.file;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.CardImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.GameIconContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;
import org.gamedevs.clashroyale.model.utils.console.Console;

/**
 * This class loads all menu and builds root scene.
 * Loads all needed files and sets their properties!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0.3
 */
public class MenuFileLoader {

    /**
     * Loads fxml files for main menu!
     * @throws IOException if failed to load files.
     */
    public void load() throws IOException{
        Console.getConsole().printTracingMessage("Loading menu fxml files started!");
        // menu container
        MenuDataContainer menuData = MenuDataContainer.getMenuDataContainer();
        // loading on wait loader
        OnWaitLoader.getOnWaitLoader();
        // Loading main menu stage icon
        menuData.setGameIcon(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/icon/cr_icon.png"))
        );
        // Loading level images
        GameIconContainer.getGameIconContainer().addLevelImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/ui/xp/1.png")
        ));
        GameIconContainer.getGameIconContainer().addLevelImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/ui/xp/2.png")
        ));
        GameIconContainer.getGameIconContainer().addLevelImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/ui/xp/3.png")
        ));
        GameIconContainer.getGameIconContainer().addLevelImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/ui/xp/4.png")
        ));
        GameIconContainer.getGameIconContainer().addLevelImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/ui/xp/5.png")
        ));
        GameIconContainer.getGameIconContainer().addLevelImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/ui/xp/6.png")
        ));
        GameIconContainer.getGameIconContainer().addLevelImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/ui/xp/7.png")
        ));
        GameIconContainer.getGameIconContainer().addLevelImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/ui/xp/8.png")
        ));
        // Loading arena images
        GameIconContainer.getGameIconContainer().addArenaImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/arenas/ui_icon_training.png")
        ));
        GameIconContainer.getGameIconContainer().addArenaImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/arenas/ui_icon_barbarian.png")
        ));
        GameIconContainer.getGameIconContainer().addArenaImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/arenas/ui_icon_bone.png")
        ));
        GameIconContainer.getGameIconContainer().addArenaImage(new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/arenas/ui_icon_pekka.png")
        ));
        // Loading card images
        CardImageContainer.getCardImageContainer().addImage(CardName.ARCHERS , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/archers.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.ARROWS , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/arrows.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.BABY_DRAGON , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/baby-dragon.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.BARBARIANS , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/barbarians.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.CANNON , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/cannon.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.FIREBALL , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/fireball.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.GIANT , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/giant.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.INFERNO_TOWER , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/inferno-tower.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.MINI_PEKKA , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/mini-pekka.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.RAGE , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/rage.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.VALKYRIE , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/valkyrie.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.WIZARD , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/wizard.png")
        ));
        CardImageContainer.getCardImageContainer().addImage(CardName.EMPTY , new Image(
                MenuFileLoader.class.getResourceAsStream("../../../view/img/cards/null.png")
        ));
        // Loading root pane
        AnchorPane mainRoot = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/menu/main_root.fxml"
        ));
        mainRoot.setLayoutX(0);
        mainRoot.setLayoutY(0);
        menuData.setRootPane(mainRoot);
        // Loading signup menu
        AnchorPane signupMenu = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/menu/sign_up.fxml"
        ));
        signupMenu.setLayoutX(0);
        signupMenu.setLayoutY(0);
        menuData.setSignupMenu(signupMenu);
        // Loading battle menu requirements.
        AnchorPane battleMenu = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/menu/main_battle.fxml"
        ));
        battleMenu.setLayoutX(0);
        battleMenu.setLayoutY(0);
        menuData.setBattleMenu(battleMenu);
        // Building deck menu requirements
        AnchorPane deckMenu = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/menu/deck_scene_menu.fxml"
        ));
        deckMenu.setLayoutX(-405);
        deckMenu.setLayoutY(0);
        menuData.setDeckMenu(deckMenu);
        // Loading slider bar
        AnchorPane sliderBar = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/menu/slider_bar.fxml"
        ));
        sliderBar.setLayoutX(0);
        sliderBar.setLayoutY(620);
        menuData.setSliderBar(sliderBar);
        // Loading cover image
        ImageView backgroundCoverForBattlePopup = new ImageView(new Image(
                getClass().getResource("./../../../view/img/menu/menu_background_cover_v2.png").toExternalForm()));
        ImageView backgroundCoverForProfilePopup = new ImageView(new Image(
                getClass().getResource("./../../../view/img/menu/menu_background_cover_v2.png").toExternalForm()));
        // Loading battle popup
        Group battlePopupGroup = new Group();
        AnchorPane battlePopupMenu = FXMLLoader.load(getClass().getResource(
                "./../../../view/fxml/menu/battle_menu_popup.fxml"
        ));
        battlePopupGroup.getChildren().add(backgroundCoverForBattlePopup);
        battlePopupMenu.setTranslateX(48.5);
        battlePopupMenu.setTranslateY(202.5);
        battlePopupGroup.getChildren().add(battlePopupMenu);
        menuData.setBattlePopupMenu(battlePopupGroup);
        // Loading profile popup
        Group profilePopupGroup = new Group();
        AnchorPane profilePopupMenu = FXMLLoader.load(getClass().getResource(
                "./../../../view/fxml/menu/profile_view_popup.fxml"
        ));
        profilePopupGroup.getChildren().add(backgroundCoverForProfilePopup);
        profilePopupMenu.setTranslateX(48.5);
        profilePopupMenu.setTranslateY(202.5);
        profilePopupGroup.getChildren().add(profilePopupMenu);
        menuData.setProfilePopupMenu(profilePopupGroup);
        // Building main menu group root
        Group mainMenuRootGroup = new Group();
        mainMenuRootGroup.getChildren().add(battleMenu);
        mainMenuRootGroup.getChildren().add(deckMenu);
        mainMenuRootGroup.getChildren().add(sliderBar);
        menuData.setMainMenuRootGroup(mainMenuRootGroup);
        // Building root scene
        Scene rootScene = new Scene(mainRoot);
        MenuDataContainer.getMenuDataContainer().setRootScene(rootScene);
        // Displaying message
        Console.getConsole().printTracingMessage("Loading menu fxml files is done!");
    }

}
