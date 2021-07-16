package org.gamedevs.clashroyale.model.updater.battle;

import org.gamedevs.clashroyale.model.game.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Side;

public class ViewManager {

    private final Side mySide;

    public ViewManager(Side mySide) {
        this.mySide = mySide;
    }

    public void addObjectToView(GameObject gameObject){
        if(gameObject == null)
            return;
        if(gameObject.getObjectTeamSide() == mySide)
            new ViewUpdater(gameObject, false).start();
        else
            new ViewUpdater(gameObject, true).start();
    }

}

