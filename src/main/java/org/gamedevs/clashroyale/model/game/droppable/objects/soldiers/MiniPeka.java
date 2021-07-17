package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class MiniPeka extends Soldier{

    public MiniPeka(int level, Side side) {
        super(side);
        hitSpeed = 1.8;
        attackTargetType = TargetType.GROUND;
        range = 3;//TODO
        areaSplash = false;

        switch (level){
            case 1 :
                hp = 600;
                damage = 325;
                break;
            case 2 :
                hp = 660;
                damage = 357;
                break;
            case 3 :
                hp = 726;
                damage = 393;
                break;
            case 4 :
                hp = 798;
                damage = 432;
                break;
            case 5 :
                hp = 876;
                damage = 474;
                break;
        }

    }
}
