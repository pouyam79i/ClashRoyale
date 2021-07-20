package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.scene.image.Image;
import org.gamedevs.clashroyale.model.cards.CardName;

import java.util.HashMap;

public class GameImageContainer {
    
    private static GameImageContainer gameImageContainer = null;
    private HashMap<CardName, Image> throwable = new HashMap<>();
    
    public void setThrowable(CardName name, Image image){
        if(image == null || name == null)
            return;
        throwable.put(name, image);
    }
    
    public Image getThrowable(CardName name){
        if(throwable.containsKey(name))
            return throwable.get(name);
        return null;
        
    }

    public static GameImageContainer getGameImageContainer(){
        if(gameImageContainer == null)
            gameImageContainer = new GameImageContainer();
        return gameImageContainer;
    }
}
