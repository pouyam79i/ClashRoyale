package org.gamedevs.clashroyale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import org.gamedevs.clashroyale.model.container.scene.MainMenuSceneContainer;
import org.gamedevs.clashroyale.model.launcher.MainMenuLauncher;
import org.gamedevs.clashroyale.model.loader.PreloaderSplashScreen;
import org.gamedevs.clashroyale.model.media.MusicPlayer;
import org.gamedevs.clashroyale.model.utils.console.Console;

/**
 * This class contains main method of Clash Royale application!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class Main extends Application {

    private Scene mainRootScene;
    private MusicPlayer musicPlayer;

    /**
     * Application initializer.
     * @throws Exception if failed to process
     */
    @Override
    public void init() throws Exception{
        musicPlayer = MusicPlayer.getMusicPlayer();
        musicPlayer.playMenuMusic();
        MainMenuLauncher menuLauncher = new MainMenuLauncher();
        menuLauncher.launch();
        // Do heavy process here before loading!
        if(MainConfig.DEBUG_MODE)
            Thread.sleep(1000); // Just checking loading page in debug mode :)
        Console.getConsole().printTracingMessage("App init finished");
    }

    /**
     * Application starter.
     * Building main stage of this application
     * @param primaryStage is the main stage (set to loading)
     * @throws Exception if failed to load 'main.fxml' or faced any other problem!
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        mainRootScene = MainMenuSceneContainer.getMenuData().getRootScene();
        primaryStage.setScene(mainRootScene);
        primaryStage.setTitle("Clash Royale");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("./view/img/icon/cr_icon.png")));
        primaryStage.setHeight(MainConfig.MAIN_STAGE_HEIGHT);
        primaryStage.setWidth(MainConfig.MAIN_STAGE_WIDTH);
        primaryStage.setResizable(false);
        primaryStage.show();
        Console.getConsole().printTracingMessage("App start finished");
    }

    /**
     * Main method of Clash Royale application!
     * @param args passing application details and main stage to main thread!
     */
    public static void main(String[] args) {
        // Setting the preloader
        System.setProperty("javafx.preloader", PreloaderSplashScreen.class.getCanonicalName());
        launch(args);
    }

}
