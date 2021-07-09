package org.gamedevs.clashroyale.model.account;

import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.player.Human;

public class AccountBuilder {

    public static Human buildNewAccount(String username, String pass){
        Human human = new Human(username, pass);
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
//        player.setAvailableCards(availableCards);

        return human;
    }
}
