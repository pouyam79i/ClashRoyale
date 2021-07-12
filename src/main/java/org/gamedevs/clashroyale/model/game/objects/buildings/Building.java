package org.gamedevs.clashroyale.model.game.objects.buildings;

import org.gamedevs.clashroyale.model.game.objects.GameObject;

public abstract class Building extends GameObject {

    /**
     * Life time of building.
     * Set a std  value for infinite life time (used for king and princesses towers)
     */
    protected int lifeTime;

    /**
     * Setting default values for building object
     */
    protected Building(){

    }

    /**
     * Thread of object (applies algorithm of game object)
     */
    @Override
    public void run() {

    }

    /**
     * Updates hp with life time or vise versa
     */
    protected void updateLifeTime(){

    }

}
