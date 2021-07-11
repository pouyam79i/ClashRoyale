package org.gamedevs.clashroyale;

import javafx.application.Application;
import javafx.stage.Stage;

import org.gamedevs.clashroyale.model.account.AccountLoader;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;
import org.gamedevs.clashroyale.model.launcher.MainGameLauncher;
import org.gamedevs.clashroyale.model.loader.file.MenuFileLoader;
import org.gamedevs.clashroyale.model.loader.view.OnWaitLoader;
import org.gamedevs.clashroyale.model.loader.view.PreloaderSplashScreen;
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
        // loading music player
        musicPlayer = MusicPlayer.getMusicPlayer();
        musicPlayer.playMenuMusic();
        // Loading all files used in main menu
        MenuFileLoader menuLauncher = new MenuFileLoader();
        menuLauncher.load();
        // Checking if we have already logged in an account!
        AccountLoader.getAccountLoader().init();
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
        primaryStage.setScene(MenuDataContainer.getMenuDataContainer().getRootScene());
        primaryStage.setTitle("Clash Royale");
        primaryStage.getIcons().add(MenuDataContainer.getMenuDataContainer().getGameIcon());
        primaryStage.setHeight(MainConfig.MAIN_STAGE_HEIGHT);
        primaryStage.setWidth(MainConfig.MAIN_STAGE_WIDTH);
        primaryStage.setResizable(false);
        primaryStage.show();
        OnWaitLoader.getOnWaitLoader().display(primaryStage.getScene());
        new MainGameLauncher().start();
        Console.getConsole().printTracingMessage("Application started!");
    }

    /**
     * Main method of 'Clash Royale' application!
     * @param args passing application details and main stage to main thread!
     */
    public static void main(String[] args) {
        // Setting the preloader
//        System.setProperty("javafx.preloader", PreloaderSplashScreen.class.getCanonicalName());
        launch(args);
    }

}
