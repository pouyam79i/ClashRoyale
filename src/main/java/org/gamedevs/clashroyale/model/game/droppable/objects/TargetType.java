package org.gamedevs.clashroyale.model.game.droppable.objects;

/**
 * Type of Target!
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public enum TargetType {

    // Immortal object cannot die!
    IMMORTAL_OBJECT(-1),

    // Place related
    GROUND(0),
    AIR_GROUND(1),
    AIR(2),

    // Object Type related
    BUILDING(3);

    // Value of target type
    private final int value;

    /**
     * @param value of target type
     */
    TargetType(int value) {
        this.value = value;
    }

    /**
     * @return value of target type
     */
    public int getValue() {
        return value;
    }

}
