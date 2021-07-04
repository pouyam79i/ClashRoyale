package org.gamedevs.clashroyale.model;

import org.gamedevs.clashroyale.model.utils.FileIO.FileIO;

public class SignUpModel {
    public boolean login(String username, String pass) {
        Player player = (Player) FileIO.searchInFile("accounts.txt",username, pass);
        if(player != null){
//            gameManager.add(player);
            return true;
        }else
            return false;
    }

    public boolean signUp(String username, String pass) {
        if(!FileIO.searchInFileByUsername("accounts.txt",username)){
            Player player = AccountBuilder.buildNewAccount(username, pass);
//            gameManager.add(player);
            return true;
        }else
            return false;
    }

}
