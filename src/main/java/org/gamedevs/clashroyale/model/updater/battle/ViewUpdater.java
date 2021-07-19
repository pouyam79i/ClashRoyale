package org.gamedevs.clashroyale.model.updater.battle;

import javafx.application.Platform;
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
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public class ViewUpdater extends Runnable {

    private GameDroppableImageContainer imageContainer;
    private final AnchorPane battleFieldPane;
    private final GameObject gameObject;
    private final CardName cardName;
    private ImageView objectView;
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
        objectView = new ImageView(currentImage);
        objectView.setFitWidth(currentImage.getWidth() / 2.5);
        objectView.setFitHeight(currentImage.getHeight() / 2.5);
        int x = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
        int y = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
        Console.getConsole().printTracingMessage("x, y final: " + x + ", " + y);
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            objectView.setLayoutX(x - gameObject.getErrorInGUIX()- MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO);
            objectView.setLayoutY(y - gameObject.getErrorInGUIY()+ MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO);
        });


        update();
    }

    public void update() {
        while (true) {
            updateImg();
            updateXY();
        }
    }

    private void updateImg() {
        Thread updateImg = new Thread() {
            @Override
            public void start() {
                Console.getConsole().printTracingMessage(previousAngle.toString() + previousState.toString());
//                while (true) {
                if (previousAngle != gameObject.getAngle() ||
                        previousState != gameObject.getState()) {
                    Image img = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            objectView.setImage(img);
                            Console.getConsole().printTracingMessage("changed");
                        }
                    });
                    previousState = gameObject.getState();
                    previousAngle = gameObject.getAngle();
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            }
        };
        updateImg.start();
    }

    private void updateXY() {


        Thread updateXY = new Thread() {

            @Override
            public void start(){
//                while (true) {
                double curX = MouseTilePosition.TranslateTileToPixelX(previousTile.getX());
                double curY = MouseTilePosition.TranslateTileToPixelY(previousTile.getY());
                while (previousTile.getY() != gameObject.getHeadPixel().getY() ||
                        previousTile.getX() != gameObject.getHeadPixel().getX()) {
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
//            }
        };
        updateXY.start();
    }
}
