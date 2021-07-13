package org.gamedevs.clashroyale.model.launcher;

import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * This class launches the battle sequence,
 * which brings the game until battle is started!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class BattleLauncher extends Runnable {

    /**
     * Constructor of BattleLauncher
     * Sets requirements!
     */
    public BattleLauncher(){
        threadName = "BattleLauncher";
    }

    /**
     * This thread brings the game until battle is started!
     */
    @Override
    public void run() {

    }

}
