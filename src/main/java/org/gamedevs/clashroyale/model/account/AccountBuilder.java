package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.container.gamedata.UserAccountContainer;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.io.AccountIO;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;

import java.util.ArrayList;

/**
 * This class builds a new account! (Applies the algorithm of building new account)
 * a class to make a new account when you want to signup
 * @author Hosna Hoseini - CE@AUT - Uni ID:9823010
 * @version 1.0
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
    private boolean accountBuilt;

    private AccountBuilder(){
        userAccountContainer = UserAccountContainer.getUserAccountContainer();
        accountBuilt = false;
    }

    /**
     * This method builds a new account,
     * and sets userdata.
     * @param username new username
     * @param password password of account!
     */
    public void buildNewAccount(String username, String password){
        if(!AccountIO.getAccountIO().searchInDirByUsername(username)) {
            Account account = null;
            try {
                account = new Account(username, password);
            } catch (Exception e) {
                Console.getConsole().printTracingMessage("Failed to build account: " + e.getMessage());
                return;
            }

            DeckContainer availableContainer = new DeckContainer();
            availableContainer.addCard(new Card(CardName.BARBARIANS, 5));
            availableContainer.addCard(new Card(CardName.ARCHERS, 3));
            availableContainer.addCard(new Card(CardName.BABY_DRAGON, 4));
            availableContainer.addCard(new Card(CardName.WIZARD, 5));
            availableContainer.addCard(new Card(CardName.MINI_PEKKA, 4));
            availableContainer.addCard(new Card(CardName.GIANT, 5));
            availableContainer.addCard(new Card(CardName.VALKYRIE, 4));
            availableContainer.addCard(new Card(CardName.RAGE, 3));
            availableContainer.addCard(new Card(CardName.FIREBALL, 4));
            availableContainer.addCard(new Card(CardName.ARROWS, 3));
            availableContainer.addCard(new Card(CardName.CANNON, 6));
            availableContainer.addCard(new Card(CardName.INFERNO_TOWER, 5));


            DeckContainer deckContainer = new DeckContainer();
            for(int i = 0; i < 8; i++)
                deckContainer.addCard(new Card(CardName.EMPTY,0));
            account.setDeckAvailable(availableContainer);
            account.setDeckContainer(deckContainer);

            userAccountContainer.setAccount(account);
            accountBuilt = true;
            FileConfig fileConfig = new FileConfig();
            fileConfig.write(FileConfig.ACCOUNT_FILENAME, username);
            fileConfig.write(FileConfig.ACCOUNT_PASSWORD, password);
            AccountIO.getAccountIO().singleObjectFileWriter(username + ".bin", account);
            Console.getConsole().printTracingMessage("New account " + username + " sign up!");
        }
    }

    /**
     * When log out of current account,
     * call this method!
     */
    public void logout(){
        accountBuilt = false;
    }

    // Getters
    public boolean isAccountLoaded() {
        return accountBuilt;
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
