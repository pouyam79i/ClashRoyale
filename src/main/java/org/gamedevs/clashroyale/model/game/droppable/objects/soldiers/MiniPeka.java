package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class MiniPeka extends Soldier{

    public MiniPeka(int level, Side side) {
        super(side);
        nameOfDroppable = CardName.MINI_PEKKA;
        hitSpeed = 1.8;
        attackTargetType = TargetType.GROUND;
        range = 1;
        areaSplash = false;
        errorInGUIX = 1 * MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO;
        errorInGUIY = 3 * MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO;
        switch (level){
            case 1 :
                hp.setValue(600);
                damage = 325;
                break;
            case 2 :
                hp.setValue(660);
                damage = 357;
                break;
            case 3 :
                hp.setValue(726);
                damage = 393;
                break;
            case 4 :
                hp.setValue(798);
                damage = 432;
                break;
            case 5 :
                hp.setValue(876);
                damage = 474;
                break;
        }

    }
}
