package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;

/**
 * This class builds a new account! (Applies the algorithm of building new account)
 */
public class AccountBuilder {

    /**
     * Only instance of this class
     */
    private static AccountBuilder accountBuilder = null;

    /**
     * Loaded account will be set in this object!
     */
    private UserAccountContainer userAccountContainer;
    /**
     * if true, it means we have loaded account!
     */
    private boolean accountLoaded;

    private AccountBuilder(){
        userAccountContainer = UserAccountContainer.getUserAccountContainer();
        accountLoaded = false;
    }

    /**
     * This method builds a new account,
     * and sets userdata.
     * @param username new username
     * @param password password of account!
     */
    public void buildNewAccount(String username, String password){
        Account account = null;
        // ********************************* Develop **************************** -> Hosna Hoseini
        /*
         *  Remove this comments after you read it!
         *  Create a proper algorithm which builds a new account for us!
         *  then load the account and put in 'userDataContainer'
         *  set accountLoaded as true!
         *  "Remember: to have proper algorithm which checks username and password to have
         * proper characters and also null is not and input!"
         */
//        try {
//            account = new Account(username, password);
//        } catch (Exception e) {}
        // End of Developing
    }

    /**
     * When log out of current account,
     * call this method!
     */
    public void logout(){
        accountLoaded = false;
    }

    // Getters
    public boolean isAccountLoaded() {
        return accountLoaded;
    }

    /**
     * @return only instance of this class!
     * if null it will build a instance of it!
     */
    public static AccountBuilder getAccountBuilder() {
        if(accountBuilder == null)
            accountBuilder = new AccountBuilder();
        return accountBuilder;
    }

}
