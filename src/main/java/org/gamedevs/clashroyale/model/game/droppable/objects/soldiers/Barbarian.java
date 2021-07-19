package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class Barbarian extends Soldier{
    public Barbarian(int level, Side side) {
        super(side);
        nameOfDroppable = CardName.BARBARIANS;
        hitSpeed = 1.5;
        attackTargetType = TargetType.GROUND;
        range = 1;
        areaSplash = false;
        errorInGUIX = 1 * MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO;
        errorInGUIY = 3 * MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO;
        switch (level){
            case 1 :
                hp = 300;
                damage = 75;
                break;
            case 2 :
                hp = 330;
                damage = 82;
                break;
            case 3 :
                hp = 363;
                damage = 90;
                break;
            case 4 :
                hp = 438;
                damage = 99;
                break;
            case 5 :
                hp = 480;
                damage = 109;
                break;
        }

    }
}
