package org.gamedevs.clashroyale.model;

import org.gamedevs.clashroyale.model.cards.Card;

import java.io.Serializable;
import java.util.ArrayList;

public class CardDeck implements Serializable {
    private ArrayList<Card> cards;

    public void addCard(Card card){
        cards.add(card);
    }

    public void removeCard(Card card){
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
