package org.gamedevs.clashroyale.model.game;

import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

public class Elixir extends Runnable {

    private float value = 4;

    @Override
    public void run() {

        while (value < 2*60){
            try {
                Thread.sleep(100);
                value += 0.05;

                stopProducingElixirIfNeeded();

            } catch (InterruptedException e) {
                Console.getConsole().printTracingMessage("Elixir sleep interrupted");
            }
        }

        while (value < 1*60){
            try {
                Thread.sleep(100);
                value += 0.1;

                stopProducingElixirIfNeeded();

            } catch (InterruptedException e) {
                Console.getConsole().printTracingMessage("Elixir sleep interrupted");
            }
        }
    }

    private void stopProducingElixirIfNeeded() {
        if(value >= 10)
            while (true){
                if(value < 10)
                    break;
            }
    }


    public float getCurrentElixir(){
        return value;
    }

    public void reduceElixir(float reduction){
        value -= reduction;
    }
}
