package org.gamedevs.clashroyale.model.loader.view;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class OnWaitLoader {

    private boolean loadingState;

    private final AnchorPane loadingScreen;

    /**
     * Loading circle of preloader
     */
    private final ImageView loadingCircle;

    private AnchorPane sceneRoot;
    private Group currentLoadingScreen;
    /**
     * Loading circle animation maker
     */
    private AnimationTimer animationTimer;

    public OnWaitLoader() throws IOException {
        loadingScreen = FXMLLoader.load(getClass().getResource(
                "./../../view/fxml/loading/loading_cover.fxml"
        ));
        loadingCircle = new ImageView(new Image(
                getClass().getResource("./../../view/img/loading/loading_circle.png").toExternalForm()));
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

    public void display(Scene currentScene){
        if(currentScene == null)
            return;
        this.sceneRoot = (AnchorPane) currentScene.getRoot();
        if(sceneRoot == null)
            return;
        animationTimer.start();
        sceneRoot.getChildren().add(currentLoadingScreen);
        loadingState = true;
    }

    public void disappear(){
        if(!loadingState)
            return;
        animationTimer.stop();
        sceneRoot.getChildren().remove(currentLoadingScreen);
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

}
