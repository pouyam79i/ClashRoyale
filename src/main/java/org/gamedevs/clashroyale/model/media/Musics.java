package org.gamedevs.clashroyale.model.media;

/**
 * Main musics in music player
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public enum Musics {

    BEFORE_MAIN_MENU(0),       // Play this music for loading and signup menu
    MAIN_MENU(1),              // Main menu music
    BATTLE_FIRST_PHASE(2),     // Battle music for first phase
    BATTLE_SECOND_PHASE(3),    // Battle music for second phase
    BATTLE_RESULT(4);          // Battle result music

    // index of music in music player memory
    private final int index;

    /**
     * @param index of media in music player memory
     */
    Musics(int index) {
        this.index = index;
    }

    /**
     * @return index of music in  music player memory
     */
    public int getIndex() {
        return index;
    }

}
