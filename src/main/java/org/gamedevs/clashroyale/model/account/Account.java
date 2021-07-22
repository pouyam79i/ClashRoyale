package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.container.gamedata.GameResultContainer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains information a user!
 * @author Pouya Mohammadi - CE@AUT Uni ID:9829039
 * @version 1.1
 */
public class Account implements Serializable {

    // Main account info:
    /**
     * Username
     */
    private final String username;
    /**
     * Password of account
     */
    private final String password;
    // Deck container:
    /**
     * Current deck of user
     */
    private DeckContainer deckContainer;
    /**
     * Current available deck of user
     */
    private DeckContainer deckAvailable;
    // XP and leve property:
    /**
     * total XP of user
     */
    private int totalXP;
    /**
     * xp needed to go to next level.
     */
    private int levelUpXP;
    /**
     * current xp (xp of this level)
     */
    private int currentLevelXP;
    // Other:
    private final ArrayList<GameResultContainer> previousGames;

    /**
     * Builds an account.
     * Sets its requirements.
     * @param username username
     * @param password password of account
     * @throws Exception will be thrown of it failed to build account
     */
    public Account(String username, String password) throws Exception {
        if(password == null || username == null)
            throw new Exception("Null username or password");
        if(username.replace(" ", "").equals(""))
            throw new Exception("Empty username");
        if(password.replace(" ", "").equals(""))
            throw new Exception("Empty password");
        this.username = username;
        this.password = password;
        previousGames = new ArrayList<GameResultContainer>();
        deckContainer = new DeckContainer();        // Setting new player deck as an empty deck!
        totalXP = 0;
    }

    /**
     * @return level of player according to total xp
     */
    public int getLevel(){
        if(totalXP < 1000){
            currentLevelXP = totalXP;
            levelUpXP = 1000;
            return 1;
        }
        else if(totalXP < 6000){
            currentLevelXP = totalXP - 1000;
            levelUpXP = 5000;
            return 2;
        }
        else if(totalXP < 16000){
            currentLevelXP = totalXP -  6000;
            levelUpXP = 10000;
            return 3;
        }
        else if(totalXP < 36000){
            currentLevelXP = totalXP -  16000;
            levelUpXP = 20000;
            return 4;
        }
        else if(totalXP < 66000){
            currentLevelXP = totalXP -  36000;
            levelUpXP = 30000;
            return 5;
        }
        else{
            currentLevelXP = totalXP - 66000;
            levelUpXP = 1;
            return 6;
        }
    }

    /**
     * a new game result to the collection
     * @param newGameResult will be added
     */
    public void addGameResult(GameResultContainer newGameResult){
        // TODO: optimize this collection
        if(newGameResult == null)
            return;
        previousGames.add(newGameResult);
    }

    /**
     * Increase xp
     * @param additionalXP will be added to current xp
     */
    public void increaseXP(int additionalXP){
        totalXP += additionalXP;
    }

    // Getters
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public DeckContainer getDeckContainer() {
        return deckContainer;
    }
    public DeckContainer getDeckAvailable() {
        return deckAvailable;
    }
    public int getTotalXP() {
        return totalXP;
    }
    public int getLevelUpXP() {
        return levelUpXP;
    }
    public int getCurrentLevelXP() {
        return currentLevelXP;
    }
    public ArrayList<GameResultContainer> getPreviousGames() {
        return previousGames;
    }

    // Setters
    public void setDeckContainer(DeckContainer deckContainer) {
        this.deckContainer = deckContainer;
    }
    public void setDeckAvailable(DeckContainer deckAvailable) {
        this.deckAvailable = deckAvailable;
    }

}
