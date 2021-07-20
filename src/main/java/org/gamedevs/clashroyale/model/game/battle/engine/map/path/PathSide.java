package org.gamedevs.clashroyale.model.game.battle.engine.map.path;

public enum PathSide {
    RIGHT(14),
    LEFT(3),
    MIDDLE(8);
    private int x;

    PathSide(int x){
        this.x = x;
    }

    public int getX() {
        return x;
    }
}
