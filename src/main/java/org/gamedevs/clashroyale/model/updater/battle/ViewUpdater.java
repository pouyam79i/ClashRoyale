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

    public ViewUpdater(GameObject gameObject, boolean isEnemy) {
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
        gameObject.setState(GameObjectState.MOVING);
        Image currentImage = imageContainer.get(cardName, gameObject.getAngle(), gameObject.getState());
        objectView = new ImageView(currentImage);
        objectView.setFitWidth(currentImage.getWidth() / 2.5);
        objectView.setFitHeight(currentImage.getHeight() / 2.5);
        int x = MouseTilePosition.TranslateTileToPixelX(gameObject.getHeadPixel().getX());
        int y = MouseTilePosition.TranslateTileToPixelY(gameObject.getHeadPixel().getY());
        Console.getConsole().printTracingMessage("x, y final: " + x + ", " + y);
        Platform.runLater(() -> {
            battleFieldPane.getChildren().add(objectView);
            if (cardName == CardName.ARCHERS || cardName == CardName.WIZARD)
                objectView.setLayoutX(x - 0.5 * MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO);
            else
                objectView.setLayoutX(x - MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO);
            objectView.setLayoutY(y - 3 * MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO);
        });

    }
}
