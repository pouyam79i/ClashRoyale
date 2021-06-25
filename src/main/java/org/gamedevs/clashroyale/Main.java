package org.gamedevs.clashroyale;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.gamedevs.clashroyale.controller.loaders.PreloaderScreen;

/**
 * This class contains main method of Clash Royale application!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Building main stage of this application
     * @param primaryStage is the main stage (set to loading)
     * @throws Exception if failed to load 'loading.fxml' or faced any other problem!
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/fxml/loading.fxml"));
//        primaryStage.setTitle("Clash Royale");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    /**
     * Main method of Clash Royale application!
     * @param args passing application details and main stage to main thread!
     */
    public static void main(String[] args) {
        // Launching main stage of this application!
        launch(args);
//        LauncherImpl.launchApplication(Main.class, PreloaderScreen.class, args);
    }

}
