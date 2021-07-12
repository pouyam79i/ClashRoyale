package org.gamedevs.clashroyale.model.game.objects;

/**
 * Type of Target!
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public enum TargetType {

    // Place related
    GROUND(0),
    AIR_GROUND(1),
    AIR(2),

    // Object Type related
    BUILDING(3);

    /**
     * @param value of target type
     */
    TargetType(int value) {}

}
