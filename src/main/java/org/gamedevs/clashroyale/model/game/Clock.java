package org.gamedevs.clashroyale.model.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private IntegerProperty clockValue = new SimpleIntegerProperty(0);
    /**
     * current second
     */
    private IntegerProperty second = new SimpleIntegerProperty(0);
    /**
     * current minute
     */
    private IntegerProperty minute = new SimpleIntegerProperty(0);

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
        while (clockValue.getValue() < 3 * 60){
            try {
                Thread.sleep(1000);
                clockValue.setValue(clockValue.add(1).getValue());
                minute.setValue(clockValue.divide(60).getValue());
                second.setValue((clockValue.subtract(minute.multiply(60))).getValue());
                System.out.println(getTimeAsString());
            } catch (InterruptedException e) {
            }
        }

    }

    public void start(){
        this.run();
    }

    public void stop(){
        this.shutdown();
    }

    /**
     * get current time in mm:ss format
     * @return current time
     */
    public String getTimeAsString(){
        String res;
        res = "0" + minute.get() + ":";
        if(second.get() < 10)
            res += "0" + second.get();
        else
            res += second.get();
        return res;
    }

    public int getClockValue() {
        return clockValue.get();
    }

    public IntegerProperty clockValueProperty() {
        return clockValue;
    }

    public int getSecond() {
        return second.get();
    }

    public IntegerProperty secondProperty() {
        return second;
    }

    public int getMinute() {
        return minute.get();
    }

    public IntegerProperty minuteProperty() {
        return minute;
    }

    public static Clock getClock(){
        if(clock == null)
            clock = new Clock();
        return clock;
    }
}
