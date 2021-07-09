package org.gamedevs.clashroyale;

import javafx.application.Application;
import javafx.stage.Stage;

import org.gamedevs.clashroyale.model.container.scene.MainMenuSceneContainer;
import org.gamedevs.clashroyale.model.container.scene.SignupMenuContainer;
import org.gamedevs.clashroyale.model.launcher.AccountLauncher;
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

    /**
     * Application music player!
     * Not needed here! (edit and simplify code)
     * Make it singleton
     */
    private MusicPlayer musicPlayer;

    /**
     * Application initializer.
     * @throws Exception if failed to process
     */
    @Override
    public void init() throws Exception{
        musicPlayer = MusicPlayer.getMusicPlayer();
        musicPlayer.playMenuMusic();
        AccountLauncher accountLauncher = new AccountLauncher();
        MainMenuLauncher menuLauncher = new MainMenuLauncher();
        accountLauncher.launch();
        menuLauncher.launch();
        // Do heavy process here before loading!
        if(MainConfig.DEBUG_MODE)
            Thread.sleep(1000); // Just checking loading page in debug mode :)
        Console.getConsole().printTracingMessage("Application initialized!");
    }

    /**
     * Application starter.
     * Building main stage of this application.
     * @param primaryStage is the main stage.
     * @throws Exception if failed to load 'main.fxml' or faced any other problem!
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setScene(SignupMenuContainer.getMenuData().getRootScene());
        primaryStage.setTitle("Clash Royale");
        primaryStage.getIcons().add(MainMenuSceneContainer.getMenuData().getGameIcon());
        primaryStage.setHeight(MainConfig.MAIN_STAGE_HEIGHT);
        primaryStage.setWidth(MainConfig.MAIN_STAGE_WIDTH);
        primaryStage.setResizable(false);
        primaryStage.show();
        Console.getConsole().printTracingMessage("Application started!");
    }

    /**
     * Main method of 'Clash Royale' application!
     * @param args passing application details and main stage to main thread!
     */
    public static void main(String[] args) {
        // Setting the preloader
        System.setProperty("javafx.preloader", PreloaderSplashScreen.class.getCanonicalName());
        launch(args);
    }

}