package generic_objects;

import misc.*;
import misc.Point;
import render_related.Material;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cube extends GenericObject {
    private Square groundSquare;
    private Square upperSquare;
    private Square backSquare;
    private Square frontSquare;
    private Square rightSquare;
    private Square leftSquare;

    public Cube() {
        super();
        this.groundSquare = new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material);
        this.upperSquare = new Square(0, 0, 1, 1, 1, 0, 0, 0, this.material);
        this.backSquare = new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material);
        this.frontSquare = new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material);
        this.rightSquare = new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material);
        this.leftSquare = new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material);
    }

    public Cube(Material material) {
        super(material);
        this.groundSquare = new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material);
        this.upperSquare = new Square(0, 0, 1, 1, 1, 0, 0, 0, this.material);
        this.backSquare = new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material);
        this.frontSquare = new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material);
        this.rightSquare = new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material);
        this.leftSquare = new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material);
    }

    public Cube(double x, double y, double z,
                double scaleX, double scaleY, double scaleZ,
                double rotateX, double rotateY, double rotateZ,
                Material material) {
        super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, material);
        this.groundSquare = new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material);
        this.upperSquare = new Square(0, 0, 1, 1, 1, 0, 0, 0, this.material);
        this.backSquare = new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material);
        this.frontSquare = new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material);
        this.rightSquare = new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material);
        this.leftSquare = new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material);
    }
    // todo make sure that everywhere the materials of the subelements (e.g. squares) are adjusted when the material of e.g. the cube is adjusted

    @Override
    public HitPointInfo calculateHitPoint(Ray ray) {
        List<HitPointInfo> hitPointInfoList = new ArrayList<>();
        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );

        hitPointInfoList.add(this.groundSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(this.upperSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(this.backSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(this.frontSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(this.rightSquare.calculateHitPoint(inverseRay));
        hitPointInfoList.add(this.leftSquare.calculateHitPoint(inverseRay));

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
