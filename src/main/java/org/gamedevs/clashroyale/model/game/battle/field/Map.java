package org.gamedevs.clashroyale.model.game.battle.field;

import org.gamedevs.clashroyale.model.game.objects.GameObject;
import org.gamedevs.clashroyale.model.game.spell.Spell;
import org.gamedevs.clashroyale.model.updater.battle.ViewManager;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;

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
    private final int width;
    /**
     * Max number of Y
     */
    private final int height;
    /**
     * Graph of pixels
     */
    private Pixel[][] pixels;

    // Droppables:
    /**
     * Alive objects of top side player
     */
    private ArrayList<GameObject> topSideAliveObj;
    /**
     * Alive objects of down side player
     */
    private ArrayList<GameObject> downSideAliveObj;
    private ViewManager viewManager;

    /**
     * Constructor of map.
     * Build a map with giving length and width.
     * @param width of map (max X)
     * @param height of map (max Y)
     */
    public Map(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new Pixel[width][height];
        topSideAliveObj = new ArrayList<GameObject>();
        downSideAliveObj = new ArrayList<GameObject>();
        viewManager = null;
        buildMap();
        matchGraph();
        lock();
    }

    /**
     *
     * @param x recommended x to drop game object
     * @param y recommended y to drop game object
     * @param gameObject is my solder or building
     * @return true if it could drop game object in map!
     */
    public boolean dropGameObject(int x, int y, GameObject gameObject){
        ArrayList<Pixel> visitedPixel;
        Pixel calledPixel = getPixel(x, y);
        if(calledPixel == null){
            Console.getConsole().printTracingMessage("called pixel (" + x + "," + y + ") does not exist!");
            return false;
        }
        if(calledPixel.isEmpty()){
            calledPixel.setGameObject(gameObject);
            gameObject.setHeadPixel(calledPixel);
            if(viewManager != null){
                viewManager.addObjectToView(gameObject);
            }
            return true;
        }
        int maxRadius = 1;      // TODO: define this in side configs
        for(int radius = 0; radius < maxRadius; radius++){
            for(int degree = 0; degree < 360;){
                if(calledPixel.peak(Angle.getAngle(degree))){
                    Pixel detectedPixel = calledPixel.getSurroundingPixel(Angle.getAngle(degree));
                    detectedPixel.setGameObject(gameObject);
                    gameObject.setHeadPixel(detectedPixel);
                    if(viewManager != null){
                        viewManager.addObjectToView(gameObject);
                    }
                    return true;
                }
                degree += Angle.STEP.getAngle();
            }
        }
        Console.getConsole().printTracingMessage("failed to drop object");
        return false;
    }

    /**
     *
     * @param x recommended x to drop game object
     * @param y recommended y to drop game object
     * @param spell is my card
     * @return true if it could drop game object in map!
     */
    public boolean dropSpell(int x, int y, Spell spell){

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
        if(x < 0 || y < 0 || x >= width || y >= height)
            return null;
        return pixels[x][y];
    }

    /**
     * Initializes all pixel
     */
    private void buildMap(){
        for(int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                pixels[i][j] = new Pixel(i, j);
            }
        }
    }

    /**
     * Connects pixel graph
     */
    private void matchGraph(){
        for(int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                Pixel currentPixel = pixels[i][j];
                if((i-1) >= 0){
                    currentPixel.setSurroundingPixel(Angle.WEST, pixels[i-1][j]);
                    pixels[i-1][j].setSurroundingPixel(Angle.EAST, currentPixel);
                }
                if((j-1) >= 0){
                    currentPixel.setSurroundingPixel(Angle.SOUTH, pixels[i][j-1]);
                    pixels[i][j-1].setSurroundingPixel(Angle.NORTH, currentPixel);
                    if((i-1) >= 0){
                        currentPixel.setSurroundingPixel(Angle.SOUTH_WEST, pixels[i-1][j-1]);
                        pixels[i-1][j-1].setSurroundingPixel(Angle.NORTH_EAST, currentPixel);
                    }
                    if((i+1) < width){
                        currentPixel.setSurroundingPixel(Angle.SOUTH_EAST, pixels[i+1][j-1]);
                        pixels[i+1][j-1].setSurroundingPixel(Angle.NORTH_WEST, currentPixel);
                    }
                }
            }
        }
    }

    /**
     * Locks all pixels
     */
    private void lock(){
        for(int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                pixels[i][j].lock();
            }
        }
    }

    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    // Getters
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

}
