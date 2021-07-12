package org.gamedevs.clashroyale.model.game.battle.field;

import org.gamedevs.clashroyale.model.cards.Card;

/**
 * Model of map.
 * Builds a pixel graph for map!
 * Contains the drop algorithm.
 * Readable map!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class Map {

    /**
     * Max number of X
     */
    private final int length;
    /**
     * Max number of Y
     */
    private final int width;
    /**
     * Graph of pixels
     */
    private Pixel[][] pixels;

    /**
     * Constructor of map.
     * Build a map with giving length and width.
     * @param length of map (max X)
     * @param width of map (max Y)
     */
    public Map(int length, int width){
        this.length = length;
        this.width = width;
        pixels = new Pixel[length][width];
        buildMap();
        matchGraph();
        lock();
    }

    /**
     *
     * @param x recommended x to drop game object
     * @param y recommended y to drop game object
     * @param card is my card
     * @return true if it could drop game object in map!
     */
    public boolean dropGameObject(int x, int y, Card card){

        return false;
    }

    /**
     * Getting a pixel with its pixels!
     * Use this to read pixels
     * @param x of pixel
     * @param y of pixel
     * @return pixel of found (else null)
     */
    public Pixel getPixel(int x, int y){
        if(x < 0 || y < 0 || x >= length || y >= width)
            return null;
        return pixels[x][y];
    }

    /**
     * Initializes all pixel
     */
    private void buildMap(){

    }

    /**
     * Connects pixel graph
     */
    private void matchGraph(){

    }

    /**
     * Locks all pixels
     */
    private void lock(){

    }

    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }

}
