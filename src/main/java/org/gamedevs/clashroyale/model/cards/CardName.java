package org.gamedevs.clashroyale.model.cards;

import java.io.Serializable;

/**
 * This class contains name of all cards,
 * which are designed for this game so far.
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0.1
 */
public enum CardName implements Serializable {

    // Soldiers
    BARBARIANS,
    ARCHERS,
    BABY_DRAGON,
    WIZARD,
    MINI_PEKKA,
    GIANT,
    VALKYRIE,

    // Spells
    RAGE,
    FIREBALL,
    ARROWS,

    // Buildings
    CANNON,
    INFERNO_TOWER,
    // Special Cards (only build once)
    KING_TOWER,
    PRINCESS_TOWER,

    // No card
    EMPTY,

}
