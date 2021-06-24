package main.java.org.gamedevs.clashroyale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Parent root = FXMLLoader.load(getClass().getResource("../../../../resources/view/fxml/loading.fxml"));
        primaryStage.setTitle("Clash Royale");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    /**
     * Main method of Clash Royale application!
     * @param args passing application details and main stage to main thread!
     */
    public static void main(String[] args) {
        // Launching main stage of this application!
        launch(args);
    }

}
