package render_related;

import configuration.Configuration;

import java.awt.*;

public class Pixel{

    private int xLocation;
    private int yLocation;
    private Color color;

    public Pixel(){
        this.xLocation = 0;
        this.yLocation = 0;
        this.color = Configuration.BACKGROUND_COLOR;
    }

    /**
     * This constructor will create a point with a certain x- and y location.
     * @param xLocation the x location of the render_related.Pixel.
     * @param yLocation the y location of the render_related.Pixel.
     * @param color the requested color of the pixel.
     */
    public Pixel(int xLocation, int yLocation, Color color){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.color = color;
    }

    /**
     * This constructor will create a point with a certain x- and y location.
     * @param xLocation the x location of the render_related.Pixel.
     * @param yLocation the y location of the render_related.Pixel.
     */
    public Pixel(int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.color = Configuration.BACKGROUND_COLOR;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
