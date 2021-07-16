package org.gamedevs.clashroyale.model.updater.battle;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.GameDroppableImageContainer;
import org.gamedevs.clashroyale.model.game.objects.GameObject;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public class ViewUpdater extends Runnable {

    private GameDroppableImageContainer imageContainer;
    private final AnchorPane battleFieldPane;
    private final GameObject gameObject;
    private final CardName cardName;
    private ImageView objectView;
    private boolean isEnemy;

    public ViewUpdater(GameObject gameObject, boolean isEnemy){
        threadName = "ViewUpdater";
        imageContainer = GameDroppableImageContainer.getGameDroppableImageContainer();
        this.battleFieldPane = MainBattleField.getMainBattleField().getBattleFieldPaneUpdatable();
        this.gameObject = gameObject;
        cardName = gameObject.getNameOfDroppable();
        this.isEnemy = isEnemy;
    }

    @Override
    public void run() {
        // Initializing thread needs
        Image currentImage = imageContainer.get(cardName, gameObject.getState(), isEnemy);
        objectView = new ImageView(currentImage);
        objectView.setFitWidth(currentImage.getWidth()/6);
        objectView.setFitHeight(currentImage.getHeight()/6);
        int x = gameObject.getHeadPixel().getX();
        int y = gameObject.getHeadPixel().getY();
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            objectView.setLayoutX(x);
            objectView.setLayoutY(y);
        });
    }

}
