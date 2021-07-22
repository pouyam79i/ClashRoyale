package org.gamedevs.clashroyale.model.game.droppable.objects;

import org.gamedevs.clashroyale.model.game.player.Side;

/**
 * This is the ImmortalObject!
 * Cannot die.
 * Used to block ground!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class ImmortalObject extends GameObject{

    /**
     * Constructor of ImmortalObject
     * sets default values
     */
    public ImmortalObject(){
        super(Side.EMPTY);
        hp.setValue(1000000);
        attackTargetType = TargetType.IMMORTAL_OBJECT;
        range = 0;
        damage = 0;
        hitSpeed = 0;
    }

    /**
     * empty run
     */
    @Override
    public void run(){}

}
