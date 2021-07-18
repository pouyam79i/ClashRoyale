package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.beans.property.SimpleDoubleProperty;
import org.gamedevs.clashroyale.MainConfig;

/**
 * This class contains (x,y) of mouse drop in game!
 * easily get(x,y) of drop in game by getting property of this call!
 * @author Pouya Mohammadi - CE@AUT 9829039
 * @version 1.0
 */
public class MouseTilePosition {

    /**
     * only instance of this class
     */
    private static MouseTilePosition mouseTilePosition;

    // This calibrates the (x,y) of beginning pixel
    private double caliberX;
    private double caliberY;
    // This is the pure x and y saved
    private final SimpleDoubleProperty x;
    private final SimpleDoubleProperty y;
    // This is the x and y with add caliber
    private final SimpleDoubleProperty xSelectedTile;
    private final SimpleDoubleProperty ySelectedTile;

    /**
     * Constructor of MouseTilePosition
     */
    private MouseTilePosition(){
        caliberX = 0;
        caliberY = 0;
        x = new SimpleDoubleProperty(0);
        y = new SimpleDoubleProperty(0);
        xSelectedTile = new SimpleDoubleProperty(0);
        ySelectedTile = new SimpleDoubleProperty(0);
    }

    /**
     * Translate x of tile to x of pixel (battle field anchor pane)
     * @param xOfTile X of tile
     * @return X of pixel
     */
    public static int TranslateTileToPixelX(double xOfTile){
        int xOfPixel = (int)(xOfTile * 20.7);
        return xOfPixel;
    }

    /**
     * Translate y of tile to y of pixel (battle field anchor pane)
     * @param yOfTile Y of tile
     * @return Y of pixel
     */
    public static int TranslateTileToPixelY(int yOfTile){
        int yOfPixel = (int) (yOfTile * 16.8);
        yOfPixel = MainConfig.STD_BATTLE_FIELD_HEIGHT - yOfPixel;
        return yOfPixel;
    }


    public static int TranslatePixelToTileX(double x){
        return (int) Math.floor(x /20.7);
    }


    public static int TranslatePixelToTileY(double y){
        return (int) Math.floor((MainConfig.STD_BATTLE_FIELD_HEIGHT - y)/16.8);
    }

    // Setters
    public void setX(double x){
        this.x.setValue(Math.floor(x));
        this.xSelectedTile.setValue((x * 20.7) + caliberX);
    }
    public void setY(double y){
        this.ySelectedTile.setValue((y * 16.8) + caliberY);
        y = MainConfig.STD_BATTLE_FIELD_Y_TILE - y;
        this.y.setValue(Math.floor(y) - 1);
    }
    public void setCaliberX(double caliberX) {
        this.caliberX = caliberX;
    }
    public void setCaliberY(double caliberY) {
        this.caliberY = caliberY;
    }

    // Getters
    public double getX() {
        return x.get();
    }
    public SimpleDoubleProperty xProperty() {
        return x;
    }
    public double getY() {
        return y.get();
    }
    public SimpleDoubleProperty yProperty() {
        return y;
    }
    public double getxSelectedTile() {
        return xSelectedTile.get();
    }
    public SimpleDoubleProperty xSelectedTileProperty() {
        return xSelectedTile;
    }
    public double getySelectedTile() {
        return ySelectedTile.get();
    }
    public SimpleDoubleProperty ySelectedTileProperty() {
        return ySelectedTile;
    }

    /**
     * @return only instance of this class
     */
    public static MouseTilePosition getMouseTilePosition() {
        if(mouseTilePosition == null)
            mouseTilePosition = new MouseTilePosition();
        return mouseTilePosition;
    }

}
