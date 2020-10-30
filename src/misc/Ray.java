package misc;

import configuration.Configuration;
import render_related.World;

public class Ray {

    private Point origin;
    private Vector dir;
    private int recurseLevel;

    public Ray(Point origin, Vector dir){
        this.origin = origin;
        this.dir = dir;
        this.recurseLevel = 0;
    }

    public Ray(Point origin, Vector dir, int recurseLevel){
        this.origin = origin;
        this.dir = dir;
        this.recurseLevel = recurseLevel;
    }

    public Ray(Point origin, Point destination){
        this.origin = origin;
        this.dir = Operations.pointSubstraction(destination, origin);
    }

    public Ray(Point origin, Point destination, int recurseLevel){
        this.origin = origin;
        this.dir = Operations.pointSubstraction(destination, origin);
        this.recurseLevel = recurseLevel;
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

    public static Ray createRay(World world, int c, int r){
        Vector dirN = Operations.scalarVectorProduct(-world.getCamera().getDistanceN(), world.getCamera().getN());
        double uCoefficient = world.getCamera().getWidth() * (2 * (double) c / (double) Configuration.SCREEN_WIDTH - 1);
        Vector dirU = Operations.scalarVectorProduct(uCoefficient, world.getCamera().getU());
        double vCoefficient = world.getCamera().getHeight() * (2 * (double) r / (double) Configuration.SCREEN_HEIGHT - 1);
        Vector dirV = Operations.scalarVectorProduct(vCoefficient, world.getCamera().getV());
        Vector dir = Operations.vectorSum(Operations.vectorSum(dirN, dirU), dirV);
        return new Ray(world.getCamera().getEye(), dir.normalize());
    }

    public int getRecurseLevel() {
        return recurseLevel;
    }

    public void setRecurseLevel(int recurseLevel) {
        this.recurseLevel = recurseLevel;
    }

    @Override
    public String toString() {
        return "Ray{\n" +
                "origin=\n" + origin +
                ", dir=\n" + dir +
                "}\n";
    }
}
