package org.gamedevs.clashroyale.model.game.battle.tools;

import javafx.beans.property.ListProperty;
import javafx.collections.ObservableList;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.container.deck.DeckContainer;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public class CardGenerator {

    DeckContainer deckContainer;
    Elixir elixir;
    ListProperty<Card> shownCards;

    public CardGenerator(DeckContainer deckContainer, Elixir elixir) {
        this.deckContainer = deckContainer;
        this.elixir = elixir;
    }


    public Card[] getInitCards() {
        Card [] cards =

                new Card[4];
        for(int i = 0; i < 4; i++) {
            Card card = deckContainer.getRandomCard();
            deckContainer.removeCard(card);
            cards[i] = card;
        }

        return cards;
    }

    public Card getNextCard(){
        Card card = deckContainer.getRandomCard();
        deckContainer.removeCard(card);
        return card;
    }

    public DeckContainer getDeckContainer() {
        return deckContainer;
    }

    public void setDeckContainer(DeckContainer deckContainer) {
        this.deckContainer = deckContainer;
    }

    public Elixir getElixir() {
        return elixir;
    }

    public void setElixir(Elixir elixir) {
        this.elixir = elixir;
    }

    public ObservableList<Card> getShownCards() {
        return shownCards.get();
    }

    public ListProperty<Card> shownCardsProperty() {
        return shownCards;
    }

    public void setShownCards(ObservableList<Card> shownCards) {
        this.shownCards.set(shownCards);
    }
}
