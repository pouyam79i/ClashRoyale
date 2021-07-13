package org.gamedevs.clashroyale.model.cards;

import org.gamedevs.clashroyale.model.game.objects.buildings.Cannon;
import org.gamedevs.clashroyale.model.game.objects.buildings.InfernoTower;
import org.gamedevs.clashroyale.model.game.objects.soldiers.*;

public class CardFactory {


    public void getGameObject(CardName cardName, int level) {
        switch (cardName) {

            case ARCHERS -> {
                for (int i = 0; i < 2; i++)
                    new Archer(level).run();
            }
            case BARBARIANS ->  {
                for (int i = 0; i < 4; i++)
                    new Barbarian(level).run();
            }
            case WIZARD -> new Wizard(level).run();
            case BABY_DRAGON -> new BabyDragon(level).run();
            case VALKYRIE -> new Valkyrie(level).run();
            case MINI_PEKKA -> new MiniPeka(level).run();
            case GIANT -> new Giant(level).run();
            case CANNON -> new Cannon(level).run();
            case INFERNO_TOWER -> new InfernoTower(level).run();
//            case ARROWS -> new Arrows(level).run();
//            case FIREBALL -> new FireBall(level).run();
//            case RAGE -> new Rage(level).run();

        }
    }
}
