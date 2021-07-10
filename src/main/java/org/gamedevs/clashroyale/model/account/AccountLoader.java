package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.gamedata.UserDataContainer;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.io.AccountIO;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * a class to load an account when you want to login
 * @author Hosna Hoseini - CE@AUT - Uni ID:9823010
 * @version 1.0
 */
public class AccountLoader {

    /**
     * obj of AccountLoader
     */
    private static AccountLoader accountLoader = null;

    /**
     * check if account loaded
     */
    private boolean accountLoaded;

    /**
     * constructor
     */
    private AccountLoader(){
        accountLoaded = false;
    }

    public void init(){
        UserDataContainer userDataContainer = UserDataContainer.getInstance();
        FileConfig fileConfig = new FileConfig();
        String accountFileName = fileConfig.read(FileConfig.ACCOUNT_FILENAME);
        String accountPassword = fileConfig.read(FileConfig.ACCOUNT_PASSWORD);
        // Develop it here ******************** Develop *********************
    }

    /**
     * searches for saved account, it will load it.
     * if fount it will load it in user data container!
     * @param username username of saved account
     * @param password password of account
     */
    public void loader(String username, String password) {
        Account account = AccountIO.getAccountIO().searchInFile("Accounts.bin", username, password);
        if(account != null){
            UserDataContainer.getInstance().setAccount(account);
            accountLoaded = true;
            FileConfig fileConfig = new FileConfig();
            fileConfig.write(FileConfig.ACCOUNT_FILENAME, username);
            fileConfig.write(FileConfig.ACCOUNT_PASSWORD, password);
            Console.getConsole().printTracingMessage("New account" + username + " login!");

        }

    }

    /**
     * check if any account loaded
     * @return true if any account loaded
     */
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
