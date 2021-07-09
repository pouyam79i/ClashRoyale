package org.gamedevs.clashroyale.model.player;

import org.gamedevs.clashroyale.model.container.deck.DeckContainer;

import java.io.Serializable;

public class Player implements Serializable {
    protected String username;
    protected DeckContainer playCards;
    protected DeckContainer availableCards;

    public Player(String username) {
        this.username = username;
    }

    public DeckContainer getPlayCards() {
        return playCards;
    }

    public void setPlayCards(DeckContainer playCards) {
        this.playCards = playCards;
    }

    public DeckContainer getAvailableCards() {
        return availableCards;
    }

    public void setAvailableCards(DeckContainer availableCards) {
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
