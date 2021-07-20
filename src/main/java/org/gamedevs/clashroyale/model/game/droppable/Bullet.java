package org.gamedevs.clashroyale.model.game.droppable;

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
        Thread thread = new Thread() {
            @Override
            public void start() {

                Image image = GameImageContainer.getGameImageContainer().getThrowable(droppable.getNameOfDroppable());

                if (image != null) {
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    imageView.setRotate(imageView.getRotate() + droppable.getAngle().getAngle() + 180);
                    double curX = source.getX();
                    double curY = source.getY();
                    double sleepTime = droppable.getHitSpeed() / Math.abs(source.distance(destination)) * 1000 * 2;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().add(imageView);
                            imageView.setLayoutX(source.getX());
                            imageView.setLayoutY(source.getY());
                        }
                    });

                    while (curX != destination.getX() ||
                            curY != destination.getY()) {

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
        Point2D source = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(sourceTile.getX()),
                MouseTilePosition.TranslateTileToPixelY(sourceTile.getY()));
        Point2D destination = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(destinationTile.getX()),
                MouseTilePosition.TranslateTileToPixelY(destinationTile.getY()));
        throwBullet(source, destination);
    }

    /**
     * throw a bullet in GUI
     *
     * @param start           source point
     * @param destinationTile destinationTile
     */
    public void throwBullet(Point2D start, Tile destinationTile) {
        Point2D destination = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(destinationTile.getX()),
                MouseTilePosition.TranslateTileToPixelY(destinationTile.getY()));
        Console.getConsole().printTracingMessage(destinationTile.getX() + "," + destinationTile.getY());
        throwBullet(start, destination);
    }
}
