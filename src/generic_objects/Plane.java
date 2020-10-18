package generic_objects;

import misc.*;

import java.awt.*;

public class Plane extends GenericObject{

    public Plane(){
        super();
    }

    public Plane(Color color){
        super(color);
    }

    public Plane(double x, double y, double z,
                 double rotateX, double rotateY, double rotateZ,
                 Color color){
        super(x, y, z, 1, 1, 1, rotateX, rotateY, rotateZ, color);
    }

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {
        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );
        HitPointInfo hitPointInfo;
        double hitTime = -inverseRay.getOrigin().getZ()/inverseRay.getDir().getZ();
        if(hitTime > 0){
            hitPointInfo = new HitPointInfo(hitTime, true, this.color);
        }
        else {
            hitPointInfo = new HitPointInfo();
        }
        return hitPointInfo;
    }
}
