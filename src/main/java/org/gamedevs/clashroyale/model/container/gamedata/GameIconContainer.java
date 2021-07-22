package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * This class contains ui icons of game!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class GameIconContainer {

    /**
     * only instance of this class
     */
    private static GameIconContainer gameIconContainer = null;

    /**
     * level image container
     */
    private ArrayList<Image> levelImages;
    /**
     * arena image container
     */
    private ArrayList<Image> arenaImages;

    /**
     * Constructor of GameIconContainer
     * builds requirements
     */
    private GameIconContainer(){
        levelImages = new ArrayList<Image>();
        arenaImages = new ArrayList<Image>();
    }

    /**
     * leve image adder
     * @param image of level
     */
    public void addLevelImage(Image image){
        levelImages.add(image);
    }

    /**
     * arena image adder
     * @param image of arena
     */
    public void addArenaImage(Image image){
        arenaImages.add(image);
    }

    // Getters
    public ArrayList<Image> getLevelImages() {
        return levelImages;
    }
    public ArrayList<Image> getArenaImages() {
        return arenaImages;
    }

    /**
     * @return only instance of this class
     */
    public static GameIconContainer getGameIconContainer() {
        if(gameIconContainer == null)
            gameIconContainer = new GameIconContainer();
        return gameIconContainer;
    }

}
