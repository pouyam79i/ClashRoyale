package org.gamedevs.clashroyale.model.cards;

import java.io.Serializable;

/**
 * This class contains name of all cards,
 * which are designed for this game so far.
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.1
 */
public enum CardName implements Serializable {

    // Soldiers
    BARBARIANS(0),
    ARCHERS(1),
    BABY_DRAGON(2),
    WIZARD(3),
    MINI_PEKKA(4),
    GIANT(5),
    VALKYRIE(6),

    // Spells
    RAGE(7),
    FIREBALL(8),
    ARROWS(9),

    // Buildings
    CANNON(10),
    INFERNO_TOWER(11),
    // Special Cards (only build once)
    KING_TOWER(12),
    PRINCESS_TOWER(13),

    // No card
    EMPTY(-1);
    /**
     * value of card
     */
    private final int value;

    /**
     * @param value of card
     */
    CardName(int value) {
    this.value = value;
    }

    /**
     * @param value of card name
     * @return card name by value
     */
    public static CardName getCardByValue(int value){
        return switch (value) {
            case 0 -> BARBARIANS;
            case 1 -> ARCHERS;
            case 2 -> BABY_DRAGON;
            case 3 -> WIZARD;
            case 4 -> MINI_PEKKA;
            case 5 -> GIANT;
            case 6 -> VALKYRIE;
            case 7 -> RAGE;
            case 8 -> FIREBALL;
            case 9 -> ARROWS;
            case 10 -> CANNON;
            case 11 -> INFERNO_TOWER;
            case 12 -> KING_TOWER;
            case 13 -> PRINCESS_TOWER;
            default -> EMPTY;
        };
    }

    // Getters
    public int getValue() {
        return value;
    }

}
