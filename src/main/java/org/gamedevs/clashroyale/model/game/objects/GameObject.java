package org.gamedevs.clashroyale.model.game.objects;

import org.gamedevs.clashroyale.model.game.battle.field.Angle;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * Main structure game object class!
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public abstract class GameObject extends Runnable {

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
    protected TargetType target;

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

    /**
     * Setting default values for game object
     */
    protected GameObject(){

    }

    public GameObject(int hp, int damage, int hitSpeed, int range) {
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
    }

    /**
     * Start attacking to the target (gives damage to target object)
     */
    protected void attack(){

    }

    /**
     * Checks attack range,
     * if target is in attack range,
     * calls attack() on that target!
     */
    protected void checkTargetRange(){

    }

}
