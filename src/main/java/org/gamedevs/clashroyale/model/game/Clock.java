package org.gamedevs.clashroyale.model.game;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private boolean Isend = false;
    /**
     * instance of clock
     */
    private static Clock clock = null;
    /**
     * instance
     */
    private StringProperty clockString = new SimpleStringProperty();
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
                timeToString();
            } catch (InterruptedException e) {
            }
        }
        Isend = true;
    }

    /**
     * stop clock
     */
    public void stop(){
        super.shutdown();
    }

    /**
     * start clock
     */
    public void startClock(){
        super.start();
    }

    /**
     * get current time in mm:ss format
     * @return current time
     */
    public void timeToString(){
        String res;
        res = "0" + minute.get() + ":";
        if(second.get() < 10)
            res += "0" + second.get();
        else
            res += second.get();

        String finalRes = res;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clockString.setValue(finalRes);
            }
        });
    }

    //Getter
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

    public String getClockString() {
        return clockString.get();
    }

    public StringProperty clockStringProperty() {
        return clockString;
    }

    public boolean isIsend() {
        return Isend;
    }
}
