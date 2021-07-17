package org.gamedevs.clashroyale.model.game.droppable;

/**
 *
 */
public enum DropType {

    OBJECT(0),
    SPELL(1);

    /**
     * Value of drop
     */
    private final int value;

    /**
     * @param value of drop
     */
    DropType(int value) {
        this.value = value;
    }

    /**
     * @return value if drop type
     */
    public int getValue() {
        return value;
    }

}
