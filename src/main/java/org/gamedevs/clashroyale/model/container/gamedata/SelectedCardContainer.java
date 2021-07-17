package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.utils.console.Console;

/**
 * Selected card container.
 * contains selected card to used it in drop!
 *
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class SelectedCardContainer {

    /**
     * only instance of this class
     */
    private static SelectedCardContainer selectedCardContainer = null;

    /**
     * Selected card field
     */
    private Card selectedCard;
    /**
     * Tells if the current selected card is dropped
     */
    private SimpleBooleanProperty selectedCardIsDropped;
    /**
     * Tells if the current selected card is dropped
     */
    private SimpleBooleanProperty selectedCardFailedToDrop;
    /**
     * Tells if selected card exists!
     */
    private SimpleBooleanProperty selectedCardExist;

    /**
     * Constructor of SelectedCardContainer
     * sets defaults
     */
    private SelectedCardContainer() {
        selectedCard = null;
        selectedCardIsDropped = new SimpleBooleanProperty();
        selectedCardFailedToDrop = new SimpleBooleanProperty();
        selectedCardFailedToDrop.setValue(false);
        selectedCardIsDropped.setValue(false);
        selectedCardExist = new SimpleBooleanProperty();
        selectedCardExist.setValue(false);
    }

    /**
     * put a card in selected card field!
     *
     * @param newCard new card will be set
     * @return
     */
    public Card put(Card newCard) {

        selectedCardExist.setValue(true);
        selectedCardIsDropped.setValue(false);
        selectedCardFailedToDrop.setValue(false);

        Card temp = selectedCard;   // If there is any selected card from before
        selectedCard = newCard;
        return temp;
    }

    /**
     * @return removes the selected card field
     */
    public Card takeOut() {

        selectedCardExist.setValue(false);
        selectedCardIsDropped.setValue(false);
        selectedCardFailedToDrop.setValue(false);
//            Console.getConsole().printTracingMessage("take out " + selectedCard.getCardName() + " " + selectedCardExist.get());


        Card temp = selectedCard;
        selectedCard = null;
        return temp;
    }

    /**
     * @return card if there is selected card
     */
    public Card getSelectedCard() {
        return selectedCard;
    }

    /**
     * drops the card in battle field
     */
    public void dropped() {

        selectedCardIsDropped.setValue(true);
        selectedCardExist.setValue(false);
        selectedCardFailedToDrop.setValue(false);

    }

    /**
     * if dropping process failed
     */
    public void droppingFailed() {

        selectedCardExist.setValue(false);
        selectedCardIsDropped.setValue(false);
        selectedCardFailedToDrop.setValue(true);

    }

    // Getters
    public boolean isSelectedCardIsDropped() {
        return selectedCardIsDropped.get();
    }

    public SimpleBooleanProperty selectedCardIsDroppedProperty() {
        return selectedCardIsDropped;
    }

    public boolean isSelectedCardExist() {
        return selectedCardExist.get();
    }

    public SimpleBooleanProperty selectedCardExistProperty() {
        return selectedCardExist;
    }

    public boolean isSelectedCardFailedToDrop() {
        return selectedCardFailedToDrop.get();
    }

    public SimpleBooleanProperty selectedCardFailedToDropProperty() {
        return selectedCardFailedToDrop;
    }

    /**
     * @return only instance of this class
     */
    public static SelectedCardContainer getSelectedCardContainer() {
        if (selectedCardContainer == null)
            selectedCardContainer = new SelectedCardContainer();
        return selectedCardContainer;
    }

}
