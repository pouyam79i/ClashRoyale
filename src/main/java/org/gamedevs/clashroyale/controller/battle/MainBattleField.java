package org.gamedevs.clashroyale.controller.battle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

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

    /**
     * Initializes requirements of battle field view
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

}
