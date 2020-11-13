package generic_objects;

import configuration.Configuration;
import misc.*;
import misc.Point;
import render_related.Material;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TaperedCylinder extends GenericObject{

    private double s;
    Plane groundPlane;
    Plane upperPlane; // todo kijk overal na of er this. staat

    public TaperedCylinder(){
        super();
        this.s = 1;
        this.groundPlane = new Plane(0, 0, 0, 180, 0, 0, this.material);
        this.upperPlane = new Plane(0, 0, 1, 0, 0, 0, this.material);
    }

    public TaperedCylinder(double s, Material material){
        super(material);
        this.s = s;
        this.groundPlane = new Plane(0, 0, 0, 180, 0, 0, this.material);
        this.upperPlane = new Plane(0, 0, 1, 0, 0, 0, this.material);
    }

    public TaperedCylinder(double s,double x, double y, double z, double scaleX,
                           double scaleY, double scaleZ, double rotateX, double rotateY,
                           double rotateZ, Material material){
        super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ,
                material);
        this.s = s;
        this.groundPlane = new Plane(0, 0, 0, 180, 0, 0, this.material);
        this.upperPlane = new Plane(0, 0, 1, 0, 0, 0, this.material);
    }

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {
        List<HitPointInfo> hitPointInfoList = new ArrayList<>();
        hitPointInfoList.add(new HitPointInfo());
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
            if(hitTime > Configuration.ROUNDING_ERROR) {
                Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
                if (hitLocation.getZ() >= 0 && hitLocation.getZ() <= 1) {
                    hitPointInfoList.add(
                            new HitPointInfo(
                                    this,
                                    Operations.pointTransformation(this.transformation, hitLocation),
                                    hitTime,
                                    Operations.vectorTransformation(
                                            this.transformation,
                                            new Vector(
                                                    hitLocation.getX(),
                                                    hitLocation.getY(),
                                                    -(this.s - 1) * (1 + (this.s - 1) * hitLocation.getZ())
                                            )
                                    )
                            )
                    );
                }
            }
        }
        else if(discriminant > 0){
            double hitTime = -(b/a) - (Math.sqrt(discriminant)/a); // first hit
            if(hitTime > Configuration.ROUNDING_ERROR) {
                Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
                if (hitLocation.getZ() >= 0 && hitLocation.getZ() <= 1) {
                    hitPointInfoList.add(
                            new HitPointInfo(
                                    this,
                                    Operations.pointTransformation(this.transformation, hitLocation),
                                    hitTime,
                                    Operations.vectorTransformation(
                                            this.transformation,
                                            new Vector(
                                                    hitLocation.getX(),
                                                    hitLocation.getY(),
                                                    -(this.s - 1) * (1 + (this.s - 1) * hitLocation.getZ())
                                            )
                                    )
                            )
                    );
                }
            }

            hitTime = -(b/a) + (Math.sqrt(discriminant)/a);
            if(hitTime > Configuration.ROUNDING_ERROR) {
                Point hitLocation2 = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
                if (hitLocation2.getZ() >= 0 && hitLocation2.getZ() <= 1) {
                    hitPointInfoList.add(
                            new HitPointInfo(
                                    this,
                                    Operations.pointTransformation(this.transformation, hitLocation2),
                                    hitTime,
                                    Operations.vectorTransformation(
                                            this.transformation,
                                            new Vector(
                                                    hitLocation2.getX(),
                                                    hitLocation2.getY(),
                                                    -(this.s - 1) * (1 + (this.s - 1) * hitLocation2.getZ())
                                            )
                                    )
                            )
                    );
                }
            }
        }

        // test groundplane
        HitPointInfo hitPointInfo1 = groundPlane.calculateHitPoint(inverseRay);
        Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitPointInfo1.getHitTime(), inverseRay.getDir()));
        if( ((Math.pow(hitLocation.getX(), 2) + Math.pow(hitLocation.getY(), 2)) <= (1 + Configuration.ROUNDING_ERROR)) && hitPointInfo1.isHit()){
            hitPointInfo1.setNormal(Operations.vectorTransformation(this.transformation, hitPointInfo1.getNormal()));
            hitPointInfo1.setHitPoint(Operations.pointTransformation(this.transformation, hitPointInfo1.getHitPoint()));
            hitPointInfoList.add(hitPointInfo1);
        }

        // test Upper plane
        HitPointInfo hitPointInfo2 = upperPlane.calculateHitPoint(inverseRay);
        hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitPointInfo2.getHitTime(), inverseRay.getDir()));
        if( ((Math.pow(hitLocation.getX(), 2) + Math.pow(hitLocation.getY(), 2)) <= (Math.pow(this.s, 2) + Configuration.ROUNDING_ERROR)) && hitPointInfo2.isHit()){
            hitPointInfo2.setNormal(Operations.vectorTransformation(this.transformation, hitPointInfo2.getNormal()));
            hitPointInfo2.setHitPoint(Operations.pointTransformation(this.transformation, hitPointInfo2.getHitPoint()));
            hitPointInfoList.add(hitPointInfo2);
        }

        // See which hitPoint is the best.
        HitPointInfo firstHit = new HitPointInfo();
        if(!hitPointInfoList.isEmpty()){
            for(HitPointInfo hitPointInfo: hitPointInfoList){
                if((!firstHit.isHit() && hitPointInfo.isHit()) || (hitPointInfo.getHitTime() < firstHit.getHitTime() && hitPointInfo.isHit()))
                    firstHit = hitPointInfo;
            }
        }
        firstHit.setObject(this);
        return firstHit;
    }
}
