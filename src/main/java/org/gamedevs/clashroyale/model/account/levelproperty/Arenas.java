package org.gamedevs.clashroyale.model.account.levelproperty;

/**
 * This enum contains information of arenas
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public enum Arenas {

    // Name and level of arenas
    ARENA_1(1, "Training Camp"),
    ARENA_2(2, "Goblin Stadium"),
    ARENA_3(3, "Bone Pit"),
    ARENA_4(4, "Barbarian Bowl"),
    ARENA_5(5, "PEKKA's Playhouse"),
    ARENA_6(6, "Legendary Arena");

    /**
     * level of arena
     */
    private final int level;
    /**
     * name of arena
     */
    private final String name;

    /**
     * Sets defaults of arena name
     * @param level of arena
     * @param name of arena
     */
    Arenas(int level, String name){
        this.level = level;
        this.name = name;
    }

    /**
     * @param level of arena
     * @return arena, according to level
     */
    public static Arenas getArenaByLevel(int level){
        return switch (level) {
            case 1 -> ARENA_1;
            case 2 -> ARENA_2;
            case 3 -> ARENA_3;
            case 4 -> ARENA_4;
            case 5 -> ARENA_5;
            case 6 -> ARENA_6;
            default -> null;
        };
    }

    // Getter
    public int getLevel() {
        return level;
    }
    public String getName() {
        return name;
    }

}
