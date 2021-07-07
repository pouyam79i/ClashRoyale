package org.gamedevs.clashroyale.model.container.scene;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * This class contains property of main menu.
 * It can be easy accessed in other class when needed.
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MainMenuSceneContainer {

    /**
     * Only instance of this class,
     * singleton pattern.
     */
    private static MainMenuSceneContainer Instance = null;

    /**
     * Root scene of main menu container.
     */
    private Scene rootScene;
    /**
     * Root anchor pane container.
     */
    private AnchorPane rootPane;
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
     * Sets default values.
     */
    private MainMenuSceneContainer(){
        rootScene = null;
        battleMenu = null;
        deckMenu = null;
        sliderBar = null;
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

    // Setter
    public void setRootScene(Scene rootScene) {
        this.rootScene = rootScene;
    }
    public void setBattleMenu(AnchorPane battleMenu) {
        this.battleMenu = battleMenu;
    }
    public void setDeckMenu(AnchorPane deckMenu) {
        this.deckMenu = deckMenu;
    }
    public void setSliderBar(AnchorPane sliderBar) {
        this.sliderBar = sliderBar;
    }
    public void setRootPane(AnchorPane rootPane) {
        this.rootPane = rootPane;
    }

    /**
     * @return MainMenuSceneContainer (if not build before. Instantiates one)
     */
    public static MainMenuSceneContainer getMenuData(){
        if(Instance == null)
            Instance = new MainMenuSceneContainer();
        return Instance;
    }

}
