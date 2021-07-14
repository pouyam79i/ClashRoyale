package org.gamedevs.clashroyale.controller.battle.main;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Control class of battle field
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MainBattleField implements Initializable {

    // fx:id
    @FXML
    private ImageView objects;
    @FXML
    private ImageView battleField;
    @FXML
    private ImageView lava;
    @FXML
    private ImageView background;
    @FXML
    private GridPane firstPass;
    @FXML
    private GridPane secondPass;

    /**
     * Initializes requirements of battle field view
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void updatePosition(Event event){

        if(event.getSource() == firstPass){
            Thread thread = (new Thread(() -> {
                Console.getConsole().printTracingMessage("Mose move detected");
            }));
            thread.setDaemon(true);
            thread.start();
        }
    }

}
