package org.gamedevs.clashroyale.model.game.droppable.spell;

import javafx.scene.image.Image;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.container.gamedata.GameImageContainer;
import org.gamedevs.clashroyale.model.container.gamedata.MouseTilePosition;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Arrows extends Spell {

    private int damage;
    public Arrows(int level, Side side) {
        super(side);
        nameOfDroppable = CardName.ARROWS;
        radius = 4;
        hitSpeed = 2;
        switch (level) {
            case 1:
                damage = 144;
                break;
            case 2:
                damage = 156;
                break;
            case 3:
                damage = 174;
                break;
            case 4:
                damage = 189;
                break;
            case 5:
                damage = 210;
                break;
        }
    }

    @Override
    protected void effect() {
        throwArrowsEffect();
        attack();
    }

    /**
     * reduce hp of enemies who are in range of this spell
     */
    private void attack() {
        ArrayList<GameObject> targets = findTargetsInRange();
        for(GameObject gameObject : targets)
            if(gameObject.getTeamSide() != teamSide)
            gameObject.reduceHP(damage);
    }

    /**
     * throw arrow and show them in gui
     */
    private void throwArrowsEffect() {
        ArrayList<Tile> tilesInRange = new ArrayList<>();
        Random random = new Random();
        Point2D start;
        if (teamSide == Side.TOP)
            start = new Point2D.Double(187, 85);
        else
            start = new Point2D.Double(187, 504);

        for (int i = (int) (headTile.getX() - 2); i <= headTile.getX() + 2; i++)
            for (int j = (int) (headTile.getX() - 2); j <= headTile.getX() + 2; j++)
                if (i >= 0 && i < MainConfig.STD_BATTLE_FIELD_X_TILE && j >= 0 && j < MainConfig.STD_BATTLE_FIELD_Y_TILE) {
                    tilesInRange.add(new Tile(i, j));
                    Console.getConsole().printTracingMessage(i + ", " + j);
                }

        for (int i = 0; i < 15; i++) {
            Tile target = tilesInRange.get(random.nextInt(tilesInRange.size() - 1));
            Console.getConsole().printTracingMessage("target: " + target.getX() + ", " + target.getY());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Bullet(this).throwBullet(start, target);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            }
        }
    }

}
