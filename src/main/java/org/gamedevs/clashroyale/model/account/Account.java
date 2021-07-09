package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.deck.DeckContainer;

import java.io.Serializable;

/**
 * This class contains information a user!
 * @author Pouya Mohammadi - CE@AUT Uni ID:9829039
 * @version 1.0
 */
public class Account implements Serializable {

    /**
     * Username
     */
    private String username;
    /**
     * Password of account
     */
    private String password;
    /**
     * Current deck of user
     */
    private DeckContainer deckContainer;
    /**
     * total XP of user
     */
    private int totalXP;

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
        deckContainer = new DeckContainer();
        totalXP = 0;
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
    public int getTotalXP() {
        return totalXP;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setDeckContainer(DeckContainer deckContainer) {
        this.deckContainer = deckContainer;
    }

}
