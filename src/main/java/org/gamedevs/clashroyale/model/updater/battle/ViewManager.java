package org.gamedevs.clashroyale.model.updater.battle;

import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.MainTowers;
import org.gamedevs.clashroyale.model.game.droppable.objects.soldiers.Soldier;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.SoldierViewUpdater;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.ViewUpdater;

public class ViewManager {

    private final Side mySide;

    public ViewManager(Side mySide) {
        this.mySide = mySide;
    }

    public void addObjectToView(GameObject gameObject) {
        if (gameObject == null)
            return;
        if (gameObject.getTeamSide() == mySide) {
            gameObject.start();
            if (gameObject instanceof Soldier)
                new SoldierViewUpdater(gameObject, false).start();
        } else {
            gameObject.start();
            if (gameObject instanceof Soldier)
                new SoldierViewUpdater(gameObject, true).start();
        }
    }

    public void matchMainTowers(MainTowers mainTower, Side side, int kind) {
        // TODO: add related tower to related view!
    }

}

