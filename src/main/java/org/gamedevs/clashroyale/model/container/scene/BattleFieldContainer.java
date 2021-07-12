package org.gamedevs.clashroyale.model.container.scene;

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

    /**
     * Constructor of BattleFieldContainer.
     * Sets default values.
     */
    private BattleFieldContainer(){
        battleField = null;
    }

    // Getters
    public AnchorPane getBattleField() {
        return battleField;
    }

    // Setters
    public void setBattleField(AnchorPane battleField) {
        if(this.battleField == null)
            this.battleField = battleField;
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
