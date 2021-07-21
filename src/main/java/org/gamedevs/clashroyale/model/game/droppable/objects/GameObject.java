package org.gamedevs.clashroyale.model.game.droppable.objects;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
import org.gamedevs.clashroyale.model.game.droppable.DropType;
import org.gamedevs.clashroyale.model.game.droppable.Droppable;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.ViewUpdater;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * Main structure game object class!
 *
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public abstract class GameObject extends Droppable {

    /**
     * hit point
     * (when character dies or health of character)
     */
    protected DoubleProperty hp = new SimpleDoubleProperty();
    /**
     * amount of giving damage!
     */
    protected int damage;

    /**
     * attack range defines
     * the max attacking radius!
     */
    protected double range;
    /**
     * attack target type
     */
    protected TargetType attackTargetType;
    /**
     * Type of this object
     */
    protected TargetType myType;
    /**
     * z value says if the object
     * is on the ground or in the air!
     */
    protected int z;

    /**
     * State of game object
     */
    protected GameObjectState state;
    /**
     * error for showing img in GUI
     */
    protected double errorInGUIX;
    protected double errorInGUIY;

    // Action property:
    /**
     * to detect state changing!
     */
    protected GameObjectState previousState;

    /**
     * if this G.O. is currently boosted -> true
     */
    protected boolean boost = false;

    /**
     * view updater
     */
    private ViewUpdater viewUpdater;
    /**
     * Constructor of game object
     *
     * @param side side of object
     */
    protected GameObject(Side side) {
        super(DropType.OBJECT, side);
        if (side == Side.TOP)
            angle = Angle.SOUTH;
        else
            angle = Angle.NORTH;
        // TODO: it is set for soldiers - change it to IDLE for towers
        state = GameObjectState.MOVING;
        z = 0; // This is zero except for baby dragon!
    }

    /**
     * updates the object one frame
     */
    @Override
    public void run(){
        checkTargetRange();
        currentFrame++;
    }

    /**
     * decide to attack or change state
     */
    protected void attackOrMove(GameObject target) {
        if (target != null) {
            new Bullet(this).throwBullet(headTile, target.getHeadTile());
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
            state = GameObjectState.IDLE;
            if(previousState != state){
                initialFrame = currentFrame;
                previousState = state;
            }
        }
    }

    /**
     * reduces this object hp.
     * @param takenDamage is the damage given to game object by enemy!
     */
    public synchronized void reduceHP(int takenDamage) {
        hp.setValue(hp.subtract(takenDamage).getValue());
    }

    /**
     * Checks attackOrMove range,
     * if target is in attackOrMove range,
     * calls attackOrMove() on that target!
     */
    protected void checkTargetRange() {
        GameObject lockedTarget = null;
        int x, y;       // beginning x,y of search area
        x = headTile.getX() - (int) Math.round(range);
        y = headTile.getY() - (int) Math.round(range);
        checkAttack:
        for (int j = 0; j <= (Math.round(range) * 2 + 1); j++) {
            for (int i = 0; i <= (Math.round(range) * 2 + 1); i++) {
                Tile searchTile = battleField.getPixel(x + i, y + j);
                if (searchTile != null) {
                    if (battleField.calculateDistance(headTile, searchTile) <= Math.round(range)) {
                        GameObject target = searchTile.getGameObject();
                        if(target == null){
                            continue;
                        }
                        if(target.getTeamSide() == teamSide){
                            continue;
                        }
                        if (attackTargetType == TargetType.BUILDING) {
                            if (target.getMyType() == TargetType.BUILDING) {
                                if (target.getHp() > 0) {
                                    lockedTarget = target;
                                    break checkAttack;
                                }
                            }
                        }
                        // Ground soldiers
                        else if (attackTargetType == TargetType.GROUND) {
                            if (target.getMyType() == TargetType.GROUND) {
                                if (target.getHp() > 0) {
                                    lockedTarget = target;
                                    break checkAttack;
                                }
                            } else if (target.getMyType() == TargetType.BUILDING) {
                                if (target.getHp() > 0) {
                                    lockedTarget = target;
                                    break checkAttack;
                                }
                            }
                        }
                        // Air and Ground targets
                        else if (attackTargetType == TargetType.AIR_GROUND) {
                            if (target.getMyType() == TargetType.AIR || target.getMyType() == TargetType.GROUND) {
                                if (target.getHp() > 0) {
                                    lockedTarget = target;
                                    break checkAttack;
                                }
                            } else if (target.getMyType() == TargetType.BUILDING) {
                                if (target.getHp() > 0) {
                                    lockedTarget = target;
                                    break checkAttack;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (lockedTarget != null) {
            if (lockedTarget.getHp() <= 0) {
                lockedTarget = null;
            }
        }
        attackOrMove(lockedTarget);
    }

    /**
     * Boost object property
     */
    public void boost(){
        if(!boost){
            boost = true;
            damage *= 1.4;
            hitSpeed *= 1.4;
        }
    }

    /**
     * Remove boost object property
     */
    public void unboost(){
        if(boost){
            boost = false;
            damage *= (1/1.4);
            hitSpeed *= (1/1.4);
        }
    }

    // Getters
    public GameObjectState getState() {
        return state;
    }
    public Angle getAngle() {
        return angle;
    }
    public double getHp() {
        return hp.get();
    }
    public DoubleProperty hpProperty() {
        return hp;
    }
    public Tile getHeadTile() {
        return headTile;
    }
    public int getDamage() {
        return damage;
    }
    public double getHitSpeed() {
        return hitSpeed;
    }
    public double getRange() {
        return range;
    }
    public TargetType getAttackTargetType() {
        return attackTargetType;
    }
    public TargetType getMyType() {
        return myType;
    }
    public int getZ() {
        return z;
    }
    public Side getTeamSide() {
        return teamSide;
    }
    public double getErrorInGUIX() {
        return errorInGUIX;
    }
    public double getErrorInGUIY() {
        return errorInGUIY;
    }

    // Setters
    public void setState(GameObjectState state) {
        this.state = state;
    }
    public boolean isBoost() {
        return boost;
    }

}
