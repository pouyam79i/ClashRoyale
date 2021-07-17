package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public abstract class Building extends GameObject {

    /**
     * Life time of building.
     * Set a std  value for infinite life time (used for king and princesses towers)
     */
    protected int lifeTime;

    /**
     * If life time matchers for building,
     */
    protected boolean effectiveLifeTime;

    /**
     * Setting default values for building object
     */
    protected Building(Side side){
        super(side);
        myType = TargetType.BUILDING;
    }

    /**
     * Thread of object (applies algorithm of game object)
     */
    @Override
    public void run() {
        while (hp > 0){
            checkTargetRange();
        }
    }

    /**
     * Updates hp with life time or vise versa
     */
    protected void updateLifeTime(){

    }

}
