package base_classes;

public class Plane {
    public Point position;
    public Vector normal;

    public Plane(){
        this.position = new Point();
        this.normal = new Vector();
    }

    public Plane(Point position, Vector normal){
        this.position = position;
        this.normal = normal;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Vector getNormal() {
        return normal;
    }

    public void setNormal(Vector normal) {
        this.normal = normal;
    }
}
