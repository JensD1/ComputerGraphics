package generic_objects;

import misc.*;
import misc.Point;
import render_related.Material;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cube extends GenericObject {

    public Cube() {
        super();
    }

    public Cube(Material material) {
        super(material);
    }

    public Cube(double x, double y, double z,
                double scaleX, double scaleY, double scaleZ,
                double rotateX, double rotateY, double rotateZ,
                Material material) {
        super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, material);
    }

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {
        List<HitPointInfo> hitPointInfoList = new ArrayList<>();
        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );
        Square groundSquare = new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material);
        Square upperSquare = new Square(0, 0, 1, 1, 1, 0, 0, 0, this.material);
        Square backSquare = new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material);
        Square frontSquare = new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material);
        Square rightSquare = new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material);
        Square leftSquare = new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material);
        hitPointInfoList.add(groundSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(upperSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(backSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(frontSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(rightSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(leftSquare.calculateHitPoint(inverseRay));

        HitPointInfo firstHit = new HitPointInfo();
        for (HitPointInfo hitPointInfo : hitPointInfoList) {
            if ((!firstHit.isHit() && hitPointInfo.isHit()) || (hitPointInfo.getHitTime() < firstHit.getHitTime() && hitPointInfo.isHit())) {
                firstHit = hitPointInfo;
            }
        }
        if(firstHit.isHit()){ // Make sure that the transformation of the cube is taken into account.
            firstHit.setHitPoint(Operations.pointTransformation(this.transformation, firstHit.getHitPoint()));
            firstHit.setNormal(Operations.vectorTransformation(this.transformation, firstHit.getNormal()));
            firstHit.setObject(this);
        }
        return firstHit;
    }
}
