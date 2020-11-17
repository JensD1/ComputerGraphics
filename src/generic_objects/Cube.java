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

    @Override
    public List<HitPointInfo> calculateHitPoint(Ray ray) {
        List<HitPointInfo> hitPointInfoList = new ArrayList<>();
        Ray inverseRay = new Ray(
                Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
                Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
        );

        addHitPointToList(hitPointInfoList, this.groundSquare.calculateHitPoint(inverseRay));
        addHitPointToList(hitPointInfoList, this.upperSquare.calculateHitPoint(inverseRay));
        addHitPointToList(hitPointInfoList, this.backSquare.calculateHitPoint(inverseRay));
        addHitPointToList(hitPointInfoList, this.frontSquare.calculateHitPoint(inverseRay));
        addHitPointToList(hitPointInfoList, this.rightSquare.calculateHitPoint(inverseRay));
        addHitPointToList(hitPointInfoList, this.leftSquare.calculateHitPoint(inverseRay));

        return hitPointInfoList;
    }

    public void addHitPointToList(List<HitPointInfo> hitPointInfoList, List<HitPointInfo> toAddList){
        if(!toAddList.isEmpty()){
            for(HitPointInfo hitPointInfo: toAddList){ // Change the parameters that needs to change
                hitPointInfo.setHitPoint(Operations.pointTransformation(this.transformation, hitPointInfo.getHitPoint()));
                hitPointInfo.setNormal(Operations.vectorTransformation(this.transformation, hitPointInfo.getNormal()));
                hitPointInfo.setObject(this);
            }
            hitPointInfoList.addAll(toAddList);
        }
    }

    public void setMaterial(Material material) {
        this.material = material;
        this.groundSquare.setMaterial(material);
        this.upperSquare.setMaterial(material);
        this.backSquare.setMaterial(material);
        this.frontSquare.setMaterial(material);
        this.rightSquare.setMaterial(material);
        this.leftSquare.setMaterial(material);
    }
}
