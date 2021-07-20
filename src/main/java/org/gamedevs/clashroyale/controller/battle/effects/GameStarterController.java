package org.gamedevs.clashroyale.controller.battle.effects;

import animatefx.animation.BounceIn;
import animatefx.animation.SlideInLeft;
import animatefx.animation.SlideInRight;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import org.gamedevs.clashroyale.model.account.levelproperty.Arenas;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class handles game starter view!
 *
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class GameStarterController implements Initializable {

    /**
     * only instance of this class
     */
    private static GameStarterController starterController = null;

    // fx:id
    @FXML
    private Label redName;
    @FXML
    private Label blueName;
    @FXML
    private Label arenaName;
    @FXML
    private Label arenaNumber;
    @FXML
    private ImageView swordImg;

    // updatable fields
    private Label redNameUpdatable;
    private Label blueNameUpdatable;
    private Label arenaNameUpdatable;
    private Label arenaNumberUpdatable;
    private ImageView swordImgUpdatable;

    /**
     * Initializing requirements
     * @param url 'not used'
     * @param resourceBundle 'not used'
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getStarterController().setRedNameUpdatable(redName);
        getStarterController().setBlueNameUpdatable(blueName);
        getStarterController().setArenaNameUpdatable(arenaName);
        getStarterController().setArenaNumberUpdatable(arenaNumber);
        getStarterController().setSwordImgUpdatable(swordImg);
    }

    /**
     * Sets game starter view
     *
     * @param blueNameText this player name (my side)
     * @param redNameText  opponent player name
     * @param arena        arena of game
     */
    public void init(String blueNameText, String redNameText, Arenas arena) {
        // TODO: not working - reason: I dont know!
        Platform.runLater(() -> {
            arenaNameUpdatable.setVisible(false);
            arenaNumberUpdatable.setVisible(false);
            swordImgUpdatable.setVisible(false);
            arenaNameUpdatable.setText(arena.getName());
            arenaNumberUpdatable.setText("Arena " + arena.getLevel());
            blueNameUpdatable.setText(blueNameText);
            redNameUpdatable.setText(redNameText);
        });
    }

    /**
     * runs related animation of game starter
     */
    public void display() {
        Platform.runLater(() -> {
            arenaNameUpdatable.setVisible(true);
            arenaNumberUpdatable.setVisible(true);
            swordImgUpdatable.setVisible(true);
            new BounceIn(swordImgUpdatable).play();
            new SlideInLeft(arenaNumberUpdatable).play();
            new SlideInRight(arenaNameUpdatable).play();
        });
    }

    // Setters
    private void setRedNameUpdatable(Label redNameUpdatable) {
        this.redNameUpdatable = redNameUpdatable;
    }

    private void setBlueNameUpdatable(Label blueNameUpdatable) {
        this.blueNameUpdatable = blueNameUpdatable;
    }

    private void setArenaNameUpdatable(Label arenaNameUpdatable) {
        this.arenaNameUpdatable = arenaNameUpdatable;
    }

    private void setArenaNumberUpdatable(Label arenaNumberUpdatable) {
        this.arenaNumberUpdatable = arenaNumberUpdatable;
    }

    private void setSwordImgUpdatable(ImageView swordImgUpdatable) {
        this.swordImgUpdatable = swordImgUpdatable;
    }

    /**
     * @return only instance of this class
     */
    public static GameStarterController getStarterController() {
        if (starterController == null) {
            starterController = new GameStarterController();
        }
        return starterController;
    }

}
