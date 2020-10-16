package base_classes;

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
}
