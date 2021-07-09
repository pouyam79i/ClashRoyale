package org.gamedevs.clashroyale.model.launcher;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.account.Account;
import org.gamedevs.clashroyale.model.container.gamedata.UserDataContainer;
import org.gamedevs.clashroyale.model.container.scene.SignupMenuContainer;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;

import java.io.IOException;

public class AccountLauncher {

    public void launch() throws IOException {
        // Checking previous logged in account
//        UserDataContainer userDataContainer = UserDataContainer.getInstance();
//        FileConfig fileConfig = new FileConfig();
//        String currentAccountFileName = fileConfig.read(FileConfig.ACCOUNT_FILENAME);
//        String password = fileConfig.read(FileConfig.ACCOUNT_PASSWORD);
//        if(currentAccountFileName != null){
//            Account account = null;
//            // Get account here
//            // **************************** DEVELOP *****************************
//            // End of get account
//            if(account != null){
//                if(account.getPassword().equals(password)){
//                    userDataContainer.setAccount(account);
//                }else {
//                    userDataContainer.setAccount(null);
//                }
//            }
//        }
        SignupMenuContainer menuData = SignupMenuContainer.getMenuData();
        // Loading signup menu
        AnchorPane signupMenu = FXMLLoader.load(getClass().getResource(
                "../../view/fxml/menu/sign_up.fxml"
        ));
        signupMenu.setLayoutX(0);
        signupMenu.setLayoutY(0);
        menuData.setSignupMenu(signupMenu);
        // Building root scene;
        Scene rootScene = new Scene(signupMenu);
        menuData.setRootScene(rootScene);
    }

}
