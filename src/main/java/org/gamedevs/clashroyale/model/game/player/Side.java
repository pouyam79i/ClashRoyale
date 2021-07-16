package org.gamedevs.clashroyale.model.game.player;

/**
 * Side of player in battle field.
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.1
 */
public enum Side {

    EMPTY(0),  // Game immortal blocks has no sides!

    TOP(1),     // Player playing in top side of map (near 0)
    DOWN(-1);    // Player playing in down side of map (near last pixel)

    // Value of side
    private final int value;

    /**
     * @param value of side
     */
    Side(int value) {
        this.value = value;
    }

    /**
     * @param currentSide current side
     * @return opposite side of current side
     */
    public static Side getOppositeSide(Side currentSide){
        if(currentSide == TOP)
            return DOWN;
        else if(currentSide == DOWN)
            return TOP;
        else
            return EMPTY;
    }

    /**
     * @param value of side
     * @return a side according to value
     */
    public static Side getSideByValue(int value){
        if(value == 1)
            return TOP;
        else if(value == -1)
            return DOWN;
        else
            return EMPTY;
    }

    /**
     * @return value of side
     */
    public int getValue(){
        return this.value;
    }

}
