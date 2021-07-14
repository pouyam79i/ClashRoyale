package org.gamedevs.clashroyale.model.loader.file;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.container.scene.BattleFieldContainer;
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
        // Loading battle field
        AnchorPane battleField = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/battle/dark_arena/main_battle_field.fxml"
        ));
        battleField.setLayoutX(-7);
        battleField.setLayoutY(-35);
        battleFieldContainer.setBattleField(battleField);
        // Loading card deck
        AnchorPane cardDeck = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/game/game_card_deck.fxml"
        ));
        cardDeck.setLayoutX(0);
        cardDeck.setLayoutY(555);
        battleFieldContainer.setCardDeck(cardDeck);
        // Loading game starter
        AnchorPane gameStarter = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/game/game_starter.fxml"
        ));
        gameStarter.setLayoutX(0);
        gameStarter.setLayoutY(700);
        battleFieldContainer.setGameStarter(gameStarter);
        // Loading result display
        AnchorPane gameResult = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/game/game_starter.fxml"
        ));
        gameResult.setLayoutX(0);
        gameResult.setLayoutY(0);
        battleFieldContainer.setGameResult(gameResult);
        // Setting main group of battle field
        Group mainBattleGroup = new Group();
        mainBattleGroup.getChildren().add(battleField);
        mainBattleGroup.getChildren().add(cardDeck);
        battleFieldContainer.setMainBattleGroup(mainBattleGroup);
        // Printing finished message
        Console.getConsole().printTracingMessage("Loading battle requirements finished!");
    }

}
