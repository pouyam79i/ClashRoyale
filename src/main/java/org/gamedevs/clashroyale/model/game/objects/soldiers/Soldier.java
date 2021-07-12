package org.gamedevs.clashroyale.model.game.objects.soldiers;

import org.gamedevs.clashroyale.model.game.objects.GameObject;

public abstract class Soldier extends GameObject {

    /**
     * Speed of player (related to movement)
     */
    protected int speed;
    /**
     * Area splash ability (attack point or effect)
     */
    protected boolean areaSplash;

    /**
     * Setting default values for soldier object
     */
    protected Soldier(){

    }

    /**
     * Thread of object (applies algorithm of game object)
     */
    @Override
    public void run() {

    }

    /**
     * Updates object to next place
     */
    protected void move(){

    }

    /**
     * Finds shortest path to the nearest target
     */
    protected void findTarget(){

    }

}
