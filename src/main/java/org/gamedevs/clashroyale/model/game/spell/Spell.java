package org.gamedevs.clashroyale.model.game.spell;

import org.gamedevs.clashroyale.model.game.battle.field.DropType;
import org.gamedevs.clashroyale.model.game.battle.field.Droppable;
import org.gamedevs.clashroyale.model.game.player.Side;

public abstract class Spell extends Droppable {

    private final Side side;

    protected Spell(Side side) {
        super(DropType.SPELL);
        this.side = side;
    }

    @Override
    public void run(){

    }

}
