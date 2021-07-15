package org.gamedevs.clashroyale.model.game.player;

/**
 * Side of player in battle field.
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public enum Side {

    EMPTY(-1),  // Game immortal blocks has no sides!

    TOP(0),     // Player playing in top side of map (near 0)
    DOWN(1);    // Player playing in down side of map (near last pixel)

    // Value of side
    private final int value;

    /**
     * @param value of side
     */
    Side(int value) {
        this.value = value;
    }

    /**
     * @return value of side
     */
    public int getValue(){
        return this.value;
    }

}
