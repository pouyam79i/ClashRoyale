package org.gamedevs.clashroyale.model.game.objects.buildings;

import org.gamedevs.clashroyale.model.game.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class PrincessTower extends Building{

    public PrincessTower(int level, Side side){
        super(side);
        hitSpeed = 0.8;
        range = 7.5;
        attackTargetType = TargetType.AIR_GROUND;
        lifeTime = 1000;
        effectiveLifeTime = false;

        switch (level){
            case 1 :
                hp = 1400;
                damage = 50;
                break;
            case 2 :
                hp = 1512;
                damage = 54;
                break;
            case 3 :
                hp = 1624;
                damage = 58;
                break;
            case 4 :
                hp = 1750;
                damage = 62;
                break;
            case 5 :
                hp = 1890;
                damage = 69;
                break;
        }
    }

}
