package org.gamedevs.clashroyale.model.game.battle.field;

/**
 *  This enum contains possible angles
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public enum Angle {

    STEP(45),           // Amount of Step needed to go to next or previous angle is '45' degree

    // Possible angles in game
    NORTH(0),
    NORTH_EAST(45),
    EAST(90),
    SOUTH_EAST(135),
    SOUTH(180),
    SOUTH_WEST(225),
    WEST(270),
    NORTH_WEST(315);

    /**
     * @param angle of enumerated type
     */
    Angle(int angle) {}

    /**
     * @param angle of direction (degree)
     * @return related angel!
     */
    public static Angle getAngle(int angle){
        if(angle >= 360){
            angle = angle % 360;
        }
        if(angle < 0){
            while (angle < 0)
                angle += 360;
        }
        return switch (angle) {
            case 0 -> Angle.NORTH;
            case 45 -> Angle.NORTH_EAST;
            case 90 -> Angle.EAST;
            case 135 -> Angle.SOUTH_EAST;
            case 180 -> Angle.SOUTH;
            case 225 -> Angle.SOUTH_WEST;
            case 270 -> Angle.WEST;
            case 315 -> Angle.NORTH_WEST;
            default -> null;
        };
    }

}
