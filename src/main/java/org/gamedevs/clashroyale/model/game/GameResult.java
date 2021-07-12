package org.gamedevs.clashroyale.model.game;

import org.gamedevs.clashroyale.model.account.Account;

public class GameResult {
    private String gameType;
    private Account account;
    private Bot bot;
    private String winner;
    private int playerScore;
    private int BotScore;
//    private GameResult gameResult = null;

    public GameResult(String gameType, Account account, Bot bot, String winner, int playerScore, int botScore) {
        this.gameType = gameType;
        this.account = account;
        this.bot = bot;
        this.winner = winner;
        this.playerScore = playerScore;
        BotScore = botScore;
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

    public Account getAccount() {
        return account;
    }

    public Bot getBot() {
        return bot;
    }

    public String getWinner() {
        return winner;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getBotScore() {
        return BotScore;
    }

    //Setter

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setBotScore(int botScore) {
        BotScore = botScore;
    }
}
