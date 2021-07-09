package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.gamedata.UserDataContainer;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;

public class AccountLoader {

    private static AccountLoader accountLoader = null;

    private boolean accountLoaded;

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
    public void loader(String username, String password){

    }

    public boolean isAccountLoaded() {
        return accountLoaded;
    }

    public static AccountLoader getAccountLoader() {
        if(accountLoader == null)
            accountLoader = new AccountLoader();
        return accountLoader;
    }

}
