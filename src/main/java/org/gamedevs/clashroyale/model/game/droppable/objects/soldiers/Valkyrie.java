package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.Building;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Valkyrie extends Soldier {
    private HashMap<Tile, ArrayList<Building>> towers = new HashMap<>();

    public Valkyrie(int level, Side side) {
        super(side);
        hitSpeed = 1.5;
        attackTargetType = TargetType.GROUND;
        range = 3;//TODO
        areaSplash = true;

        switch (level) {
            case 1:
                hp = 880;
                damage = 120;
                break;
            case 2:
                hp = 968;
                damage = 132;
                break;
            case 3:
                hp = 1064;
                damage = 145;
                break;
            case 4:
                hp = 1170;
                damage = 159;
                break;
            case 5:
                hp = 1284;
                damage = 175;
                break;
        }

    }

    /**
     * Start attacking to the target (gives damage to target object)
     */
    @Override
    protected void attack(GameObject target) {
        if (!towers.containsKey(headTile))
            towers.put(headTile, new ArrayList<>());

        if (target != null) {
            state = GameObjectState.ATTACK;
            if (target.getMyType() != TargetType.BUILDING || !towers.get(headTile).contains(target)) {
                if (target.getMyType() == TargetType.BUILDING)
                    towers.get(headTile).add((Building) target);
                target.reduceHP(damage);
            }
            areaSplashDamage(target);
            try {
                Thread.sleep((int) (hitSpeed * 1000));
            } catch (InterruptedException ignored) {
            }
        } else {
            state = GameObjectState.MOVING;
        }
    }

    /**
     * run area splash for each target which is directly hit by valkyrie
     * @param target target which is directly hit by valkyrie
     */
    protected void areaSplashDamage(GameObject target) {
        float range = 1;
        Thread areaSplashDamageThread = (new Thread(() -> {
            int x, y;       // beginning x,y of search area

            x = target.getHeadPixel().getX() - (int) Math.round(range);
            y = target.getHeadPixel().getY() - (int) Math.round(range);
            for (int j = 0; j <= (Math.round(range) * 2 + 1); j++) {
                for (int i = 0; i <= (Math.round(range) * 2 + 1); i++) {
                    Tile searchTile = battleField.getPixel(x + i, y + j);
                    if (searchTile != null) {
                        if (battleField.calculateDistance(target.getHeadPixel(), searchTile) <= Math.round(range)) {
                            GameObject subTarget = null;
                            // Ground soldiers
                            if (subTarget.getTeamSide() != teamSide) {
                                if (subTarget.getMyType() == TargetType.GROUND || subTarget.getMyType() == TargetType.BUILDING) {
                                    if (subTarget.getHp() > 0 &&
                                            (subTarget.getMyType() != TargetType.BUILDING || !towers.get(headTile).contains(subTarget))) {
                                        subTarget.reduceHP(damage);
                                        if (subTarget.getMyType() == TargetType.BUILDING)
                                            towers.get(headTile).add((Building) subTarget);

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }));
        areaSplashDamageThread.setDaemon(true);
        areaSplashDamageThread.start();
    }
}
