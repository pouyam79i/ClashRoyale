package org.gamedevs.clashroyale.controller.battle.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.tools.Clock;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Control class of battle field
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MainBattleField implements Initializable {

    /**
     * Only instance of this class
     */
    private static MainBattleField mainBattleField = null;

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
    private ImageView selectedTile;
    @FXML
    private GridPane firstPass;
    @FXML
    private GridPane secondPass;
    @FXML
    private Label clockLabel;
    // Updatable properties
    private ImageView selectedTileUpdatable;

    /**
     * Initializes requirements of battle field view
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: uncomment line bellow when out of test!
//        selectedTile.setVisible(false);
        clockLabel.textProperty().bind(Clock.getClock().clockStringProperty());
        MouseTilePosition.getMouseTilePosition().setCaliberX(selectedTile.getLayoutX());
        MouseTilePosition.getMouseTilePosition().setCaliberY(selectedTile.getLayoutY());
        selectedTile.layoutXProperty().bindBidirectional(MouseTilePosition.getMouseTilePosition().xSelectedTileProperty());
        selectedTile.layoutYProperty().bindBidirectional(MouseTilePosition.getMouseTilePosition().ySelectedTileProperty());
        getMainBattleField().setSelectedTileUpdatable(selectedTile);
    }

    /**
     * updates (x,y) of dropping card!
     * @param mouseEvent used to get x or y
     */
    @FXML
    private void updatePosition(MouseEvent mouseEvent){
        Thread thread = (new Thread(() -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            x = Math.floor(x/20.7);
            y = Math.floor(y/16.8);
            if(y == 5 || y == 6){
                if((0 <= x && x <= 2) || (4 <= x && x <= 13) || (15 <= x && x <= 18)){
                    return;
                }
            }
            if(y > 20 || x > 17 || x < 0 || y < 0){
                return;
            }
            x *= 20.7;
            y *= 16.8;
            // TODO: uncomment this to know where is (x,y) of pointer
//            Console.getConsole().printTracingMessage("Mose move detected: " + x  + ", " + y);
            MouseTilePosition.getMouseTilePosition().setX(x);
            MouseTilePosition.getMouseTilePosition().setY(y);
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Set visibilty of selected tile!
     * @param visibility of selected tile image
     */
    public void setSelectedTileVisibility(boolean visibility){
        selectedTile.setVisible(visibility);
    }

    // Setters
    private void setSelectedTileUpdatable(ImageView selectedTileUpdatable) {
        this.selectedTileUpdatable = selectedTileUpdatable;
    }

    /**
     * @return only instance of this class
     */
    public static MainBattleField getMainBattleField() {
        if(mainBattleField == null)
            mainBattleField = new MainBattleField();
        return mainBattleField;
    }

}
