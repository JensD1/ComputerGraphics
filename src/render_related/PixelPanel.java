package render_related;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PixelPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage bufferedImage;

    public PixelPanel(){

    }
    public PixelPanel(int width, int height){
        super(true);
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
    }

    public void drawPixel(Pixel pixel){
        bufferedImage.setRGB(pixel.getxLocation(), pixel.getyLocation(), pixel.getColor().getRGB());
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(bufferedImage, null, null);
    }
}
