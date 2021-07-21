package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.soldiers.Soldier;
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

    }

    /**
     * update game object view in GUI
     */
    @Override
    public void update() {

        updateExist();
        updateImg();
        updatePosition();

    }

    /**
     * update game object image position in GUI
     */
    public void updatePosition() {

        if (previousTile.getY() != gameObject.getHeadPixel().getY() ||
                previousTile.getX() != gameObject.getHeadPixel().getX()) {
            Runnable move = new Runnable() {
                @Override
                public void run() {
                    double curX = MouseTilePosition.TranslateTileToPixelX(previousTile.getX());
                    double curY = MouseTilePosition.TranslateTileToPixelY(previousTile.getY());
                    double destX = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
                    double destY = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
                    double sleepTime = (((Soldier) gameObject).getSpeed() * 100 - 1) / Math.max(destX - curX, destY - curY);
                    double deltaX;
                    double deltaY;
                    do {
                        deltaX = destX - curX;
                        deltaY = destY - curY;
                        if (deltaX != 0) {
                            curX = curX + (deltaX > 0 ? 1 : -1);
                        }
                        if (deltaY != 0) {
                            curY = curY + Math.abs(deltaY / deltaX) * (deltaY > 0 ? 1 : -1);
                        }
                        double finalCurY = curY;
                        double finalCurX = curX;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                objectView.setLayoutX(finalCurX - gameObject.getErrorInGUIX());
                                objectView.setLayoutY(finalCurY - gameObject.getErrorInGUIY());
                            }
                        });
                        try {
                            Thread.sleep((long) sleepTime);
                        } catch (InterruptedException e) {
                        }
                    } while (deltaX != 0 || deltaY != 0);
                    previousTile = gameObject.getHeadTile();
                    Thread.interrupted();
                }

            };
            service.execute(move);
        }
    }


    /**
     * check if this G.O. hp is zero or less remove it
     */
    public void updateExist() {
        if (gameObject.getHp() <= 0) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    battleFieldPane.getChildren().remove(objectView);

                }
            });
        }

    }
}
