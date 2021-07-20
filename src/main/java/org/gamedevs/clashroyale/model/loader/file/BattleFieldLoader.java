package org.gamedevs.clashroyale.model.loader.file;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.GameDroppableImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.GameImageContainer;
import org.gamedevs.clashroyale.model.container.scene.BattleFieldContainer;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.io.IOException;

/**
 * This class loads all battle field required files.
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class BattleFieldLoader {

    /**
     * Loads all required files.
     * sets their properties to default.
     * @throws IOException if failed to load files!
     */
    public void load() throws IOException {
        Console.getConsole().printTracingMessage("Loading battle requirements...");
        // Getting battle field container
        BattleFieldContainer battleFieldContainer = BattleFieldContainer.getBattleFieldContainer();
        // Getting battle field image container
        GameDroppableImageContainer cardAnimationContainer = GameDroppableImageContainer.getGameDroppableImageContainer();
        // Loading gif animations:
        for(int cardNameValue = 0; cardNameValue <= 6; cardNameValue++){
            for (int objectStateValue = 1; objectStateValue <= 2; objectStateValue++){
                for(int angle = 0; angle < 360; angle = angle + 45){
                    try {
                        cardAnimationContainer.set(CardName.getCardByValue(cardNameValue),
                                Angle.getAngle(angle),
                                GameObjectState.getState(objectStateValue),
                                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/" +
                                        CardName.getCardByValue(cardNameValue).toString() + "/" +
                                        GameObjectState.ATTACK.toString() + "-" + Angle.getAngle(angle).toString() + ".gif"))
                        );
                    }catch (Exception error){
                        Console.getConsole().printTracingMessage("Failed to load gif of " +
                                CardName.getCardByValue(cardNameValue).toString() + " - " + Angle.getAngle(angle) +
                                " - " + GameObjectState.getState(objectStateValue).toString());
                    }
                }
            }
        }

        //load buildings
        GameImageContainer.getGameImageContainer().setBuilding(CardName.CANNON, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/buildings/cannon.png")));
        GameImageContainer.getGameImageContainer().setBuilding(CardName.INFERNO_TOWER, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/buildings/inferno_tower.png")));

        //load throwable
        GameImageContainer.getGameImageContainer().setThrowable(CardName.CANNON,new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/Cannon_Ball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.WIZARD,new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/fireball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.FIREBALL,new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/fireball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.BABY_DRAGON,new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/fireball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.WIZARD,new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/fireball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.ARCHERS,new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/arrows.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.ARROWS,new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/arrows.png")));

        // Loading battle field
        AnchorPane battleField = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/battle/dark_arena/main_battle_field.fxml"
        ));
        battleField.setLayoutX(-7);
        battleField.setLayoutY(-35);
        battleFieldContainer.setBattleField(battleField);
        // Loading card deck
        AnchorPane cardDeck = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/battle/tools/game_card_deck.fxml"
        ));
        cardDeck.setLayoutX(0);
        cardDeck.setLayoutY(555);
        battleFieldContainer.setCardDeck(cardDeck);
        // Loading game starter
        AnchorPane gameStarter = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/battle/tools/game_starter.fxml"
        ));
        gameStarter.setLayoutX(0);
        gameStarter.setLayoutY(0);
        battleFieldContainer.setGameStarter(gameStarter);
        // Loading result display
        AnchorPane gameResult = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/battle/tools/game_result.fxml"
        ));
        gameResult.setLayoutX(0);
        gameResult.setLayoutY(0);
        battleFieldContainer.setGameResult(gameResult);
        // Loading timer label
        AnchorPane gameTimer = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/battle/tools/game_timer.fxml"
        ));
        gameTimer.setLayoutX(310);
        gameTimer.setLayoutY(5);
        battleFieldContainer.setGameTimer(gameTimer);
        // Setting main group of battle field
        Group mainBattleGroup = new Group();
        mainBattleGroup.getChildren().add(battleField);
        mainBattleGroup.getChildren().add(gameTimer);
        mainBattleGroup.getChildren().add(cardDeck);
        battleFieldContainer.setMainBattleGroup(mainBattleGroup);
        // Printing finished message
        Console.getConsole().printTracingMessage("Loading battle requirements finished!");
    }

}
