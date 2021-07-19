package org.gamedevs.clashroyale.model.updater.battle;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.GameDroppableImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public class ViewUpdater extends Runnable {

    private GameDroppableImageContainer imageContainer;
    private final AnchorPane battleFieldPane;
    private final GameObject gameObject;
    private final CardName cardName;
    private ObjectView objectView;
    private boolean isEnemy;
    private GameObjectState previousState;
    private Angle previousAngle;
    private Tile previousTile;

    public ViewUpdater(GameObject gameObject, boolean isEnemy) {
        threadName = "ViewUpdater";
        imageContainer = GameDroppableImageContainer.getGameDroppableImageContainer();
        this.battleFieldPane = MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable();
        this.gameObject = gameObject;
        cardName = gameObject.getNameOfDroppable();
        this.isEnemy = isEnemy;
        previousTile = gameObject.getHeadPixel();
        previousState = gameObject.getState();
        previousAngle = gameObject.getAngle();
    }

    @Override
    public void run() {
        // Initializing thread needs
        Image currentImage = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
        objectView = new ObjectView(gameObject, currentImage);
        objectView.getImageView().setFitWidth(currentImage.getWidth() / 2.5);
        objectView.getImageView().setFitHeight(currentImage.getHeight() / 2.5);
        int x = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
        int y = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
        Console.getConsole().printTracingMessage("x, y final: " + x + ", " + y);
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            objectView.setLayoutX(x - gameObject.getErrorInGUIX() - MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO);
            objectView.setLayoutY(y - gameObject.getErrorInGUIY() + MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO);
        });


        update();
    }


    private void updateImg() {

        if (previousAngle != gameObject.getAngle() ||
                previousState != gameObject.getState()) {
            Image img = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    objectView.getImageView().setImage(img);
                    Console.getConsole().printTracingMessage("changed");
                }
            });
            previousState = gameObject.getState();
            previousAngle = gameObject.getAngle();
        }
    }

    private void update() {
        Thread updateXY = new Thread() {
            @Override
            public void start() {
                while (true) {
                    updateImg();
                    updatePosition();
                }
            }
        };
        updateXY.start();
    }

    public void updatePosition() {
        double curX = MouseTilePosition.TranslateTileToPixelX(previousTile.getX());
        double curY = MouseTilePosition.TranslateTileToPixelY(previousTile.getY());
        while (previousTile.getY() != gameObject.getHeadPixel().getY() ||
                previousTile.getX() != gameObject.getHeadPixel().getX()) {
            updateImg();
            double destX = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
            double destY = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
            double deltaX = destX - curX;
            double deltaY = destY - curY;
//                        Console.getConsole().printTracingMessage("deltaX: " + deltaX +  "deltaY: " + deltaY);
            if (deltaX != 0) {
                curX = curX + (deltaX > 0 ? 1 : -1);
//                            Console.getConsole().printTracingMessage("add x" + curX);
            }
            if (deltaY != 0) {
                curY = curY + (deltaY > 0 ? 1 : -1);
//                            Console.getConsole().printTracingMessage("add y" + curY);
            }
            double finalCurY = curY;
            double finalCurX = curX;
//                        Console.getConsole().printTracingMessage(curX + ", " + curY);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
//                                Console.getConsole().printTracingMessage("update plat");
                    objectView.setLayoutX(finalCurX - gameObject.getErrorInGUIX());
                    objectView.setLayoutY(finalCurY - gameObject.getErrorInGUIY());
                }
            });
            previousTile = new Tile(MouseTilePosition.TranslatePixelToTileX(curX), MouseTilePosition.TranslatePixelToTileY(curY));
//                        Console.getConsole().printTracingMessage("new x,y:" + curX + ", " + curY + " new Tile x,y:" + previousTile.getX() + ", " + previousTile.getY());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Console.getConsole().printTracingMessage("equal");

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ObjectView extends AnchorPane {
        private ProgressBar progressBar = new ProgressBar();
        private ImageView imageView = new ImageView();
        private double maxHp;

        public ObjectView(GameObject gameObject, Image image) {
            maxHp = gameObject.getHp();
            this.imageView.setImage(image);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    progressBar.progressProperty().bind(gameObject.hpProperty().divide(maxHp));
                }
            });
            progressBar.setPrefWidth(25);
            progressBar.setPrefHeight(5);

            progressBar.setLayoutX(15);
            if (gameObject.getTeamSide() == Side.TOP)
                progressBar.setStyle("-fx-accent: red;");
            else
                progressBar.setStyle("-fx-accent: blue;");

            getChildren().add(imageView);
            getChildren().add(progressBar);
        }

        public ProgressBar getProgressBar() {
            return progressBar;
        }

        public void setProgressBar(ProgressBar progressBar) {
            this.progressBar = progressBar;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

    }
}
