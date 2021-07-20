package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;
/**
 * a class which handle view of soldier in GUI
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
public class SoldierViewUpdater extends ViewUpdater {
    public SoldierViewUpdater(GameObject gameObject, boolean isEnemy) {
        super(gameObject, isEnemy);

    }

    /**
     * update image of game object regarding to its state and angle
     */
    private void updateImg() {

        if (previousAngle != gameObject.getAngle() ||
                previousState != gameObject.getState()) {
            Image img = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    objectView.getImageView().setImage(img);
                }
            });
            previousState = gameObject.getState();
            previousAngle = gameObject.getAngle();
        }
    }

    /**
     * update game object view in GUI
     */
    @Override
    public void update() {
        Thread update = new Thread() {
            @Override
            public void start() {
                while (true) {
                    updateImg();
                    updatePosition();
                }
            }
        };
        update.start();
    }

    /**
     * update game object image position in GUI
     */
    public void updatePosition() {
        double curX = MouseTilePosition.TranslateTileToPixelX(previousTile.getX());
        double curY = MouseTilePosition.TranslateTileToPixelY(previousTile.getY());
        while (previousTile.getY() != gameObject.getHeadPixel().getY() ||
                previousTile.getX() != gameObject.getHeadPixel().getX()) {
            updateImg();
            double destX = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
            double destY = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
            double deltaX = destX - curX;
            double deltaY = destY - curY;
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
            previousTile = new Tile(MouseTilePosition.TranslatePixelToTileX(curX), MouseTilePosition.TranslatePixelToTileY(curY));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
