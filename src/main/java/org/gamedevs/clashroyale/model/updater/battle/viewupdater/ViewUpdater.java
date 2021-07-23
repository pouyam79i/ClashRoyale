package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
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
public abstract class ViewUpdater {

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
    private int cntToShoot;

    /**
     * constructor
     * @param gameObject gameObject
     * @param isEnemy true if its enemies g.o.
     */
    public ViewUpdater(GameObject gameObject, boolean isEnemy) {
        imageContainer = GameDroppableImageContainer.getGameDroppableImageContainer();
        this.battleFieldPane = MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable();
        this.gameObject = gameObject;
        cardName = gameObject.getNameOfDroppable();
        this.isEnemy = isEnemy;
        previousTile = gameObject.getHeadPixel();
        previousState = gameObject.getState();
        previousAngle = gameObject.getAngle();

    }

    /**
     * put initial image in GUI
     */
    public abstract void placeInitImg() ;

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
//                    if (gameObject.isBoost()) {
//                        Effect boost = new ColorAdjust(50, 0, 0, 0);
//                        objectView.getImageView().setEffect(boost);
//                    } else {
//                        Effect unboost = new ColorAdjust(0, 0, 0, 0);
//                        objectView.getImageView().setEffect(unboost);
//                    }
                }
            });
            previousState = gameObject.getState();
            previousAngle = gameObject.getAngle();
        }
    }

    /**
     * check if G.O. is in the attack state throw bullet
     */
    public void throwBulletIfAttack() {
        if (gameObject.getState() == GameObjectState.ATTACK && gameObject.getLockedTarget() != null) {
            if (previousState != GameObjectState.ATTACK)
                cntToShoot = 0;
            else
                cntToShoot++;
            Console.getConsole().printTracingMessage(String.valueOf(cntToShoot));
            if (cntToShoot % (gameObject.getHitSpeed() * 10) == 0)
                new Bullet(gameObject).throwBullet(gameObject.getHeadTile(), gameObject.getLockedTarget().getHeadTile());
        }
    }

    /**
     * remove G.O. from battle field
     */
    public void remove() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().contains(objectView))
                    objectView.getChildren().remove(objectView.getImageView());
                    objectView.getChildren().remove(objectView.getProgressBar());
                    MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(objectView);
            }
        });
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

        public ObjectView() {
            progressBar = new ProgressBar();
            imageView = new ImageView();
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

        public double getMaxHp() {
            return maxHp;
        }

        public void setMaxHp(double maxHp) {
            this.maxHp = maxHp;
        }
    }
}
