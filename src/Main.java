import misc.*;
import misc.Point;
import generic_objects.Sphere;
import render_related.Camera;
import render_related.Renderer;
import render_related.World;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // see of the columns and rows are correct.
        // The anwser should be:
        // [15 14 13 12]
        // [11 10 9 8]
        // [7 6 5 4]
        // [3 2 1 0]
//        double[][] elementsMatrix1 = {{15, 14, 13, 12}, {11, 10, 9, 8} ,{7, 6, 5, 4} ,{3, 2, 1, 0}};
//        Matrix matrix1 = new Matrix(elementsMatrix1);
//
//        System.out.println(matrix1);
//
//        double[][] elementsMatrix2 = {{0, 1, 2, 3}, {4, 5, 6, 7} ,{8, 9, 10, 11} ,{12, 13, 14, 15}};
//        Matrix matrix2 = new Matrix(elementsMatrix2);
//        System.out.println(matrix2);
//
//        Operations operations = new Operations();
//
//        Matrix matrix3 = operations.matrixProduct(matrix1, matrix2);
//        System.out.println(matrix3);
//
//        Matrix matrix4 = operations.matrixAddition(matrix1, matrix2);
//        System.out.println(matrix4);
//
//        // test transformations
//        TransformationBuilder transformationBuilder = new TransformationBuilder();
//        Matrix matrix5 = transformationBuilder.translation(1, 2, 3).create();
//        System.out.println(matrix5);
//
//
//        Matrix matrix6 = transformationBuilder.reset().translation(1, 2, 3).shearingXY(3).rotateY(60).create();
//        System.out.println(matrix6);
//        Matrix matrix7 = transformationBuilder.inverseRotateY(60).inverseShearingXY(3).inverseTranslation(1, 2, 3).create();
//        System.out.println(matrix7);
//        Vector vector1 = new Vector(1, 7, -5);
//        System.out.println(vector1);
//        Vector vector2 = operations.vectorTransformation(matrix6, vector1);
//        System.out.println(matrix6);
//        System.out.println(vector2);

//        double[][] elementsMatrix = {{6, 2, 0, 2}, {3, 7, 8, 3} ,{4, 6, 8, 1} ,{9, 4, 7, 3}};
//        double[][] elementsMatrix2 = {{12, 4, 26, 5}, {7, 3, 5, 4} ,{3, 4, 45, 5} ,{7, 5, 3, 3}};
//        Matrix matrix1 = new Matrix(elementsMatrix);
//        Matrix matrix2 = new Matrix(elementsMatrix2);
//        System.out.println(Operations.matrixProduct(matrix1, matrix2));


        // test if a ray hits the translated sphere correctly
//        TransformationBuilder transformationBuilder = new TransformationBuilder();
//        Matrix trMatrix = transformationBuilder.translation(2, 2, 2).create();
//        Matrix invMatrix = transformationBuilder.reset().inverseTranslation(2, 2, 2).create();
//        Ray ray = new Ray(new Point(-5, 2, 2), new Vector(1, 0, 0));
//        Ray inverseRay = new Ray(
//                Operations.pointTransformation(invMatrix, ray.getOrigin()),
//                Operations.vectorTransformation(invMatrix, ray.getDir())
//        );
//        System.out.println(inverseRay.getDir());
//        System.out.println(inverseRay.getOrigin());
//
//        Sphere sphere = new Sphere(trMatrix, invMatrix, Color.CYAN);
//        HitPointInfo hitPointInfo = sphere.calculateHitPoint(ray);
//        System.out.println(hitPointInfo.getHitTime());
//        System.out.println(hitPointInfo.isHit());


// Test a scene
        TransformationBuilder transformationBuilder = new TransformationBuilder();
        Matrix trMatrix = transformationBuilder.translation(5, 0, 0).create();
        Matrix invMatrix = transformationBuilder.reset().inverseTranslation(5, 0, 0).create();
        System.out.println(trMatrix);
        System.out.println(invMatrix);
        Camera camera = new Camera();
        camera.setEye(new Point(10, 10, 10));
        camera.setDistanceN(1000);
        camera.setN(new Vector(1, 1, 1));
        camera.setV(new Vector(1, 1, -2));
        camera.setU(new Vector(-1, 1, 0));

        World world = new World(camera);
        Sphere sphere = new Sphere();
        sphere.setColor(Color.YELLOW);
        sphere.setTransformation(trMatrix);
        sphere.setInverseTransformation(invMatrix);
        world.addObject(sphere);

        Sphere sphere3 = new Sphere();
        sphere3.setColor(Color.BLUE);
        sphere3.setTransformation(transformationBuilder.reset().translation(0,5,0).create());
        sphere3.setInverseTransformation(transformationBuilder.reset().translation(0,5,0).create());
        world.addObject(sphere3);

        Sphere sphere2 = new Sphere();
        sphere2.setColor(Color.RED);
//        sphere2.setTransformation(transformationBuilder.reset().scaling(2, 2, 2).create());
//        sphere2.setInverseTransformation(transformationBuilder.reset().inverseScaling(2, 2, 2).create());
        world.addObject(sphere2);

        Sphere sphere4 = new Sphere();
        sphere4.setColor(Color.MAGENTA);
        sphere4.setTransformation(transformationBuilder.reset().translation(0,0,5).create());
        sphere4.setInverseTransformation(transformationBuilder.reset().translation(0,0,5).create());
        world.addObject(sphere4);

        Renderer renderer = new Renderer();
        renderer.renderFrame(world);

    }
}
