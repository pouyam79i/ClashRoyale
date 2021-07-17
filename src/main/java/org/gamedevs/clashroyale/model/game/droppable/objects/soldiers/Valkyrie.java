package org.gamedevs.clashroyale.model.game.droppable.objects.soldiers;

import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.Building;
import org.gamedevs.clashroyale.model.game.player.Side;

import java.util.ArrayList;
import java.util.HashMap;

public class Valkyrie extends Soldier {


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
        ArrayList<Building> hitTowers = new ArrayList<>();

        if (target != null) {
            state = GameObjectState.ATTACK;
            areaSplashDamage(hitTowers);
            try {
                Thread.sleep((int) (hitSpeed * 1000));
            } catch (InterruptedException ignored) {
            }
        } else {
            state = GameObjectState.MOVING;
        }
    }

    /**
     * area splash for target which is hit by valkyrie
     *
     * @param hitTowers array which store towers which one hit by valkyrie in this turn of attack
     */
    protected void areaSplashDamage(ArrayList<Building> hitTowers) {
        float range = 1;
        Thread areaSplashDamageThread = (new Thread(() -> {
            int x, y;       // beginning x,y of search area

            x = getHeadPixel().getX() - (int) Math.round(range);
            y = getHeadPixel().getY() - (int) Math.round(range);
            for (int j = 0; j <= (Math.round(range) * 2 + 1); j++) {
                for (int i = 0; i <= (Math.round(range) * 2 + 1); i++) {
                    Tile searchTile = battleField.getPixel(x + i, y + j);
                    if (searchTile != null) {
                        if (battleField.calculateDistance(headTile, searchTile) <= Math.round(range)) {
                            GameObject subTarget = null;
                            // Ground soldiers
                            if (subTarget.getTeamSide() != teamSide) {
                                if (subTarget.getMyType() == TargetType.GROUND || subTarget.getMyType() == TargetType.BUILDING) {
                                    if (subTarget.getHp() > 0 &&
                                            (subTarget.getMyType() != TargetType.BUILDING || !hitTowers.contains(subTarget))) {
                                        subTarget.reduceHP(damage);
                                        if (subTarget.getMyType() == TargetType.BUILDING)
                                            hitTowers.add((Building) subTarget);

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
