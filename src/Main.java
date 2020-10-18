import configuration.Configuration;
import generic_objects.*;
import misc.*;
import misc.Point;
import render_related.*;

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

//        Ray ray = new Ray(new Point(5, 15, 10), new Vector(-1, -1, -1));
//        Sphere sphere = new Sphere();
//        TransformationBuilder transformationBuilder = new TransformationBuilder();
//        sphere.setTransformation(transformationBuilder.reset().translation(-5, 5, 0).create());
//        sphere.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-5, 5, 0).create());
//        System.out.println(sphere.calculateHitPoint(ray).getHitTime());

//        PixelPlotter pixelPlotter = new PixelPlotter();
//        HitPointInfo hitPointInfo = new HitPointInfo();
//        hitPointInfo.setColor(Color.CYAN);
//        pixelPlotter.addPixelToCanvas(new Pixel(0, 0, hitPointInfo.getColor()));
//        pixelPlotter.addPixelToCanvas(new Pixel(10, 10, hitPointInfo.getColor()));
//        pixelPlotter.addPixelToCanvas(new Pixel(20, 20, hitPointInfo.getColor()));
//        pixelPlotter.addPixelToCanvas(new Pixel(30, 30, hitPointInfo.getColor()));
//        pixelPlotter.renderFrame();

// Test a scene

        Camera camera = new Camera();
//        camera.setCameraLocation(new Point(10, 10, 10), 0, 0, 0);
        camera.setEye(new Point(15, 15, 15));
        camera.setDistanceN(1000);
        camera.setN(new Vector(1, 1, 1));
        camera.setV(new Vector(1, 1, -2));
        camera.setU(new Vector(-1, 1, 0));
        World world = new World(camera);

        world.addObject(new Sphere(0.6, 1, 1, 1, Color.RED));
        world.addObject(new Plane(0, 0, 0, 0, 0, 0, Color.DARK_GRAY));
        world.addObject(new Plane(0, 0, 0, 90, 0, 0, Color.ORANGE));
        world.addObject(new Plane(0, 0, 0, 0, 90, 0, Color.YELLOW));
        world.addObject(new Square(5, 0.1, 5,2, 1, 90, 0, 0,  Color.BLUE));
        world.addObject(new TaperedCylinder(0, 6, 2, 0, 1, 1, 2, 0, 0, 0, Color.GREEN));
        world.addObject(new TaperedCylinder(0, 6, 2, 3.7, 1, 1, -2, 0, 0, 0, Color.GREEN));
        world.addObject(new TaperedCylinder(1, 5, 5, 0, 1, 1, 2, 0, 0, 0, Color.MAGENTA));
        world.addObject(new TaperedCylinder(0.5, 2, 6, 0, 1, 1, 2, 0, 0, 0, Color.CYAN));
        world.addObject(new Cube(2, 2, 5, 1, 1, 1, 0, 0, 0, Color.LIGHT_GRAY));

        Renderer renderer = new Renderer();
        renderer.renderFrame(world);
    }
}
