package org.gamedevs.clashroyale.model.game.battle.engine.map.path;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.console.ConsoleColor;

import java.util.ArrayList;

/**
 * Finds the best path between 2 tiles!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
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
        if (src == des){
            Console.getConsole().printTracingMessage(ConsoleColor.BLUE_BOLD + "Warning: no need to find path! src is des");
            path.reset();
            return;
        }
        ArrayList<Tile> newPathList = new ArrayList<Tile>();
        Tile headingTile = src;
        Angle bestAngle = findBestAngel(headingTile.getX(), headingTile.getY(), des.getX(), des.getY());
        int counterLimit = 0;
        pathFinderLoop:
        while (bestAngle != null){
            for(int i = 0; i <= 360; i = i + 45){
                if (headingTile.getSurroundingPixel(bestAngle) == null){
                    bestAngle = Angle.getAngle(bestAngle.getAngle() + 45);
                    if(bestAngle == null){
                        Console.getConsole().printTracingMessage(ConsoleColor.BLUE_BOLD + "Failed to find best angle");
                        break pathFinderLoop;
                    }
                    continue;
                }
                else if(!headingTile.getSurroundingPixel(bestAngle).isEmpty(z)){
                    bestAngle = Angle.getAngle(bestAngle.getAngle() + 45);
                    if(bestAngle == null){
                        Console.getConsole().printTracingMessage(ConsoleColor.BLUE_BOLD + "Failed to find best angle");
                        break pathFinderLoop;
                    }
                }
                else {
                    break;
                }
            }
            if(headingTile.getSurroundingPixel(bestAngle) == null){
                break ;
            }else {
                headingTile = headingTile.getSurroundingPixel(bestAngle);
            }
            newPathList.add(headingTile);
            counterLimit++;
            if(counterLimit > 100){
                Console.getConsole().printTracingMessage(ConsoleColor.BLUE_BOLD + "Counter limit over loaded");
                break;
            }
        }
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
        if(srcX == desX && srcY == desY){
            Console.getConsole().printTracingMessage(ConsoleColor.BLUE_BOLD + "Null angle as src == des");
            return null;
        }
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
                Console.getConsole().printTracingMessage(ConsoleColor.BLUE_BOLD + "Null angle as no more value!");
                return null;
            }
        }
    }

    // Getters
    public Path getPath() {
        return path;
    }

}
