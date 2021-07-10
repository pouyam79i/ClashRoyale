package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;

/**
 * Account loader applies the algorithm of loading account,
 * calls and builds required objects.
 * If account is loaded it will be set in account -> UserAccountContainer !
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.1
 */
public class AccountLoader {

    /**
     * Only instance of AccountLoader
     */
    private static AccountLoader accountLoader = null;

    /**
     * Loaded account will be set in this object!
     */
    private UserAccountContainer userAccountContainer;
    /**
     * if true, it means we have loaded account!
     */
    private boolean accountLoaded;

    /**
     * Constructor of AccountLoader
     * Sets fields as Defaults
     */
    private AccountLoader(){
        userAccountContainer = UserAccountContainer.getUserAccountContainer();
        accountLoaded = false;
    }

    /**
     * This method is called in launcher!
     * So we will check if we have previously loaded account!
     */
    public void init(){
        FileConfig fileConfig = new FileConfig();
        String accountFileName = fileConfig.read(FileConfig.ACCOUNT_FILENAME);
        String accountPassword = fileConfig.read(FileConfig.ACCOUNT_PASSWORD);
        // Develop it here ******************** Develop ********************* -> Pouya Mohammadi

        // End of Developing area!
    }

    /**
     * searches for saved account, it will load it. (Applies the algorithm)
     * if fount it will load it in user data container!
     * @param username username of saved account
     * @param password password of account
     */
    public void loadAccount(String username, String password){
        // Develop it here ******************** Develop ********************* -> Hosna Hoseini
        /*
         * Delete this comment after you read it!
         * Call an create required objects!
         * Call methods of file io which searches and load Account from file!
         * If Account founded and load it and put in 'userDataContainer'
         * Set file Config value (username or filename, which suits your design)
         *  "Remember: to have proper algorithm which checks username and password to have
         * proper characters and also null is not and input!
         * set accountLoaded true if it is loaded"
         */
        // End of Developing area!
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
     * @return Only instance of this class
     */
    public static AccountLoader getAccountLoader() {
        if(accountLoader == null)
            accountLoader = new AccountLoader();
        return accountLoader;
    }

}
