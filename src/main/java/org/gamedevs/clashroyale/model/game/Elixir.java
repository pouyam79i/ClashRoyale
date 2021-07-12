package org.gamedevs.clashroyale.model.game;

import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.time.LocalTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class to represent and handle Elixir in game
 *
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class Elixir extends Runnable {

    /**
     * elixir value
     */
    private boolean running = true;
    Clock clock = Clock.getClock();
    private float value = 4;
    private static Elixir player1Elixir = null;

    private Elixir() {
    }

    public void run() {
        clock.start();
        do {
            if (clock.getCurrentSecond() < 2 * 2)
                value += 0.05;
            else if (clock.getCurrentSecond() < 10 * 1) {
                value += 0.1;
            } else
                break;
            stopProducingElixirIfNeeded();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Console.getConsole().printTracingMessage("elixir thread interrupted");
            }
        } while (true);
    }

    /**
     * check if the elixir is more than 10 stop producing it
     */
    private void stopProducingElixirIfNeeded() {
        long timePassed;
        if (value >= 10) {
            do {
                if (value < 10)
                    break;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Console.getConsole().printTracingMessage("elixir thread interrupted while not producing");
                }
            } while (clock.getCurrentSecond() < 10);
        }

    }


    /**
     * get current elixir value
     *
     * @return elixir value
     */
    public float getCurrentElixir() {
        return value;
    }

    /**
     * reduce elixir
     *
     * @param reduction amount of used elixir
     */
    public void reduceElixir(float reduction) {
        value -= reduction;
    }

    public static Elixir getPlayer1Elixir() {
        if (player1Elixir == null)
            player1Elixir = new Elixir();

        return player1Elixir;
    }
}
