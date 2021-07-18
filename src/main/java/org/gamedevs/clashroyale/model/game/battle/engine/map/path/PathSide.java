package org.gamedevs.clashroyale.model.game.battle.engine.map.path;

public enum PathSide {
    RIGHT(0),
    LEFT(50),
    MIDDLE(100);
    private int x;

    PathSide(int x){
        this.x = x;
    }

    public int getX() {
        return x;
    }
}
