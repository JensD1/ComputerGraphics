package base_classes;

public class Point {

    private double[] point;

    public Point(){
        this.point = new double[4];
        for(int i = 0; i<3; i++){
            this.point[i] = 0;
        }
        point[3] = 1;
    }

    public Point(double x, double y, double z){
        this.point = new double[4];
        this.point[0] = x;
        this.point[1] = y;
        this.point[2] = z;
        this.point[3] = 1;
    }

    public Point(double[] point){
        if(point.length != 4)
            throw new IllegalArgumentException("base_classes.Point is not of the correct size.");
        this.point = point;
    }

    public void setX(double value){
        this.point[0] = value;
    }

    public void setY(double value){
        this.point[1] = value;
    }

    public void setZ(double value){
        this.point[2] = value;
    }

    public double getX(){
        return this.point[0];
    }

    public double getY(){
        return this.point[1];
    }

    public double getZ(){
        return this.point[2];
    }

    public double getElement(int element){
        return this.point[element];
    }

    public void setElement(int element, double value){
        this.point[element] = value;
    }

    @Override
    public String toString() {
        String returnString = "";
        for(int i = 0; i<4; i++){
            returnString = returnString.concat("["+point[i] +"]\n");
        }
        return returnString;
    }
}
