package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

/**
 * Giant soldier.
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public class Giant extends Soldier{

    /**
     * Constructor of Giant
     * Setting Giant property
     * @param level of Giant
     * @param side of team
     */
    public Giant(int level, Side side) {
        super(side);
        nameOfDroppable = CardName.GIANT;
        hitSpeed = 1.5;
        attackTargetType = TargetType.BUILDING;
        range = 1;
        areaSplash = false;
        errorInGUIX = 1 * MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO;
        errorInGUIY = 3 * MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO;
        switch (level){
            case 1 :
                hp.setValue(2000);
                damage = 126;
                break;
            case 2 :
                hp.setValue(2200);
                damage = 138;
                break;
            case 3 :
                hp.setValue(2420);
                damage = 152;
                break;
            case 4 :
                hp.setValue(2660);
                damage = 167;
                break;
            case 5 :
                hp.setValue(2920);
                damage = 183;
                break;
        }
    }
}
