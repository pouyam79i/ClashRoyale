package org.gamedevs.clashroyale.model.loader.file;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.gamedevs.clashroyale.model.container.scene.BattleFieldContainer;

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
        // Getting battle field container
        BattleFieldContainer battleFieldContainer = BattleFieldContainer.getBattleFieldContainer();
        //Loading battle field
        AnchorPane battleField = FXMLLoader.load(getClass().getResource(
                "../../../view/fxml/battle/dark_arena/main_battle_field.fxml"
        ));
        battleField.setLayoutX(-7);
        battleField.setLayoutY(-10);
        battleFieldContainer.setBattleField(battleField);
    }

}
