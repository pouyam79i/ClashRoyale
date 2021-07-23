package org.gamedevs.clashroyale.model.updater.battle;

import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.MainTowers;
import org.gamedevs.clashroyale.model.game.droppable.objects.soldiers.Soldier;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.BuildingViewUpdater;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.SoldierViewUpdater;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.TowerViewUpdater;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.ViewUpdater;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.util.ArrayList;
/**
 * a class which handle view of game object in GUI
 *
 * @version 1.0
 */
public class ViewManager {

    private final Side mySide;
    private ArrayList<ViewUpdater> viewUpdaters = new ArrayList<>();
    public ViewManager(Side mySide) {
        this.mySide = mySide;
    }

    /**
     * make a new view updater regarding to g.o.
     * @param gameObject gameObject
     * @param kind kind of tower
     * @return view updater
     */
    public ViewUpdater addObjectToView(GameObject gameObject, int kind) {
        if (gameObject == null)
            return null;
        if (gameObject.getTeamSide() == mySide) {
            if(gameObject instanceof MainTowers){
                ViewUpdater vu= new TowerViewUpdater(gameObject, false, kind);
                return vu;
            }
            else if (gameObject instanceof Soldier) {
                ViewUpdater vu= new SoldierViewUpdater(gameObject, false);
                return vu;
            }
            else {
                ViewUpdater vu= new BuildingViewUpdater(gameObject, false);
                return vu;
            }

        } else {
            if(gameObject instanceof MainTowers){
                ViewUpdater vu= new TowerViewUpdater(gameObject, true, kind);
                return vu;
            }
            else if (gameObject instanceof Soldier){
                ViewUpdater vu= new SoldierViewUpdater(gameObject, true);
                return vu;
            }
            else {
                ViewUpdater vu= new BuildingViewUpdater(gameObject, true);
                return vu;
            }

        }
    }

}

