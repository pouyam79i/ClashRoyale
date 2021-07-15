package org.gamedevs.clashroyale.model.game.spell;

import org.gamedevs.clashroyale.model.game.battle.field.DropType;
import org.gamedevs.clashroyale.model.game.battle.field.Droppable;

public abstract class Spell extends Droppable {

    protected Spell() {
        super(DropType.SPELL);
    }

}
