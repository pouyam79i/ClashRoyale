package org.gamedevs.clashroyale.model.game.objects.buildings;

import org.gamedevs.clashroyale.model.game.objects.TargetType;

public class InfernoTower extends Building {

    int maxDamage;

    public InfernoTower(int level) {
        hitSpeed = 0.4;
        target = TargetType.AIR_GROUND;
        range = 6;
        lifeTime = 40;

        switch (level) {
            case 1:
                hp = 800;
                damage = 20;
                maxDamage = 400;
                break;
            case 2:
                hp = 880;
                damage = 22;
                maxDamage = 440;
                break;
            case 3:
                hp = 968;
                damage = 24;
                maxDamage = 484;
                break;
            case 4:
                hp = 1064;
                damage = 26;
                maxDamage = 532;
                break;
            case 5:
                hp = 1168;
                damage = 29;
                maxDamage = 584;
                break;
        }
    }
}
