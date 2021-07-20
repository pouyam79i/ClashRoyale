package org.gamedevs.clashroyale.model.updater.battle;

import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.MainTowers;
import org.gamedevs.clashroyale.model.game.droppable.objects.soldiers.Soldier;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.BuildingViewUpdater;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.SoldierViewUpdater;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.ViewUpdater;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.util.ArrayList;

public class ViewManager extends Runnable {

    private final Side mySide;
    private ArrayList<ViewUpdater> viewUpdaters = new ArrayList<>();
    public ViewManager(Side mySide) {
        this.mySide = mySide;
    }

    public void addObjectToView(GameObject gameObject) {
        if (gameObject == null)
            return;
        if (gameObject.getTeamSide() == mySide) {
            if (gameObject instanceof Soldier) {
                new SoldierViewUpdater(gameObject, false).start();
            }
            else {
                new BuildingViewUpdater(gameObject, false).start();
            }

        } else {
            if (gameObject instanceof Soldier)
                new SoldierViewUpdater(gameObject, true).start();
            else
                new BuildingViewUpdater(gameObject, false).start();

        }
    }

    public void matchMainTowers(MainTowers mainTower, Side side, int kind) {
        // TODO: add related tower to related view!
    }

    @Override
    public void run() {

    }
}

