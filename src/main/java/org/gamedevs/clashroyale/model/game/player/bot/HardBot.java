package org.gamedevs.clashroyale.model.game.player.bot;

import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.model.cards.Card;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Map;
import org.gamedevs.clashroyale.model.game.battle.engine.map.Tile;
import org.gamedevs.clashroyale.model.game.battle.engine.map.path.PathSide;
import org.gamedevs.clashroyale.model.game.battle.tools.CardGenerator;
import org.gamedevs.clashroyale.model.game.battle.tools.Elixir;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.player.Side;

import java.util.Random;

/**
 *
 */
public class HardBot extends Bot {

    /**
     * Gets main game tools to be able to play
     *
     * @param map           of game
     * @param playerSide    side of player (TOP/DOWN)
     * @param elixir        counter of elixir
     * @param cardGenerator player card generator
     * @param level         level of player
     */
    public HardBot(Map map, Side playerSide, Elixir elixir, CardGenerator cardGenerator, int level) {
        super(map, playerSide, elixir, cardGenerator, level);
    }

    /**
     * Applies the algorithm of bot! (Hard bot)
     * Decides what to do for next!
     */
    @Override
    protected void algorithm() {
        pickCard();
    }

    @Override
    protected void pickCard() {
        Thread pickCard = new Thread(){
            @Override
            public void start() {
                Card card;
                do {
                    card = gameDeck.getUnlockCards().getRandomCard();
                } while (card == null);
                int[] coordinate = findBestXY();
                if (drop(coordinate[0], coordinate[1], card))
                    removeCard(card);
            }
        };
        pickCard.start();
    }

    private PathSide findSideWithMoreEnemy() {
        int rightCnt = 0;
        int leftCnt = 0;
        for (GameObject gameObject : map.getDownSideAliveObj())
            if (gameObject.getHeadPixel().getX() < MainConfig.STD_BATTLE_FIELD_X_TILE)
                rightCnt++;
            else
                leftCnt++;

        if (rightCnt > leftCnt)
            return PathSide.RIGHT;
        else
            return PathSide.LEFT;
    }

    private PathSide findSideWithLessTowerHp() {
        PathSide side;
        if (leftPrincessTower.getHp() > 0 && rightPrincessTower.getHp() > 0) {
            if (leftPrincessTower.getHp() < rightPrincessTower.getHp())
                side = PathSide.LEFT;
            else
                side = PathSide.RIGHT;
        } else {
            if (leftPrincessTower.getHp() > 0)
                side = PathSide.LEFT;
            else if (rightPrincessTower.getHp() > 0)
                side = PathSide.RIGHT;
            else
                side = PathSide.MIDDLE;
        }
        return side;
    }

    private int[] findBestXY() {
        int[] coordinate = new int[2];
        PathSide towerHp = findSideWithLessTowerHp();
        PathSide enemy = findSideWithMoreEnemy();
        if (enemy == towerHp) {
            coordinate[0] = towerHp.getX();
        } else {

        }
        return coordinate;
    }
}
