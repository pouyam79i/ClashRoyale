package org.gamedevs.clashroyale.model.game.battle.tools;

/**
 * This class handles timer count down job!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class Timer {

    /**
     * seconds of timer
     */
    private int second;
    /**
     * minutes left in timer
     */
    private int min;
    /**
     * If time hase ended
     */
    private boolean endOfTime;

    /**
     * Setting requirements of timer
     * @param min pos value of minute
     * @param second pos value of second
     */
    public Timer(int min, int second){
        if(min < 0)
            min *= -1;
        if(second < 0)
            second *= -1;
        this.min = min;
        this.second = second;
        endOfTime = min == 0 && second == min;
    }

    /**
     * decrees 1 second
     * if time has ended it does not thing
     */
    public void decrees(){
        if(!endOfTime){
            if(second == 0){
                if(min == 0){
                    endOfTime = true;
                    return;
                }
                second = 59;
                min--;
            }
            else {
                second--;
            }
        }
    }

    // Getters
    public int getSecond() {
        return second;
    }
    public int getMin() {
        return min;
    }
    public boolean isEndOfTime() {
        return endOfTime;
    }

}
