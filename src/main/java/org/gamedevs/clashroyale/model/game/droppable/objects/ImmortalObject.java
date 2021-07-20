package org.gamedevs.clashroyale.model.game.droppable.objects;

import org.gamedevs.clashroyale.model.game.player.Side;

public class ImmortalObject extends GameObject{

    public ImmortalObject(){
        super(Side.EMPTY);
        threadName = "ImmortalObject";
        hp.setValue(1000000);
        attackTargetType = TargetType.IMMORTAL_OBJECT;
        range = 0;
        damage = 0;
        hitSpeed = 0;
    }

    @Override
    public void run() {
        this.shutdown();
    }

}
