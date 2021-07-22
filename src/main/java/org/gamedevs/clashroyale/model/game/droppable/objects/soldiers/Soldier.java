package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.battle.engine.map.path.Path;
import org.gamedevs.clashroyale.model.game.battle.engine.map.path.PathFinder;
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.console.ConsoleColor;

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
    protected int speed = 1; // TODO: remove this and put in in constructor
    /**
     * Area splash ability (attackOrMove point or effect)
     */
    protected boolean areaSplash;
    /**
     * closest target tile
     */
    protected Tile closestTargetTile;
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
        state = GameObjectState.MOVING;
        previousState = GameObjectState.IDLE;
        closestTargetTile = null;
    }

    @Override
    protected void attackOrMove(GameObject target) {
        if (target != null) {
//            new Bullet(this).throwBullet(headTile, target.getHeadTile());
            state = GameObjectState.ATTACK;
            if(previousState != state){
                initialFrame = currentFrame;
                previousState = state;
            }
            // checking frame rate updateif in attack
            if((currentFrame - initialFrame) % (hitSpeed * 10) == 0){
                target.reduceHP(damage);
            }
        } else {
            // TODO: this is set for buildings -> for soldier it must be moving
            state = GameObjectState.MOVING;
            if(previousState != state){
                initialFrame = currentFrame;
                previousState = state;
            }
        }
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
            Console.getConsole().printTracingMessage(ConsoleColor.RED_BOLD + "In findClosestTargetTile");
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
        return nextTile;
    }

    /**
     * move me to next tile
     *
     * @param nextTile next tile
     * @return true if i have moved successfully
     */
    protected boolean move(Tile nextTile) {
        if(nextTile == null)
            Console.getConsole().printTracingMessage(ConsoleColor.GREEN_BOLD + "In Move - nextTile is null");
        if (nextTile != null) {
            Angle nextTileAngel = headTile.getSurroundingTileAngel(nextTile);
            Console.getConsole().printTracingMessage(ConsoleColor.GREEN_BOLD + "In Move - next tile not empty");
            if (nextTileAngel != null) {
                if (headTile.carry(angle, z)) {
                    Console.getConsole().printTracingMessage(ConsoleColor.GREEN_BOLD + "Moved");
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
        Console.getConsole().printTracingMessage(ConsoleColor.GREEN_BOLD + "1- mover called");
        if(pathFinder == null){
            pathFinder = new PathFinder(battleField);
            path = pathFinder.getPath();
        }
        if (state == GameObjectState.MOVING) {
            if((currentFrame - initialFrame) % ((speed * 10)) != 0)
                return;
            Console.getConsole().printTracingMessage(ConsoleColor.GREEN_BOLD + "In moving state");
            if(closestTargetTile == null){
                closestTargetTile = findClosestTargetTile();
                Console.getConsole().printTracingMessage(ConsoleColor.GREEN_BOLD + "findClosestTargetTile is null");
            }
            if (closestTargetTile != null) {
                closestTargetTile = findClosestTargetTile();
                pathFinder.findPath(headTile, closestTargetTile, z);
                if(!move(path.forward()))
                Console.getConsole().printTracingMessage(ConsoleColor.GREEN_BOLD + "Moving soldier " +
                        ConsoleColor.RED_BOLD + "Failed!!!!");
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

    public double getSpeed() {
        return speed;
    }

}
