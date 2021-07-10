package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.scene.image.Image;
import java.util.ArrayList;

public class GameIconContainer {

    private static GameIconContainer gameIconContainer = null;

    private ArrayList<Image> levelImages;
    private ArrayList<Image> arenaImages;

    private GameIconContainer(){
        levelImages = new ArrayList<Image>();
        arenaImages = new ArrayList<Image>();
    }

    public void addLevelImage(Image image){
        levelImages.add(image);
    }

    public void addArenaImage(Image image){
        arenaImages.add(image);
    }


    public ArrayList<Image> getLevelImages() {
        return levelImages;
    }

    public ArrayList<Image> getArenaImages() {
        return arenaImages;
    }

    public static GameIconContainer getGameIconContainer() {
        if(gameIconContainer == null)
            gameIconContainer = new GameIconContainer();
        return gameIconContainer;
    }

}
