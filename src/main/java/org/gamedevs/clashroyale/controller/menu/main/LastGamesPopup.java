package org.gamedevs.clashroyale.controller.menu.main;

import animatefx.animation.BounceIn;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.container.gamedata.GameResultContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.container.scene.MenuDataContainer;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Last game popup displays information of last games!
 * @author Pouya Mohammadi - CE@AUT Uni ID:9829039
 * @version 1.0
 */
public class LastGamesPopup implements Initializable {

    /**
     * only instance of this class
     */
    private static LastGamesPopup lastGamesPopup = null;

    // fx:id
    @FXML
    private ListView<GameResultContainer> lastGamesList;
    @FXML
    private Label emptyLabel;
    @FXML
    private Button backBtn;

    // updatable properties
    private ListView<GameResultContainer> lastGamesListUpdatable;
    private Label emptyLabelUpdatable;

    /**
     * last games collection
     */
    private SimpleListProperty<GameResultContainer> lastGames;

    /**
     * This class sets requirements
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getLastGamesPopup().setLastGamesListUpdatable(lastGamesList);
        getLastGamesPopup().setEmptyLabelUpdatable(emptyLabel);
        emptyLabel.setVisible(false);
    }

    /**
     * Initializes the collection, with account property
     */
    public void init(){
        Platform.runLater(() -> {
            lastGames = new SimpleListProperty<GameResultContainer>(FXCollections.observableList(
                    UserAccountContainer.getUserAccountContainer().getAccount().getPreviousGames()
            ));
            lastGamesListUpdatable.setItems(
                    lastGames
            );
            lastGamesListUpdatable.setCellFactory(lastGameCell -> new LastGameCell());
        });
    }

    /**
     * updates list view!
     * @param newGameResult will be added to the collection!
     */
    public void update(GameResultContainer newGameResult){
        Platform.runLater(() -> {
            lastGames.add(newGameResult);
        });
    }

    /**
     * back to previous menu
     */
    @FXML
    private void backToMain(){
        Thread thread = (new Thread(() -> {
            new BounceIn(backBtn).play();
            try {
                Thread.sleep(MainConfig.STD_BUTTON_ANIMATION_LATENCY);
            } catch (InterruptedException ignored) {}
            Platform.runLater(() -> {
                AnchorPane battleMainRoot = (AnchorPane) backBtn.getScene().getRoot();
                battleMainRoot.getChildren().remove(MenuDataContainer.getMenuDataContainer().getLastGamesPopupMenu());
            });
        }));
        thread.setDaemon(true);
        thread.start();
    }

    // Setters
    private void setLastGamesListUpdatable(ListView<GameResultContainer> lastGamesListUpdatable) {
        this.lastGamesListUpdatable = lastGamesListUpdatable;
    }
    private void setEmptyLabelUpdatable(Label emptyLabelUpdatable) {
        this.emptyLabelUpdatable = emptyLabelUpdatable;
    }

    /**
     * @return the only instance of this class
     */
    public static LastGamesPopup getLastGamesPopup() {
        if(lastGamesPopup == null)
            lastGamesPopup = new LastGamesPopup();
        return lastGamesPopup;
    }

}
