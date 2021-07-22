package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.cards.CardName;

import java.util.HashMap;

/**
 * This class contains images of cards!
 *
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class GameImageContainer {

    /**
     * Only instance of this class
     */
    private static GameImageContainer gameImageContainer = null;
    /**
     * throwable image container map
     */
    private HashMap<CardName, Image> throwable = new HashMap<>();

    /**
     * Adds an image to container
     *
     * @param name  name of card
     * @param image image of card
     */
    public void setThrowable(CardName name, Image image) {
        if (image == null || name == null)
            return;
        throwable.put(name, image);
    }

    /**
     * Get image of card by name of card
     *
     * @param name name of card
     * @return image of card (if not found returns null)
     */
    public Image getThrowable(CardName name) {
        if (throwable.containsKey(name))
            return throwable.get(name);
        return null;

    }

    /**
     * @return only instance of this class
     */
    public static GameImageContainer getGameImageContainer() {
        if (gameImageContainer == null)
            gameImageContainer = new GameImageContainer();
        return gameImageContainer;
    }

}
