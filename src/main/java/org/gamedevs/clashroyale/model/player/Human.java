package org.gamedevs.clashroyale.model.player;

import org.gamedevs.clashroyale.model.cards.CardDeck;

import java.io.Serializable;

public class Human extends Player implements Serializable {

    private String password;
    private int xp;
//    private Level level;

    public Human(String username,String pass) {
        super(username);
        this.password = pass;

    }

    public String getPassword() {
        return password;
    }

}
