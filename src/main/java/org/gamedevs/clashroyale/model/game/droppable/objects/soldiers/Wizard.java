package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

/**
 * Wizard soldier.
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public class Wizard extends Soldier{

    /**
     * Constructor of Wizard
     * Setting Wizard property
     * @param level of Wizard
     * @param side of team
     */
    public Wizard(int level, Side side) {
        super(side);
        nameOfDroppable = CardName.WIZARD;
        hitSpeed = 1.7;
        attackTargetType = TargetType.AIR_GROUND;
        range = 5;
        areaSplash = true;
        errorInGUIX = 0.5 * MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO;
        errorInGUIY = 3 * MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO;
        switch (level){
            case 1 :
                hp.setValue(340);
                damage = 130;
                break;
            case 2 :
                hp.setValue(374);
                damage = 143;
            case 3:
                hp.setValue(411);
                damage = 157;
                break;
            case 4 :
                hp.setValue(452);
                damage = 172;
                break;
            case 5 :
                hp.setValue(496);
                damage = 182;
                break;
        }

    }
}
