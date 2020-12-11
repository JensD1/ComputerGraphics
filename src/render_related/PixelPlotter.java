package render_related;

import configuration.Configuration;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class PixelPlotter {

    private PixelPanel canvas;
    JFrame frame;

    public PixelPlotter(){
        canvas = new PixelPanel(Configuration.SCREEN_WIDTH, Configuration.SCREEN_HEIGHT);
        this.frame = new JFrame("Render result");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(Configuration.SCREEN_WIDTH, Configuration.SCREEN_HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.add(canvas);
        setVisible(true);
    }

    public void setVisible(boolean visible){
        this.frame.setVisible(visible);
    }

    public void addPixelToCanvas(Pixel pixel){
        canvas.drawPixel(pixel);
    }

    public void renderFrame(){
        canvas.repaint();
    }

}
