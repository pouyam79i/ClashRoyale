package org.gamedevs.clashroyale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.gamedevs.clashroyale.model.loader.OnWaitLoader;
import org.gamedevs.clashroyale.model.loader.PreloaderSplashScreen;

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
            Thread.sleep(2000); // Just checking loading page in debug mode :)
    }

    /**
     * Application starter.
     * Building main stage of this application
     * @param primaryStage is the main stage (set to loading)
     * @throws Exception if failed to load 'main.fxml' or faced any other problem!
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane mainRoot = FXMLLoader.load(getClass().getResource(
                "./view/fxml/menu/main_root.fxml"
        ));
        Scene mainRootScene = new Scene(mainRoot);
        primaryStage.setScene(mainRootScene);
        primaryStage.setTitle("Clash Royale");
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("./view/img/icon/cr_icon.png")));
        primaryStage.setHeight(MainConfig.MAIN_STAGE_HEIGHT);
        primaryStage.setWidth(MainConfig.MAIN_STAGE_WIDTH);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
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
