package misc;

public class Vector {

    private double[] vector;

    public Vector(){
        this.vector = new double[4];
        for(int i = 0; i<4; i++){
            this.vector[i] = 0;
        }
    }

    public Vector(double x, double y, double z){
        this.vector = new double[4];
        this.vector[0] = x;
        this.vector[1] = y;
        this.vector[2] = z;
        this.vector[3] = 0;
    }

    public Vector(double[] vector){
        if(vector.length != 4)
            throw new IllegalArgumentException("base_classes.Vector is not of the correct size.");
        this.vector = vector;
    }

    public double norm(){
        return Math.sqrt(vector[0] * vector[0]+ vector[1] * vector[1]+ vector[2] * vector[2]);
    }

    public Vector normalize(){
        double norm = norm();
        return new Vector(vector[0]/norm, vector[1]/norm, vector[2]/norm);
    }

    public void setX(double value){
        this.vector[0] = value;
    }

    public void setY(double value){
        this.vector[1] = value;
    }

    public void setZ(double value){
        this.vector[2] = value;
    }

    public double getX(){
        return this.vector[0];
    }

    public double getY(){
        return this.vector[1];
    }

    public double getZ(){
        return this.vector[2];
    }

    public double getElement(int element){
        return this.vector[element];
    }

    public void setElement(int element, double value){
        this.vector[element] = value;
    }

    @Override
    public String toString() {
        String returnString = "";
        for(int i = 0; i<4; i++){
            returnString = returnString.concat("["+vector[i] +"]\n");
        }
        return returnString;
    }
}
