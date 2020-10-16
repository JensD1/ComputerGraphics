package render_related;

import base_classes.HitPointInfo;
import configuration.Configuration;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Pixel extends JPanel {

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

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(this.color);

        Dimension size = getSize();
        int w = size.width ;
        int h = size.height;

        int x = xLocation;
        int y = yLocation;
        g2d.drawLine(x, y, x, y);
    }

}
