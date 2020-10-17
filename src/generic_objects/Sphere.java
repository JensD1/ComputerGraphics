package generic_objects;

import misc.HitPointInfo;
import misc.Matrix;
import misc.Operations;
import misc.Ray;
import configuration.Configuration;

import java.awt.*;

public class Sphere extends GenericObject{

    Matrix transformation;
    Matrix inverseTransformation;

    public Sphere(){
        super();
        this.transformation = Matrix.createUnitMatrix();
        this.inverseTransformation = Matrix.createUnitMatrix();
    }

    public Sphere(Color color){
        super(color);
        this.transformation = Matrix.createUnitMatrix();
        this.inverseTransformation = Matrix.createUnitMatrix();
    }


    public Sphere(Matrix transformation, Matrix inverseTransformation, Color color){
        super(color);
        this.transformation = transformation;
        this.inverseTransformation = inverseTransformation;
    }

    public Matrix getTransformation() {
        return transformation;
    }

    public void setTransformation(Matrix transformation) {
        this.transformation = transformation;
    }

    public Matrix getInverseTransformation() {
        return inverseTransformation;
    }

    public void setInverseTransformation(Matrix inverseTransformation) {
        this.inverseTransformation = inverseTransformation;
    }

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {

        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );

        HitPointInfo hitPointInfo = new HitPointInfo();
        double a = Math.abs(Operations.dotProduct(inverseRay.getDir(), inverseRay.getDir()));
        double b = Operations.dotProduct(inverseRay.getOrigin(), inverseRay.getDir());
        double c = Operations.dotProduct(inverseRay.getOrigin(), inverseRay.getOrigin()) - 1;

        double discriminant = Math.pow(b, 2) - a * c;

        // if discriminate is negative, the standard hitPointInfo will be returned, which is non-hit
        if(Math.abs(discriminant) < Configuration.ROUNDING_ERROR) { // if discriminant is 0
            hitPointInfo.setHitTime(-b/a);
            hitPointInfo.setHit(true);
            hitPointInfo.setColor(this.color);
        }
        else if(discriminant > 0){
            hitPointInfo.setHitTime(-(b/a) - (Math.sqrt(discriminant)/a));
            hitPointInfo.setHit(true);
            hitPointInfo.setColor(this.color);
        }
        return hitPointInfo;
    }
}
