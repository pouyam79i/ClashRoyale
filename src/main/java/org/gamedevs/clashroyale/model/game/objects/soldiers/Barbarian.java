package org.gamedevs.clashroyale.model.game.objects.soldiers;

import org.gamedevs.clashroyale.model.game.objects.TargetType;

public class Barbarian extends Soldier{
    public Barbarian(int level) {
        hitSpeed = 1.5;
        target = TargetType.GROUND;
        range = 3;//TODO
        areaSplash = false;

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
