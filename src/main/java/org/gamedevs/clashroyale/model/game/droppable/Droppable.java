package org.gamedevs.clashroyale.model.game.droppable;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * This class contains the structure of all droppable types!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.1
 */
public abstract class Droppable {

    /**
     * Type of drop!
     * 'Object' or 'Spell'.
     */
    private final DropType dropType;
    /**
     * team side of droppable
     */
    protected final Side teamSide;
    /**
     * card name of droppable
     */
    protected CardName nameOfDroppable;
    /**
     * BattleField
     */
    protected Map battleField;
    /**
     * speed of attacking
     * (giving damage to the enemy)
     */
    protected double hitSpeed;
    /**
     * angle of droppable
     */
    protected Angle angle;
    // Position properties:
    /**
     * Head tile of object!
     */
    protected Tile headTile;
    // Action property:
    /**
     * first frame
     */
    protected long initialFrame;
    /**
     * currnet frame
     */
    protected long currentFrame;

    /**
     * Constructor of Droppable.
     * Sets default of droppable.
     * @param dropType type of drop
     * @param side     side of drop
     */
    protected Droppable(DropType dropType, Side side) {
        this.dropType = dropType;
        this.teamSide = side;
        // Setting initial angle
        if(side == Side.DOWN){
            angle = Angle.NORTH;
        }else {
            angle = Angle.SOUTH;
        }
    }

    /**
     * advances one frame
     */
    public abstract void run();

    // Getters
    public Side getTeamSide() {
        return teamSide;
    }
    public DropType getDropType() {
        return dropType;
    }
    public Tile getHeadPixel() {
        return headTile;
    }
    public CardName getNameOfDroppable() {
        return nameOfDroppable;
    }

    // Setters
    public void setBattleField(Map battleField) {
        this.battleField = battleField;
    }
    public void setHeadPixel(Tile headTile) {
        this.headTile = headTile;
    }
    public Angle getAngle() {
        return angle;
    }
    public double getHitSpeed() {
        return hitSpeed;
    }
    public void setInitialFrame(long initialFrame) {
        this.initialFrame = initialFrame;
    }

    public void setAngle(Angle angle) {
        this.angle = angle;
    }
}
