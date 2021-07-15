package org.gamedevs.clashroyale.model.game.battle.engine;

/**
 * This class contains game types!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public enum GameType {

    SINGLE_OFFLINE("Single training"),
    DOUBLE_OFFLINE("Double training"),
    SINGLE_ONLINE("Official math"),
    DOUBLE_ONLINE("Double Online match");

    /**
     * name of game type
     */
    private final String name;

    /**
     * Setting name of game type
     * @param name of game type
     */
    GameType(String name) {
        this.name = name;
    }

    // Getters
    public String getName() {
        return name;
    }

}
