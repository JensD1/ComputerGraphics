package objects;

import configuration.Configuration;
import misc.*;
import render_related.Material;

import java.util.List;

public abstract class GenericObject {

    protected Matrix transformation;
    protected Matrix inverseTransformation;
    protected Material material;
    protected int priority; // The priority level of the object. -1 means no defined priority.

    public GenericObject(){
        this.transformation = Matrix.createIdentityMatrix();
        this.inverseTransformation = Matrix.createIdentityMatrix();
        this.material = new Material();
        this.priority = Configuration.LOWEST_PRIORITY;
    }

    public GenericObject(Material material){
        this.transformation = Matrix.createIdentityMatrix();
        this.inverseTransformation = Matrix.createIdentityMatrix();
        this.material = material;
        this.priority = Configuration.LOWEST_PRIORITY;
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
        this.priority = Configuration.LOWEST_PRIORITY;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void resetTransformation(){
        this.transformation = Matrix.createIdentityMatrix();
        this.inverseTransformation = Matrix.createIdentityMatrix();
    }
}
