package org.gamedevs.clashroyale.model.game.droppable;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.Droppable;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.Cannon;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.InfernoTower;
import org.gamedevs.clashroyale.model.game.droppable.objects.soldiers.*;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.game.droppable.spell.Arrows;
import org.gamedevs.clashroyale.model.game.droppable.spell.FireBall;
import org.gamedevs.clashroyale.model.game.droppable.spell.Rage;

import java.util.ArrayList;

/**
 * A class to make ans start new game object regarding to card name
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class CardFactory {

    /**
     * This method builds droppable items accoring to name of card
     * @param cardName name of card
     * @param level level of card
     * @param cardSide side of card
     * @return droppable items in an arry list
     */
    public static ArrayList<Droppable> buildDroppableItems(CardName cardName, int level, Side cardSide){
        ArrayList<Droppable> droppableItems = new ArrayList<Droppable>();
        switch (cardName) {
            case ARCHERS -> {
                droppableItems.add(new Archer(level, cardSide));
                droppableItems.add(new Archer(level, cardSide));
            }
            case BARBARIANS -> {
                droppableItems.add(new Barbarian(level, cardSide));
                droppableItems.add(new Barbarian(level, cardSide));
                droppableItems.add(new Barbarian(level, cardSide));
                droppableItems.add(new Barbarian(level, cardSide));
                droppableItems.add(new Barbarian(level, cardSide));
            }
            case BABY_DRAGON -> droppableItems.add(new BabyDragon(level, cardSide));
            case WIZARD -> droppableItems.add(new Wizard(level, cardSide));
            case MINI_PEKKA -> droppableItems.add(new MiniPeka(level, cardSide));
            case VALKYRIE -> droppableItems.add(new Valkyrie(level, cardSide));
            case GIANT -> droppableItems.add(new Giant(level, cardSide));
            case CANNON -> droppableItems.add(new Cannon(level, cardSide));
            case INFERNO_TOWER -> droppableItems.add(new InfernoTower(level, cardSide));
            case RAGE -> droppableItems.add(new Rage(level, cardSide));
            case FIREBALL -> droppableItems.add(new FireBall(level, cardSide));
            case ARROWS -> droppableItems.add(new Arrows(level, cardSide));
        }
        return droppableItems;
    }

}
