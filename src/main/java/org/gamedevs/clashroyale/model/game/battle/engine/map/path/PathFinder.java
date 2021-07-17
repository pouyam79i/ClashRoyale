package org.gamedevs.clashroyale.model.game.battle.engine.map.path;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;

import java.util.ArrayList;

/**
 * Finds the best path between 2 tiles!
 */
public class PathFinder {

    /**
     * path container
     */
    private Path path;
    /**
     * battle field of game
     */
    private Map map;

    /**
     * Constructor of path finder
     * @param map of game
     */
    public PathFinder(Map map){
        path = new Path();
        this.map = map;
    }

    /**
     * Find the best path between two tile
     * @param src beginning tile
     * @param des destination tile
     */
    public void findPath(Tile src, Tile des, int z){
        if(src == null || des == null)
            return;
        ArrayList<Tile> newPathList = new ArrayList<Tile>();
        Tile headingTile = src;
        Angle bestAngle = findBestAngel(headingTile.getX(), headingTile.getY(), des.getX(), des.getY());
        while (bestAngle != null){
            while (!headingTile.getSurroundingPixel(bestAngle).isEmpty(z)){
                bestAngle = Angle.getAngle(bestAngle.getAngle() + 45); // TODO: improve its performance
            }
            newPathList.add(headingTile.getSurroundingPixel(bestAngle));
            bestAngle = findBestAngel(headingTile.getX(), headingTile.getY(), des.getX(), des.getY());
        }
        newPathList.add(des);
        path.setPath(newPathList);
    }

    /**
     * best angel between to point!
     * @param srcX init x
     * @param srcY init y
     * @param desX dest x
     * @param desY dest y
     * @return best angle, if they are equal it returns null.
     */
    private Angle findBestAngel(int srcX, int srcY, int desX, int desY){
        if(srcX - desX > 0){
            if(srcY - desY > 0){
                return Angle.SOUTH_WEST;
            }
            else if(srcY - desY < 0){
                return Angle.NORTH_WEST;
            }
            else {
                return Angle.WEST;
            }
        }
        else if(srcX - desX < 0){
            if(srcY - desY > 0){
                return Angle.SOUTH_EAST;
            }
            else if(srcY - desY < 0){
                return Angle.NORTH_EAST;
            }
            else {
                return Angle.EAST;
            }
        }
        else {
            if(srcY - desY > 0){
                return Angle.SOUTH;
            }
            else if(srcY - desY < 0){
                return Angle.NORTH;
            }
            else {
                return null;
            }
        }
    }

    // Getters
    public Path getPath() {
        return path;
    }

}
