package render_related;

import misc.*;
import configuration.Configuration;

public class Camera {

    private Point eye;
    private Vector u;
    private Vector v;
    private Vector n;

    private int width = Math.floorDiv(Configuration.SCREEN_WIDTH, 2);
    private int height = Math.floorDiv(Configuration.SCREEN_HEIGHT, 2);

    private double distanceN;

    public Camera(){
        this.eye = new Point();
        this.u = new Vector(0, 1, 0);
        this.v = new Vector(0, 0, 1);
        this.n = new Vector(1, 0, 0);
        this.width = Configuration.SCREEN_WIDTH/2;
        this.height = Configuration.SCREEN_HEIGHT/2;
        this.distanceN = 1;
    }

    public Camera(Point eye, Vector u, Vector v, Vector n, int width, int height, double distanceN){
        this.eye = eye;
        this.u = u.normalize();
        this.v = v.normalize();
        this.n = n.normalize();
        this.width = width;
        this.height = height;
        this.distanceN = distanceN;
    }

    public void setCameraLocation(Point position, Point look, Vector up){
        this.eye = position;
        this.n = Operations.pointSubstraction(position, look);
        this.u = Operations.vectorCrossProduct(up, this.n);
        if(this.u.norm() < Configuration.ROUNDING_ERROR){
            this.u = new Vector(0, 1, 0);
        }
        this.v = Operations.vectorCrossProduct(this.n, this.u);

        // normalize all vectors
        this.n = this.n.normalize();
        this.u = this.u.normalize();
        this.v = this.v.normalize();
    }

    public Point getEye() {
        return eye;
    }

    public void setEye(Point eye) {
        this.eye = eye;
    }

    public Vector getU() {
        return u;
    }

    public void setU(Vector u) {
        this.u = u.normalize();
    }

    public Vector getV() {
        return v;
    }

    public void setV(Vector v) {
        this.v = v.normalize();
    }

    public Vector getN() {
        return n;
    }

    public void setN(Vector n) {
        this.n = n.normalize();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getDistanceN() {
        return distanceN;
    }

    public void setDistanceN(double distanceN) {
        this.distanceN = distanceN;
    }
}
