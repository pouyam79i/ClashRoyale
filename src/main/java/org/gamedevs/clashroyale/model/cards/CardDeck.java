package org.gamedevs.clashroyale.model.cards;

import org.gamedevs.clashroyale.model.cards.Card;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * a class to hold a set of card as card deck for players
 *
 * @author Pouya Mohammadi - Hosna Hoseini
 * 9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public class CardDeck implements Serializable {

    //array of card in this card deck
    private ArrayList<Card> cards;

    /**
     * add a new card
     * @param card card
     */
    public void addCard(Card card){
        cards.add(card);
    }

    /**
     * remove a card
     * @param card card
     */
    public void removeCard(Card card){
        cards.remove(card);
    }

    //Getter
    public ArrayList<Card> getCards() {
        return cards;
    }

    //Setter
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
