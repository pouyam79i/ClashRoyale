package org.gamedevs.clashroyale.model.game.battle.engine.map;

import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.spell.Spell;
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
     * Graph of tiles
     */
    private Tile[][] tiles;

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
        tiles = new Tile[width][height];
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
        ArrayList<Tile> visitedTile;
        Tile calledTile = getPixel(x, y);
        if(calledTile == null){
            Console.getConsole().printTracingMessage("called pixel (" + x + "," + y + ") does not exist!");
            return false;
        }
        if(calledTile.isEmpty(gameObject.getZ())){
            calledTile.setGameObject(gameObject);
            gameObject.setHeadPixel(calledTile);
            if(viewManager != null){
                viewManager.addObjectToView(gameObject);
            }
            return true;
        }
        int maxRadius = 1;      // TODO: define this in side configs
        for(int radius = 0; radius < maxRadius; radius++){
            for(int degree = 0; degree < 360;){
                if(calledTile.peak(Angle.getAngle(degree), gameObject.getZ())){
                    Tile detectedTile = calledTile.getSurroundingPixel(Angle.getAngle(degree));
                    detectedTile.setGameObject(gameObject);
                    gameObject.setHeadPixel(detectedTile);
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
     * calculates the distance between two tiles
     * @param src beginning tile
     * @param des ending tile
     * @return double value of distance
     */
    public double calculateDistance(Tile src, Tile des){
        if(src == null || des == null)
            return -1;
        int x1 = src.getX();
        int y1 = src.getY();
        int x2 = des.getX();
        int y2 = des.getY();
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    /**
     * Getting a pixel with its tiles!
     * Use this to read tiles
     * @param x of pixel
     * @param y of pixel
     * @return pixel of found (else null)
     */
    public Tile getPixel(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return null;
        return tiles[x][y];
    }

    /**
     * Initializes all pixel
     */
    private void buildMap(){
        for(int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Connects pixel graph
     */
    private void matchGraph(){
        for(int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                Tile currentTile = tiles[i][j];
                if((i-1) >= 0){
                    currentTile.setSurroundingPixel(Angle.WEST, tiles[i-1][j]);
                    tiles[i-1][j].setSurroundingPixel(Angle.EAST, currentTile);
                }
                if((j-1) >= 0){
                    currentTile.setSurroundingPixel(Angle.SOUTH, tiles[i][j-1]);
                    tiles[i][j-1].setSurroundingPixel(Angle.NORTH, currentTile);
                    if((i-1) >= 0){
                        currentTile.setSurroundingPixel(Angle.SOUTH_WEST, tiles[i-1][j-1]);
                        tiles[i-1][j-1].setSurroundingPixel(Angle.NORTH_EAST, currentTile);
                    }
                    if((i+1) < width){
                        currentTile.setSurroundingPixel(Angle.SOUTH_EAST, tiles[i+1][j-1]);
                        tiles[i+1][j-1].setSurroundingPixel(Angle.NORTH_WEST, currentTile);
                    }
                }
            }
        }
    }

    /**
     * Locks all tiles
     */
    private void lock(){
        for(int j = 0; j < height; j++){
            for (int i = 0; i < width; i++){
                tiles[i][j].lock();
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
