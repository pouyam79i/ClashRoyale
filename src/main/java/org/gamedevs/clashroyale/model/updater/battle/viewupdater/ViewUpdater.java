package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.GameDroppableImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.GameImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * a class which handle view of game object in GUI
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
public abstract class ViewUpdater extends Runnable {

    protected GameDroppableImageContainer imageContainer;
    protected final AnchorPane battleFieldPane;
    protected final GameObject gameObject;
    protected final CardName cardName;
    protected ObjectView objectView;
    protected boolean isEnemy;
    protected GameObjectState previousState;
    protected Angle previousAngle;
    protected Tile previousTile;
    protected boolean previousBoost;

    public ViewUpdater(GameObject gameObject, boolean isEnemy) {
        threadName = "ViewUpdater";
        imageContainer = GameDroppableImageContainer.getGameDroppableImageContainer();
        this.battleFieldPane = MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable();
        this.gameObject = gameObject;
        cardName = gameObject.getNameOfDroppable();
        this.isEnemy = isEnemy;
        previousTile = gameObject.getHeadPixel();
        previousState = gameObject.getState();
        previousAngle = gameObject.getAngle();
    }

    @Override
    public void run() {
        // Initializing thread needs
        Image currentImage = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
        objectView = new ObjectView(gameObject, currentImage);
        objectView.getImageView().setFitWidth(currentImage.getWidth() / 2.5);
        objectView.getImageView().setFitHeight(currentImage.getHeight() / 2.5);
        int x = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
        int y = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
        Console.getConsole().printTracingMessage("x, y final: " + x + ", " + y);
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            objectView.setLayoutX(x - gameObject.getErrorInGUIX() );
            objectView.setLayoutY(y - gameObject.getErrorInGUIY() );
        });

        update();
    }


    /**
     * update GUI
     */
    public abstract void update();

    /**
     * update image of game object regarding to its state and angle
     */
    public void updateImg() {

        if (previousAngle != gameObject.getAngle() ||
                previousState != gameObject.getState() ||
                previousBoost != gameObject.isBoost()) {
            Image img = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    objectView.getImageView().setImage(img);
                    if(gameObject.isBoost()){
                        Effect boost = new ColorAdjust(50, 0, 0, 0);
                        objectView.getImageView().setEffect(boost);
                    }else {
                        Effect unboost = new ColorAdjust(0, 0, 0, 0);
                        objectView.getImageView().setEffect(unboost);
                    }
                }
            });
            previousState = gameObject.getState();
            previousAngle = gameObject.getAngle();
        }
    }
    /**
     * Anchor pane which contains game object image and progress bar
     * in order to show in GUI
     */
    class ObjectView extends AnchorPane {
        private ProgressBar progressBar = new ProgressBar();
        private ImageView imageView = new ImageView();
        private double maxHp;

        /**
         * constructor
         *
         * @param gameObject gameObject
         * @param image      image
         */
        public ObjectView(GameObject gameObject, Image image) {
            maxHp = gameObject.getHp();
            this.imageView.setImage(image);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    progressBar.progressProperty().bind(gameObject.hpProperty().divide(maxHp));
                }
            });
            progressBar.setPrefWidth(25);
            progressBar.setPrefHeight(5);

            progressBar.setLayoutX(15);
            if (gameObject.getTeamSide() == Side.TOP)
                progressBar.setStyle("-fx-accent: red;");
            else
                progressBar.setStyle("-fx-accent: blue;");

            getChildren().add(imageView);
            getChildren().add(progressBar);
        }

        //Getters and Setters
        public ProgressBar getProgressBar() {
            return progressBar;
        }

        public void setProgressBar(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

    }
}
