package org.gamedevs.clashroyale.model.cards;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;

/**
 * This class contains main method of Clash Royale application!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class Card implements Serializable {

    /**
     * Name of card
     */
    private final CardName cardName;
    /**
     * Cost of card (elixir)
     */
    private final int cost;

    private boolean lock = false;

    /**
     * Constructor of Card
     * Sets default values
     * @param cardName name of card
     * @param cost cost of card (elixir)
     */
    public Card(CardName cardName, int cost) {
        this.cardName = cardName;
        this.cost = cost;
    }

    // Getters
    public CardName getCardName() {
        return cardName;
    }
    public int getCost() {
        return cost;
    }

    public boolean isLock() {
        return lock;
    }


    public void setLock(boolean lock) {
        this.lock  = lock;
    }
}