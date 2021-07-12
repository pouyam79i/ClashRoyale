package org.gamedevs.clashroyale.model.game;

import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;
/**
 * A class to represent and handle Elixir in game
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class Elixir extends Runnable {

    /**
     * elixir value
     */
    private float value = 4;

    /**
     * start producing elixir (2 min -> slow, 1 min->fast)
     */
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

    /**
     * check if the elixir is more than 10 stop producing it
     */
    private void stopProducingElixirIfNeeded() {
        if(value >= 10)
            while (true){
                if(value < 10)
                    break;
            }
    }


    /**
     * get current elixir value
     * @return elixir value
     */
    public float getCurrentElixir(){
        return value;
    }

    /**
     * reduce elixir
     * @param reduction amount of used elixir
     */
    public void reduceElixir(float reduction){
        value -= reduction;
    }
}
