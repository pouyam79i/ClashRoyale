package org.gamedevs.clashroyale.model.game.objects.soldiers;

import org.gamedevs.clashroyale.model.game.objects.TargetType;

public class MiniPeka extends Soldier{

    public MiniPeka(int level) {
        hitSpeed = 1.8;
        target = TargetType.GROUND;
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
