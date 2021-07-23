package org.gamedevs.clashroyale.model.game.droppable.objects.buildings;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObjectState;
import org.gamedevs.clashroyale.model.game.droppable.objects.TargetType;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.awt.geom.Point2D;

/**
 * a class which handle inferno tower
 *
 * @author Pouya Mohammadi -Hosna Hoseini
 * 9826039 -CE@AUT     9823010 -CE@AUT
 * @version 1.0
 */
public class InfernoTower extends Building {

    private int maxDamage;


    public InfernoTower(int level, Side side) {
        super(side);
        hitSpeed = 0.1;
        attackTargetType = TargetType.AIR_GROUND;
        range = 6;
        lifeTime = 40;
        effectiveLifeTime = true;
        nameOfDroppable = CardName.INFERNO_TOWER;
        errorInGUIX = 0.15 * MainConfig.STD_BATTLE_FIELD_X_TILE_RATIO;
        errorInGUIY = 2.75 * MainConfig.STD_BATTLE_FIELD_Y_TILE_RATIO;
        switch (level) {
            case 1:
                hp.setValue(800);
                damage = 20;
                maxDamage = 400;
                break;
            case 2:
                hp.setValue(880);
                damage = 22;
                maxDamage = 440;
                break;
            case 3:
                hp.setValue(968);
                damage = 24;
                maxDamage = 484;
                break;
            case 4:
                hp.setValue(1064);
                damage = 26;
                maxDamage = 532;
                break;
            case 5:
                hp.setValue(1168);
                damage = 29;
                maxDamage = 584;
                break;
        }
    }

}