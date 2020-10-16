package generic_objects;

import base_classes.HitPointInfo;
import base_classes.Operations;
import base_classes.Ray;
import configuration.Configuration;

public class Sphere implements GenericObject{

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {
        Operations operations = new Operations();
        HitPointInfo hitPointInfo = new HitPointInfo();
        double a = Math.abs(operations.dotProduct(ray.getDir(), ray.getDir()));
        double b = operations.dotProduct(ray.getOrigin(), ray.getDir());
        double c = operations.dotProduct(ray.getOrigin(), ray.getOrigin()) - 1;

        double discriminant = Math.pow(b, 2) - a * c;

        // if discriminate is negatice, the standard hitpointinfo will be returned, which is non-hit
        if(Math.abs(discriminant) < Configuration.ROUNDING_ERROR) { // if discriminant is 0
            hitPointInfo.setHitTime(-b/a);
            hitPointInfo.setHit(true);
        }
        else if(discriminant > 0){
            hitPointInfo.setHitTime(-(b/a) - (Math.sqrt(discriminant)/a));
            hitPointInfo.setHit(true);
        }
        return hitPointInfo;
    }
}
