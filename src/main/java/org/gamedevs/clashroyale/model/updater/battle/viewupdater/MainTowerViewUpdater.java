package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.GameDroppableImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.Building;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.InfernoTower;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.MainTowers;
import org.gamedevs.clashroyale.model.updater.battle.viewupdater.ViewUpdater;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public class MainTowerViewUpdater extends ViewUpdater {

    private long startTime = System.currentTimeMillis();

    public MainTowerViewUpdater(GameObject gameObject, boolean isEnemy){
        super(gameObject, isEnemy);
        imageContainer = GameDroppableImageContainer.getGameDroppableImageContainer();
        Image currentImage = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
        objectView = new ViewUpdater.ObjectView(gameObject, currentImage);
        objectView.getImageView().setFitWidth(currentImage.getWidth());
        objectView.getImageView().setFitHeight(currentImage.getHeight());
        int x = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
        int y = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
        Console.getConsole().printTracingMessage("x, y final: " + x + ", " + y);
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            objectView.setLayoutX(x - gameObject.getErrorInGUIX() );
            objectView.setLayoutY(y - gameObject.getErrorInGUIY() );
        });

    }


    /**
     * update GUI
     */
    @Override
    public void update() {

        updateImg();
        updateExist();

    }

    public void updateExist() {
        if (gameObject.getHp() <= 0 || System.currentTimeMillis() - startTime >= ((Building) gameObject).getLifeTime() * 1000L)
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if(gameObject.getNameOfDroppable() == CardName.INFERNO_TOWER)
                        MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(((InfernoTower)gameObject).getLine());

                    Console.getConsole().printTracingMessage("delete building");
                    MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(objectView);
                }
            });
    }

}
