package org.gamedevs.clashroyale.model.game.battle.tools;

import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * A class to represent and handle clock (timer) in game
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class Clock extends Runnable {

    /**
     * seconds passed from beginning of game
     */
    private int value = 0;
    /**
     * current second
     */
    private int second = 0;
    /**
     * current minute
     */
    private int minute = 0;

    /**
     * instance of clock
     */
    private static Clock clock = null;

    /**
     * constructor
     */
    private Clock() {
    }

    /**
     * start clock from 0 to 3 min
     */
    @Override
    public void run() {
        while (value < 3 * 60){
            try {
                Thread.sleep(1000);
                value ++;
                minute = value / 60;
                second = value % 60;
            } catch (InterruptedException e) {
                Console.getConsole().printTracingMessage("clock sleep interrupted");
            }
        }

    }

    /**
     * get current time in mm:ss format
     * @return current time
     */
    public String getTimeAsString(){
        String res;
        res = "0" + minute + ":";
        if(second < 10)
            res += "0" + second;
        else
            res += second;
        return res;
    }

    /**
     * get seconds passed from the beginning of game
     * @return second
     */
    public int getCurrentSecond(){
        return value;
    }

    public static Clock getClock(){
        if(clock == null)
            clock = new Clock();
        return clock;
    }
}
