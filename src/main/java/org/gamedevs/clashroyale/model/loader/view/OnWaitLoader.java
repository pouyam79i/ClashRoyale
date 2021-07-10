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
 * @version 1.1
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
    private final AnchorPane loadingScreen;
    /**
     * Loading circle of loader.
     */
    private final ImageView loadingCircle;
    /**
     * root pane
     */
    private AnchorPane rootPane;
    /**
     * group of loading screen node!
     */
    private Group currentLoadingScreen;
    /**
     * Loading circle animation maker
     */
    private AnimationTimer animationTimer;

    /**
     * Constructor of OnWaitLoader,
     * sets the requirements loading screen!
     * @throws IOException if failed to load files.
     */
    private OnWaitLoader() throws IOException {
        loadingScreen = FXMLLoader.load(getClass().getResource(
                "./../../../view/fxml/loading/loading_cover.fxml"
        ));
        loadingCircle = new ImageView(new Image(
                getClass().getResource("./../../../view/img/loading/loading_circle.png").toExternalForm()));
        currentLoadingScreen = new Group();
        currentLoadingScreen.getChildren().add(loadingScreen);
        loadingCircle.setPreserveRatio(true);
        loadingCircle.setFitHeight(60);
        loadingCircle.setX(165);
        loadingCircle.setY(310);
        animationTimer = animation();
        currentLoadingScreen.getChildren().add(loadingCircle);
        loadingState = false;
    }

    /**
     * Displaying a loading screen by passing current scene.
     * @param currentScene current scene
     */
    public void display(Scene currentScene){
        if(loadingState)
            return;
        if(currentScene == null)
            return;
        this.rootPane = (AnchorPane) currentScene.getRoot();
        if(rootPane == null)
            return;
        animationTimer.start();
        rootPane.getChildren().add(currentLoadingScreen);
        loadingState = true;
    }

    /**
     * Displaying a loading screen by passing root pane
     * @param rootPane root pane (STD pane is anchor pane)
     */
    public void display(AnchorPane rootPane){
        if(loadingState)
            return;
        if(rootPane == null)
            return;
        this.rootPane = rootPane;
        animationTimer.start();
        rootPane.getChildren().add(currentLoadingScreen);
        loadingState = true;
    }

    /**
     * Removes and disappear the loading screen node!
     */
    public void disappear(){
        if(!loadingState)
            return;
        animationTimer.stop();
        rootPane.getChildren().remove(currentLoadingScreen);
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
                loadingCircle.setRotate(loadingCircle.getRotate() + 7);
            }
        };
    }

    /**
     * @return only instance onWaitLoader!
     * If it is null it builds one!
     */
    public static OnWaitLoader getOnWaitLoader() throws IOException {
        if(onWaitLoader == null)
            onWaitLoader = new OnWaitLoader();
        return onWaitLoader;
    }

}
