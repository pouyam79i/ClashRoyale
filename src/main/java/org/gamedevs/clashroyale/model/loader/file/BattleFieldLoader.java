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
 *
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class BattleFieldLoader {

    /**
     * Loads all required files.
     * sets their properties to default.
     *
     * @throws IOException if failed to load files!
     */
    public void load() throws IOException {
        Console.getConsole().printTracingMessage("Loading battle requirements...");
        // Getting battle field container
        BattleFieldContainer battleFieldContainer = BattleFieldContainer.getBattleFieldContainer();
        // Getting battle field image container
        GameDroppableImageContainer cardAnimationContainer = GameDroppableImageContainer.getGameDroppableImageContainer();
        // Loading gif animations:
        for (int cardNameValue = 0; cardNameValue <= 6; cardNameValue++) {
            for (int objectStateValue = 1; objectStateValue <= 2; objectStateValue++) {
                for (int angle = 0; angle < 360; angle = angle + 45) {
                    try {
                        cardAnimationContainer.set(CardName.getCardByValue(cardNameValue),
                                Angle.getAngle(angle),
                                GameObjectState.getState(objectStateValue),
                                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/" +
                                        CardName.getCardByValue(cardNameValue).toString() + "/" +
                                        GameObjectState.getState(objectStateValue) + "-" + Angle.getAngle(angle).toString() + ".gif"))
                        );
                    } catch (Exception error) {
                        Console.getConsole().printTracingMessage("Failed to load gif of " +
                                CardName.getCardByValue(cardNameValue).toString() + " - " + Angle.getAngle(angle) +
                                " - " + GameObjectState.getState(objectStateValue).toString());
                    }
                }
            }
        }

        for (int cardNameValue = 0; cardNameValue <= 6; cardNameValue++) {
                for (int angle = 0; angle < 360; angle = angle + 45) {
                    try {
                        cardAnimationContainer.set(CardName.getCardByValue(cardNameValue),
                                Angle.getAngle(angle),
                                GameObjectState.IDLE,
                                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/" +
                                        CardName.getCardByValue(cardNameValue).toString() + "/" +
                                        GameObjectState.MOVING.toString() + "-" + Angle.getAngle(angle).toString() + ".gif"))
                        );
                    } catch (Exception error) {
                        Console.getConsole().printTracingMessage("Failed to load gif of " +
                                CardName.getCardByValue(cardNameValue).toString() + " - " + Angle.getAngle(angle) +
                                " - idle");
                    }
            }
        }


        //load inferno tower img
        for (int objectStateValue = 0; objectStateValue <= 2; objectStateValue++) {
            for (int angle = 0; angle < 360; angle = angle + 45) {
                try {
                    cardAnimationContainer.set(CardName.INFERNO_TOWER,
                            Angle.getAngle(angle),
                            GameObjectState.getState(objectStateValue), new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/buildings/inferno_tower.png")));


                } catch (Exception error) {
                    Console.getConsole().printTracingMessage("Failed to load gif of " +
                            CardName.INFERNO_TOWER + " - " + Angle.getAngle(angle) +
                            " - " + GameObjectState.getState(objectStateValue).toString());
                }
            }
        }

        //load cannon img
        for (int objectStateValue = 1; objectStateValue <= 2; objectStateValue++) {
            for (int angle = 0; angle < 360; angle = angle + 45) {
                try {
                    cardAnimationContainer.set(CardName.CANNON,
                            Angle.getAngle(angle),
                            GameObjectState.getState(objectStateValue), new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/buildings/CANNON/" + Angle.getAngle(angle) + ".png")));

                } catch (Exception error) {
                    Console.getConsole().printTracingMessage("Failed to load gif of " +
                            CardName.CANNON + " - " + Angle.getAngle(angle) +
                            " - " + GameObjectState.getState(objectStateValue).toString());
                }
            }
        }

        for (int angle = 0; angle < 360; angle = angle + 45) {
            try {
                cardAnimationContainer.set(CardName.CANNON,
                        Angle.getAngle(angle),
                        GameObjectState.IDLE, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/buildings/CANNON/" + Angle.getAngle(angle) + ".png")));

            } catch (Exception error) {
                Console.getConsole().printTracingMessage("Failed to load gif of " +
                        CardName.CANNON + " - " + Angle.getAngle(angle) +
                        " - idle");
            }
        }

        //load throwable
        GameImageContainer.getGameImageContainer().setThrowable(CardName.CANNON, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/Cannon_Ball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.KING_TOWER, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/Cannon_Ball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.PRINCESS_TOWER, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/Cannon_Ball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.WIZARD, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/fireball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.FIREBALL, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/fireball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.BABY_DRAGON, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/fireball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.WIZARD, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/fireball.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.ARCHERS, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/arrows.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.ARROWS, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/arrows.png")));
        GameImageContainer.getGameImageContainer().setThrowable(CardName.INFERNO_TOWER, new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/img/throwable/yellow_ball.png")));

        GameImageContainer.getGameImageContainer().setTower("KING_ENEMY", new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/KING_TOWER/KING_TOWER_ENEMY.png")));
        GameImageContainer.getGameImageContainer().setTower("PRINCESS_LEFT_ENEMY", new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/PRINCESS_TOWER/PRINCESS_TOWER_ENEMY.png")));
        GameImageContainer.getGameImageContainer().setTower("PRINCESS_RIGHT_ENEMY", new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/PRINCESS_TOWER/PRINCESS_TOWER_ENEMY.png")));
        GameImageContainer.getGameImageContainer().setTower("KING", new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/KING_TOWER/KING_TOWER.png")));
        GameImageContainer.getGameImageContainer().setTower("PRINCESS_LEFT", new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/PRINCESS_TOWER/PRINCESS_TOWER.png")));
        GameImageContainer.getGameImageContainer().setTower("PRINCESS_RIGHT", new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/PRINCESS_TOWER/PRINCESS_TOWER.png")));

        // loading battle field
        reloadBattleField();

        // Printing finished message
        Console.getConsole().printTracingMessage("Loading battle requirements finished!");
    }

    /**
     * This class reloads battle field properties!
     */
    public static void reloadBattleField(){
        try {
            // Battle field container!
            BattleFieldContainer battleFieldContainer = BattleFieldContainer.getBattleFieldContainer();
            // Loading battle field
            AnchorPane battleField = FXMLLoader.load(BattleFieldLoader.class.getResource(
                    "../../../view/fxml/battle/dark_arena/main_battle_field.fxml"
            ));
            battleField.setLayoutX(-7);
            battleField.setLayoutY(-35);
            battleFieldContainer.setBattleField(battleField);
            // Loading card deck
            AnchorPane cardDeck = FXMLLoader.load(BattleFieldLoader.class.getResource(
                    "../../../view/fxml/battle/tools/game_card_deck.fxml"
            ));
            cardDeck.setLayoutX(0);
            cardDeck.setLayoutY(555);
            battleFieldContainer.setCardDeck(cardDeck);
            // Loading game starter
            AnchorPane gameStarter = FXMLLoader.load(BattleFieldLoader.class.getResource(
                    "../../../view/fxml/battle/tools/game_starter.fxml"
            ));
            gameStarter.setLayoutX(0);
            gameStarter.setLayoutY(0);
            battleFieldContainer.setGameStarter(gameStarter);
            // Loading result display
            AnchorPane gameResult = FXMLLoader.load(BattleFieldLoader.class.getResource(
                    "../../../view/fxml/battle/tools/game_result.fxml"
            ));
            gameResult.setLayoutX(0);
            gameResult.setLayoutY(0);
            battleFieldContainer.setGameResult(gameResult);
            // Loading timer label
            AnchorPane gameTimer = FXMLLoader.load(BattleFieldLoader.class.getResource(
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
        }catch (IOException e){
            Console.getConsole().printTracingMessage("Failed to reload battle field! -> " + e.getMessage());
        }
    }

}
