package org.gamedevs.clashroyale.model.game.battle.engine.map;

import javafx.application.Platform;
import org.gamedevs.clashroyale.MainConfig;
import org.gamedevs.clashroyale.controller.battle.main.MainBattleField;
import org.gamedevs.clashroyale.model.game.droppable.objects.GameObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.ImmortalObject;
import org.gamedevs.clashroyale.model.game.droppable.objects.buildings.MainTowers;
import org.gamedevs.clashroyale.model.game.droppable.spell.Spell;
import org.gamedevs.clashroyale.model.game.player.Side;
import org.gamedevs.clashroyale.model.updater.battle.ViewManager;
import org.gamedevs.clashroyale.model.utils.console.Console;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Model of map.
 * Builds a pixel graph for map!
 * Contains the drop algorithm.
 * Readable map!
 *
 * @author Pouya Mohammadi - CE@AUT - Uni ID:9829039
 * @version 1.0
 */
public class Map {

    /**
     * Max number of X
     */
    private final int width;
    /**
     * Max number of Y
     */
    private final int height;
    /**
     * Graph of tiles
     */
    private Tile[][] tiles;

    // Droppables:
    /**
     * Alive objects of top side player.
     */
    private ArrayList<GameObject> topSideAliveObj;
    /**
     * Alive objects of down side player.
     */
    private ArrayList<GameObject> downSideAliveObj;
    private ArrayList<GameObject> allAlive;

    /**
     * currnet frame
     */
    protected long currentFrame;
    /**
     * View manager of map.
     */
    private ViewManager viewManager;

