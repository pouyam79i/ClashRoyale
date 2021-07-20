package org.gamedevs.clashroyale.model.game.droppable.spell;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.DropType;
import org.gamedevs.clashroyale.model.game.droppable.Droppable;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;
/**
 * a class which handle spells
 *
 * @author Pouya Mohammadi -Hosna Hoseini
 * 9826039 -CE@AUT     9823010 -CE@AUT
 * @version 1.0
 */
public abstract class Spell extends Droppable {

    protected double radius;

    protected Spell(Side side) {
        super(DropType.SPELL, side);
    }

    @Override
    public void run() {
        effect();
    }

    protected abstract void effect();

    protected ArrayList<GameObject> findTargetsInRange() {
        ArrayList<GameObject> targets = new ArrayList<>();
        Thread targetRangeCheckerThread = (new Thread(() -> {
            int x, y;       // beginning x,y of search area
            x = headTile.getX() - (int) Math.round(radius);
            y = headTile.getY() - (int) Math.round(radius);
            for (int j = 0; j <= (Math.round(radius) * 2 + 1); j++) {
                for (int i = 0; i <= (Math.round(radius) * 2 + 1); i++) {
                    Tile searchTile = battleField.getPixel(x + i, y + j);
                    if (searchTile != null) {
                        if (battleField.calculateDistance(headTile, searchTile) <= Math.round(radius)) {
                            GameObject target = null;
                            target = searchTile.getGameObject();
                            if (target != null && target.getHp() > 0) {
                                targets.add(target);
                            }
                        }
                    }
                }
            }
        }));
        targetRangeCheckerThread.setDaemon(true);
        targetRangeCheckerThread.start();
        try {
            targetRangeCheckerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return targets;
    }


}
