package org.gamedevs.clashroyale.controller.battle.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.container.gamedata.PlayerContainer;
import org.gamedevs.clashroyale.model.container.gamedata.SelectedCardContainer;
import org.gamedevs.clashroyale.model.game.player.Player;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Control class of battle field
 *
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
    private ImageView background;

    @FXML
    private ImageView lava;

    @FXML
    private ImageView battleField;

    @FXML
    private ImageView objects;

    @FXML
    private AnchorPane battleFieldPane;

    @FXML
    private ImageView leftPrincessTowerEnemyImg;

    @FXML
    private ImageView kingTowerImg;

    @FXML
    private ImageView rightPrincessTowerEnemyImg;

    @FXML
    private ImageView lefttPrincessTowerImg;

    @FXML
    private ImageView rightPrincessTowerImg;

    @FXML
    private ImageView kingTowerEnemyImg;

    @FXML
    private ImageView selectedTile;


    // Updatable properties
    private ImageView selectedTileUpdatable;
    private AnchorPane battleFieldPaneUpdatable;

    /**
     * Initializes requirements of battle field view
     *
     * @param url            'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: uncomment line bellow when out of test!
//        selectedTile.setVisible(false);
        MouseTilePosition.getMouseTilePosition().setCaliberX(selectedTile.getLayoutX());
        MouseTilePosition.getMouseTilePosition().setCaliberY(selectedTile.getLayoutY());
        selectedTile.layoutXProperty().bindBidirectional(MouseTilePosition.getMouseTilePosition().xSelectedTileProperty());
        selectedTile.layoutYProperty().bindBidirectional(MouseTilePosition.getMouseTilePosition().ySelectedTileProperty());
        getMainBattleField().setSelectedTileUpdatable(selectedTile);
        getMainBattleField().setBattleFieldPaneUpdatable(battleFieldPane);
    }

    /**
     * updates (x,y) of dropping card!
     *
     * @param mouseEvent used to get x or y
     */
    @FXML
    private void updatePosition(MouseEvent mouseEvent) {
        Thread thread = (new Thread(() -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            x = Math.floor(x / 20.7);
            y = Math.floor(y / 16.8);
            if (y > 29 || x > 17 || x < 0 || y < 0) {
                return;
            }
            MouseTilePosition.getMouseTilePosition().setX(x);
            MouseTilePosition.getMouseTilePosition().setY(y);
            // TODO: uncomment this to know where is (x,y) of pointer
//            Console.getConsole().printTracingMessage("Mose move detected: " + MouseTilePosition.getMouseTilePosition().getX()
//                    + ", " + MouseTilePosition.getMouseTilePosition().getY());
        }));
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    void dropCard(MouseEvent event) {
        Player player = PlayerContainer.getPlayerContainer().getPlayer();
        if (SelectedCardContainer.getSelectedCardContainer().isSelectedCardExist()) {
            if (player.drop(event.getX(), event.getY(), SelectedCardContainer.getSelectedCardContainer().getSelectedCard())) {
                SelectedCardContainer.getSelectedCardContainer().dropped();
            } else
                SelectedCardContainer.getSelectedCardContainer().droppingFailed();
        }
    }

    /**
     * Set visibility of selected tile!
     *
     * @param visibility of selected tile image
     */
    public void setSelectedTileVisibility(boolean visibility) {
        selectedTile.setVisible(visibility);
    }

    public AnchorPane getBattleFieldPaneUpdatable() {
        return battleFieldPaneUpdatable;
    }

    // Setters
    private void setSelectedTileUpdatable(ImageView selectedTileUpdatable) {
        this.selectedTileUpdatable = selectedTileUpdatable;
    }

    public void setBattleFieldPaneUpdatable(AnchorPane battleFieldPaneUpdatable) {
        this.battleFieldPaneUpdatable = battleFieldPaneUpdatable;
    }

    /**
     * @return only instance of this class
     */
    public static MainBattleField getMainBattleField() {
        if (mainBattleField == null)
            mainBattleField = new MainBattleField();
        return mainBattleField;
    }

}