    /**
     * Constructor of map.
     * Build a map with giving length and width.
     *
     * @param width  of map (max X)
     * @param height of map (max Y)
     */
    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        topSideAliveObj = new ArrayList<GameObject>();
        downSideAliveObj = new ArrayList<GameObject>();
        allAlive = new ArrayList<GameObject>();
        viewManager = null;
        buildMap();
        matchGraph();
        lock();
        if(width == MainConfig.STD_BATTLE_FIELD_X_TILE && height == MainConfig.STD_BATTLE_FIELD_Y_TILE)
            blockWithImmortals();
        currentFrame = 0;
    }

    /**
     * @param x          recommended x to drop game object
     * @param y          recommended y to drop game object
     * @param gameObject is my solder or building
     * @return true if it could drop game object in map!
     */
    public boolean dropGameObject(int x, int y, GameObject gameObject) {

        ArrayList<Tile> visitedTile;
        Tile calledTile = getPixel(x, y);
        if (calledTile == null) {
            Console.getConsole().printTracingMessage("called pixel (" + x + "," + y + ") does not exist!");
            return false;
        }
        if (calledTile.isEmpty(gameObject.getZ())) {
            calledTile.setGameObject(gameObject);
            gameObject.setBattleField(this);
            gameObject.setHeadPixel(calledTile);
            gameObject.setInitialFrame(currentFrame);
            Console.getConsole().printTracingMessage("set obj x,y to " + gameObject.getHeadPixel().getX() + ", " + gameObject.getHeadPixel().getY());
            if (viewManager != null) {
                gameObject.setViewUpdater(viewManager.addObjectToView(gameObject));
            }
            getOneSideObjects(gameObject.getTeamSide()).add(gameObject);
            allAlive.add(gameObject);
            return true;
        }
        int maxRadius = 1;      // TODO: define this in side configs
        for (int radius = 0; radius < maxRadius; radius++) {
            for (int degree = 0; degree < 360; ) {
                if (calledTile.peak(Angle.getAngle(degree), gameObject.getZ())) {
                    Tile detectedTile = calledTile.getSurroundingPixel(Angle.getAngle(degree));
                    gameObject.setBattleField(this);
                    detectedTile.setGameObject(gameObject);
                    gameObject.setHeadPixel(detectedTile);
                    gameObject.setInitialFrame(currentFrame);
                    if (viewManager != null) {
                        gameObject.setViewUpdater(viewManager.addObjectToView(gameObject));
                    }
                    getOneSideObjects(gameObject.getTeamSide()).add(gameObject);
                    allAlive.add(gameObject);
                    return true;
                }
                degree += Angle.STEP.getAngle();
            }
        }
        Console.getConsole().printTracingMessage("failed to drop object");
        return false;
    }

    /**
     * @param x     recommended x to drop game object
     * @param y     recommended y to drop game object
     * @param spell is my card
     * @return true if it could drop game object in map!
     */
    public boolean dropSpell(int x, int y, Spell spell) {
        Tile calledTile = getPixel(x, y);
        if (calledTile == null) {
            Console.getConsole().printTracingMessage("called pixel (" + x + "," + y + ") does not exist!");
            return false;
        }
        spell.setHeadPixel(calledTile);
        spell.setBattleField(this);
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                spell.run();
//
//            }
//        });
//        thread.start();
        spell.run();
        Console.getConsole().printTracingMessage("set obj x,y to " + spell.getHeadPixel().getX() + ", " + spell.getHeadPixel().getY());
        return true;
    }

    /**
     * drop main towers on its position!
     *
     * @param mainTower side
     * @param side      side of tower
     * @param kind      of tower (left princess is -1, king is 0, right princess is 1)
     */
    public void setMainTower(MainTowers mainTower, Side side, int kind) {
        if (side == Side.DOWN) {
            if (kind == -1) {
                mainTower.setHeadPixel(tiles[3][5]);
                for (int j = 4; j <= 6; j++) {
                    for (int i = 2; i <= 4; i++) {
                        tiles[i][j].setGameObject(mainTower);
                    }
                }
            } else if (kind == 0) {
                mainTower.setHeadPixel(tiles[8][2]);
                for (int j = 0; j <= 3; j++) {
                    for (int i = 7; i <= 10; i++) {
                        tiles[i][j].setGameObject(mainTower);
                    }
                }
            } else if (kind == 1) {
                mainTower.setHeadPixel(tiles[14][5]);
                for (int j = 4; j <= 6; j++) {
                    for (int i = 13; i <= 15; i++) {
                        tiles[i][j].setGameObject(mainTower);
                    }
                }
            }
        } else if (side == Side.TOP) {
            if (kind == -1) {
                mainTower.setHeadPixel(tiles[3][24]);
                for (int j = 23; j <= 25; j++) {
                    for (int i = 2; i <= 4; i++) {
                        tiles[i][j].setGameObject(mainTower);
                    }
                }
            } else if (kind == 0) {
                mainTower.setHeadPixel(tiles[8][27]);
                for (int j = 26; j <= 29; j++) {
                    for (int i = 7; i <= 10; i++) {
                        tiles[i][j].setGameObject(mainTower);
                    }
                }
            } else if (kind == 1) {
                mainTower.setHeadPixel(tiles[14][24]);
                for (int j = 23; j <= 25; j++) {
                    for (int i = 13; i <= 15; i++) {
                        tiles[i][j].setGameObject(mainTower);
                    }
                }
            }
        }
        // TODO: connect to view!
        getOneSideObjects(mainTower.getTeamSide()).add(mainTower);
        allAlive.add(mainTower);
        mainTower.setBattleField(this);
        mainTower.setInitialFrame(currentFrame);
    }

    /**
     * calculates the distance between two tiles
     *
     * @param src beginning tile
     * @param des ending tile
     * @return double value of distance
     */
    public double calculateDistance(Tile src, Tile des) {
        if (src == null || des == null)
            return -1;
        int x1 = src.getX();
        int y1 = src.getY();
        int x2 = des.getX();
        int y2 = des.getY();
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    /**
     * Getting a pixel with its tiles!
     * Use this to read tiles
     *
     * @param x of pixel
     * @param y of pixel
     * @return pixel of found (else null)
     */
    public Tile getPixel(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height)
            return null;
        return tiles[x][y];
    }

    /**
     * returns list of one side objet
     *
     * @param side of objects
     * @return array list of one side objet list
     */
    public ArrayList<GameObject> getOneSideObjects(Side side) {
        if (side == Side.TOP)
            return topSideAliveObj;
        else if (side == Side.DOWN)
            return downSideAliveObj;
        return null;
    }

    /**
     * updates all objects in one frame!
     */
    public void updateObjects(long currentFrame) {
        ArrayList<GameObject> gameObjectsToShow = new ArrayList<>();
        for(GameObject gameObject : allAlive)
            gameObjectsToShow.add(gameObject);
        this.currentFrame = currentFrame;
        try {
            Iterator<GameObject> gameObjectIterator = gameObjectsToShow.iterator();
            while (gameObjectIterator.hasNext()) {
                GameObject gameObject = gameObjectIterator.next();
                try {
                    gameObject.run();   // refresh one frame
                    if(gameObject.getViewUpdater() != null)
                        gameObject.getViewUpdater().update();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * removes dead objects
     */
    public void refreshAlive() {
        Iterator<GameObject> allAliveIterator = allAlive.iterator();
        while (allAliveIterator.hasNext()) {
            try {
                GameObject obj = allAliveIterator.next();
                if (obj.getHp() <= 0) {
                    allAliveIterator.remove();
                    obj.getHeadTile().removeObj(obj.getZ());
                    obj.setHeadPixel(null);
                    if (obj.getTeamSide() == Side.TOP) {
                        topSideAliveObj.remove(obj);
                    } else {
                        downSideAliveObj.remove(obj);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Initializes all pixel
     */
    private void buildMap() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Connects pixel graph
     */
    private void matchGraph() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                Tile currentTile = tiles[i][j];
                if ((i - 1) >= 0) {
                    currentTile.setSurroundingPixel(Angle.WEST, tiles[i - 1][j]);
                    tiles[i - 1][j].setSurroundingPixel(Angle.EAST, currentTile);
                }
                if ((j - 1) >= 0) {
                    currentTile.setSurroundingPixel(Angle.SOUTH, tiles[i][j - 1]);
                    tiles[i][j - 1].setSurroundingPixel(Angle.NORTH, currentTile);
                    if ((i - 1) >= 0) {
                        currentTile.setSurroundingPixel(Angle.SOUTH_WEST, tiles[i - 1][j - 1]);
                        tiles[i - 1][j - 1].setSurroundingPixel(Angle.NORTH_EAST, currentTile);
                    }
                    if ((i + 1) < width) {
                        currentTile.setSurroundingPixel(Angle.SOUTH_EAST, tiles[i + 1][j - 1]);
                        tiles[i + 1][j - 1].setSurroundingPixel(Angle.NORTH_WEST, currentTile);
                    }
                }
            }
        }
    }

    /**
     * Locks all tiles
     */
    private void lock() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                tiles[i][j].lock();
            }
        }
    }

    /**
     * put immortal object in block sites
     */
    private void blockWithImmortals(){
        // TODO: this is set for 18x30 map size
        for (int j = 14; j <= 15; j++) {
            for (int i = 0; i <= 17; i++) {
                if(i == 3 || i == 14)
                    continue;
                tiles[i][j].setGameObject(new ImmortalObject());
            }
        }
    }

    // Setters
    public void setViewManager(ViewManager viewManager) {
        this.viewManager = viewManager;
    }

    // Getters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<GameObject> getTopSideAliveObj() {
        return topSideAliveObj;
    }

    public ArrayList<GameObject> getDownSideAliveObj() {
        return downSideAliveObj;
    }

}
