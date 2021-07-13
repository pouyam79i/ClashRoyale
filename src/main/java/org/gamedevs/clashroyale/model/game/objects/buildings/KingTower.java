package org.gamedevs.clashroyale.model.game.objects.buildings;

import org.gamedevs.clashroyale.model.game.objects.TargetType;

public class KingTower extends Building{

    public KingTower(int level){
        hitSpeed = 1;
        range = 7;
        target = TargetType.AIR_GROUND;
        lifeTime = 1000;
        effectiveLifeTime = false;

        switch (level){
            case 1 :
                hp = 2400;
                damage = 50;
                break;
            case 2 :
                hp = 2568;
                damage = 53;
                break;
            case 3 :
                hp = 2736;
                damage = 57;
                break;
            case 4 :
                hp = 2904;
                damage = 60;
                break;
            case 5 :
                hp = 3096;
                damage = 64;
                break;
        }
    }

}
