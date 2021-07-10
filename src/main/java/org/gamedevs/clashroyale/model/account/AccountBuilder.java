package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.player.Human;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.io.AccountIO;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;
/**
 * a class to make a new account when you want to signup
 * @author Hosna Hoseini - CE@AUT - Uni ID:9823010
 * @version 1.0
 */
public class AccountBuilder {

    /**
     * obj of AccountBuilder
     */
    private static AccountBuilder accountBuilder = null;

    /**
     * check if account built or not
     */
    private boolean accountBuilt;

    /**
     * Constructor
     */
    private AccountBuilder(){
        accountBuilt = false;
    };

    /**
     * build a new account if there is no previous account with this username before
     * @param username username
     * @param pass password
     * @throws Exception when username or password are empty
     */
    public void buildNewAccount(String username, String pass) throws Exception {

        if(!AccountIO.searchInFileByUsername("Accounts.bin", username)) {
            Account account = new Account(username, pass);
            DeckContainer playCards = new DeckContainer();

//        playCards.addCard(new Arrows());
//        playCards.addCard(new Archers());
//        playCards.addCard(new BabyDragon());
//        playCards.addCard(new Barbarians());
//        playCards.addCard(new FireBall());
//        playCards.addCard(new Gient());
//        playCards.addCard(new InfernoTower());
//        playCards.addCard(new MiniPeka());
//        player.setPlayCards(playCards);
//
//        CardDeck availableCards = new CardDeck();
//        availableCards.addCard(new Rage());
//        availableCards.addCard(new Valkyrie());
//        availableCards.addCard(new Wizard());
//        availableCards.addCard(new Cannon());
//        account.setAvailableCards(availableCards);

            accountBuilt = true;
            FileConfig fileConfig = new FileConfig();
            fileConfig.write(FileConfig.ACCOUNT_FILENAME, username);
            fileConfig.write(FileConfig.ACCOUNT_PASSWORD, pass);
            AccountIO.getAccountIO().singleObjectFileWriter("Accounts.bin", account);
            Console.getConsole().printTracingMessage("New account" + username + " sign up!");
        }
    }
    /**
     * check if any account built
     * @return true if any account built
     */
    public boolean isAccountBuilt() {
        return accountBuilt;
    }
    /**
     * @return AccountBuilder obj
     */
    public static AccountBuilder getAccountBuilder() {
        if(accountBuilder == null)
            accountBuilder = new AccountBuilder();
        return accountBuilder;
    }


}
