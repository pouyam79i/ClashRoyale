package org.gamedevs.clashroyale.model.game.objects;

/**
 * State of game objects.
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public enum GameObjectState {

    IDLE(0),       // Doing nothing
    MOVING(1),     // Moving state
    ATTACK(2);     // Attacking state (not moving!)

    /**
     * @param value of game state
     */
    GameObjectState(int value) {}

    /**
     * Returns related value of state
     * @param value of state
     * @return related state fo value
     */
    public static GameObjectState getState(int value){
        return switch (value) {
            case 0 -> GameObjectState.IDLE;
            case 1 -> GameObjectState.MOVING;
            case 2 -> GameObjectState.ATTACK;
            default -> null;
        };
    }

}
