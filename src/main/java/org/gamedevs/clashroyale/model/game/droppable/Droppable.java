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

    public void throwBullet(Image image, Point2D source, Point2D destination) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        imageView.setRotate(imageView.getRotate() + angle.getAngle() + 180);
        double curX = source.getX();
        double curY = source.getY();
        double sleepTime = hitSpeed / Math.abs(source.distance(destination)) * 1000;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().add(imageView);
                imageView.setLayoutX(source.getX());
                imageView.setLayoutY(source.getY());
            }
        });

        while (curX != destination.getY() ||
                curY != destination.getX()) {

            double deltaX = destination.getX() - curX;
            double deltaY = destination.getY() - curY;
            if (deltaX != 0) {
                curX = curX + (deltaX > 0 ? 1 : -1);
            }
            if (deltaY != 0) {
                curY = curY + Math.abs(deltaY / deltaX) * (deltaY > 0 ? 1 : -1);
            }
            double finalCurY = curY;
            double finalCurX = curX;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    imageView.setLayoutX(finalCurX);
                    imageView.setLayoutY(finalCurY);
                }
            });
            try {
                Thread.sleep((long) sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(imageView);
            }
        });

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

}
