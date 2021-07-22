package org.gamedevs.clashroyale.model.updater.battle.viewupdater;

import javafx.application.Platform;
import javafx.scene.image.Image;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.container.gamedata.GameImageContainer;
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.Building;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.MainTowers;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * a class which handle view of buildings in GUI
 *
 * @author Hosna Hoseini 9823010 -CE@AUT
 * @version 1.0
 */
public class TowerViewUpdater extends ViewUpdater {
    private long startTime = System.currentTimeMillis();
    private int kind;
    private  int cntToShoot = 0;
    public TowerViewUpdater(GameObject gameObject, boolean isEnemy, int kind) {
        super(gameObject, isEnemy);
        this.kind = kind;
        placeInitImg();
    }

    @Override
    public void placeInitImg() {
        setMainTower((MainTowers) gameObject, gameObject.getTeamSide(), kind);
    }

    /**
     * update GUI
     */
    @Override
    public void update() {
        updateExist();
        throwBulletIfAttack();
        updateExist();

    }

    /**
     * drop main towers on its position!
     *
     * @param mainTower side
     * @param side      side of tower
     * @param kind      of tower (left princess is -1, king is 0, right princess is 1)
     */
    public void setMainTower(MainTowers mainTower, Side side, int kind) {
        GameImageContainer gameImageContainer = GameImageContainer.getGameImageContainer();

        objectView = new ObjectView();
        Image currentImage = null;
        int x = 0;
        int y = 0;
        if (side == Side.DOWN) {
            if (kind == -1) {
                currentImage = gameImageContainer.getTower("PRINCESS_LEFT");
                x = 19;
                y = 348;
                objectView.getImageView().setFitWidth(111);
                objectView.getImageView().setFitHeight(118);
//                objectView.getProgressBar().setLayoutX(47);
//                objectView.getProgressBar().setLayoutY(413);
                objectView.getProgressBar().setPrefWidth(50);
                objectView.getProgressBar().setPrefHeight(8);
            } else if (kind == 0) {
                currentImage = gameImageContainer.getTower("KING_ENEMY");
                x = 128;
                y = 388;
                objectView.getImageView().setFitWidth(125);
                objectView.getImageView().setFitHeight(136);
                objectView.getProgressBar().setPrefWidth(80);
//                objectView.getProgressBar().setLayoutX(159);
//                objectView.getProgressBar().setLayoutY(470);
                objectView.getProgressBar().setPrefHeight(10);
            } else if (kind == 1) {
                currentImage = gameImageContainer.getTower("PRINCESS_RIGHT");
                x = 247;
                y = 348;
                objectView.getImageView().setFitWidth(111);
                objectView.getImageView().setFitHeight(118);
//                objectView.getProgressBar().setLayoutX(276);
//                objectView.getProgressBar().setLayoutY(413);
                objectView.getProgressBar().setPrefWidth(50);
                objectView.getProgressBar().setPrefHeight(8);
            }
        } else if (side == Side.TOP) {
            if (kind == -1) {
                currentImage = gameImageContainer.getTower("PRINCESS_LEFT_ENEMY");
                x = 22;
                y = 30;
                objectView.getImageView().setFitWidth(111);
                objectView.getImageView().setFitHeight(118);
//                objectView.getProgressBar().setLayoutX(47);
//                objectView.getProgressBar().setLayoutY(38);
                objectView.getProgressBar().setPrefWidth(50);
                objectView.getProgressBar().setPrefHeight(8);
            } else if (kind == 0) {
                currentImage = gameImageContainer.getTower("KING");
                x = 128;
                y = -43;
                objectView.getImageView().setFitWidth(125);
                objectView.getImageView().setFitHeight(136);
//                objectView.getProgressBar().setLayoutX(159);
//                objectView.getProgressBar().setLayoutY(-43);
                objectView.getProgressBar().setPrefWidth(80);
                objectView.getProgressBar().setPrefHeight(10);
            } else if (kind == 1) {
                currentImage = gameImageContainer.getTower("PRINCESS_RIGHT_ENEMY");
                x = 247;
                y = 30;
                objectView.getImageView().setFitWidth(111);
                objectView.getImageView().setFitHeight(118);
//                objectView.getProgressBar().setLayoutX(274);
//                objectView.getProgressBar().setLayoutY(38);
                objectView.getProgressBar().setPrefWidth(50);
                objectView.getProgressBar().setPrefHeight(8);
            }
        }
        objectView.setMaxHp(gameObject.getHp());
        objectView.getImageView().setImage(currentImage);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                objectView.getProgressBar().progressProperty().bind(gameObject.hpProperty().divide(objectView.getMaxHp()));
            }
        });
        objectView.getProgressBar().setLayoutX(30);
        if (gameObject.getTeamSide() == Side.TOP)
            objectView.getProgressBar().setStyle("-fx-accent: red;");
        else
            objectView.getProgressBar().setStyle("-fx-accent: blue;");

        objectView.getChildren().add(objectView.getImageView());
        objectView.getChildren().add(objectView.getProgressBar());
        int finalX = x;
        int finalY = y;
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            objectView.setLayoutX(finalX);
            objectView.setLayoutY(finalY);
        });
    }


    public void updateExist() {
        if (gameObject.getHp() <= 0 || System.currentTimeMillis() - startTime >= ((Building) gameObject).getLifeTime() * 1000L)
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//
//                    objectView.getChildren().remove(objectView.getProgressBar());
//                    objectView.getChildren().remove(objectView.getImageView());
//                    MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable().getChildren().remove(objectView);
//                }
//            });
            remove();
    }

    /**
     * check if G.O. is in the attack state throw bullet
     */
    public void throwBulletIfAttack() {
        cntToShoot++;
        Console.getConsole().printTracingMessage(String.valueOf(cntToShoot));
        if (cntToShoot % (gameObject.getHitSpeed() * 10) == 0 && gameObject.getLockedTarget() != null)
            new Bullet(gameObject).throwBullet(gameObject.getHeadTile(), gameObject.getLockedTarget().getHeadTile());
    }
}
