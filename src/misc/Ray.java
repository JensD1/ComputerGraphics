package misc;

import configuration.Configuration;
import generic_objects.GenericObject;
import render_related.World;

public class Ray {

    private Point origin;
    private Vector dir;
    private int recurseLevel;
    private GenericObject insideObject;

    public Ray(Point origin, Vector dir){
        this.origin = origin;
        this.dir = dir;
        this.recurseLevel = 0;
        this.insideObject = null;
    }

    public Ray(Point origin, Vector dir, int recurseLevel){
        this.origin = origin;
        this.dir = dir;
        this.recurseLevel = recurseLevel;
        this.insideObject = null;
    }

    public Ray(Point origin, Vector dir, int recurseLevel, GenericObject insideObject){
        this.origin = origin;
        this.dir = dir;
        this.recurseLevel = recurseLevel;
        this.insideObject = insideObject;
    }

    public Ray(Point origin, Point destination){
        this.origin = origin;
        this.dir = Operations.pointSubstraction(destination, origin);
        this.recurseLevel = 0;
        this.insideObject = null;
    }

    public Ray(Point origin, Point destination, int recurseLevel){
        this.origin = origin;
        this.dir = Operations.pointSubstraction(destination, origin);
        this.recurseLevel = recurseLevel;
        this.insideObject = null;
    }

    public Ray(Point origin, Point destination, int recurseLevel, GenericObject insideObject){
        this.origin = origin;
        this.dir = Operations.pointSubstraction(destination, origin);
        this.recurseLevel = recurseLevel;
        this.insideObject = insideObject;
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

    public GenericObject getInsideObject() {
        return insideObject;
    }

    public void setInsideObject(GenericObject insideObject) {
        this.insideObject = insideObject;
    }

    @Override
    public String toString() {
        return "Ray{\n" +
                "origin=\n" + origin +
                ", dir=\n" + dir +
                "}\n";
    }
}
