package org.gamedevs.clashroyale.model.cards.spell;

import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.cards.CardName;

/**
 * This class contains main method of Clash Royale application!
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public abstract class Spell extends Card {

    public Spell(CardName cardName, int cost) {
        super(cardName, cost);
    }

}
