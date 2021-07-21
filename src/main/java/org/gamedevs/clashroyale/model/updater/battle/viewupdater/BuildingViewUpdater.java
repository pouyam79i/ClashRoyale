package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.Building;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * a class which handle view of buildings in GUI
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
public class BuildingViewUpdater extends ViewUpdater {
    private long startTime = System.currentTimeMillis();


    public BuildingViewUpdater(GameObject gameObject, boolean isEnemy) {
        super(gameObject, isEnemy);
    }

    /**
     * update GUI
     */
    @Override
    public void update() {

        updateImg();
        updateExist();

    }

    public void updateExist() {
        if (gameObject.getHp() <= 0 || System.currentTimeMillis() - startTime >= ((Building)gameObject).getLifeTime() * 1000)
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(objectView);
                }
            });
    }
}