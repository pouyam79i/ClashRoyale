package org.gamedevs.clashroyale.model.container.gamedata;

import org.gamedevs.clashroyale.model.game.player.Player;

/**
 * Player container contains current player interface,
 * player allows to interact with the game.
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class PlayerContainer {

    /**
     * only instance of this class
     */
    private static PlayerContainer playerContainer = null;

    /**
     * Player container field.
     * allows to interact with game engines!
     */
    private Player player;

    /**
     * Constructor of PlayerContainer
     */
    private PlayerContainer(){
        player = null;
    }

    // Getter
    public Player getPlayer() {
        return player;
    }

    // Setter
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return only instance of this class
     */
    public static PlayerContainer getPlayerContainer() {
        if(playerContainer == null)
            playerContainer = new PlayerContainer();
        return playerContainer;
    }

}
