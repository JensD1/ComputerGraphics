package misc;

import configuration.Configuration;
import render_related.World;

public class Ray {

    private Point origin;
    private Vector dir;
    private double t;

    public Ray(Point origin, Vector dir){
        this.origin = origin;
        this.dir = dir;
        this.t = 0;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Vector getDir() {
        return dir;
    }

    public void setDir(Vector dir) {
        this.dir = dir;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public static Ray createRay(World world, int c, int r){
        Vector dirN = Operations.scalarVectorProduct(-world.getCamera().getDistanceN(), world.getCamera().getN());
        double uCoefficient = world.getCamera().getWidth() * (2 * (double) c / (double) Configuration.SCREEN_WIDTH - 1);
        Vector dirU = Operations.scalarVectorProduct(uCoefficient, world.getCamera().getU());
        double vCoefficient = world.getCamera().getHeight() * (2 * (double) r / (double) Configuration.SCREEN_HEIGHT - 1);
        Vector dirV = Operations.scalarVectorProduct(vCoefficient, world.getCamera().getV());
        Vector dir = Operations.vectorSum(Operations.vectorSum(dirN, dirU), dirV);
        return new Ray(world.getCamera().getEye(), dir.normalize());
    }
}
