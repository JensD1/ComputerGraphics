package generic_objects;

import misc.*;
import render_related.Material;

import java.util.List;

public abstract class GenericObject {

    protected Matrix transformation;
    protected Matrix inverseTransformation;
    protected Material material;

    public GenericObject(){
        this.transformation = Matrix.createUnitMatrix();
        this.inverseTransformation = Matrix.createUnitMatrix();
        this.material = new Material();
    }

    public GenericObject(Material material){
        this.transformation = Matrix.createUnitMatrix();
        this.inverseTransformation = Matrix.createUnitMatrix();
        this.material = material;
    }

    public GenericObject(double x, double y, double z,
                         double scaleX, double scaleY, double scaleZ,
                         double rotateX, double rotateY, double rotateZ,
                         Material material){
        TransformationBuilder transformationBuilder = new TransformationBuilder();
        this.transformation = transformationBuilder.scaling(scaleX, scaleY, scaleZ)
                .rotateX(rotateX).rotateY(rotateY).rotateZ(rotateZ)
                .translation(x, y, z).create();
        this.inverseTransformation = transformationBuilder.reset()
                .inverseTranslation(x, y, z)
                .inverseRotateZ(rotateZ).inverseRotateY(rotateY).inverseRotateX(rotateX)
                .inverseScaling(scaleX, scaleY, scaleZ)
                .create();
        this.material = material;
    }

    public abstract List<HitPointInfo> calculateHitPoint(Ray ray);

    public Matrix getTransformation() {
        return transformation;
    }

    public void setTransformation(Matrix transformation) {
        this.transformation = transformation;
    }

    public Matrix getInverseTransformation() {
        return inverseTransformation;
    }

    public void setInverseTransformation(Matrix inverseTransformation) {
        this.inverseTransformation = inverseTransformation;
    }

    public Material getMaterial() {
        return material;
    }

    public abstract void setMaterial(Material material);
}
