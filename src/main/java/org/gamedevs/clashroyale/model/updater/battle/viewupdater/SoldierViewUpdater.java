package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.soldiers.Soldier;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * a class which handle view of soldier in GUI
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
public class SoldierViewUpdater extends ViewUpdater {
    ExecutorService service = Executors.newSingleThreadExecutor();


    public SoldierViewUpdater(GameObject gameObject, boolean isEnemy) {
        super(gameObject, isEnemy);
        placeInitImg();


    }

    @Override
    public void placeInitImg() {
        Image currentImage = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
        objectView = new ObjectView(gameObject, currentImage);
        objectView.getImageView().setFitWidth(currentImage.getWidth() / 2.5);
        objectView.getImageView().setFitHeight(currentImage.getHeight() / 2.5);
        int x = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
        int y = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            objectView.setLayoutX(x - gameObject.getErrorInGUIX());
            objectView.setLayoutY(y - gameObject.getErrorInGUIY());
        });
    }

    /**
     * update game object view in GUI
     */
    @Override
    public void update() {

        updateExist();
        updateImg();
        throwBulletIfAttack();
        updatePosition();
        updateExist();


    }

    /**
     * update game object image position in GUI
     */
    public void updatePosition() {

        if (previousTile.getY() != gameObject.getHeadPixel().getY() ||
                previousTile.getX() != gameObject.getHeadPixel().getX()) {

            double destX = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
            double destY = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
            double curX = MouseTilePosition.TranslateTileToPixelX(previousTile.getX());
            double curY = MouseTilePosition.TranslateTileToPixelY(previousTile.getY());

//            Path path = new Path();
//
//            MoveTo moveTo = new MoveTo(curX  - gameObject.getErrorInGUIX(), curY- gameObject.getErrorInGUIY());
//            LineTo lineTo = new LineTo(destX - gameObject.getErrorInGUIX(), destY - gameObject.getErrorInGUIY());
//
//            path.getElements().add(moveTo);
//            path.getElements().add(lineTo);
//
//            //Creating a path transition
//            PathTransition pathTransition = new PathTransition();
//            pathTransition.setDuration(Duration.millis(100));
//            pathTransition.setNode(objectView);
//            pathTransition.setPath(path);
//            pathTransition.setCycleCount(1);
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    pathTransition.play();
//                }
//            });
//
            if (objectView != null) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        objectView.setLayoutX(destX - gameObject.getErrorInGUIX());
                        objectView.setLayoutY(destY - gameObject.getErrorInGUIY());
                    }
                });
                previousTile = gameObject.getHeadTile();
            }
        }
    }


    /**
     * check if this G.O. hp is zero or less remove it
     */
    public void updateExist() {
        if (gameObject.getHp() <= 0) {

//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    objectView.getChildren().remove(objectView.getProgressBar());
//                    objectView.getChildren().remove(objectView.getImageView());
//                    battleFieldPane.getChildren().remove(objectView);
//                }
//            });
            remove();
        }


        }
}
