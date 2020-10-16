package render_related;

import base_classes.HitPointInfo;
import base_classes.Operations;
import base_classes.Ray;
import base_classes.Vector;
import configuration.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Renderer extends JPanel{

    private JFrame frame;
    private BufferedImage canvas;

    public Renderer() {
        canvas = new BufferedImage(Configuration.SCREEN_WIDTH, Configuration.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.frame = new JFrame("Render result");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(Configuration.SCREEN_WIDTH, Configuration.SCREEN_HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void renderFrame(World world) {
        HitPointInfo hitPointInfo = null;
        Operations operations = new Operations();
        int countHits = 0;
        int countMisses = 0;
        for (int r = 1; r <= Configuration.SCREEN_HEIGHT; r++) {
            for (int c = 1; c <= Configuration.SCREEN_WIDTH; c++) {
                Vector dirN = operations.scalarVectorProduct(-world.getCamera().getDistanceN(), world.getCamera().getN());
                double uCoefficient = world.getCamera().getWidth() * (2 * (double) c / (double) Configuration.SCREEN_WIDTH - 1);
                Vector dirU = operations.scalarVectorProduct(uCoefficient, world.getCamera().getU());
                double vCoefficient = world.getCamera().getHeight() * (2 * (double) r / (double) Configuration.SCREEN_HEIGHT - 1);
                Vector dirV = operations.scalarVectorProduct(vCoefficient, world.getCamera().getV());
                Vector dir = operations.vectorSum(operations.vectorSum(dirN, dirU), dirV);
                Ray ray = new Ray(world.getCamera().getEye(), dir);
                hitPointInfo = world.calculateBestHitpoint(ray);
                addPointToCanvas(c,transformYCoordinate(r) , createColorFromHitPointInfo(hitPointInfo));
            }
        }
        frame.add(this);
    }

    public void addPointToCanvas(int x, int y, Color color) {
        this.canvas.setRGB(x, y, color.getRGB());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(canvas, null, null);
    }

    public int transformYCoordinate(int y){
        return Configuration.SCREEN_HEIGHT-y+1;
    }

    public Color createColorFromHitPointInfo(HitPointInfo hitPointInfo) {
        Color color;
        if(hitPointInfo.isHit()){
            color = Color.red;
        }
        else
            color = Color.BLACK;
        return color;
    }

}
