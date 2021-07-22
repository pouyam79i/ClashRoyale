package org.gamedevs.clashroyale.model.game.droppable;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
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
        source = new Point2D.Float((float) source.getX() + 10, (float) source.getY() + 8);
        destination = new Point2D.Float((float) destination.getX() + 10, (float) destination.getY() + 8);
        Point2D finalSource = source;
        Point2D finalDestination = destination;
        Thread thread = new Thread() {
            @Override
            public void start() {

                Image image = GameImageContainer.getGameImageContainer().getThrowable(droppable.getNameOfDroppable());

                if (image != null) {
                    Console.getConsole().printTracingMessage(droppable.nameOfDroppable.toString() +" bullet throwing");
                    ImageView imageView = new ImageView(image);
                    if (droppable.nameOfDroppable == CardName.CANNON ||
                            droppable.nameOfDroppable == CardName.BABY_DRAGON ||
                            droppable.nameOfDroppable == CardName.ARCHERS ||
                            droppable.nameOfDroppable == CardName.KING_TOWER ||
                            droppable.nameOfDroppable == CardName.PRINCESS_TOWER) {
                        imageView.setFitWidth(10);
                        imageView.setFitHeight(10);
                    } else {

                        imageView.setFitWidth(25);
                        imageView.setFitHeight(25);
                    }
                    imageView.setRotate(imageView.getRotate() + droppable.getAngle().getAngle() + 180);
                    double curX = finalSource.getX();
                    double curY = finalSource.getY();
                    double sleepTime = droppable.getHitSpeed() / Math.abs(finalSource.distance(finalDestination)) * 1000 * 2;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().add(imageView);
                            imageView.setLayoutX(finalSource.getX());
                            imageView.setLayoutY(finalSource.getY());
                        }
                    });

                    while (curX != finalDestination.getX() ||
                            curY != finalDestination.getY()) {

                        double deltaX = finalDestination.getX() - curX;
                        double deltaY = finalDestination.getY() - curY;
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
            }

        };
        thread.start();

    }

    /**
     * throw a bullet in GUI
     *
     * @param sourceTile      sourceTile
     * @param destinationTile destinationTile
     */
    public void throwBullet(Tile sourceTile, Tile destinationTile) {
        if(sourceTile!= null && destinationTile != null) {
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
        if(destinationTile != null) {

            Point2D destination = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(destinationTile.getX()),
                    MouseTilePosition.TranslateTileToPixelY(destinationTile.getY()));
            throwBullet(start, destination);
        }
    }
}
