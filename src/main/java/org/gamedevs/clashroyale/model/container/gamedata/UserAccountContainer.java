package org.gamedevs.clashroyale.model.container.gamedata;

import org.gamedevs.clashroyale.model.account.Account;

/**
 * This class holds user data information!
 * @author Pouya Mohammadi - CE@AUT Uni ID:9829039
 * @version 1.0
 */
public class UserAccountContainer {

    /**
     * Only instance of this class,
     * singleton pattern.
     */
    private static UserAccountContainer userAccountContainer = null;

    /**
     * Account container
     */
    private Account account;

    /**
     * Constructor of UserAccountContainer
     */
    private UserAccountContainer(){
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
     * @return UserAccountContainer obj
     */
    public static UserAccountContainer getUserAccountContainer() {
        if(userAccountContainer == null)
            userAccountContainer = new UserAccountContainer();
        return userAccountContainer;
    }

}
