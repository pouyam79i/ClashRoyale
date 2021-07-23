package org.gamedevs.clashroyale.model.game.battle.tools;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.container.gamedata.GameResultContainer;
import org.gamedevs.clashroyale.model.game.battle.engine.GameType;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.console.ConsoleColor;

/**
 * This class contains game result!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.1
 */
public class GameResult {

    /**
     * type of game
     */
    private final GameType gameType;
    /**
     * first player in this game
     */
    private final String topPlayerName;
    private final SimpleIntegerProperty topPlayerScore;
    /**
     * second player in this game
     */
    private final String downPlayerName;
    private final SimpleIntegerProperty downPlayerScore;
    /**
     * Side of winner
     */
    private Side winnerSide;
    /**
     * result container
     */
    private GameResultContainer resultContainer;

    /**
     * constructor
     * @param gameType type of game
     * @param topPlayerName topPlayerName
     * @param downPlayerName downPlayerName
     */
    public GameResult(GameType gameType, String topPlayerName, String downPlayerName) {
        this.gameType = gameType;
        this.topPlayerName = topPlayerName;
        this.downPlayerName = downPlayerName;
        topPlayerScore = new SimpleIntegerProperty();
        topPlayerScore.setValue(0);
        downPlayerScore = new SimpleIntegerProperty();
        downPlayerScore.setValue(0);
        winnerSide = null;
    }

    /**
     * adds one score to player
     * @param side of player
     */
    public void addScore(Side side){
        if(side == Side.TOP){
            try{
                Platform.runLater(() -> topPlayerScore.setValue(topPlayerScore.add(1).getValue()));
            }catch (Exception e){
                topPlayerScore.setValue(topPlayerScore.add(1).getValue());
                if(MainConfig.DEBUG_MODE){
                    e.printStackTrace();
                }
            }
            if(topPlayerScore.getValue() == 3){
                lock();
            }
        }
        else if(side == Side.DOWN){
            try{
                Platform.runLater(() -> downPlayerScore.setValue(downPlayerScore.add(1).getValue()));
            }catch (Exception e){
                downPlayerScore.setValue(downPlayerScore.add(1).getValue());
                if(MainConfig.DEBUG_MODE){
                    e.printStackTrace();
                }
            }
            if(downPlayerScore.getValue() == 3){
                lock();
            }
        }
        else {
            Console.getConsole().printTracingMessage(ConsoleColor.RED_BOLD + "Failed to detect side");
        }
    }

    /**
     * sets full score to a player
     * @param side of player
     */
    public void setFullScore(Side side){
        if(side == null)
            return;
        if(side == Side.TOP){
            try{
                Platform.runLater(() -> {
                    topPlayerScore.setValue(3);
                });
            }catch (Exception e){
                topPlayerScore.setValue(3);
                if(MainConfig.DEBUG_MODE){
                    e.printStackTrace();
                }
            }
            lock();
        }
        else{
            try{
                Platform.runLater(() -> {
                    downPlayerScore.setValue(3);
                });
            }catch (Exception e){
                downPlayerScore.setValue(3);
                if(MainConfig.DEBUG_MODE){
                    e.printStackTrace();
                }
            }
            lock();
        }
    }

    /**
     * @return true if some one has reached full score!
     */
    public boolean checkWinner(){
        if(topPlayerScore.getValue() == 3 || downPlayerScore.getValue() == 3){
            Console.getConsole().printTracingMessage(ConsoleColor.RED_BOLD + "Winner detected!");
            lock();
            return true;
        }
        return false;
    }

    /**
     * Locks the result and sets winner
     */
    private void lock(){
        if(topPlayerScore.getValue() > downPlayerScore.getValue())
            winnerSide = Side.TOP;
        else
            winnerSide = Side.DOWN;
        resultContainer = new GameResultContainer(
                topPlayerName, topPlayerScore.getValue(),
                downPlayerName, downPlayerScore.getValue()
        );
        Console.getConsole().printTracingMessage(
                "result container top: " + topPlayerScore.getValue() + " down: " + downPlayerScore.getValue()
        );
    }

    // Getters
    public String getTopPlayerName() {
        return topPlayerName;
    }
    public int getTopPlayerScore() {
        return topPlayerScore.get();
    }
    public SimpleIntegerProperty topPlayerScoreProperty() {
        return topPlayerScore;
    }
    public String getDownPlayerName() {
        return downPlayerName;
    }
    public int getDownPlayerScore() {
        return downPlayerScore.get();
    }
    public SimpleIntegerProperty downPlayerScoreProperty() {
        return downPlayerScore;
    }
    public Side getWinnerSide() {
        return winnerSide;
    }
    public GameType getGameType() {
        return gameType;
    }
    public GameResultContainer getResultContainer() {
        return resultContainer;
    }

}
