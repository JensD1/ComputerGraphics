package generic_objects;

import configuration.Configuration;
import misc.HitPointInfo;
import misc.Operations;
import misc.Ray;
import misc.Point;

import java.awt.*;

public class Square extends GenericObject{

    public Square(){
        super();
    }

    public Square(Color color){
        super(color);
    }

    public Square(double x, double y, double z,
                 double scaleX, double scaleY,
                 double rotateX, double rotateY, double rotateZ,
                 Color color){
        super(x, y, z, scaleX, scaleY, 1, rotateX, rotateY, rotateZ, color);
    }

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {
        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );
        double hitTime = -inverseRay.getOrigin().getZ()/inverseRay.getDir().getZ();
        HitPointInfo hitPointInfo = new HitPointInfo();
        if(hitTime>0) {
            Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
            if (Math.abs(hitLocation.getX()) <= 1 && Math.abs(hitLocation.getY()) <= 1) {
                hitPointInfo.setHitTime(hitTime);
                hitPointInfo.setHit(true);
                hitPointInfo.setColor(this.color);
            }
        }
        return hitPointInfo;
    }
}
