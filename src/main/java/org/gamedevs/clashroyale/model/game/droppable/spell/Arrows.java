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
    private int level;
    private Side side;
    public Arrows(int level, Side side) {
        super(side);
        nameOfDroppable = CardName.ARROWS;
        radius = 4;
        hitSpeed = 1;
        this.level = level;
        this.side = side;
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
//        attackOrMove();
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
        if (teamSide == Side.TOP) {
            start = new Point2D.Double(187, 10);
            Console.getConsole().printTracingMessage("up " + start.getY());
        }
        else {
            start = new Point2D.Double(187, 504);
            Console.getConsole().printTracingMessage("down"  + start.getY());
        }

        for (int i = (int) (headTile.getX() - 2); i <= headTile.getX() + 2; i++)
            for (int j = (int) (headTile.getY() - 2); j <= headTile.getY() + 2; j++)
                if (i >= 0 && i < MainConfig.STD_BATTLE_FIELD_X_TILE && j >= 0 && j < MainConfig.STD_BATTLE_FIELD_Y_TILE) {
                    tilesInRange.add(new Tile(i, j));
                }

        for (int i = 0; i < 20; i++) {
            Tile target = tilesInRange.get(random.nextInt(tilesInRange.size() - 1));
//            Console.getConsole().printTracingMessage("target: " + target.getX() + ", " + target.getY());
            Thread thread = new Thread(){
                @Override
                public void start() {
                    new Bullet(new Arrows(level,side)).throwBullet(start, target);
                }
            };
            thread.setDaemon(true);
            thread.start();
        }
    }

}
