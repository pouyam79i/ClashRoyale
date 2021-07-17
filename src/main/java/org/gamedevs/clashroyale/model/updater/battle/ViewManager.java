package org.gamedevs.clashroyale.model.updater.battle;

import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Side;

public class ViewManager {

    private final Side mySide;

    public ViewManager(Side mySide) {
        this.mySide = mySide;
    }

    public void addObjectToView(GameObject gameObject){
        if(gameObject == null)
            return;
        if(gameObject.getTeamSide() == mySide)
            new ViewUpdater(gameObject, false).start();
        else
            new ViewUpdater(gameObject, true).start();
    }

}

