package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.player.Human;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.io.AccountIO;
import org.gamedevs.clashroyale.model.utils.io.FileConfig;

public class AccountBuilder {

    private static AccountBuilder accountBuilder = null;

    private boolean accountBuilt = false;
    private AccountBuilder(){};

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

    public boolean isAccountBuilt() {
        return accountBuilt;
    }

    public static AccountBuilder getAccountBuilder() {
        if(accountBuilder == null)
            accountBuilder = new AccountBuilder();
        return accountBuilder;
    }


}
