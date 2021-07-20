package org.gamedevs.clashroyale.controller.menu.main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.game.battle.tools.GameResult;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.io.IOException;

/**
 * Game result cell is controller of
 * game result view in list view of last games.
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public class LastGameCell extends ListCell<GameResult> {

    // fx:id -> graphic related image views
    @FXML
    private ImageView redCrown1;
    @FXML
    private ImageView redCrown2;
    @FXML
    private ImageView redCrown3;
    @FXML
    private ImageView blueCrown1;
    @FXML
    private ImageView blueCrown2;
    @FXML
    private ImageView blueCrown3;
    @FXML
    private ImageView darkRedCrown1;
    @FXML
    private ImageView darkRedCrown2;
    @FXML
    private ImageView darkRedCrown3;
    @FXML
    private ImageView darkBlueCrown1;
    @FXML
    private ImageView darkBlueCrown2;
    @FXML
    private ImageView darkBlueCrown3;
    // name properties!
    @FXML
    private Label myName;
    @FXML
    private Label enemyName;
    // main container
    @FXML
    private AnchorPane lastGameCell;

    /**
     * fxml loader
     */
    private FXMLLoader fxmlLoader = null;

    /**
     * Updates items view
     * @param gameResult game result container
     * @param empty if empty
     */
    @Override
    protected void updateItem(GameResult gameResult, boolean empty){
        super.updateItem(gameResult, empty);
        if(empty || gameResult == null) {
            setText(null);
            setGraphic(null);
        }
        else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(getClass().getResource("../../../view/fxml/menu/list/last_game_cell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    Console.getConsole().printTracingMessage("Failed to load game result cell: " + e.getMessage());
                }
            }
            // logic of view:
            int myScore = 0, enemyScore = 0;
            String myName = "", enemyName = "";
            if(gameResult.getDownPlayerName().equals(
                    UserAccountContainer.getUserAccountContainer().getAccount().getUsername()
            )){
                myName = gameResult.getDownPlayerName();
                enemyName = gameResult.getTopPlayerName();
                myScore = gameResult.getDownPlayerScore();
                enemyScore = gameResult.getTopPlayerScore();
            }else {
                myName = gameResult.getTopPlayerName();
                enemyName = gameResult.getDownPlayerName();
                myScore = gameResult.getTopPlayerScore();
                enemyScore = gameResult.getDownPlayerScore();
            }
            this.myName.setText(myName);
            this.enemyName.setText(enemyName);
            // Setting blue crowns
            switch (myScore) {
                case 0 -> {
                    blueCrown1.setVisible(false);
                    blueCrown2.setVisible(false);
                    blueCrown3.setVisible(false);
                    darkBlueCrown1.setVisible(true);
                    darkBlueCrown2.setVisible(true);
                    darkBlueCrown3.setVisible(true);
                }
                case 1 -> {
                    blueCrown1.setVisible(true);
                    blueCrown2.setVisible(false);
                    blueCrown3.setVisible(false);
                    darkBlueCrown1.setVisible(false);
                    darkBlueCrown2.setVisible(true);
                    darkBlueCrown3.setVisible(true);
                }
                case 2 -> {
                    blueCrown1.setVisible(true);
                    blueCrown2.setVisible(true);
                    blueCrown3.setVisible(false);
                    darkBlueCrown1.setVisible(false);
                    darkBlueCrown2.setVisible(false);
                    darkBlueCrown3.setVisible(true);
                }
                case 3 -> {
                    blueCrown1.setVisible(true);
                    blueCrown2.setVisible(true);
                    blueCrown3.setVisible(true);
                    darkBlueCrown1.setVisible(false);
                    darkBlueCrown2.setVisible(false);
                    darkBlueCrown3.setVisible(false);
                }
            }
            // Setting red crown
            switch (enemyScore) {
                case 0 -> {
                    redCrown1.setVisible(false);
                    redCrown2.setVisible(false);
                    redCrown3.setVisible(false);
                    darkRedCrown1.setVisible(true);
                    darkRedCrown2.setVisible(true);
                    darkRedCrown3.setVisible(true);
                }
                case 1 -> {
                    redCrown1.setVisible(true);
                    redCrown2.setVisible(false);
                    redCrown3.setVisible(false);
                    darkRedCrown1.setVisible(false);
                    darkRedCrown2.setVisible(true);
                    darkRedCrown3.setVisible(true);
                }
                case 2 -> {
                    redCrown1.setVisible(true);
                    redCrown2.setVisible(true);
                    redCrown3.setVisible(false);
                    darkRedCrown1.setVisible(false);
                    darkRedCrown2.setVisible(false);
                    darkRedCrown3.setVisible(true);
                }
                case 3 -> {
                    redCrown1.setVisible(true);
                    redCrown2.setVisible(true);
                    redCrown3.setVisible(true);
                    darkRedCrown1.setVisible(false);
                    darkRedCrown2.setVisible(false);
                    darkRedCrown3.setVisible(false);
                }
            }
            setText(null);
            setGraphic(lastGameCell);
        }
    }

}
