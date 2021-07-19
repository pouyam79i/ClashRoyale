package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class KingTower extends MainTowers{

    public KingTower(int level, Side side){
        super(side);
        nameOfDroppable = CardName.KING_TOWER;
        hitSpeed = 1;
        range = 7;
        attackTargetType = TargetType.AIR_GROUND;
        lifeTime = 1000;
        effectiveLifeTime = false;

        switch (level){
            case 1 :
                hp.setValue(2400);
                damage = 50;
                break;
            case 2 :
                hp.setValue(2568);
                damage = 53;
                break;
            case 3 :
                hp.setValue(2736);
                damage = 57;
                break;
            case 4 :
                hp.setValue(2904);
                damage = 60;
                break;
            case 5 :
                hp.setValue(3096);
                damage = 64;
                break;
        }
    }

}
