package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.Building;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.InfernoTower;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * a class which handle view of buildings in GUI
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
public class BuildingViewUpdater extends ViewUpdater {
    private long startTime = System.currentTimeMillis();


    public BuildingViewUpdater(GameObject gameObject, boolean isEnemy) {
        super(gameObject, isEnemy);
        placeInitImg();

    }

    @Override
    public void placeInitImg() {
        Image currentImage = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
        objectView = new ObjectView(gameObject, currentImage);
        objectView.getImageView().setFitWidth(currentImage.getWidth() / 2.5);
        objectView.getImageView().setFitHeight(currentImage.getHeight() / 2.5);
        int x = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
        int y = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            objectView.setLayoutX(x - gameObject.getErrorInGUIX());
            objectView.setLayoutY(y - gameObject.getErrorInGUIY());
        });
    }

    /**
     * update GUI
     */
    @Override
    public void update() {
        updateExist();
        updateImg();
        throwBulletIfAttack();
        updateExist();

    }

    public void updateExist() {
        if (gameObject.getHp() <= 0 || System.currentTimeMillis() - startTime >= ((Building) gameObject).getLifeTime() * 1000L){

//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    if(gameObject.getNameOfDroppable() == CardName.INFERNO_TOWER)
//                        MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(((InfernoTower)gameObject).getLine());
//                    objectView.getChildren().remove(objectView.getProgressBar());
//                    objectView.getChildren().remove(objectView.getImageView());
//                    MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(objectView);
//                }
//            });
            remove();
    }
    }
}