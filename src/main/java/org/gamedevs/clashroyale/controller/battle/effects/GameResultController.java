package org.gamedevs.clashroyale.controller.battle.effects;

import animatefx.animation.BounceIn;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.gamedevs.clashroyale.model.game.battle.tools.GameResult;
import org.gamedevs.clashroyale.model.game.player.Side;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class displays game result in view!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class GameResultController implements Initializable {

    /**
     * only instance of this class
     */
    private static GameResultController resultController;

    // fx:id
    @FXML
    private Label blueWinnerLabel;
    @FXML
    private Label redWinnerLabel;
    @FXML
    private ImageView crownRed1;
    @FXML
    private ImageView crownRed2;
    @FXML
    private ImageView crownRed3;
    @FXML
    private ImageView crownBlue1;
    @FXML
    private ImageView crownBlue2;
    @FXML
    private ImageView crownBlue3;
    @FXML
    private Label redName;
    @FXML
    private Label blueName;
    @FXML
    private ImageView blueSprinkle;
    @FXML
    private ImageView redSprinkle;

    // Updatable properties:
    private Label blueWinnerLabelUpdatable;
    private Label redWinnerLabelUpdatable;
    private ImageView crownRed1Updatable;
    private ImageView crownRed2Updatable;
    private ImageView crownRed3Updatable;
    private ImageView crownBlue1Updatable;
    private ImageView crownBlue2Updatable;
    private ImageView crownBlue3Updatable;
    private Label redNameUpdatable;
    private Label blueNameUpdatable;

    /**
     * if true it means go to main menu
     */
    private SimpleBooleanProperty checked;

    /**
     * Sets requirements of this class
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getResultController().setBlueWinnerLabelUpdatable(blueWinnerLabel);
        getResultController().setRedWinnerLabelUpdatable(redWinnerLabel);
        getResultController().setCrownBlue1Updatable(crownBlue1);
        getResultController().setCrownBlue2Updatable(crownBlue2);
        getResultController().setCrownBlue3Updatable(crownBlue3);
        getResultController().setCrownRed1Updatable(crownRed1);
        getResultController().setCrownRed2Updatable(crownRed2);
        getResultController().setCrownRed3Updatable(crownRed3);
        getResultController().setRedNameUpdatable(redName);
        getResultController().setBlueNameUpdatable(blueName);
        checked = new SimpleBooleanProperty();
        checked.set(false);
        getResultController().setChecked(checked);
    }

    /**
     * Sets the check property as true
     */
    @FXML
    public void goToMain(){
        checked.setValue(true);
    }

    /**
     * Displays animation of game result
     * @param gameResult game result container
     * @param mySide my side of playing
     */
    public void display(GameResult gameResult, Side mySide){
        int redScore, blueScore;
        String redName = null, blueName = null;
        boolean blueWinner = false;
        if(mySide == Side.TOP){
                blueScore = gameResult.getTopPlayerScore();
                redScore = gameResult.getDownPlayerScore();
                blueName = gameResult.getTopPlayerName();
                redName = gameResult.getDownPlayerName();
        }
        else{
            redScore = gameResult.getTopPlayerScore();
            blueScore = gameResult.getDownPlayerScore();
            redName = gameResult.getTopPlayerName();
            blueName = gameResult.getDownPlayerName();
        }
        String blueNameFinal = blueName;
        String redNameFinal = redName;
        Platform.runLater(() -> {
            blueNameUpdatable.setText(blueNameFinal);
            redNameUpdatable.setText(redNameFinal);
        });
        int finalRedScore = redScore;
        int finalBlueScore = blueScore;
        Thread thread = (new Thread(() -> {
            for(int i = 1; i <= 3; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                if(i <= finalRedScore){
                    if(i == 1){
                        Platform.runLater(() -> {
                            crownRed1Updatable.setVisible(true);
                            new BounceIn(crownRed1Updatable);
                        });
                    }
                    else if(i == 2){
                        Platform.runLater(() -> {
                            crownRed2Updatable.setVisible(true);
                            new BounceIn(crownRed2Updatable);
                        });
                    }
                    else if(i == 3){
                        Platform.runLater(() -> {
                            crownRed3Updatable.setVisible(true);
                            new BounceIn(crownRed3Updatable);
                        });
                    }
                }
                if(i <= finalBlueScore){
                    if(i == 1){
                        Platform.runLater(() -> {
                            crownBlue1Updatable.setVisible(true);
                            new BounceIn(crownBlue1Updatable);
                        });
                    }
                    else if(i == 2){
                        Platform.runLater(() -> {
                            crownBlue2Updatable.setVisible(true);
                            new BounceIn(crownBlue2Updatable);
                        });
                    }
                    else if(i == 3){
                        Platform.runLater(() -> {
                            crownBlue3Updatable.setVisible(true);
                            new BounceIn(crownBlue3Updatable);
                        });
                    }
                }
            }
            if(finalRedScore > finalBlueScore){
                Platform.runLater(() -> {
                    redWinnerLabelUpdatable.setVisible(true);
                    new BounceIn(redWinnerLabelUpdatable);
                });
            }
            else if(finalRedScore < finalBlueScore){
                Platform.runLater(() -> {
                    blueWinnerLabelUpdatable.setVisible(true);
                    new BounceIn(blueWinnerLabelUpdatable);
                });
            }
            else {
                Platform.runLater(() -> {
                    redWinnerLabelUpdatable.setVisible(true);
                    blueWinnerLabelUpdatable.setVisible(true);
                    new BounceIn(redWinnerLabelUpdatable);
                    new BounceIn(blueWinnerLabelUpdatable);
                });
            }
        }));
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * initializes the pane
     */
    public void init(){
        checked.setValue(false);
        Platform.runLater(() -> {
            blueWinnerLabelUpdatable.setVisible(false);
            redWinnerLabelUpdatable.setVisible(false);
            crownBlue1Updatable.setVisible(false);
            crownBlue2Updatable.setVisible(false);
            crownBlue3Updatable.setVisible(false);
            crownRed1Updatable.setVisible(false);
            crownRed2Updatable.setVisible(false);
            crownRed3Updatable.setVisible(false);
        });
    }

    // Getter
    public boolean isChecked() {
        return checked.get();
    }
    public SimpleBooleanProperty checkedProperty() {
        return checked;
    }

    // Setters
    private void setBlueWinnerLabelUpdatable(Label blueWinnerLabelUpdatable) {
        this.blueWinnerLabelUpdatable = blueWinnerLabelUpdatable;
    }
    private void setRedWinnerLabelUpdatable(Label redWinnerLabelUpdatable) {
        this.redWinnerLabelUpdatable = redWinnerLabelUpdatable;
    }
    private void setCrownRed1Updatable(ImageView crownRed1Updatable) {
        this.crownRed1Updatable = crownRed1Updatable;
    }
    private void setCrownRed2Updatable(ImageView crownRed2Updatable) {
        this.crownRed2Updatable = crownRed2Updatable;
    }
    private void setCrownRed3Updatable(ImageView crownRed3Updatable) {
        this.crownRed3Updatable = crownRed3Updatable;
    }
    private void setCrownBlue1Updatable(ImageView crownBlue1Updatable) {
        this.crownBlue1Updatable = crownBlue1Updatable;
    }
    private void setCrownBlue2Updatable(ImageView crownBlue2Updatable) {
        this.crownBlue2Updatable = crownBlue2Updatable;
    }
    private void setCrownBlue3Updatable(ImageView crownBlue3Updatable) {
        this.crownBlue3Updatable = crownBlue3Updatable;
    }
    private void setRedNameUpdatable(Label redNameUpdatable) {
        this.redNameUpdatable = redNameUpdatable;
    }
    private void setBlueNameUpdatable(Label blueNameUpdatable) {
        this.blueNameUpdatable = blueNameUpdatable;
    }
    private void setChecked(SimpleBooleanProperty checked) {
        this.checked = checked;
    }

    /**
     * @return only instance of this class
     */
    public static GameResultController getResultController() {
        if(resultController == null)
            resultController = new GameResultController();
        return resultController;
    }

}
