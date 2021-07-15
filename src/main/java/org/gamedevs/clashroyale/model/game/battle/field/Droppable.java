package org.gamedevs.clashroyale.model.game.battle.field;

import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public abstract class Droppable extends Runnable {

    private final DropType dropType;

    protected Droppable(DropType dropType){
        this.dropType = dropType;
    }

}
