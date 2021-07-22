package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

import java.util.ArrayList;

/**
 *
 */
public class PrincessTower extends MainTowers{

    /**
     * Constructor of PrincessTower.
     * Sets defaults of princes tower!
     * @param level of PrincessTower
     * @param side of PrincessTower
     */
    public PrincessTower(int level, Side side){
        super(side);
        nameOfDroppable = CardName.PRINCESS_TOWER;
        hitSpeed = 0.8;
        range = 7.5;
        attackTargetType = TargetType.AIR_GROUND;
        lifeTime = 1000;
        effectiveLifeTime = false;
        switch (level){
            case 1 :
                hp.setValue(1400);
                damage = 50;
                break;
            case 2 :
                hp.setValue(1512);
                damage = 54;
                break;
            case 3 :
                hp.setValue(1624);
                damage = 58;
                break;
            case 4 :
                hp.setValue(1750);
                damage = 62;
                break;
            case 5 :
                hp.setValue(1890);
                damage = 69;
                break;
        }
    }

    @Override
    public void run(){
        checkTargetRange();
        currentFrame++;
        if(hp.getValue() <= 0){
            if(!resultSet){
                gameResult.addScore(Side.getOppositeSide(teamSide));
            }
        }
    }

}
