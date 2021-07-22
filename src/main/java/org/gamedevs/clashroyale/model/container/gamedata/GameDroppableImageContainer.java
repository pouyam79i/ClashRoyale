package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;

import java.util.HashMap;

/**
 * This class contains all images of card animations (gifs)
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.1
 */
public class GameDroppableImageContainer {

    /**
     * only instance of this class
     */
    private static GameDroppableImageContainer gameDroppableImageContainer = null;

    /**
     * Image container
     */
    private final HashMap<CardName, HashMap<Angle, HashMap<GameObjectState, Image>>> imageContainer;

    /**
     * Constructor of GameDroppableImageContainer
     * sets requirements
     */
    private GameDroppableImageContainer(){
        imageContainer = new HashMap<CardName, HashMap<Angle, HashMap<GameObjectState, Image>>>();
    }

    /**
     * set a loaded image for the card
     * @param cardName name of card
     * @param angle of object
     * @param objectState state of card
     * @param image of card in the mentioned state
     */
    public void set(CardName cardName, Angle angle, GameObjectState objectState, Image image){
        if(image == null || cardName == null || objectState == null || angle == null)
            return;
        if(imageContainer.containsKey(cardName)){
            if(imageContainer.get(cardName).containsKey(angle)){
                imageContainer.get(cardName).get(angle).put(objectState, image);
            }else {
                HashMap<GameObjectState, Image> angleContainer = new  HashMap<GameObjectState, Image>();
                angleContainer.put(objectState, image);
                imageContainer.get(cardName).put(angle, angleContainer);
            }

        }else {
            HashMap<Angle, HashMap<GameObjectState, Image>> cardContainer = new HashMap<Angle, HashMap<GameObjectState, Image>>();
            HashMap<GameObjectState, Image> angleContainer = new  HashMap<GameObjectState, Image>();
            angleContainer.put(objectState, image);
            cardContainer.put(angle, angleContainer);
            imageContainer.put(cardName, cardContainer);
        }

    }

    /**
     * get related card image
     * @param cardName name of card
     * @param angle of object
     * @param objectState state of card
     * @return image of related card and its state
     */
    public Image get(CardName cardName, Angle angle,GameObjectState objectState){
        Image image = null;
        if(imageContainer.containsKey(cardName)){

            if(imageContainer.get(cardName).containsKey(angle)){

                if(imageContainer.get(cardName).get(angle).containsKey(objectState)){

                    image = imageContainer.get(cardName).get(angle).get(objectState);
                }
            }
        }
        return image;
    }

    /**
     * @return only instance of this class
     */
    public static GameDroppableImageContainer getGameDroppableImageContainer() {
        if(gameDroppableImageContainer == null)
            gameDroppableImageContainer = new GameDroppableImageContainer();
        return gameDroppableImageContainer;
    }

}
