package org.gamedevs.clashroyale.model.game;

import org.gamedevs.clashroyale.model.account.Account;

/**
 * A class to store game result
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class GameResult {

    /**
     * type of game (easy/hard bot - human)
     */
    private String gameType;

    /**
     * human player in this game
     */
//    private Human human;

    /**
     * bot player in this game
     */
//    private Bot bot;

    /**
     * winner name
     */
    private String winner;

//    private GameResult gameResult = null;

    public GameResult(String gameType/*, Human human, Bot bot*/, String winner, int playerScore, int botScore) {
        this.gameType = gameType;
//        this.human = human;
//        this.bot = bot;
        this.winner = winner;

    }

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

//    public Bot getBot() {
//        return bot;
//    }

    public String getWinner() {
        return winner;
    }


    //Setter

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

//
//    public void setBot(Bot bot) {
//        this.bot = bot;
//    }


//    public Human getHuman() {
//        return human;
//    }
//
//    public void setHuman(Human human) {
//        this.human = human;
//    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

}
