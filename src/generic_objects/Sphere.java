package generic_objects;

import misc.*;
import configuration.Configuration;

import java.awt.*;

public class Sphere extends GenericObject{

    public Sphere(){
        super();
    }

    public Sphere(Color color){
        super(color);
    }

    public Sphere(double radius, double x, double y, double z, Color color){
        super(x, y, z, radius, radius, radius, 0, 0, 0, color);
    }

    public Sphere(Matrix transformation, Matrix inverseTransformation, Color color){
        super(color);
        this.transformation = transformation;
        this.inverseTransformation = inverseTransformation;
    }

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {

        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );

        HitPointInfo hitPointInfo = new HitPointInfo();
        double a = Operations.dotProduct(inverseRay.getDir(), inverseRay.getDir());
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
//        if(hitPointInfo.isHit()){
//            // calculate x, y and z coordinates
//            System.out.println("Hitpoint coordinates x, y, z:");
//            System.out.println();
//        }
//        else{
//            System.out.println("No hit.");
//        }
        return hitPointInfo;
    }
}
