package org.gamedevs.clashroyale.model.game.objects.soldiers;

import org.gamedevs.clashroyale.model.game.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class Giant extends Soldier{
    public Giant(int level, Side side) {
        super(side);
        hitSpeed = 1.5;
        attackTargetType = TargetType.BUILDING;
        range = 3;//TODO
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
