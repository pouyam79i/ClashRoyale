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

    public ViewUpdater addObjectToView(GameObject gameObject) {
        if (gameObject == null)
            return null;
        if (gameObject.getTeamSide() == mySide) {
            if (gameObject instanceof Soldier) {
                ViewUpdater vu= new SoldierViewUpdater(gameObject, false);
                vu.start();
                return vu;
            }
            else {
                ViewUpdater vu= new BuildingViewUpdater(gameObject, false);
                vu.start();
                return vu;
            }

        } else {
            if (gameObject instanceof Soldier){
                ViewUpdater vu= new SoldierViewUpdater(gameObject, true);
                vu.start();
                return vu;
            }
            else {
                ViewUpdater vu= new BuildingViewUpdater(gameObject, true);
                vu.start();
                return vu;
            }

        }
    }

    public void matchMainTowers(MainTowers mainTower, Side side, int kind) {
        // TODO: add related tower to related view!
    }

    @Override
    public void run() {

    }
}

