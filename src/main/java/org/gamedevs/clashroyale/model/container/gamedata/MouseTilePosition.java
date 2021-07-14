package org.gamedevs.clashroyale.model.container.gamedata;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * This class contains (x,y) of mouse drop in game!
 * easily get(x,y) of droup in game by getting property of this call!
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

    // Setters
    public void setX(double x){
        this.x.setValue(x);
        this.xSelectedTile.setValue(x + caliberX);
    }
    public void setY(double y){
        this.y.setValue(y);
        this.ySelectedTile.setValue(y + caliberY);
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
