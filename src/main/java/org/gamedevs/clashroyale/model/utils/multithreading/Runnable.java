package org.gamedevs.clashroyale.model.utils.multithreading;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.console.ConsoleColor;

/**
 * This class contains the structure of Runnable classes!
 * It can run a class as a new thread!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.4.2
 */
public abstract class Runnable implements java.lang.Runnable{

    /**
     * contains this thread
     */
    private Thread thisThread;
    /**
     * Running state of thread
     */
    private boolean runningState;
    /**
     * Trace mode tells if you need to trace
     * this thread in console.
     */
    private final boolean traceMode = MainConfig.DEBUG_MODE;
    /**
     * contains the state of thread loop.
     * if false, thread is still running.
     * if true, the process of finishing begin!
     */
    protected volatile boolean finished;
    /**
     * Name of thread!
     */
    protected String threadName = "EMPTY";

    /**
     * Constructor of Runnable.
     * Setup required fields to make a runnable object!
     */
    protected Runnable(){
        finished = false;
        runningState = false;
        thisThread = null;
    }

    /**
     * Calling for start thread!
     * Object will be running in a new thread!
     * Override this method to have safer start!
     * remember to use this.initial(); if it is overridden.
     */
    public synchronized void start(){
        this.initial();
    }

    /**
     * Calling for shutdown thread!
     * Override this method to have safer shutdown!
     * remember to use this.close();  if it is overridden.
     */
    public synchronized void shutdown(){
        this.close();
    }

    /**
     * Builds and run a new thread
     * to run this object's run method in a new thread!
     * Only one thread will be built!
     */
    protected synchronized void initial(){
        finished = false;
        if(!runningState){
            thisThread = new Thread(this);
            runningState = true;
            if(traceMode){
                Console.getConsole().println(
                        "[" + ConsoleColor.GREEN_BOLD + threadName + ConsoleColor.RESET + "]" + " Initialized!"
                );
            }
            thisThread.setDaemon(true);
            thisThread.start();
        }
    }

    /**
     * Tells the thread to begin the process of killing it self.
     * this method must be called within the child class.
     * It is better to call it in shutdown() method to have safer shutdown.
     */
    protected synchronized void close(){
        finished = true;
        if (runningState){
            try {
                thisThread.interrupt();
                thisThread = null;
                runningState = false;
                if(traceMode){
                    Console.getConsole().println(
                            "[" + ConsoleColor.RED_BOLD + threadName + ConsoleColor.RESET + "]" + " Killed!"
                    );
                }
            }catch (Exception ignored){}
        }
    }

    /**
     * This method helps to trace a thread in console,
     * and also remember to set MainConfig.DEBUG_MODE to 'true'
     * else you will not see the message!
     * @param message will be printed in console!
     */
    protected void printTraceMessage(String message){
        if(traceMode){
            Console.getConsole().println(
                    "[" + ConsoleColor.YELLOW_BOLD + threadName + ConsoleColor.RESET + "]"
                            + " Tracing message: " + message
            );
        }
    }

    /**
     * @return running state!
     */
    public boolean isRunning() {
        return runningState;
    }

    /**
     * @return thread of this object!
     */
    protected Thread getThisThread() {
        return thisThread;
    }

}
