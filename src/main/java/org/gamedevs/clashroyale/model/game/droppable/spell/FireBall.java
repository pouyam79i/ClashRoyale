package org.gamedevs.clashroyale.model.game.droppable.spell;

import org.gamedevs.clashroyale.model.cards.CardName;
import org.gamedevs.clashroyale.model.game.droppable.Bullet;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Side;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FireBall extends Spell{
    private int damage;
    public FireBall(int level, Side side){
        super(side);
        nameOfDroppable = CardName.FIREBALL;
        radius = 4;
        hitSpeed = 3;
        switch (level) {
            case 1:
                damage = 325;
                break;
            case 2:
                damage = 357;
                break;
            case 3:
                damage = 393;
                break;
            case 4:
                damage = 432;
                break;
            case 5:
                damage = 474;
                break;
        }
    }

    @Override
    protected void effect() {
        Point2D start;
        if (teamSide == Side.TOP)
            start = new Point2D.Double(187, 85);
        else
            start = new Point2D.Double(187, 504);

        new Bullet(this).throwBullet(start , headTile);

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

}
