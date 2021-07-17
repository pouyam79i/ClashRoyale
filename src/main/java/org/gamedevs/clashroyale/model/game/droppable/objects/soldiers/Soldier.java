package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;

public abstract class Soldier extends GameObject {

    /**
     * Speed of player (related to movement)
     */
    protected int speed;
    /**
     * Area splash ability (attack point or effect)
     */
    protected boolean areaSplash;

    /**
     * Setting default values for soldier object
     */
    protected Soldier(Side side){
        super(side);
        myType = TargetType.GROUND; // except for baby dragon!
    }

    /**
     * Thread of object (applies algorithm of game object)
     */
    @Override
    public void run() {

    }

    /**
     * move me to next tile
     * @param nextTile next tile
     * @return true if i have moved successfully
     */
    protected boolean move(Tile nextTile){
        if(nextTile != null){
            Angle nextTileAngel = headTile.getSurroundingTileAngel(nextTile);
            if(nextTileAngel != null){
                if(headTile.carry(angle, z)){
                    headTile = nextTile;
                    try {
                        Thread.sleep((int)(speed * 1000));
                    } catch (InterruptedException ignored) {}
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates object to next place
     */
    protected void mover(){
        Thread moverThread = (new Thread(() -> {
            while (hp > 0){
                if(state == GameObjectState.MOVING){

                }
            }
        }));
        moverThread.setDaemon(true);
        moverThread.start();
    }

    /**
     * Finds shortest path to the nearest target
     */
    protected void findTarget(){

    }

}
