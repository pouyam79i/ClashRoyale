package org.gamedevs.clashroyale.model.game.objects;

import org.gamedevs.clashroyale.model.game.battle.field.Angle;
import org.gamedevs.clashroyale.model.game.battle.field.DropType;
import org.gamedevs.clashroyale.model.game.battle.field.Droppable;
import org.gamedevs.clashroyale.model.game.battle.field.Pixel;
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
    /**
     * Object do not attack same sides
     */
    protected final Side objectTeamSide;
    // Shape property
    protected boolean isStar;
    protected double neededTiles;
    protected Pixel centerPixel;

    /**
     * Constructor of game object
     * @param side side of object
     */
    protected GameObject(Side side) {
        super(DropType.OBJECT);
        this.objectTeamSide = side;
        angle = Angle.NORTH;
        state = GameObjectState.IDLE;
        z = 0;
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
