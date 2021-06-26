package org.gamedevs.clashroyale.model.preloaders;

import javafx.animation.AnimationTimer;
import javafx.application.Preloader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.gamedevs.clashroyale.model.utils.console.Console;

/**
 * Preloader class, displays preloader splash screen
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class PreloaderSplashScreen extends Preloader {

    /**
     * Preloader stage
     */
    private Stage preloaderStage;
    /**
     * Preloader scene
     */
    private Scene scene;

    /**
     * Loading circle animation maker
     */
    private AnimationTimer animationTimer;

    /**
     * Height of preloader screen
     */
    private final int HEIGHT = 404;
    /**
     * Width of preloader screen
     */
    private final int WIDTH = 720;

    /**
     * Background image of preloader
     */
    private final ImageView backGroundImage = new ImageView(new Image(
            getClass().getResource("./../../view/img/loading/preloader_image1.png").toExternalForm()));
    /**
     * Loading circle of preloader
     */
    private final ImageView loadingCircle = new ImageView(new Image(
            getClass().getResource("./../../view/img/loading/loading_circle.png").toExternalForm()));

    /**
     * Constructor of PreloaderSplashScreen
     */
    public PreloaderSplashScreen(){}

    /**
     * Initializing a proper scene for preloader screen
     */
    @Override
    public void init() {
        try {
            Group root = new Group();
            backGroundImage.setFitHeight(HEIGHT);
            backGroundImage.setFitWidth(WIDTH);
            loadingCircle.setPreserveRatio(true);
            loadingCircle.setFitHeight(60);
            loadingCircle.setX(20);
            loadingCircle.setY(320);
            root.getChildren().add(backGroundImage);
            root.getChildren().add(loadingCircle);
            scene = new Scene(root);
        }catch (Exception e){
            Console.getConsole().printTracingMessage("Failed to build scene for preloader. \nreason:"
                    + e.getMessage());
            // Also we can add a logger here :)
        }
    }

    /**
     * Starts and shows the preloader stage
     * @param stage of preloader
     */
    @Override
    public void start(Stage stage){
        try {
            // Setteing preloaderStage to be able to hide it later!
            this.preloaderStage = stage;
            // Setting stage settings
            preloaderStage.setScene(scene);
            preloaderStage.initStyle(StageStyle.UNDECORATED);
            preloaderStage.setTitle("Clash Royale");
            // Showing stage
            preloaderStage.show();
            // Starting loading circle animation
            animationTimer = animation();
            animationTimer.start();
        } catch (Exception e){
            Console.getConsole().printTracingMessage("Failed to build stage for preloader. \nreason:"
                    + e.getMessage());
            // Also we can add a logger here :)
        }
    }

    /**
     * Handling progress notification here
     * @param info of preloader notification
     */
    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification info){

    }

    /**
     * Handling state changes
     * mainly used to close preloader!
     * @param info of state changing notification
     */
    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info){
        StateChangeNotification.Type type = info.getType();
        switch (type){
            case BEFORE_START:  // This case is used when init finished and start is called
                try {
                    animationTimer.stop();
                    preloaderStage.hide();
                    Console.getConsole().printTracingMessage("Preloader splash screen process finished");
                }catch (Exception e){
                    Console.getConsole().printTracingMessage("Failed to close stage of preloader properly" +
                            ". \nreason:" + e.getMessage());
                    // Also we can add a logger here :)
                }
                break;
        }
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
