package org.gamedevs.clashroyale.model.game;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

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
    private DoubleProperty elixirValue = new SimpleDoubleProperty(4);
    /**
     * clock which elixir work regarding to it
     */
    Clock clock = Clock.getClock();

    /**
     * time (in seconds) that elixir has to produce slowly
     */
    private static final int SLOW_PRODUCTION_DURATION = 60 * 2;
    /**
     * whole time (in seconds) that elixir has to produce
     */
    private static final int PRODUCTION_DURATION = 60 * 3;
    /**
     * maximum elixir that can be produce;
     */
    private static final int MAXIMUM_ELIXIR = 10;

    /**
     * Elixir instance
     */
    private static Elixir player1Elixir = null;

    /**
     * constructor
     */
    private Elixir() {
    }

    /**
     * produce elixir considering speed for 3 minute
     * stop when it reaches 3 min
     */
    public void run() {

        do {

            if (elixirValue.get() < MAXIMUM_ELIXIR) {
                if (clock.getClockValue() < SLOW_PRODUCTION_DURATION)
                    Platform.runLater(() -> elixirValue.setValue(elixirValue.add(0.05).getValue()));
                else
                    Platform.runLater(() -> elixirValue.setValue(elixirValue.add(0.1).getValue()));
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Console.getConsole().printTracingMessage("interruption in elixir thread while sleeping");
            }

        } while (clock.getClockValue() < PRODUCTION_DURATION);
    }


    /**
     * stop Producing elixir
     */
    public void stopProducing() {
        super.shutdown();
    }


    /**
     * start elixir
     */
    public void startElixir() {
        super.start();
    }

    /**
     * reduce elixir
     *
     * @param reduction amount of used elixir
     */
    public void reduceElixir(float reduction) {
        Platform.runLater(() -> elixirValue.setValue(elixirValue.subtract(reduction).getValue()));
    }

    //Getter
    public double getElixirValue() {
        return elixirValue.get();
    }

    public DoubleProperty elixirValueProperty() {
        return elixirValue;
    }

    public static Elixir getPlayer1Elixir() {
        if (player1Elixir == null)
            player1Elixir = new Elixir();

        return player1Elixir;
    }

}
