package org.gamedevs.clashroyale.model.game.battle.engine.map;

import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;

import java.util.HashMap;

/**
 * Tile of map.
 * Memory structure of model used for game!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0.1
 */
public class Tile {

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
    private final HashMap<Angle, Tile> surroundingPixels;
    /**
     * Game object which is in this pixels.
     * This object is in z = 0
     */
    private GameObject gameObject;
    /**
     * this object is not in z = 0
     */
    private GameObject flyingGameObject;
    /**
     * Lock if used for data securing the pixel
     */
    private boolean lock;

    /**
     * Constructor of Tile
     * @param x of pixel
     * @param y of pixel
     */
    public Tile(int x, int y){
        lock = false;
        this.x = x;
        this.y = y;
        gameObject = null;
        flyingGameObject = null;
        surroundingPixels = new HashMap<Angle, Tile>();
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
     * @param gameObject will be set,
     * z of object: if 0 means on ground else means it is a flying object.
     * @return if it could set game object returns true else will be false!
     */
    public synchronized boolean setGameObject(GameObject gameObject){
        int z = gameObject.getZ();
        if(z == 0){
            if(isEmpty(z)){
                this.gameObject = gameObject;
                return true;
            }
            return false;
        }else {
            if(isEmpty(z)){
                this.flyingGameObject = gameObject;
                return true;
            }
            return false;
        }
    }

    /**
     * Tells if next pixel is available,
     * according to the given direction!
     * @param angle of next pixel
     * @return true if it is empty
     */
    public boolean peak(Angle angle, int z){
        Tile nextTile = surroundingPixels.get(angle);
        if(nextTile != null){
            return nextTile.isEmpty(z);
        }
        return false;
    }

    /**
     * Gets the surrounding pixel.
     * @param angle of next pixel with respect to this pixel.
     * @return pixel if there is any pixel.
     */
    public Tile getSurroundingPixel(Angle angle){
        return surroundingPixels.get(angle);
    }

    /**
     * Used for setting next tile graph.
     * @param angle of next tile (respected to this tile).
     * @param tile in that angle!
     */
    public void setSurroundingPixel(Angle angle, Tile tile){
        if(!lock)
            surroundingPixels.put(angle, tile);
    }

    /**
     * @return if no game object is here returns true!
     */
    public boolean isEmpty(int z){
        if(z == 0){
            return (gameObject == null);
        }else {
            return (flyingGameObject == null);
        }
    }

    /**
     * carry object of this pixel to another pixel
     * @param z of current object
     * @return true of it could carry the object to another place
     */
    public synchronized boolean carry(Angle angle, int z){
        if(z == 0){
            if(gameObject == null)
                return true;
            if(getSurroundingPixel(angle).setGameObject(gameObject)){
                gameObject = null;
                return true;
            }
            return false;
        }else {
            if(flyingGameObject == null)
                return true;
            if(getSurroundingPixel(angle).setGameObject(flyingGameObject)){
                flyingGameObject = null;
                return true;
            }
            return false;
        }
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
