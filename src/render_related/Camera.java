package render_related;

import misc.*;
import configuration.Configuration;

public class Camera {

    private Point eye;
    private Vector u;
    private Vector v;
    private Vector n;// todo Leid af uit v en u door vectorproduct.

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


    /**
     * This method will set the camera's location, diraction and roll.
     * @param position the position of the origin
     * @param theta the corner in degrees that the camera makes with the positive x-axis in the xy-plane
     * @param phi the corner in degrees that the camera makes with the positive z-axis.
     * @param roll the corner in degrees that the camera is tilted counterclockwise.
     */
    public void setCameraLocation(Point position, double theta, double phi, double roll){ // todo fix method
        this.eye = position;
        TransformationBuilder transformationBuilder = new TransformationBuilder();
        Vector arbitraryAxisVectorRoll = new Vector(1, 0, 0);
        Vector arbitraryAxisVectorTilt = new Vector(0, 1, 0);
        this.n = new Vector(1, 0, 0);
        this.u = new Vector(0, 1, 0);
        this.v = new Vector(0, 0, 1);
        Operations.vectorTransformation(transformationBuilder.rotateZ(theta).create(), arbitraryAxisVectorTilt);
        Operations.vectorTransformation(transformationBuilder.rotateArbitraryAxis(phi, arbitraryAxisVectorTilt).create(), arbitraryAxisVectorRoll);
        Matrix transformationMatrix = transformationBuilder.rotateArbitraryAxis(roll, arbitraryAxisVectorRoll).create();
        this.n = Operations.vectorTransformation(transformationMatrix, this.n).normalize();
        this.u = Operations.vectorTransformation(transformationMatrix, this.u).normalize();
        this.v = Operations.vectorTransformation(transformationMatrix, this.v).normalize();
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
