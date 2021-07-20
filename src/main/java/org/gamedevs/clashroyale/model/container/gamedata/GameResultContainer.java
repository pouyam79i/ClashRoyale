package org.gamedevs.clashroyale.model.container.gamedata;

import java.io.Serializable;

/**
 * This class contains the game results!
 * @author Pouya Mohammadi - CE@AUT Uni ID:9829039
 * @version 1.0
 */
public class GameResultContainer implements Serializable {

    /**
     * first player in this game
     */
    private final String topPlayerName;
    private final int topPlayerScore;
    /**
     * second player in this game
     */
    private final String downPlayerName;
    private final int downPlayerScore;

    /**
     * Sets required fields
     * @param topPlayerName name of top player
     * @param topPlayerScore score of top player
     * @param downPlayerName name of down player
     * @param downPlayerScore score of down player
     */
    public GameResultContainer(String topPlayerName, int topPlayerScore, String downPlayerName, int downPlayerScore) {
        this.topPlayerName = topPlayerName;
        this.topPlayerScore = topPlayerScore;
        this.downPlayerName = downPlayerName;
        this.downPlayerScore = downPlayerScore;
    }

    // Getters
    public String getTopPlayerName() {
        return topPlayerName;
    }
    public int getTopPlayerScore() {
        return topPlayerScore;
    }
    public String getDownPlayerName() {
        return downPlayerName;
    }
    public int getDownPlayerScore() {
        return downPlayerScore;
    }

}

