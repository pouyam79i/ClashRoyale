package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.cards.CardName;

import java.util.HashMap;

/**
 * This class contains images of cards!
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public class CardImageContainer {

    /**
     * Only instance of this class
     */
    private static CardImageContainer cardImageContainer = null;

    /**
     * card image container map
     */
    private HashMap<CardName, Image> cardImageMap;

    /**
     * Constructor of CardImageContainer,
     * initializes requirements.
     */
    private CardImageContainer(){
        cardImageMap = new HashMap<CardName, Image>();
    }

    /**
     * Adds an image to container
     * @param cardName name of card
     * @param image image of card
     */
    public void addImage(CardName cardName, Image image){
        if(cardName == null || image == null)
            return;
        if(cardImageMap.containsKey(cardName))
            return;
        cardImageMap.put(cardName, image);
    }

    /**
     * Get image of card by name of card
     * @param cardName name of card
     * @return image of card (if not found returns null)
     */
    public Image getCardImage(CardName cardName) {
        Image image = null;
        if (cardName != null){
            if (cardImageMap.containsKey(cardName))
                image = cardImageMap.get(cardName);
        }
        return image;
    }

    /**
     * @return only instance of this class
     */
    public static CardImageContainer getCardImageContainer() {
        if(cardImageContainer == null)
            cardImageContainer = new CardImageContainer();
        return cardImageContainer;
    }

}
