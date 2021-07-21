package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.battle.tools.GameResult;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Side;

import java.util.ArrayList;

/**
 * Main towers contains score for opponent player
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public abstract class MainTowers extends Building{

    /**
     * when main tower dies one score must be added to another player
     */
    private GameResult gameResult;

    /**
     * Setting default values for building object.
     * @param side side of main tower
     */
    protected MainTowers(Side side) {
        super(side);
    }

}



