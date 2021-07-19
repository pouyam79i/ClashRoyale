package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class Wizard extends Soldier{
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
                hp = 340;
                damage = 130;
                break;
            case 2 :
                hp = 374;
                damage = 143;
            case 3:
                hp = 411;
                damage = 157;
                break;
            case 4 :
                hp = 452;
                damage = 172;
                break;
            case 5 :
                hp = 496;
                damage = 182;
                break;
        }

    }
}
