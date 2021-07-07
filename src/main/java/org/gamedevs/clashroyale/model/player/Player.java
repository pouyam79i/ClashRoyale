package org.gamedevs.clashroyale.model.player;

import org.gamedevs.clashroyale.model.cards.CardDeck;

import java.io.Serializable;

public class Player implements Serializable {
    protected String username;
    protected CardDeck playCards;
    protected CardDeck availableCards;

    public Player(String username) {
        this.username = username;
    }

    public CardDeck getPlayCards() {
        return playCards;
    }

    public void setPlayCards(CardDeck playCards) {
        this.playCards = playCards;
    }

    public CardDeck getAvailableCards() {
        return availableCards;
    }

    public void setAvailableCards(CardDeck availableCards) {
        this.availableCards = availableCards;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                '}';
    }
}
