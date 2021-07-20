package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public class BuildingViewUpdater extends ViewUpdater {
    public BuildingViewUpdater(GameObject gameObject, boolean isEnemy) {
        super(gameObject, isEnemy);
    }

    @Override
    public void update() {
        Thread update = new Thread() {
            @Override
            public void start() {
                while (true) {
                    updateImg();
                }
            }
        };
        update.start();

    }

    /**
     * update image of game object regarding to its state and angle
     */
    private void updateImg() {
        if (previousAngle != gameObject.getAngle()) {
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
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
