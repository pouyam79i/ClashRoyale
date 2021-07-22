package org.gamedevs.clashroyale.model.game.battle.tools;

import javafx.application.Platform;
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
     * timer has values of time!
     */
    private final Timer timer;
    /**
     * When time is up
     */
    private boolean endOfTime;
    /**
     * instance
     */
    private final SimpleStringProperty clockString;

    /**
     * Constructor of clock
     */
    public Clock() {
        timer = new Timer(3, 0);
        endOfTime = false;
        finished = false;
        clockString = new SimpleStringProperty();
        updateStringOfTimer();
        threadName = "Clock";
    }

    /**
     * start timer count down!
     */
    @Override
    public void run() {
        while (!timer.isEndOfTime()){
            if(finished)
                return;
            try {
                Thread.sleep(999);
            } catch (InterruptedException ignored) {}
            timer.decrees();
            updateStringOfTimer();
        }
        endOfTime = true;
    }

    /**
     * stop clock
     */
    public void stop(){
        this.shutdown();
    }

    /**
     * get current time in mm:ss format
     */
    public void updateStringOfTimer(){
        String clockLabelText = timer.getMin() + ":";
        if(timer.getSecond() < 10)
            clockLabelText += "0" + timer.getSecond();
        else
            clockLabelText += "" + timer.getSecond();
        String finalLabel = clockLabelText;
        Platform.runLater(() -> {
            clockString.setValue(finalLabel);
        });
    }

    public int getTotalSeconds(){
        return (timer.getMin() * 60) + timer.getSecond();
    }

    //Getter
    public boolean isEndOfTime() {
        return endOfTime;
    }
    public StringProperty clockStringProperty() {
        return clockString;
    }

}
