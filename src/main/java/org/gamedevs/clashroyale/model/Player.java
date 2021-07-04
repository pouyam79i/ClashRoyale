package org.gamedevs.clashroyale.model;

import java.io.Serializable;

public class Player implements Serializable {

    private String username;
    private String password;
    private CardDeck playCards;
    private CardDeck availableCards;
    private int xp;
//    private Level level;

    public Player(String username, String pass) {
        this.username = username;
        this.password = pass;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
