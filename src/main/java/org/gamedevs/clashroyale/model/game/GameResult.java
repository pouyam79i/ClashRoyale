package org.gamedevs.clashroyale.model.game;

import org.gamedevs.clashroyale.model.account.Account;

/**
 * A class to store game result
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class GameResult {

    /**
     * first player in this game
     */
    private String player1Name;

    /**
     * second player in this game
     */
    private String player2Name;

    /**
     * winner name
     */
    private String winner;

    /**
     * constructor
     * @param gameType gameType
     * @param player1Name player1Name
     * @param player2Name player2Name
     * @param winner winner name
     */
    public GameResult(String gameType, String player1Name, String player2Name, String winner) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.winner = winner;
    }


    //Getter and setters
    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
