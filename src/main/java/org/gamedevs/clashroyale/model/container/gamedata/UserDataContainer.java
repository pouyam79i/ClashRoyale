package org.gamedevs.clashroyale.model.container.gamedata;

import org.gamedevs.clashroyale.model.account.Account;

/**
 * This class holds user data information!
 * @author Pouya Mohammadi - CE@AUT Uni ID:9829039
 * @version 1.0
 */
public class UserDataContainer {

    /**
     * Only instance of this class,
     * singleton pattern.
     */
    private static UserDataContainer instance = null;

    /**
     * Account container
     */
    private Account account;

    /**
     * Constructor of UserDataContainer
     */
    private UserDataContainer(){
        account = null;
    }

    // Getters
    public Account getAccount() {
        return account;
    }

    // Setters
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return UserDataContainer obj
     */
    public static UserDataContainer getInstance() {
        if(instance == null)
            return instance;
        return instance;
    }

}
