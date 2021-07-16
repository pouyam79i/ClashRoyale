package org.gamedevs.clashroyale.model.game.battle.field;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public abstract class Droppable extends Runnable {

    protected CardName nameOfDroppable;
    private final DropType dropType;

    protected Droppable(DropType dropType){
        nameOfDroppable = CardName.EMPTY;
        this.dropType = dropType;
    }

    public CardName getNameOfDroppable() {
        return nameOfDroppable;
    }

}
