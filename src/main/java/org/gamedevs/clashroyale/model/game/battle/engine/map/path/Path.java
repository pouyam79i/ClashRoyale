package org.gamedevs.clashroyale.model.game.battle.engine.map.path;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;

import java.util.ArrayList;

/**
 * This class contains the path for soldiers to move forward
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class Path {

    /**
     * path collections
     */
    private ArrayList<Tile> path;
    /**
     * index of current tile
     */
    private int index = 0;

    /**
     * Constructor of Path
     * sets
     */
    public Path(){
        index = 0;
        path = new ArrayList<Tile>();
    }

    /**
     * resets the current path
     */
    public void reset(){
        index = 0;
        path = new ArrayList<Tile>();
    }

    /**
     * sets a new path
     * @param newPath will be set as current path
     */
    public void setPath(ArrayList<Tile> newPath){
        if(newPath != null){
            index = 0;
            path = newPath;
        }
    }

    /**
     * go one tile forward to the point!
     * if reached or empty returns null
     * @return next tile
     */
    public Tile forward(){
        if(index < path.size()){
            index++;
            return path.get(index - 1);
        }
        return null;
    }

    public int getCost(){
        return (path.size() - index);
    }

}
