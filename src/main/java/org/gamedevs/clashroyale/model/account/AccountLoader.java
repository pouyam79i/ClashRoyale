package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.io.AccountIO;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;

/**
 * a class to load an account when you want to login
 * @author Hosna Hoseini - CE@AUT - Uni ID:9823010
 * @version 1.0
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
        // Develop it here ******************** Develop *********************
    }

    /**
     * searches for saved account, it will load it. (Applies the algorithm)
     * if fount it will load it in user data container!
     * @param username username of saved account
     * @param password password of account
     */
    public void loadAccount(String username, String password) {
        Account account = AccountIO.getAccountIO().searchInFile("Accounts.bin", username, password);
        if (account != null) {
            userAccountContainer.setAccount(account);
            accountLoaded = true;
            FileConfig fileConfig = new FileConfig();
            fileConfig.write(FileConfig.ACCOUNT_FILENAME, username);
            fileConfig.write(FileConfig.ACCOUNT_PASSWORD, password);
            Console.getConsole().printTracingMessage("New account" + username + " login!");
        }
    }

    /**
     * When log out of current account,
     * call this method!
     */
    public void logout(){
        accountLoaded = false;
    }

    /**
     * check if any account loaded
     * @return true if any account loaded
     */
    // Getters
    public boolean isAccountLoaded() {
        return accountLoaded;
    }

    /**
     * get instance of account loader
     * @return instance of account loader
     */
    public static AccountLoader getAccountLoader() {
        if(accountLoader == null)
            accountLoader = new AccountLoader();
        return accountLoader;
    }

}
