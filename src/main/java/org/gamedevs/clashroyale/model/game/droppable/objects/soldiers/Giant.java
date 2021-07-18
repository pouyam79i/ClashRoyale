package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class Giant extends Soldier{
    public Giant(int level, Side side) {
        super(side);
        nameOfDroppable = CardName.GIANT;
        hitSpeed = 1.5;
        attackTargetType = TargetType.BUILDING;
        range = 1;
        areaSplash = false;

        switch (level){
            case 1 :
                hp = 2000;
                damage = 126;
                break;
            case 2 :
                hp = 2200;
                damage = 138;
                break;
            case 3 :
                hp = 2420;
                damage = 152;
                break;
            case 4 :
                hp = 2660;
                damage = 167;
                break;
            case 5 :
                hp = 2920;
                damage = 183;
                break;
        }

    }
}
