package org.gamedevs.clashroyale.controller.battle.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.container.gamedata.PlayerContainer;
import org.gamedevs.clashroyale.model.container.gamedata.SelectedCardContainer;
import org.gamedevs.clashroyale.model.game.player.Player;

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
    @FXML
    private ProgressBar kingTowerProgress;
    @FXML
    private ProgressBar leftPrincessTowerProgress;
    @FXML
    private ProgressBar rightPrincessTowerProgress;
    @FXML
    private ProgressBar kingTowerEnemyProgress;
    @FXML
    private ProgressBar leftPrincessTowerEnemyProgress;
    @FXML
    private ProgressBar rightPrincessTowerEnemyProgress;
    @FXML
    private ImageView fullLimitArea;
    @FXML
    private ImageView leftLimitArea;
    @FXML
    private ImageView rightLimitArea;
    @FXML
    private ImageView backLimitArea;


    //updatable
    private AnchorPane battleFieldPaneUpdatable;
    private static ImageView selectedTileUpdatable = new ImageView();

    /**
     * Initializes requirements of battle field view
     *
     * @param url            'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: uncomment line bellow when out of test!
        selectedTile.visibleProperty().bindBidirectional(
                SelectedCardContainer.getSelectedCardContainer().selectedCardExistProperty()
        );
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
