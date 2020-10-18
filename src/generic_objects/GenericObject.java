package generic_objects;

import misc.HitPointInfo;
import misc.Matrix;
import misc.Ray;
import misc.TransformationBuilder;

import java.awt.*;

public abstract class GenericObject {

    protected Matrix transformation;
    protected Matrix inverseTransformation;
    protected Color color;

    public GenericObject(){
        this.color = Color.BLACK;
        this.transformation = Matrix.createUnitMatrix();
        this.inverseTransformation = Matrix.createUnitMatrix();
    }

    public GenericObject(Color color){
        this.color = color;
        this.transformation = Matrix.createUnitMatrix();
        this.inverseTransformation = Matrix.createUnitMatrix();
    }

    public GenericObject(double x, double y, double z,
                         double scaleX, double scaleY, double scaleZ,
                         double rotateX, double rotateY, double rotateZ,
          Color color){
        this.color = color;
        TransformationBuilder transformationBuilder = new TransformationBuilder();
        this.transformation = transformationBuilder.scaling(scaleX, scaleY, scaleZ)
                .rotateX(rotateX).rotateY(rotateY).rotateZ(rotateZ)
                .translation(x, y, z).create();
        this.inverseTransformation = transformationBuilder.reset()
                .inverseTranslation(x, y, z)
                .inverseRotateZ(rotateZ).inverseRotateY(rotateY).inverseRotateX(rotateX)
                .inverseScaling(scaleX, scaleY, scaleZ)
                .create();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract HitPointInfo calculateHitPoint(Ray ray);

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
}
