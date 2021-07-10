package org.gamedevs.clashroyale.model.container.scene;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

/**
 * This class contains property of main menu.
 * It can be easy accessed in other class when needed.
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0.1
 */
public class MenuSceneContainer {

    /**
     * Only instance of this class,
     * singleton pattern.
     */
    private static MenuSceneContainer Instance = null;

    /**
     * Root scene of main menu container.
     */
    private Scene rootScene;
    /**
     * Root anchor pane container.
     */
    private AnchorPane rootPane;
    /**
     * signup menu anchor pane container
     */
    private AnchorPane signupMenu;
    /**
     * Battle menu anchor pane container.
     */
    private AnchorPane battleMenu;
    /**
     * Deck menu anchor pane container.
     */
    private AnchorPane deckMenu;
    /**
     * Slide bar anchor pane container.
     */
    private AnchorPane sliderBar;
    /**
     * Battle popup group container.
     */
    private Group battlePopupMenu;
    /**
     * Profile pop group container.
     */
    private Group profilePopupMenu;
    /**
     * Icon of game at main menu;
     */
    private Image gameIcon;

    /**
     * Sets default values.
     */
    private MenuSceneContainer(){
        rootScene = null;
        battleMenu = null;
        deckMenu = null;
        sliderBar = null;
        battlePopupMenu = null;
        profilePopupMenu = null;
        gameIcon = null;
    }

    // Getters
    public Scene getRootScene() {
        return rootScene;
    }
    public AnchorPane getBattleMenu() {
        return battleMenu;
    }
    public AnchorPane getDeckMenu() {
        return deckMenu;
    }
    public AnchorPane getSliderBar() {
        return sliderBar;
    }
    public AnchorPane getRootPane() {
        return rootPane;
    }
    public Group getBattlePopupMenu() {
        return battlePopupMenu;
    }
    public Group getProfilePopupMenu() {
        return profilePopupMenu;
    }
    public Image getGameIcon() {
        return gameIcon;
    }
    public AnchorPane getSignupMenu() {
        return signupMenu;
    }

    // Setter
    public void setRootScene(Scene rootScene) {
        if(this.rootScene != null)
            return;
        this.rootScene = rootScene;
    }
    public void setBattleMenu(AnchorPane battleMenu) {
        if(this.battleMenu != null)
            return;
        this.battleMenu = battleMenu;
    }
    public void setDeckMenu(AnchorPane deckMenu) {
        if(this.deckMenu != null)
            return;
        this.deckMenu = deckMenu;
    }
    public void setSliderBar(AnchorPane sliderBar) {
        if(this.sliderBar != null)
            return;
        this.sliderBar = sliderBar;
    }
    public void setRootPane(AnchorPane rootPane) {
        if(this.rootPane != null)
            return;
        this.rootPane = rootPane;
    }
    public void setBattlePopupMenu(Group battlePopupMenu) {
        if(this.battlePopupMenu != null)
            return;
        this.battlePopupMenu = battlePopupMenu;
    }
    public void setProfilePopupMenu(Group profilePopupMenu) {
        if(this.profilePopupMenu != null)
            return;
        this.profilePopupMenu = profilePopupMenu;
    }
    public void setGameIcon(Image gameIcon) {
        if(this.gameIcon != null)
            return;
        this.gameIcon = gameIcon;
    }
    public void setSignupMenu(AnchorPane signupMenu) {
        this.signupMenu = signupMenu;
    }

    /**
     * @return MenuSceneContainer (if not build before. Instantiates one)
     */
    public static MenuSceneContainer getMenuData(){
        if(Instance == null)
            Instance = new MenuSceneContainer();
        return Instance;
    }

}
