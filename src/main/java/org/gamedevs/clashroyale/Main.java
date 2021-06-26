package org.gamedevs.clashroyale;

import javafx.application.Application;
import javafx.stage.Stage;
import org.gamedevs.clashroyale.model.preloaders.PreloaderSplashScreen;

/**
 * This class contains main method of Clash Royale application!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Application initializer.
     * @throws Exception if failed to process
     */
    @Override
    public void init() throws Exception{
        // Do heavy process here before loading!
        if(MainConfig.DEBUG_MODE)
            Thread.sleep(10000); // Just checking loading page in debug mode :)
    }

    /**
     * Application starter.
     * Building main stage of this application
     * @param primaryStage is the main stage (set to loading)
     * @throws Exception if failed to load 'main.fxml' or faced any other problem!
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

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
