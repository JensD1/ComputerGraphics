package render_related;

import misc.HitPointInfo;
import misc.Operations;
import misc.Ray;
import misc.Vector;
import configuration.Configuration;

import java.awt.*;

public class Renderer{

    public Renderer() {
    }

    public void renderFrame(World world) {
        PixelPlotter pixelPlotter = new PixelPlotter();
        HitPointInfo hitPointInfo;
        for (int c = 0; c < Configuration.SCREEN_WIDTH; c++) {
            for (int r = 0; r < Configuration.SCREEN_HEIGHT; r++) {
                Ray ray = Ray.createRay(world, c, r);
                hitPointInfo = world.calculateBestHitpoint(ray);
                pixelPlotter.addPixelToCanvas(
                        createPixel(c, r , hitPointInfo)
                );
            }
        }
        pixelPlotter.renderFrame();
    }

    public Pixel createPixel(int x, int y, HitPointInfo hitPointInfo) {
        Pixel pixel;
        if(hitPointInfo.isHit()){
            pixel = new Pixel(x, y, hitPointInfo.getColor());
        }
        else
            pixel = new Pixel(x, y, Color.BLACK);
        return pixel;
    }

}
