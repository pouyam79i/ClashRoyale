package org.gamedevs.clashroyale.controller.battle.main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private static ImageView leftPrincessTowerEnemyImgUpdatable = new ImageView();
    private static ImageView kingTowerImgUpdatable = new ImageView();
    private static ImageView rightPrincessTowerEnemyImgUpdatable = new ImageView();
    private static ImageView leftPrincessTowerImgUpdatable = new ImageView();
    private static ImageView rightPrincessTowerImgUpdatable = new ImageView();
    private static ImageView kingTowerEnemyImgUpdatable = new ImageView();
    private static ImageView selectedTileUpdatable = new ImageView();
    private static ProgressBar kingTowerProgressUpdatable = new ProgressBar();
    private static ProgressBar leftPrincessTowerProgressUpdatable = new ProgressBar();
    private static ProgressBar rightPrincessTowerProgressUpdatable = new ProgressBar();
    private static ProgressBar kingTowerEnemyProgressUpdatable = new ProgressBar();
    private static ProgressBar leftPrincessTowerEnemyProgressUpdatable = new ProgressBar();
    private static ProgressBar rightPrincessTowerEnemyProgressUpdatable = new ProgressBar();
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
        selectedTile.visibleProperty().bindBidirectional(
                SelectedCardContainer.getSelectedCardContainer().selectedCardExistProperty()
        );
        MouseTilePosition.getMouseTilePosition().setCaliberX(selectedTile.getLayoutX());
        MouseTilePosition.getMouseTilePosition().setCaliberY(selectedTile.getLayoutY());
        selectedTile.layoutXProperty().bindBidirectional(MouseTilePosition.getMouseTilePosition().xSelectedTileProperty());
        selectedTile.layoutYProperty().bindBidirectional(MouseTilePosition.getMouseTilePosition().ySelectedTileProperty());
        getMainBattleField().setSelectedTileUpdatable(selectedTile);
        getMainBattleField().setBattleFieldPaneUpdatable(battleFieldPane);

        //set updatable for towers
        setKingTowerEnemyProgressUpdatable(kingTowerEnemyProgress);
        setKingTowerEnemyImgUpdatable(kingTowerEnemyImg);
        setRightPrincessTowerEnemyImgUpdatable(rightPrincessTowerEnemyImg);
        setRighttPrincessTowerEnemyProgressUpdatable(rightPrincessTowerEnemyProgress);
        setLeftPrincessTowerEnemyImgUpdatable(leftPrincessTowerEnemyImg);
        setLeftPrincessTowerEnemyProgressUpdatable(leftPrincessTowerEnemyProgress);
        setKingTowerProgressUpdatable(kingTowerProgress);
        setKingTowerImgUpdatable(kingTowerImg);
        setRightPrincessTowerImgUpdatable(rightPrincessTowerImg);
        setRightPrincessTowerProgressUpdatable(rightPrincessTowerProgress);
        setLefttPrincessTowerImgUpdatable(lefttPrincessTowerImg);
        setLeftPrincessTowerProgressUpdatable(leftPrincessTowerProgress);
    }

    public void init() {
        bindProgressBars();
        addListenerForRemovingTower();
    }

    /**
     * bind progress bar with towers hp
     */
    private void bindProgressBars() {
        kingTowerProgressUpdatable.progressProperty().bind(PlayerContainer.getPlayerContainer().getPlayer().getKingTower().hpProperty().divide(PlayerContainer.getPlayerContainer().getPlayer().getKingTower().getHp()));
        leftPrincessTowerProgressUpdatable.progressProperty().bind(PlayerContainer.getPlayerContainer().getPlayer().getLeftPrincessTower().hpProperty().divide(PlayerContainer.getPlayerContainer().getPlayer().getLeftPrincessTower().getHp()));
        rightPrincessTowerProgressUpdatable.progressProperty().bind(PlayerContainer.getPlayerContainer().getPlayer().getRightPrincessTower().hpProperty().divide(PlayerContainer.getPlayerContainer().getPlayer().getRightPrincessTower().getHp()));
        kingTowerEnemyProgressUpdatable.progressProperty().bind(PlayerContainer.getPlayerContainer().getBot().getKingTower().hpProperty().divide(PlayerContainer.getPlayerContainer().getBot().getKingTower().getHp()));
        leftPrincessTowerEnemyProgressUpdatable.progressProperty().bind(PlayerContainer.getPlayerContainer().getBot().getLeftPrincessTower().hpProperty().divide(PlayerContainer.getPlayerContainer().getBot().getLeftPrincessTower().getHp()));
        rightPrincessTowerEnemyProgressUpdatable.progressProperty().bind(PlayerContainer.getPlayerContainer().getBot().getRightPrincessTower().hpProperty().divide(PlayerContainer.getPlayerContainer().getBot().getRightPrincessTower().getHp()));
        kingTowerProgressUpdatable.setStyle("-fx-accent: blue;");
        rightPrincessTowerProgressUpdatable.setStyle("-fx-accent: blue;");
        leftPrincessTowerProgressUpdatable.setStyle("-fx-accent: blue;");
    }

    /**
     * set listener for removing tower when hp <= 0
     */
    private void addListenerForRemovingTower() {
        PlayerContainer.getPlayerContainer().getPlayer().getLeftPrincessTower().hpProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if (t1.doubleValue() <= 0) {
                            battleFieldPaneUpdatable.getChildren().remove(leftPrincessTowerImgUpdatable);
                            battleFieldPaneUpdatable.getChildren().remove(leftPrincessTowerProgressUpdatable);
                        }
                    }
                }
        );
        PlayerContainer.getPlayerContainer().getPlayer().getRightPrincessTower().hpProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if (t1.doubleValue() <= 0) {
                            battleFieldPaneUpdatable.getChildren().remove(rightPrincessTowerImgUpdatable);
                            battleFieldPaneUpdatable.getChildren().remove(rightPrincessTowerProgressUpdatable);
                        }
                    }
                }
        );
        PlayerContainer.getPlayerContainer().getPlayer().getKingTower().hpProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if (t1.doubleValue() <= 0) {
                            battleFieldPaneUpdatable.getChildren().remove(kingTowerImgUpdatable);
                            battleFieldPaneUpdatable.getChildren().remove(kingTowerProgressUpdatable);
                        }
                    }
                }
        );
        PlayerContainer.getPlayerContainer().getBot().getLeftPrincessTower().hpProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if (t1.doubleValue() <= 0) {
                            battleFieldPaneUpdatable.getChildren().remove(leftPrincessTowerEnemyImgUpdatable);
                            battleFieldPaneUpdatable.getChildren().remove(leftPrincessTowerEnemyProgressUpdatable);
                        }
                    }
                }
        );
        PlayerContainer.getPlayerContainer().getBot().getRightPrincessTower().hpProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if (t1.doubleValue() <= 0) {
                            battleFieldPaneUpdatable.getChildren().remove(rightPrincessTowerEnemyImgUpdatable);
                            battleFieldPaneUpdatable.getChildren().remove(rightPrincessTowerEnemyProgressUpdatable);
                        }
                    }
                }
        );
        PlayerContainer.getPlayerContainer().getBot().getKingTower().hpProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if (t1.doubleValue() <= 0) {
                            battleFieldPaneUpdatable.getChildren().remove(kingTowerEnemyImgUpdatable);
                            battleFieldPaneUpdatable.getChildren().remove(kingTowerEnemyProgressUpdatable);
                        }
                    }
                }
        );
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

    public void setLeftPrincessTowerEnemyImgUpdatable(ImageView leftPrincessTowerEnemyImgUpdatable) {
        this.leftPrincessTowerEnemyImgUpdatable = leftPrincessTowerEnemyImgUpdatable;
    }

    public void setKingTowerImgUpdatable(ImageView kingTowerImgUpdatable) {
        this.kingTowerImgUpdatable = kingTowerImgUpdatable;
    }

    public void setRightPrincessTowerEnemyImgUpdatable(ImageView rightPrincessTowerEnemyImgUpdatable) {
        this.rightPrincessTowerEnemyImgUpdatable = rightPrincessTowerEnemyImgUpdatable;
    }

    public void setLefttPrincessTowerImgUpdatable(ImageView lefttPrincessTowerImgUpdatable) {
        this.leftPrincessTowerImgUpdatable = lefttPrincessTowerImgUpdatable;
    }

    public void setRightPrincessTowerImgUpdatable(ImageView rightPrincessTowerImgUpdatable) {
        this.rightPrincessTowerImgUpdatable = rightPrincessTowerImgUpdatable;
    }

    public void setKingTowerEnemyImgUpdatable(ImageView kingTowerEnemyImgUpdatable) {
        this.kingTowerEnemyImgUpdatable = kingTowerEnemyImgUpdatable;
    }

    public void setKingTowerProgressUpdatable(ProgressBar kingTowerProgressUpdatable) {
        this.kingTowerProgressUpdatable = kingTowerProgressUpdatable;
    }

    public void setLeftPrincessTowerProgressUpdatable(ProgressBar leftPrincessTowerProgressUpdatable) {
        this.leftPrincessTowerProgressUpdatable = leftPrincessTowerProgressUpdatable;
    }

    public void setRightPrincessTowerProgressUpdatable(ProgressBar rightPrincessTowerProgressUpdatable) {
        this.rightPrincessTowerProgressUpdatable = rightPrincessTowerProgressUpdatable;
    }

    public void setKingTowerEnemyProgressUpdatable(ProgressBar kingTowerEnemyProgressUpdatable) {
        this.kingTowerEnemyProgressUpdatable = kingTowerEnemyProgressUpdatable;
    }

    public void setLeftPrincessTowerEnemyProgressUpdatable(ProgressBar leftPrincessTowerEnemyProgressUpdatable) {
        this.leftPrincessTowerEnemyProgressUpdatable = leftPrincessTowerEnemyProgressUpdatable;
    }

    public void setRighttPrincessTowerEnemyProgressUpdatable(ProgressBar righttPrincessTowerEnemyProgressUpdatable) {
        this.rightPrincessTowerEnemyProgressUpdatable = righttPrincessTowerEnemyProgressUpdatable;
    }

}
