package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.objects.GameObjectState;

import java.util.HashMap;

/**
 * This class contains all images of card animations (gifs)
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class GameDroppableImageContainer {

    /**
     * only instance of this class
     */
    private static GameDroppableImageContainer gameDroppableImageContainer = null;

    /**
     * Image container
     */
    private final HashMap<CardName, HashMap<GameObjectState, Image>> allyImageContainer;
    private final HashMap<CardName, HashMap<GameObjectState, Image>> enemyImageContainer;

    /**
     * Constructor of GameDroppableImageContainer
     * sets requirements
     */
    private GameDroppableImageContainer(){
        allyImageContainer = new HashMap<CardName, HashMap<GameObjectState, Image>>();
        enemyImageContainer = new HashMap<CardName, HashMap<GameObjectState, Image>>();
    }

    /**
     * set a loaded image for the card
     * @param cardName name of card
     * @param state state of card
     * @param isEnemyCard if the image is related to enemy
     * @param image of card in the mentioned state
     */
    public void set(CardName cardName, GameObjectState state, boolean isEnemyCard, Image image){
        if(image == null)
            return;
        if(isEnemyCard){
            if(enemyImageContainer.containsKey(cardName)){
                enemyImageContainer.get(cardName).put(state, image);
            }else {
                HashMap<GameObjectState, Image> imageStateContainer = new HashMap<GameObjectState, Image>();
                imageStateContainer.put(state, image);
                enemyImageContainer.put(cardName, imageStateContainer);
            }
        }else {
            if(allyImageContainer.containsKey(cardName)){
                allyImageContainer.get(cardName).put(state, image);
            }else {
                HashMap<GameObjectState, Image> imageStateContainer = new HashMap<GameObjectState, Image>();
                imageStateContainer.put(state, image);
                allyImageContainer.put(cardName, imageStateContainer);
            }
        }
    }

    /**
     * get related card image
     * @param cardName name of card
     * @param state state of card
     * @param isEnemyCard if the image is related to enemy
     * @return image of related card and its state
     */
    public Image get(CardName cardName, GameObjectState state, boolean isEnemyCard){
        Image image = null;
        if(isEnemyCard){
            if(enemyImageContainer.containsKey(cardName)){
                if(enemyImageContainer.get(cardName).containsKey(state)){
                    image = enemyImageContainer.get(cardName).get(state);
                }
            }
        }else {
            if(allyImageContainer.containsKey(cardName)){
                if(allyImageContainer.get(cardName).containsKey(state)){
                    image = allyImageContainer.get(cardName).get(state);
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
