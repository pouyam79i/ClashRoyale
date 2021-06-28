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

}