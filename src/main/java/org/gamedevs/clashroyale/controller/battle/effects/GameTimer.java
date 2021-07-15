package org.gamedevs.clashroyale.controller.battle.effects;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Game timer controller.
 * It shows game time!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class GameTimer implements Initializable {

    /**
     * only instance of game timer
     */
    private static GameTimer gameTimer = null;

    // fx:id
    @FXML
    private Label timerLabel;

    // Updatable fields
    private Label timerLabelUpdatable;

    /**
     * Setting updatable fields
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getGameTimer().setTimerLabelUpdatable(timerLabel);
    }

    /**
     * binding timer label
     * @param timerLabelString will be bound to timer label
     */
    public void bindTimerLabel(StringProperty timerLabelString){
        timerLabelUpdatable.textProperty().bindBidirectional(timerLabelString);
    }

    // Setter
    private void setTimerLabelUpdatable(Label timerLabelUpdatable) {
        this.timerLabelUpdatable = timerLabelUpdatable;
    }

    /**
     * @return only instance of this class
     */
    public static GameTimer getGameTimer() {
        if(gameTimer == null)
            gameTimer = new GameTimer();
        return gameTimer;
    }

}
