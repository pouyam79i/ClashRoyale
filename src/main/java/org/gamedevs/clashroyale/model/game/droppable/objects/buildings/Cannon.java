package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;
/**
 * a class which handle cannon
 *
 * @author Pouya Mohammadi -Hosna Hoseini
 * 9826039 -CE@AUT     9823010 -CE@AUT
 * @version 1.0
 */
public class Cannon extends Building{
    public Cannon(int level, Side side){
        super(side);
        hitSpeed = 0.8;
        attackTargetType = TargetType.GROUND;
        range = 5.5;
        lifeTime = 30;
        effectiveLifeTime = true;
        nameOfDroppable = CardName.CANNON;
        errorInGUIX =  0.5 * MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO;
        errorInGUIY = 2 * MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO;
        switch (level){
            case 1 :
                hp.setValue(380);
                damage = 60;
                break;
            case 2 :
                hp.setValue(418);
                damage = 66;
                break;
            case 3 :
                hp.setValue(459);
                damage = 72;
                break;
            case 4 :
                hp.setValue(505);
                damage = 79;
                break;
            case 5 :
                hp.setValue(554);
                damage = 87;
                break;
        }
    }
}
