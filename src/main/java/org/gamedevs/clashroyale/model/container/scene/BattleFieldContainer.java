package org.gamedevs.clashroyale.model.container.scene;

import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;

/**
 * This class contains properties
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class BattleFieldContainer {

    /**
     * Only instance of this class
     */
    private static BattleFieldContainer battleFieldContainer = null;

    /**
     * Anchor pane of battle field
     */
    private AnchorPane battleField;
    private AnchorPane cardDeck;
    private AnchorPane gameStarter;
    private AnchorPane gameResult;
    private AnchorPane gameTimer;
    private Group mainBattleGroup;

    /**
     * Constructor of BattleFieldContainer.
     * Sets default values.
     */
    private BattleFieldContainer(){
        battleField = null;
        cardDeck = null;
        gameStarter = null;
        gameResult = null;
        gameTimer = null;
        mainBattleGroup = null;
    }

    // Getters
    public AnchorPane getBattleField() {
        return battleField;
    }
    public AnchorPane getCardDeck() {
        return cardDeck;
    }
    public AnchorPane getGameStarter() {
        return gameStarter;
    }
    public AnchorPane getGameResult() {
        return gameResult;
    }
    public Group getMainBattleGroup() {
        return mainBattleGroup;
    }
    public AnchorPane getGameTimer() {
        return gameTimer;
    }

    // Setters
    public void setBattleField(AnchorPane battleField) {
        this.battleField = battleField;
    }
    public void setCardDeck(AnchorPane cardDeck) {
        this.cardDeck = cardDeck;
    }
    public void setGameStarter(AnchorPane gameStarter) {
        this.gameStarter = gameStarter;
    }
    public void setGameResult(AnchorPane result) {
        this.gameResult = result;
    }
    public void setMainBattleGroup(Group mainBattleGroup) {
        this.mainBattleGroup = mainBattleGroup;
    }
    public void setGameTimer(AnchorPane gameTimer) {
        this.gameTimer = gameTimer;
    }

    /**
     * @return only instance of this class
     */
    public static BattleFieldContainer getBattleFieldContainer() {
        if(battleFieldContainer == null)
            battleFieldContainer = new BattleFieldContainer();
        return battleFieldContainer;
    }

}
