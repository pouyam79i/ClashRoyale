package org.gamedevs.clashroyale.model;

import org.gamedevs.clashroyale.model.account.Account;
import org.gamedevs.clashroyale.model.account.AccountBuilder;
import org.gamedevs.clashroyale.model.player.Human;
import org.gamedevs.clashroyale.model.utils.io.AccountIO;

/**
 * model for sign up and login scene
 *
 * @author Pouya Mohammadi - Hosna Hoseini
 * 9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class SignUpModel {

//
//
//    /**
//     * if a player with specific username and password has been
//     * sign up before and his/her info has been stored, then load
//     * the information and get this player to game manager to start game!
//     *
//     * @param username username
//     * @param pass password
//     * @return true if the login process was successful
//     */
//    public boolean login(String username, String pass) {
//        //TODO: replace "AccountIO.searchInFile" with searching in file names and check password
//        Account account = AccountIO.searchInFile("accounts.bin",username, pass);
//        if(human != null){
//            Human newHuman = AccountBuilder.buildNewAccount(username,pass);
////            gameManager.addNewPlayer(newHuman);
//            AccountIO.singleObjectFileWriter(username + ".bin", newHuman);
//            return true;
//        }else
//            return false;
//    }
//
//    /**
//     * if a player with specific username has NOT been
//     * sign up before, then get the information and make
//     * new player and give it to game manager to start game!
//     *
//     * @param username username
//     * @param pass password
//     * @return true if the sign up process was successful
//     */
//    public boolean signUp(String username, String pass) {
//        //TODO: replace "AccountIO.searchInFileByUsername" with searching in file names
//        if(!AccountIO.searchInFileByUsername("accounts.bin",username)){
//            Human human = AccountBuilder.buildNewAccount(username, pass);
////            gameManager.addNewPlayer(human);
//            return true;
//        }else
//            return false;
//    }

}
