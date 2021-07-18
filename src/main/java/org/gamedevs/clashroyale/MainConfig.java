package org.gamedevs.clashroyale;

/**
 * This interface contains main configuration values
 * of ClashRoyale application!
 * @author Pouya Mohammadi - "pouyamohammadyirbu@gmail.com"
 * @version 1.0 'Developing mode'
 */
public interface MainConfig {

    /**
     * If you want to enable methods and features
     * that helps you to test and trace application
     * performance set its value to 'true'.
     * Else if you want to release the product,
     * set this value to 'false'.
     */
    boolean DEBUG_MODE = true;

    /**
     * Main game aspect ratio
     * 16 as height
     * 9 as width
     */
    String STD_ASPECT_RATIO = "16:9";
    /**
     * Main stage height standard
     */
    int MAIN_STAGE_HEIGHT = 720;
    /**
     * Main stage width standard
     */
    int MAIN_STAGE_WIDTH = 405;

    /**
     * Standard battle field height
     */
    int STD_BATTLE_FIELD_HEIGHT = 504;
    int STD_BATTLE_FIELD_Y_TILE = 30;
    double STD_BATTLE_FIELD_Y_TILE_RATIO = 16.8;
    /**
     * Standard battle field width
     */
    int STD_BATTLE_FIELD_WIDTH = 374;
    int STD_BATTLE_FIELD_X_TILE = 18;
    double STD_BATTLE_FIELD_X_TILE_RATIO = 20.7;


    /**
     * Standard latency needed to show btn animation in 'milliseconds'.
     */
    int STD_BUTTON_ANIMATION_LATENCY = 200;

}
