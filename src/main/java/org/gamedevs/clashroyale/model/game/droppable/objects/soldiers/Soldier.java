package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.battle.engine.map.path.Path;
import org.gamedevs.clashroyale.model.game.battle.engine.map.path.PathFinder;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

import java.util.ArrayList;

/**
 * a class which handle soliders
 * @author Pouya Mohammadi -Hosna Hoseini
 * 9826039 -CE@AUT     9823010 -CE@AUT
 * @version 1.0
 */
public abstract class Soldier extends GameObject {

    /**
     * Speed of player (related to movement)
     */
    protected int speed;
    /**
     * Area splash ability (attackOrMove point or effect)
     */
    protected boolean areaSplash;
    /**
     * Path finder motor
     */
    protected PathFinder pathFinder;
    /**
     * founded path
     */
    protected Path path;

    /**
     * Setting default values for soldier object
     */
    protected Soldier(Side side) {
        super(side);
        myType = TargetType.GROUND; // except for baby dragon!
    }

    /**
     * Updates soldier about one thread
     */
    @Override
    public void run() {
        checkTargetRange();
        mover();
        currentFrame++;
    }

    /**
     * this algorithm finds closest target tile
     *
     * @return tile of closest target
     */
    protected Tile findClosestTargetTile() {
        ArrayList<GameObject> aliveEnemies = battleField.getOneSideObjects(Side.getOppositeSide(teamSide));
        aliveEnemies = new ArrayList<GameObject>(aliveEnemies);
        Tile nextTile = null;
        if (aliveEnemies != null) {
            double distance = 1000; // TODO: max distance - unreachable
            for (GameObject enemy : aliveEnemies) {
                // Giant
                if (attackTargetType == TargetType.BUILDING) {
                    if (enemy.getMyType() == TargetType.BUILDING) {
                        if (distance > battleField.calculateDistance(headTile, enemy.getHeadPixel())) {
                            distance = battleField.calculateDistance(headTile, enemy.getHeadPixel());
                            nextTile = enemy.getHeadPixel();
                        }
                    }
                } else if (attackTargetType == TargetType.GROUND) {
                    if (enemy.getMyType() == TargetType.BUILDING || enemy.getMyType() == TargetType.GROUND) {
                        if (distance > battleField.calculateDistance(headTile, enemy.getHeadPixel())) {
                            distance = battleField.calculateDistance(headTile, enemy.getHeadPixel());
                            nextTile = enemy.getHeadPixel();
                        }
                    }
                } else {
                    if (distance > battleField.calculateDistance(headTile, enemy.getHeadPixel())) {
                        distance = battleField.calculateDistance(headTile, enemy.getHeadPixel());
                        nextTile = enemy.getHeadPixel();
                    }
                }
            }
        }
        return null;
    }

    /**
     * move me to next tile
     *
     * @param nextTile next tile
     * @return true if i have moved successfully
     */
    protected boolean move(Tile nextTile) {
        if (nextTile != null) {
            Angle nextTileAngel = headTile.getSurroundingTileAngel(nextTile);
            if (nextTileAngel != null) {
                if (headTile.carry(angle, z)) {
                    headTile = nextTile;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates object to next place
     */
    protected void mover() {
        if(pathFinder == null){
            pathFinder = new PathFinder(battleField);
            path = pathFinder.getPath();
        }
        if (state == GameObjectState.MOVING) {
            Tile closestTargetTile = findClosestTargetTile();
            if (closestTargetTile != findClosestTargetTile()) {
                closestTargetTile = findClosestTargetTile();
                pathFinder.findPath(headTile, closestTargetTile, z);
                move(path.forward());
            }

        }
    }

    /**
     * boosts the soldier property
     */
    @Override
    public void boost(){
        if(!boost){
            super.boost();
            speed *= 1.4;
        }
    }

    /**
     * removes boost of soldier property
     */
    @Override
    public void unboost(){
        if(boost){
            super.unboost();
            speed *= (1/1.4);
        }
    }

}
