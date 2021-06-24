package main.java.org.gamedevs.clashroyale.model.utils.multithreading;

/**
 * This class contains the structure of Runnable classes!
 * It can run a class as a new thread!
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.4
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
                thisThread.stop();
                thisThread = null;
                runningState = false;
            }catch (Exception ignored){}
        }
    }

    /**
     * @return running state!
     */
    public boolean isRunning() {
        return runningState;
    }

}
