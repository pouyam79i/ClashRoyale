package org.gamedevs.clashroyale.model.game.droppable;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * This class contains the structure of all droppable types!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public abstract class Droppable extends Runnable {

    /**
     * Type of drop!
     * 'Object' or 'Spell'.
     */
    private final DropType dropType;
    /**
     * team side of droppable
     */
    protected final Side teamSide;
    /**
     * card name of droppable
     */
    protected CardName nameOfDroppable;
    /**
     * BattleField
     */
    protected Map battleField;

    /**
     * Constructor of Droppable.
     * Sets default of droppable.
     * @param dropType type of drop
     * @param side side of drop
     */
    protected Droppable(DropType dropType, Side side){
        this.dropType = dropType;
        this.teamSide = side;
    }

    public CardName getNameOfDroppable() {
        return nameOfDroppable;
    }

    // Getters
    public Side getTeamSide() {
        return teamSide;
    }
    public DropType getDropType() {
        return dropType;
    }

    // Setters
    public void setBattleField(Map battleField) {
        this.battleField = battleField;
    }

}
