package org.gamedevs.clashroyale.model.game.droppable.objects;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.DropType;
import org.gamedevs.clashroyale.model.game.droppable.Droppable;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

/**
 * Main structure game object class!
 * @author Pouya Mohammadi - CE@AUT - Uni ID: 9829039
 * @version 1.0
 */
public abstract class GameObject extends Droppable {

    /**
     * hit point
     * (when character dies or health of character)
     */
    protected DoubleProperty hp = new SimpleDoubleProperty();
    /**
     * amount of giving damage!
     */
    protected int damage;
    /**
     * speed of attacking
     * (giving damage to the enemy)
     */
    protected double hitSpeed;
    /**
     * attack range defines
     * the max attacking radius!
     */
    protected double range;
    /**
     * attack target type
     */
    protected TargetType attackTargetType;
    /**
     * Type of this object
     */
    protected TargetType myType;
    /**
     * z value says if the object
     * is on the ground or in the air!
     */
    protected int z;
    /**
     * angle of object
     */
    protected Angle angle;
    /**
     * State of game object
     */
    protected GameObjectState state;
    protected double errorInGUIX;
    protected double errorInGUIY;
    // Position properties:
    /**
     * Head pixel of object!
     */
    protected Tile headTile;

    /**
     * Constructor of game object
     * @param side side of object
     */
    protected GameObject(Side side) {
        super(DropType.OBJECT, side);
        angle = Angle.NORTH;
        state = GameObjectState.MOVING;
        z = 0; // This is zero except for baby dragon!
    }

    /**
     * Start attacking to the target (gives damage to target object)
     */
    protected void attack(GameObject target){
        if(target != null){
            state = GameObjectState.ATTACK;
            target.reduceHP(damage);
            try {
                Thread.sleep((int)(hitSpeed * 1000));
            } catch (InterruptedException ignored) {}
        }
        else {
            state = GameObjectState.MOVING;
        }
    }

    /**
     * reduces this object hp.
     * @param takenDamage is the damage given to game object by enemy!
     */
    public synchronized void reduceHP(int takenDamage){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                hp.setValue(hp.subtract(takenDamage).getValue());
            }
        });
    }

    /**
     * Checks attack range,
     * if target is in attack range,
     * calls attack() on that target!
     */
    protected void checkTargetRange(){
        Thread targetRangeCheckerThread = (new Thread(() -> {
            GameObject lockedTarget = null;
            int x, y;       // beginning x,y of search area
            checkAttack:
            while (hp.get() > 0){
                attack(lockedTarget);
                if (lockedTarget != null){
                    if(lockedTarget.getHp() <= 0){
                        lockedTarget = null;
                        continue;
                    }
                   if(Math.round(range) >= battleField.calculateDistance(headTile, lockedTarget.getHeadPixel())){
                        continue;
                   }
                }
                x = headTile.getX() - (int)Math.round(range);
                y = headTile.getY() - (int) Math.round(range);
                for(int j = 0; j <= (Math.round(range) * 2 + 1); j++){
                    for(int i = 0; i <= (Math.round(range) * 2 + 1); i++) {
                        Tile searchTile = battleField.getPixel(x + i, y + j);
                        if(searchTile != null){
                            if(battleField.calculateDistance(headTile, searchTile) <= Math.round(range)){
                                GameObject target = null;
                                // Giant
                                if(attackTargetType == TargetType.BUILDING){
                                    target = searchTile.getGameObject();
                                    if(target.getTeamSide() != teamSide){
                                        if(target.getMyType() == TargetType.BUILDING){
                                            if(target.getHp() > 0){
                                                lockedTarget = target;
                                                continue checkAttack;
                                            }
                                        }
                                    }
                                }
                                // Ground soldiers
                                else if(attackTargetType == TargetType.GROUND){
                                    if(target.getTeamSide() != teamSide){
                                        if(target.getMyType() == TargetType.GROUND){
                                            if(target.getHp() > 0){
                                                lockedTarget = target;
                                                continue checkAttack;
                                            }
                                        }
                                        else if(target.getMyType() == TargetType.BUILDING){
                                            if(target.getHp() > 0){
                                                lockedTarget = target;
                                                continue checkAttack;
                                            }
                                        }
                                    }
                                }
                                else if(attackTargetType == TargetType.AIR_GROUND){
                                    if(target.getTeamSide() != teamSide){
                                        if(target.getMyType() == TargetType.AIR || target.getMyType() == TargetType.GROUND){
                                            if(target.getHp() > 0){
                                                lockedTarget = target;
                                                continue checkAttack;
                                            }
                                        }
                                        else if(target.getMyType() == TargetType.BUILDING){
                                            if(target.getHp() > 0){
                                                lockedTarget = target;
                                                continue checkAttack;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(hp.get() > 0){
                Console.getConsole().printTracingMessage("Failed to complete check attack loop!");
            }
        }));
        targetRangeCheckerThread.setDaemon(true);
        targetRangeCheckerThread.start();
    }

    // Getters
    public GameObjectState getState() {
        return state;
    }
    public Angle getAngle() {
        return angle;
    }
    public Tile getHeadPixel() {
        return headTile;
    }

    public double getHp() {
        return hp.get();
    }

    public DoubleProperty hpProperty() {
        return hp;
    }

    public Tile getHeadTile() {
        return headTile;
    }

    public int getDamage() {
        return damage;
    }
    public double getHitSpeed() {
        return hitSpeed;
    }
    public double getRange() {
        return range;
    }
    public TargetType getAttackTargetType() {
        return attackTargetType;
    }
    public TargetType getMyType() {
        return myType;
    }
    public int getZ() {
        return z;
    }
    public Side getTeamSide() {
        return teamSide;
    }

    public double getErrorInGUIX() {
        return errorInGUIX;
    }

    public double getErrorInGUIY() {
        return errorInGUIY;
    }

    // Setters
    public void setHeadPixel(Tile headTile) {
        this.headTile = headTile;
    }

    public void setState(GameObjectState state) {
        this.state = state;
    }
}
