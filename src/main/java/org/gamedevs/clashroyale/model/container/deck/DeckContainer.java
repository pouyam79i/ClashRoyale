package org.gamedevs.clashroyale.model.container.deck;

import org.gamedevs.clashroyale.model.cards.Card;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * a class to hold a set of card as card deck for players
 *
 * @author Pouya Mohammadi - Hosna Hoseini
 * 9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.1
 */
public class DeckContainer implements Serializable {

    /**
     * deck contains cards.
     */
    private ArrayList<Card> deck;

    /**
     * DeckContainer constructor,
     * initial requirements.
     */
    public DeckContainer() {
        deck = new ArrayList<Card>();
    }

    /**
     * adds a new card
     *
     * @param index of card
     * @param card  card of player will be added (or replaced with another card in deck)
     * @return a card if it is replaced by another card in the deck, else will be null!
     */
    public void addCard(int index, Card card) {

        deck.add(index, card);

    }

    /**
     * adds a new card
     *
     * @param card card of player will be added (or replaced with another card in deck)
     * @return a card if it is replaced by another card in the deck, else will be null!
     */
    public void addCard(Card card) {

        deck.add(card);
    }

    /**
     * removes card from deck
     *
     * @param card of player
     */
    public Card removeCard(Card card) {
        Card temp = null;
        if (deck.contains(card)) {
            deck.remove(card);
            temp = card;
        }
        return temp;
    }

    /**
     * removes card from deck
     *
     * @param index of card
     */
    public void removeCard(int index) {

        deck.add(index, null);

    }


    public Card getRandomCard() {
        return deck.get(deck.size() - 1);
    }

    // Getter
    public ArrayList<Card> getDeck() {
        return deck;
    }

    // Setter
    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

}
