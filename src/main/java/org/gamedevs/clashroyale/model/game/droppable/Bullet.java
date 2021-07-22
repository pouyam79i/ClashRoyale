package org.gamedevs.clashroyale.model.game.droppable;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.GameImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.awt.geom.Point2D;

/**
 * a class which handle any kind of bullet for game objects
 *
 * @author Hosna Hoseini
 * 9823010 -CE@AUT
 * @version 1.0
 */
public class Bullet {
    private Droppable droppable;

    public Bullet(Droppable droppable) {
        this.droppable = droppable;
    }

    /**
     * throw a bullet in GUI
     *
     * @param source      source point
     * @param destination destination point
     */
    public void throwBullet(Point2D source, Point2D destination) {

        Image img = GameImageContainer.getGameImageContainer().getThrowable(droppable.getNameOfDroppable());
        if(img != null) {
            ImageView imageView = new ImageView(img);
            if (droppable.nameOfDroppable == CardName.CANNON ||
                    droppable.nameOfDroppable == CardName.BABY_DRAGON ||
                    droppable.nameOfDroppable == CardName.ARCHERS ||
                    droppable.nameOfDroppable == CardName.KING_TOWER ||
                    droppable.nameOfDroppable == CardName.PRINCESS_TOWER ||
                    droppable.nameOfDroppable == CardName.WIZARD) {
                imageView.setFitWidth(10);
                imageView.setFitHeight(10);
            } else {

                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
            }
            Path path = new Path();

            MoveTo moveTo = new MoveTo(source.getX(), source.getY());
            LineTo lineTo = new LineTo(destination.getX(), destination.getY());

            path.getElements().add(moveTo);
            path.getElements().add(lineTo);

            //Creating a path transition
            PathTransition pathTransition = new PathTransition();
            pathTransition.setDuration(Duration.millis(droppable.getHitSpeed() * 1000));
            pathTransition.setNode(imageView);
            pathTransition.setPath(path);
            pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
            pathTransition.setCycleCount(1);
            pathTransition.setAutoReverse(true);
            pathTransition.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(imageView);
                }
            });
            //Playing the animation
            pathTransition.play();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().add(imageView);
                }
            });

        }
    }

    /**
     * throw a bullet in GUI
     *
     * @param sourceTile      sourceTile
     * @param destinationTile destinationTile
     */
    public void throwBullet(Tile sourceTile, Tile destinationTile) {
        if (sourceTile != null && destinationTile != null) {
            Point2D source = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(sourceTile.getX()),
                    MouseTilePosition.TranslateTileToPixelY(sourceTile.getY()));
            Point2D destination = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(destinationTile.getX()),
                    MouseTilePosition.TranslateTileToPixelY(destinationTile.getY()));
            throwBullet(source, destination);
        }
    }

    /**
     * throw a bullet in GUI
     *
     * @param start           source point
     * @param destinationTile destinationTile
     */
    public void throwBullet(Point2D start, Tile destinationTile) {
        if (destinationTile != null) {
            Point2D destination = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(destinationTile.getX()),
                    MouseTilePosition.TranslateTileToPixelY(destinationTile.getY()));
            throwBullet(start, destination);
        }
    }
}
