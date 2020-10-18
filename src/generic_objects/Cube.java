package generic_objects;

import misc.HitPointInfo;
import misc.Operations;
import misc.Point;
import misc.Ray;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cube extends GenericObject {

    public Cube() {
        super();
    }

    public Cube(Color color) {
        super(color);
    }

    public Cube(double x, double y, double z,
                double scaleX, double scaleY, double scaleZ,
                double rotateX, double rotateY, double rotateZ,
                Color color) {
        super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, color);
    }

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {
        List<HitPointInfo> hitPointInfoList = new ArrayList<>();
        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );
        Square groundSquare = new Square(0, 0, -1, 1, 1, 0, 0, 0, this.color); // todo check normals (also with squares and planes) and other objects
        Square upperSquare = new Square(0, 0, 1, 1, 1, 0, 0, 0, this.color);
        Square backSquare = new Square(0, -1, 0, 1, 1, 90, 0, 0, this.color);
        Square frontSquare = new Square(0, 1, 0, 1, 1, 90, 0, 0, this.color);
        Square rightSquare = new Square(-1, 0, 0, 1, 1, 0, 90, 0, this.color);
        Square leftSquare = new Square(1, 0, 0, 1, 1, 0, 90, 0, this.color);
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
        return firstHit;
    }
}
