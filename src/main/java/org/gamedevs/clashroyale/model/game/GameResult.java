package org.gamedevs.clashroyale.model.game;

import org.gamedevs.clashroyale.model.account.Account;

/**
 * A class to store game result
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class GameResult {

    /**
     * type of game (easy/hard bot - human) (human - human) (group - group)
     */
    private String gameType;

//    /**
//     * first player in this game
//     */
//    private Player player1;
//
//    /**
//     * second player in this game
//     */
//    private Player player2;

    /**
     * winner name
     */
    private String winner;

//    private GameResult gameResult = null;

//    public GameResult(String gameType, Player player1, Player player2, String winner) {
//        this.gameType = gameType;
//        this.player1 = player1;
//        this.player2 = player2;
//        this.winner = winner;
//    }


//    public GameResult getGameResult(){
//        if(gameResult == null)
//            gameResult = new GameResult();
//        return gameResult;
//    }
//


    //Getter
    public String getGameType() {
        return gameType;
    }

//    public Player getPlayer1() {
//        return player1;
//    }
//
//    public Player getPlayer2() {
//        return player2;
//    }

    public String getWinner() {
        return winner;
    }


    //Setter

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

//    public void setPlayer1(Player player1) {
//        this.player1 = player1;
//    }
//
//    public void setPlayer2(Player player2) {
//        this.player2 = player2;
//    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
