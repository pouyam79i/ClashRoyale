package org.gamedevs.clashroyale.model.loader.file;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.GameDroppableImageContainer;
import org.gamedevs.clashroyale.model.container.scene.BattleFieldContainer;
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
        // King tower
        cardAnimationContainer.set(CardName.KING_TOWER, GameObjectState.IDLE, false,
                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/KING_TOWER/KING_TOWER.png"))
        );
        cardAnimationContainer.set(CardName.KING_TOWER, GameObjectState.IDLE, true,
                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/KING_TOWER/KING_TOWER_ENEMY.png"))
        );
        // Princess towers
        cardAnimationContainer.set(CardName.PRINCESS_TOWER, GameObjectState.IDLE, false,
                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/PRINCESS_TOWER/PRINCESS_TOWER.png"))
        );
        cardAnimationContainer.set(CardName.PRINCESS_TOWER, GameObjectState.IDLE, true,
                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/PRINCESS_TOWER/PRINCESS_TOWER_ENEMY.png"))
        );
        // Loading more gifs...
        cardAnimationContainer.set(CardName.BABY_DRAGON, GameObjectState.MOVING, false,
                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/BABY_DRAGON/MOVING-NORTH.gif"))
        );

        cardAnimationContainer.set(CardName.BABY_DRAGON, GameObjectState.MOVING, true,
                new Image(BattleFieldLoader.class.getResourceAsStream("../../../view/gif/BABY_DRAGON/MOVING-NORTH.gif"))
        );


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
        gameStarter.setLayoutY(700);
        battleFieldContainer.setGameStarter(gameStarter);
        // Loading result display
        AnchorPane gameResult = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/battle/tools/game_starter.fxml"
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
