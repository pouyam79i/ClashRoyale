package org.gamedevs.clashroyale.model.game.droppable.spell;

import org.gamedevs.clashroyale.model.game.droppable.DropType;
import org.gamedevs.clashroyale.model.game.droppable.Droppable;
import org.gamedevs.clashroyale.model.game.player.Side;

public abstract class Spell extends Droppable {

    protected Spell(Side side) {
        super(DropType.SPELL, side);
    }

    @Override
    public void run(){
        effect();
    }

    protected abstract void effect();

}
