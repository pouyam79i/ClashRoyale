package org.gamedevs.clashroyale.model;

import org.gamedevs.clashroyale.model.cards.Card;

import java.util.ArrayList;

public class AccountBuilder {

    public static Player buildNewAccount(String username, String pass){
        Player player = new Player(username, pass);
        CardDeck playCards = new CardDeck();

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

        return player;
    }
}
