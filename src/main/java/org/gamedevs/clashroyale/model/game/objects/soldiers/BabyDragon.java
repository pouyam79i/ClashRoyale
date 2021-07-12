package org.gamedevs.clashroyale.model.game.objects.soldiers;

import org.gamedevs.clashroyale.model.game.objects.TargetType;

public class BabyDragon extends Soldier{
    public BabyDragon(int level) {
        hitSpeed = 1.8;
        target = TargetType.AIR_GROUND;
        range = 3;
        areaSplash = true;

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
