package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

/**
 * Baby dragon soldier
 * Can throw fire
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public class BabyDragon extends Soldier{

    /**
     * Constructor of BabyDragon
     * Setting baby dragon property
     * @param level of baby dragon
     * @param side of team
     */
    public BabyDragon(int level, Side side) {
        super(side);
        nameOfDroppable = CardName.BABY_DRAGON;
        hitSpeed = 1.8;
        attackTargetType = TargetType.AIR_GROUND;
        myType = TargetType.AIR;
        range = 3;
        areaSplash = true;
        z = 1;
        errorInGUIX = 1 * MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO;
        errorInGUIY = 3 * MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO;
        switch (level){
            case 1 :
                hp.setValue(800);
                damage = 100;
                break;
            case 2 :
                hp.setValue(880);
                damage = 110;
                break;
            case 3 :
                hp.setValue(968);
                damage = 121;
                break;
            case 4 :
                hp.setValue(1064);
                damage = 133;
                break;
            case 5 :
                hp.setValue(1168);
                damage = 146;
                break;
        }
    }

}
