package org.gamedevs.clashroyale.model.game.battle.tools;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * A class to represent and handle Elixir in game
 *
 * @author Hosna Hoseini - CE@AUT - Uni ID: 9823010
 * @version 1.0
 */
public class Elixir implements Runnable {

    /**
     * elixir value
     */
    private boolean running = true;
    Clock clock = Clock.getClock();
    private DoubleProperty value = new SimpleDoubleProperty(4);
    private static Elixir player1Elixir = null;

    private Elixir() {
    }

    public void run() {
        Thread thread = new Thread(clock);
        thread.start();
        do {
            if (clock.getCurrentSecond() < 60 * 2)
                value.setValue(value.add(0.05).getValue());

            else if (clock.getCurrentSecond() < 60 * 3) {
                value.setValue(value.add(0.1).getValue());

            } else
                break;
            stopProducingElixirIfNeeded();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        } while (true);
    }

    /**
     * check if the elixir is more than 10 stop producing it
     */
    private void stopProducingElixirIfNeeded() {
        if (value.getValue() >= 10) {
            do {
                if (value.getValue() < 10)
                    break;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            } while (clock.getCurrentSecond() < 10);
        }

    }

    public double getValue() {
        return value.get();
    }

    public DoubleProperty valueProperty() {
        return value;
    }

    /**
     * reduce elixir
     *
     * @param reduction amount of used elixir
     */
    public void reduceElixir(float reduction) {
        value.setValue(value.subtract(reduction).getValue());

    }

    public static Elixir getPlayer1Elixir() {
        if (player1Elixir == null)
            player1Elixir = new Elixir();

        return player1Elixir;
    }

}
