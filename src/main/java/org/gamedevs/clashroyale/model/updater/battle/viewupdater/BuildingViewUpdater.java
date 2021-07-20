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

}
