package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.Building;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BuildingViewUpdater extends ViewUpdater {
    public BuildingViewUpdater(GameObject gameObject, boolean isEnemy) {
        super(gameObject, isEnemy);
    }

    @Override
    public void update() {
        final boolean[] running = {true};
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        battleFieldPane.getChildren().remove(objectView);
                    }
                });
                running[0] = false;
                timer.cancel();
            }
        };

        timer.schedule(task, ((Building) gameObject).getLifeTime() * 1000L);

        Thread update = new Thread() {
            @Override
            public void start() {
                while (running[0]) {
                    updateImg();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                timer.cancel();
            }
        };
        update.start();

    }

}
