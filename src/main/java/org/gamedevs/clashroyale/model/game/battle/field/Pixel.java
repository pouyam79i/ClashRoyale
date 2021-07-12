package org.gamedevs.clashroyale.model.game.battle.field;

import org.gamedevs.clashroyale.model.game.objects.GameObject;

import java.util.HashMap;

/**
 * Pixel of map.
 * Memory structure of model used for game!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class Pixel {

    /**
     * X of pixel
     */
    private final int x;
    /**
     * Y of pixel
     */
    private final int y;
    /**
     * Graph of surrounding pixels
     */
    private final HashMap<Angle, Pixel> surroundingPixels;
    /**
     * Game object which is in this pixels
     */
    private GameObject gameObject;
    /**
     * Lock if used for data securing the pixel
     */
    private boolean lock;

    /**
     * Constructor of Pixel
     * @param x of pixel
     * @param y of pixel
     */
    public Pixel(int x, int y){
        lock = false;
        this.x = x;
        this.y = y;
        gameObject = null;
        surroundingPixels = new HashMap<Angle, Pixel>();
        initial();
    }

    /**
     * when a game object if going to take himself out!
     * @return true if it could remove the object!
     */
    public synchronized boolean takeGameObject(GameObject gameObject){
        if(gameObject == this.gameObject){
            this.gameObject = null;
            return true;
        }
        return false;
    }

    /**
     * set a game object in pixel
     * @param gameObject will be set
     * @return if it could set game object returns true else will be false!
     */
    public synchronized boolean setGameObject(GameObject gameObject){
        if(isEmpty()){
            this.gameObject = gameObject;
            return true;
        }
        return false;
    }

    /**
     * Tells if next pixel is available,
     * according to the given direction!
     * @param angle of next pixel
     * @return true if it is empty
     */
    public boolean peak(Angle angle){
        Pixel nextPixel = surroundingPixels.get(angle);
        if(nextPixel != null){
            return nextPixel.isEmpty();
        }
        return false;
    }

    /**
     * used for setting next pixel graph.
     * @param angle of next pixel (respected to this pixel)
     * @param pixel in that angle!
     */
    public void setSurroundingPixel(Angle angle, Pixel pixel){
        if(!lock)
            surroundingPixels.put(angle, pixel);
    }

    /**
     * @return if no game object is here returns true!
     */
    public boolean isEmpty(){
        return (gameObject == null);
    }

    /**
     * Used to lock main information of pixel,
     * used for securing pixel!
     */
    public void lock(){
        lock = true;
    }

    /**
     * Initial value of surrounding pixels
     */
    private void initial(){
        if(lock)
            return;
        surroundingPixels.put(Angle.NORTH, null);
        surroundingPixels.put(Angle.NORTH_EAST, null);
        surroundingPixels.put(Angle.EAST, null);
        surroundingPixels.put(Angle.SOUTH_EAST, null);
        surroundingPixels.put(Angle.SOUTH, null);
        surroundingPixels.put(Angle.SOUTH_WEST, null);
        surroundingPixels.put(Angle.WEST, null);
        surroundingPixels.put(Angle.NORTH_WEST, null);
    }

    // Getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public GameObject getGameObject() {
        return gameObject;
    }

}
