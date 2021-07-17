package org.gamedevs.clashroyale.model.game.droppable.objects;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.DropType;
import org.gamedevs.clashroyale.model.game.droppable.Droppable;
import org.gamedevs.clashroyale.model.game.player.Side;

/**
 * Main structure game object class!
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public abstract class GameObject extends Droppable {

    /**
     * hit point
     * (when character dies or health of character)
     */
    protected int hp;
    /**
     * amount of giving damage!
     */
    protected int damage;
    /**
     * speed of attacking
     * (giving damage to the enemy)
     */
    protected double hitSpeed;
    /**
     * attack range defines
     * the max attacking radius!
     */
    protected double range;
    /**
     * attack target
     * the max attacking radius!
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
     * angle of object
     */
    protected Angle angle;
    /**
     * State of game object
     */
    protected GameObjectState state;

    // Position properties:
    /**
     * Head pixel of object!
     */
    protected Tile headTile;

    /**
     * Constructor of game object
     * @param side side of object
     */
    protected GameObject(Side side) {
        super(DropType.OBJECT, side);
        angle = Angle.NORTH;
        state = GameObjectState.IDLE;
        z = 0; // This is zero except for baby dragon!
    }

    /**
     * Start attacking to the target (gives damage to target object)
     */
    protected void attack(GameObject target){
        if(target != null)
            target.reduceHP(damage);
    }

    /**
     * reduces this object hp.
     * @param takenDamage is the damage given to game object by enemy!
     */
    public synchronized void reduceHP(int takenDamage){
        hp -= takenDamage;
    }

    /**
     * Checks attack range,
     * if target is in attack range,
     * calls attack() on that target!
     */
    protected void checkTargetRange(){

    }

    // Getters
    public GameObjectState getState() {
        return state;
    }
    public Angle getAngle() {
        return angle;
    }
    public Tile getHeadPixel() {
        return headTile;
    }
    public int getHp() {
        return hp;
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

    // Setters
    public void setZ(int z) {
        this.z = z;
    }
    public void setHeadPixel(Tile headTile) {
        this.headTile = headTile;
    }

}
