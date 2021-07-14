package org.gamedevs.clashroyale.model.loader.view;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * On Wait loader builds and add a loading
 * group to the scene.
 * @author Pouya Mohammadi CE@AUT - UniID: 9829039
 * @version 1.2
 */
public class OnWaitLoader {

    /**
     * Only instance of this class!
     */
    private static OnWaitLoader onWaitLoader = null;

    /**
     * on loading state will be true,
     * when loading group is running on the screen!
     */
    private boolean loadingState;
    /**
     * Loading screen pane
     */
    private AnchorPane transparentLoadingScreen;
    /**
     * Battle loading screen
     */
    private AnchorPane battleLoadingScreen;
    /**
     * Loading circle of transparent loader.
     */
    private ImageView transparentLoadingCircle;
    /**
     * Loading circle of battle loader.
     */
    private ImageView battleLoadingCircle;
    /**
     * root pane
     */
    private AnchorPane rootPane;
    /**
     * group of transparent loading screen!
     */
    private Group transparentLoading;
    /**
     * group of battle loading screen!
     */
    private Group battleLoading;
    /**
     * Loading circle animation maker
     */
    private AnimationTimer animationTimer;
    /**
     * if current loading screen is battle loader!
     */
    private boolean isBattleLoading;

    /**
     * Constructor of OnWaitLoader,
     * sets the requirements loading screen!
     */
    private OnWaitLoader() {
        isBattleLoading = false;
        transparentLoading = null;
        battleLoading = null;
        loadingState = false;
    }

    /**
     * loads the requirements loading screen!
     * @throws IOException if failed to load files.
     */
    public void load() throws IOException {
        transparentLoadingScreen = FXMLLoader.load(getClass().getResource(
                "./../../../view/fxml/loading/transparent_loading_cover.fxml"
        ));
        battleLoadingScreen = FXMLLoader.load(getClass().getResource(
                "./../../../view/fxml/loading/battle_loading_cover.fxml"
        ));
        // loading circle
        Image loadingCircle = new Image(
                getClass().getResource("./../../../view/img/loading/loading_circle.png").toExternalForm());
        // setting up transparent loader
        transparentLoadingCircle = new ImageView(loadingCircle);
        transparentLoadingCircle.setPreserveRatio(true);
        transparentLoadingCircle.setFitHeight(60);
        transparentLoadingCircle.setX(165);
        transparentLoadingCircle.setY(310);
        transparentLoading = new Group();
        transparentLoading.getChildren().add(transparentLoadingScreen);
        transparentLoading.getChildren().add(transparentLoadingCircle);
        // setting up battle loading screen
        battleLoadingCircle = new ImageView(loadingCircle);
        battleLoadingCircle.setPreserveRatio(true);
        battleLoadingCircle.setFitHeight(60);
        battleLoadingCircle.setX(300);
        battleLoadingCircle.setY(600);
        battleLoading = new Group();
        battleLoading.getChildren().add(battleLoadingScreen);
        battleLoading.getChildren().add(battleLoadingCircle);
        // Setting up animation
        animationTimer = animation();
    }

    /**
     * Displaying transparent loading screen by passing current scene.
     * This method displays transparent loader
     * @param currentScene current scene
     */
    public void display(Scene currentScene){
        if(loadingState)
            return;
        if(currentScene == null)
            return;
        if(transparentLoading == null)
            return;
        this.rootPane = (AnchorPane) currentScene.getRoot();
        if(rootPane == null)
            return;
        animationTimer.start();
        rootPane.getChildren().add(transparentLoading);
        loadingState = true;
        isBattleLoading = false;
    }

    /**
     * Displaying transparent loading screen by passing root pane
     * @param rootPane root pane (STD pane is anchor pane)
     */
    public void display(AnchorPane rootPane){
        if(loadingState)
            return;
        if(rootPane == null)
            return;
        if(transparentLoading == null)
            return;
        this.rootPane = rootPane;
        animationTimer.start();
        rootPane.getChildren().add(transparentLoading);
        loadingState = true;
        isBattleLoading = false;
    }

    /**
     * Displaying battle loading screen by passing current scene.
     * This method displays battle loader
     * @param currentScene current scene
     */
    public void displayBattleLoadingScreen(Scene currentScene){
        if(loadingState)
            return;
        if(currentScene == null)
            return;
        if(battleLoading == null)
            return;
        this.rootPane = (AnchorPane) currentScene.getRoot();
        if(rootPane == null)
            return;
        animationTimer.start();
        rootPane.getChildren().add(battleLoading);
        loadingState = true;
        isBattleLoading = true;
    }

    /**
     * Displaying battle loading screen by passing root pane
     * @param rootPane root pane (STD pane is anchor pane)
     */
    public void displayBattleLoadingScreen(AnchorPane rootPane){
        if(loadingState)
            return;
        if(rootPane == null)
            return;
        if(battleLoading == null)
            return;
        this.rootPane = rootPane;
        animationTimer.start();
        rootPane.getChildren().add(battleLoading);
        loadingState = true;
        isBattleLoading = true;
    }

    /**
     * Removes and disappear the loading screen node!
     */
    public void disappear(){
        if(!loadingState)
            return;
        if(isBattleLoading)
            rootPane.getChildren().remove(battleLoading);
        else
            rootPane.getChildren().remove(transparentLoading);

        animationTimer.stop();
        rootPane = null;
        loadingState = false;
    }

    /**
     * This animation timer is used for loading circle
     * so it will rotate!
     * @return an animator
     */
    private AnimationTimer animation() {
        return new AnimationTimer() {
            @Override
            public void handle(long now) {
                transparentLoadingCircle.setRotate(transparentLoadingCircle.getRotate() + 7);
                battleLoadingCircle.setRotate(battleLoadingCircle.getRotate() + 7);
            }
        };
    }

    /**
     * @return only instance onWaitLoader!
     * If it is null it builds one!
     */
    public static OnWaitLoader getOnWaitLoader() {
        if(onWaitLoader == null)
            onWaitLoader = new OnWaitLoader();
        return onWaitLoader;
    }

}
