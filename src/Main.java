import base_classes.*;
import base_classes.Point;
import configuration.Configuration;
import generic_objects.Sphere;
import render_related.Camera;
import render_related.Pixel;
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




        Camera camera = new Camera();
        camera.setEye(new Point(5, 0, 0));
        camera.setDistanceN(100);
        World world = new World(camera);
        world.addObject(new Sphere());
        Renderer renderer = new Renderer();
        renderer.renderFrame(world);


//        Ray ray = new Ray(new Point(-5, -5, -5), new Vector(4.5, 5, 5));
//        Sphere sphere = new Sphere();
//        System.out.println(sphere.calculateHitPoint(ray).getHitTime());

//        Renderer renderer = new Renderer();
//        renderer.addPointToFrame(new Pixel(0, 0));
//        renderer.addPointToFrame(new Pixel(1, 1));
//        renderer.addPointToFrame(new Pixel(5, 5, Color.BLACK));
//        renderer.addPointToFrame(new Pixel(15, 15, Color.BLACK));
//        renderer.addPointToFrame(new Pixel(25, 25, Color.BLACK));
//        renderer.addPointToFrame(new Pixel(620, 440, Color.BLACK));
//        renderer.addPointToFrame(new Pixel(20, Configuration.SCREEN_HEIGHT- 480+1));
    }
}
