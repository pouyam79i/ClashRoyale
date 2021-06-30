package org.gamedevs.clashroyale.model;

import java.io.File;

/**
 * Enums for loading picture of cards in card deck scene
 * @author Pouya Mohammadi - Hosna Hoseini
 *         9829039 -CE@AUT   9823010 -CE@AUT
 * @version 1.0
 */
public enum CardsImage {
    ARROWS(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/arrows.png").toURI().toString()),
    ARCHERS(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/archers.png").toURI().toString()),
    BABY_DRAGON(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/baby-dragon.png").toURI().toString()),
    BARBARIANS(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/barbarians.png").toURI().toString()),
    CANNON(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/cannon.png").toURI().toString()),
    FIREBALL(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/fireball.png").toURI().toString()),
    GIANT(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/giant.png").toURI().toString()),
    INFERNO_TOWER(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/inferno-tower.png").toURI().toString()),
    MINI_PEKKA(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/mini-pekka.png").toURI().toString()),
    RAGE(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/rage.png").toURI().toString()),
    VALKYRIE(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/valkyrie.png").toURI().toString()),
    WIZARD(new File("src/main/resources/org/gamedevs/clashroyale/view/img/cards/wizard.png").toURI().toString());



    private String url;

    CardsImage(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
