package org.gamedevs.clashroyale.model.utils.console;

import org.gamedevs.clashroyale.MainConfig;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Console class contains required tools to interact with console.
 * plus it uses singleton pattern and also must be used in a synchronized way.
 * Console is used to make a good way to interact with user.
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.1.2
 */
public class Console{

    /**
     * This is the only instance of Console
     */
    private static Console consoleInstance = null;
    /**
     * scanner is used to read from console
     */
    private final Scanner scanner;
    /**
     * Tracer mode
     */
    private final boolean tracerMode = MainConfig.DEBUG_MODE;

    /**
     * Console Constructor
     * Setup required tools.
     */
    private Console(){
        // Getting a scanner
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    /**
     * This method is used to read a line from console
     * It never returns null!
     * @return String
     */
    public synchronized String readConsole(){
        String input = scanner.nextLine();
        if(input == null)
            return "";
        return input;
    }

    /**
     * This method use print method of System.out to print the input.
     * @param input will be printed
     */
    public synchronized void print(String input){
        System.out.print(input + ConsoleColor.RESET);
    }

    /**
     * Use this method to print tracing message in console
     * @param message will be printed in debug mode!
     */
    public synchronized void printTracingMessage(String message){
        if(tracerMode){
            println(ConsoleColor.YELLOW_BOLD + "Tracing message: "
                    + ConsoleColor.RESET + message);
        }
    }

    /**
     * This method use println method of System.out to print the input.
     * @param input will be printed
     */
    public synchronized void println(String input){
        print(input + ConsoleColor.RESET + "\n");
    }

    /**
     * Return the console!
     * If it is being run for first time it construct a Console Then returns it!
     * @return Console
     */
    public synchronized static Console getConsole() {
        if(consoleInstance == null)
            consoleInstance = new Console();
        return consoleInstance;
    }

}
