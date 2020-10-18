package generic_objects;

import configuration.Configuration;
import misc.HitPointInfo;
import misc.Operations;
import misc.Point;
import misc.Ray;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaperedCylinder extends GenericObject{

    private double s;

    public TaperedCylinder(){
        super();
        this.s = 1;
    }

    public TaperedCylinder(double s, Color color){
        super(color);
        this.s = s;
    }

    public TaperedCylinder(double s,double x, double y, double z,
                  double scaleX, double scaleY, double scaleZ,
                  double rotateX, double rotateY, double rotateZ,
                  Color color){
        super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, color);
        this.s = s;
    }

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {
        List<HitPointInfo> hitPointInfoList = new ArrayList<>();
        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );

        // test the mantle
        double d = (this.s - 1) * inverseRay.getDir().getZ();
        double f = 1 + (this.s - 1) * inverseRay.getOrigin().getZ();
        double a = Math.pow(inverseRay.getDir().getX(), 2) + Math.pow(inverseRay.getDir().getY(), 2) - Math.pow(d, 2);
        double b = inverseRay.getOrigin().getX() * inverseRay.getDir().getX() + inverseRay.getOrigin().getY() * inverseRay.getDir().getY() - f * d;
        double c = Math.pow(inverseRay.getOrigin().getX(), 2) + Math.pow(inverseRay.getOrigin().getY(), 2) - Math.pow(f, 2);

        double discriminant = Math.pow(b, 2) - a * c;

        // if discriminate is negative, the standard hitPointInfo will be returned, which is non-hit
        if(Math.abs(discriminant) < Configuration.ROUNDING_ERROR) { // if discriminant is 0
            double hitTime = -b/a;
            Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
            if(hitLocation.getZ() >= 0 && hitLocation.getZ() <= 1){
                hitPointInfoList.add(new HitPointInfo(hitTime, true, this.color));
            }
        }
        else if(discriminant > 0){
            double hitTime = -(b/a) - (Math.sqrt(discriminant)/a); // first hit
            Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
            if(hitLocation.getZ() >= 0 && hitLocation.getZ() <= 1){
                hitPointInfoList.add(new HitPointInfo(hitTime, true, this.color));
            }

            hitTime = -(b/a) + (Math.sqrt(discriminant)/a);
            Point hitLocation2 = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
            if(hitLocation2.getZ() >= 0 && hitLocation2.getZ() <= 1){
                hitPointInfoList.add(new HitPointInfo(hitTime, true, this.color));
            }
        }

        // test groundplane
        Plane groundPlane = new Plane(this.color);
        HitPointInfo hitPointInfo1 = groundPlane.calculateHitPoint(inverseRay);
        Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitPointInfo1.getHitTime(), inverseRay.getDir()));
        if( Math.pow(hitLocation.getX(), 2) + Math.pow(hitLocation.getY(), 2) <= 1){
            hitPointInfoList.add(hitPointInfo1);
        }

        // test Upper plane
        Plane upperPlane = new Plane(0, 0, 1, 0, 0, 0, this.color);
        HitPointInfo hitPointInfo2 = upperPlane.calculateHitPoint(inverseRay);
        hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitPointInfo2.getHitTime(), inverseRay.getDir()));
        if( Math.pow(hitLocation.getX(), 2) + Math.pow(hitLocation.getY(), 2) <= Math.pow(this.s, 2)){
            hitPointInfoList.add(hitPointInfo2);
        }

        HitPointInfo firstHit = new HitPointInfo();
        if(!hitPointInfoList.isEmpty()){
            for(HitPointInfo hitPointInfo: hitPointInfoList){
                if((!firstHit.isHit() && hitPointInfo.isHit()) || (hitPointInfo.getHitTime() < firstHit.getHitTime() && hitPointInfo.isHit()))
                    firstHit = hitPointInfo;
            }
        }

        return firstHit;
    }
}
