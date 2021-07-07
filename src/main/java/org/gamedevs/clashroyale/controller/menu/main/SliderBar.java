package org.gamedevs.clashroyale.controller.menu.main;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.util.Duration;

import org.gamedevs.clashroyale.model.container.scene.MainMenuSceneContainer;

/**
 * Slider bar controller.
 * In charge of sliding between scenes in main menu
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class SliderBar {

    /**
     * Cover image (Blue transparent cover)
     * Used for selected button
     */
    @FXML
    private ImageView btnCover;

    /**
     * Battle key value. used to transfer battle menu.
     */
    private  KeyValue battleKV;
    /**
     * Deck key value. used to transfer deck menu.
     */
    private KeyValue deckKV;
    /**
     * Cover key value. used to transfer cover image view.
     */
    private KeyValue coverKV;
    /**
     * speed of animation
     */
    private final double duration = 0.4;
    /**
     * it prevents multi call on right turn.
     */
    private boolean turnRight = true;
    /**
     * it prevents multi call on left turn.
     */
    private boolean turnLeft = false;

    /**
     * Set the animation to bring battle menu with slide
     */
    @FXML
    private void bringBattleMenu(){
        if(turnRight)
            return;
        turnRight = true;
        turnLeft = false;
        battleKV = new KeyValue(MainMenuSceneContainer.getMenuData().getBattleMenu().translateXProperty(), 0, Interpolator.EASE_IN);
        deckKV = new KeyValue(MainMenuSceneContainer.getMenuData().getDeckMenu().translateXProperty(), 0, Interpolator.EASE_IN);
        coverKV = new KeyValue(btnCover.translateXProperty(), 0, Interpolator.EASE_IN);
        animate();
    }

    /**
     * Set the animation to bring deck menu with slide
     */
    @FXML
    private void bringDeckMenu(){
        if(turnLeft)
            return;
        turnRight = false;
        turnLeft = true;
        battleKV = new KeyValue(MainMenuSceneContainer.getMenuData().getBattleMenu().translateXProperty(), 405, Interpolator.EASE_IN);
        deckKV = new KeyValue(MainMenuSceneContainer.getMenuData().getDeckMenu().translateXProperty(), 405, Interpolator.EASE_IN);
        coverKV = new KeyValue(btnCover.translateXProperty(), -203, Interpolator.EASE_IN);
        animate();
    }

    /**
     * Slide between scene with mouse scroller.
     * @param scrollEvent used to find out where to slide!
     */
    @FXML
    private void scrollHandler(ScrollEvent scrollEvent){
        if(scrollEvent.getDeltaY() > 0)
            bringBattleMenu();
        if(scrollEvent.getDeltaY() < 0)
            bringDeckMenu();
    }

    /**
     * Sets the animation property,
     * and run it.
     */
    private void animate(){
        Timeline timeline = new Timeline();
        KeyFrame battleKF = new KeyFrame(Duration.seconds(duration), battleKV);
        KeyFrame deckKF = new KeyFrame(Duration.seconds(duration), deckKV);
        KeyFrame coverKF = new KeyFrame(Duration.seconds(duration), coverKV);
        timeline.getKeyFrames().add(battleKF);
        timeline.getKeyFrames().add(deckKF);
        timeline.getKeyFrames().add(coverKF);
        timeline.play();
    }

}
