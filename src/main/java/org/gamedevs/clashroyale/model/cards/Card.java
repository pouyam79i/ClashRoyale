package org.gamedevs.clashroyale.model.cards;

import javafx.scene.image.Image;

import java.io.Serializable;

/**
 * This class contains main method of Clash Royale application!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public abstract class Card implements Serializable {

    /**
     * Name of card
     */
    private final CardName cardName;
    /**
     * Cost of card (elixir)
     */
    private final int cost;
    /**
     * Current xp of card
     */
    protected int xp;
    /**
     * Current leve of card
     */
    protected int leve;
    /**
     * Xp needed to upgrade card!
     */
    protected int levelUpXPScale;

    /**
     * Constructor of Card
     * Sets default values
     * @param cardName name of card
     * @param cost cost of card (elixir)
     */
    protected Card(CardName cardName, int cost) {
        this.cardName = cardName;
        this.cost = cost;
        xp = 0;
        leve = 1;
    }

    // Getters
    public CardName getCardName() {
        return cardName;
    }
    public int getCost() {
        return cost;
    }

}
