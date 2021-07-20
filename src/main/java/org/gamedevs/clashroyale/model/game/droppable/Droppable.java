package org.gamedevs.clashroyale.model.game.droppable;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;
import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;

/**
 * This class contains the structure of all droppable types!
 *
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public abstract class Droppable extends Runnable {

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
     * angle of object
     */
    protected Angle angle = Angle.NORTH;
    // Position properties:
    /**
     * Head pixel of object!
     */
    protected Tile headTile;

    /**
     * Constructor of Droppable.
     * Sets default of droppable.
     *
     * @param dropType type of drop
     * @param side     side of drop
     */
    protected Droppable(DropType dropType, Side side) {
        this.dropType = dropType;
        this.teamSide = side;
    }

    public CardName getNameOfDroppable() {
        return nameOfDroppable;
    }



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
}
