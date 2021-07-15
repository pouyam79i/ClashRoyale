package org.gamedevs.clashroyale.model.game.objects.buildings;

import org.gamedevs.clashroyale.model.game.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class Cannon extends Building{
    public Cannon(int level, Side side){
        super(side);
        hitSpeed = 0.8;
        attackTargetType = TargetType.GROUND;
        range = 5.5;
        lifeTime = 30;
        effectiveLifeTime = true;

        switch (level){
            case 1 :
                hp = 380;
                damage = 60;
                break;
            case 2 :
                hp = 418;
                damage = 66;
                break;
            case 3 :
                hp = 459;
                damage = 72;
                break;
            case 4 :
                hp = 505;
                damage = 79;
                break;
            case 5 :
                hp = 554;
                damage = 87;
                break;
        }
    }
}
