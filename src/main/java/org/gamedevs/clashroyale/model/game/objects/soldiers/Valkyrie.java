package org.gamedevs.clashroyale.model.game.objects.soldiers;

import org.gamedevs.clashroyale.model.game.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public class Valkyrie extends Soldier{
    public Valkyrie(int level, Side side) {
        super(side);
        hitSpeed = 1.5;
        attackTargetType = TargetType.GROUND;
        range = 3;//TODO
        areaSplash = true;

        switch (level){
            case 1 :
                hp = 880;
                damage = 120;
                break;
            case 2 :
                hp = 968;
                damage = 132;
                break;
            case 3 :
                hp = 1064;
                damage = 145;
                break;
            case 4 :
                hp = 1170;
                damage = 159;
                break;
            case 5 :
                hp = 1284;
                damage = 175;
                break;
        }

    }
}
