package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class BabyDragon extends Soldier{

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
                hp = 800;
                damage = 100;
                break;
            case 2 :
                hp = 880;
                damage = 110;
                break;
            case 3 :
                hp = 968;
                damage = 121;
                break;
            case 4 :
                hp = 1064;
                damage = 133;
                break;
            case 5 :
                hp = 1168;
                damage = 146;
                break;
        }
    }

}
