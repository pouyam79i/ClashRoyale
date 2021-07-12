package org.gamedevs.clashroyale.model.game;

import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Runnable {

    private int value = 0;
    private int second = 0;
    private int minute = 0;

    @Override
    public void run() {
        while (value < 3*60){
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

    public String getTimeAsString(){
        String res;
        res = "0" + minute + ":";
        if(second < 10)
            res += "0" + second;
        else
            res += second;
        return res;
    }

    public int getCurrentSecond(){
        return value;
    }

}
