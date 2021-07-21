package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Angle;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.droppable.objects.soldiers.Archer;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;
import org.gamedevs.clashroyale.model.utils.multithreading.Runnable;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * a class which handle buildings
 *
 * @author Pouya Mohammadi -Hosna Hoseini
 * 9826039 -CE@AUT     9823010 -CE@AUT
 * @version 1.0
 */
public abstract class Building extends GameObject {

    /**
     * Life time of building.
     * Set a std  value for infinite life time (used for king and princesses towers)
     */
    protected int lifeTime;

    /**
     * If life time matchers for building,
     */
    protected boolean effectiveLifeTime;
    /**
     * locked target to hit
     */
    protected GameObject lockedTarget;

    protected ExecutorService service = Executors.newSingleThreadExecutor();

    /**
     * Setting default values for building object
     */
    protected Building(Side side) {
        super(side);
        myType = TargetType.BUILDING;
    }

    /**
     * Start attacking to the target (gives damage to target object)
     */
    @Override
    protected void attackOrMove(GameObject target) {
        if (target != null) {
            new Bullet(this).throwBullet(headTile, target.getHeadTile());
            state = GameObjectState.ATTACK;
            Angle angle = headTile.calculateAngle(target.getHeadTile());
            if (angle != null)
                setAngle(angle);
            target.reduceHP(damage);
            Console.getConsole().printTracingMessage(nameOfDroppable + " " + teamSide + " reduce hp " + target.getNameOfDroppable());
            try {
                Thread.sleep((int) (hitSpeed * 1000));
            } catch (InterruptedException ignored) {
            }
        } else {
            state = GameObjectState.IDLE;
        }
    }

    /**
     * Thread of object (applies algorithm of game object)
     */
    @Override
    public void run() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                attackOrMove(findTargetsInRange());
            }
        };
        service.execute(runnable);
    }

    protected GameObject findTargetsInRange() {
        ArrayList<GameObject> targets = new ArrayList<>();
        Thread targetRangeCheckerThread = (new Thread(() -> {
            if (lockedTarget == null ||
                    distance(headTile, lockedTarget.getHeadTile()) > range) {
                int x, y;       // beginning x,y of search area
                x = headTile.getX() - (int) Math.round(range);
                y = headTile.getY() - (int) Math.round(range);
                for (int j = 0; j <= (Math.round(range) * 2 + 1); j++) {
                    for (int i = 0; i <= (Math.round(range) * 2 + 1); i++) {
                        Tile searchTile = battleField.getPixel(x + i, y + j);
                        if (searchTile != null) {
                            if (battleField.calculateDistance(headTile, searchTile) <= Math.round(range)) {
                                GameObject target = null;
                                target = searchTile.getGameObject();
                                if (target != null && target.getHp() > 0 && target.getMyType() == target.getAttackTargetType() && target.getTeamSide() != teamSide) {
                                    targets.add(target);

                                }
                            }
                        }
                    }
                }
                for (GameObject gameObject : targets)
                    Console.getConsole().printTracingMessage("-" + gameObject.getNameOfDroppable().toString());
                if (targets.size() > 0) {
                    lockedTarget = targets.get(0);
                    double minDis = distance(headTile, targets.get(0).getHeadTile());
                    for (GameObject gameObject : targets)
                        if (distance(headTile, gameObject.getHeadTile()) < minDis) {
                            minDis = distance(headTile, gameObject.getHeadTile());
                            lockedTarget = gameObject;
                        }
                }
//                Console.getConsole().printTracingMessage("done");
            }
        }));
        targetRangeCheckerThread.setDaemon(true);
        targetRangeCheckerThread.start();
        try {
            targetRangeCheckerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return lockedTarget;

    }

    public double distance(Tile source, Tile dest) {
        if(dest != null && source != null) {
            Point2D src = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(source.getX()),
                    MouseTilePosition.TranslateTileToPixelY(source.getY()));
            Point2D dst = new Point2D.Double(MouseTilePosition.TranslateTileToPixelX(dest.getX()),
                    MouseTilePosition.TranslateTileToPixelY(dest.getY()));

            return Math.abs(src.distance(dst));
        }
        return 0;
    }

    /**
     * Updates hp with life time or vise versa
     */
    protected void updateLifeTime() {

    }

    public int getLifeTime() {
        return lifeTime;
    }
}
