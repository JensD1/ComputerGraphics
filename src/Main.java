import generic_objects.*;
import misc.CustomColor;
import misc.Point;
import misc.Vector;
import render_related.*;

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
		// self created materials
		Material redMaterial = new Material(new CustomColor(0.8, 0.2, 0.1), new CustomColor(1, 1, 1),
				16, new CustomColor(), new CustomColor(0.3, 0.3, 0.3));
		Material blueMaterial = new Material(new CustomColor(0, 0.7, 0.8), new CustomColor(0.8, 0.5, 0.5),
				100, new CustomColor(), new CustomColor(0.3, 0.3, 0.3));
		Material brownMaterial = new Material(new CustomColor(0.5450, 0.27058, 0.07451), new CustomColor(0.7, 0.6, 0.5),
				0.25 * 128, new CustomColor(), new CustomColor(0.05, 0.0, 0.0));

		// Materials from http://devernay.free.fr/cours/opengl/materials.html
		Material emerald = new Material(new CustomColor(0.07568, 0.61424, 0.07568), new CustomColor(0.633, 0.727811, 0.633),
				0.6 * 128, new CustomColor(), new CustomColor(0.0215, 0.1745, 0.0215));
		Material jade = new Material(new CustomColor(0.54, 0.89, 0.63), new CustomColor(0.316228, 0.316228, 0.316228),
				0.1 * 128, new CustomColor(), new CustomColor(0.135, 0.2225, 0.1575));
		Material obsidian = new Material(new CustomColor(0.18275, 0.17, 0.22525), new CustomColor(0.332741, 0.328634, 0.346435),
				0.3 * 128, new CustomColor(), new CustomColor(0.05375, 0.05, 0.06625));
		Material pearl = new Material(new CustomColor(1, 0.829, 0.829), new CustomColor(0.296648, 0.296648, 0.296648),
				0.088 * 128, new CustomColor(), new CustomColor(0.25, 0.20725, 0.20725));
		Material ruby = new Material(new CustomColor(0.61424, 0.04136, 0.04136), new CustomColor(0.727811, 0.626959, 0.626959),
				0.6 * 128, new CustomColor(), new CustomColor(0.1745, 0.01175, 0.01175));
		Material turquoise = new Material(new CustomColor(0.396, 0.74151, 0.69102), new CustomColor(0.297254, 0.30829, 0.306678),
				0.1 * 128, new CustomColor(), new CustomColor(0.1, 0.18725, 0.1745));
		Material brass = new Material(new CustomColor(0.780392, 0.568627, 0.113725), new CustomColor(0.992157, 0.941176, 0.807843),
				0.21794872 * 128, new CustomColor(), new CustomColor(0.329412, 0.223529, 0.027451));
		Material bronze = new Material(new CustomColor(0.714, 0.4284, 0.18144), new CustomColor(0.393548, 0.271906, 0.166721),
				0.2 * 128, new CustomColor(), new CustomColor(0.2125, 0.1275, 0.054));
		Material chrome = new Material(new CustomColor(0.4, 0.4, 0.4), new CustomColor(0.774597, 0.774597, 0.774597),
				0.6 * 128, new CustomColor(), new CustomColor(0.25, 0.25, 0.25));
		Material copper = new Material(new CustomColor(0.7038, 0.27048, 0.0828), new CustomColor(0.256777, 0.137622, 0.086014),
				0.1 * 128, new CustomColor(), new CustomColor(0.19125, 0.0735, 0.0225));
		Material gold = new Material(new CustomColor(0.75164, 0.60648, 0.22648), new CustomColor(0.628281, 0.555802, 0.366065),
				0.4 * 128, new CustomColor(), new CustomColor(0.24725, 0.1995, 0.0745));
		Material silver = new Material(new CustomColor(0.50754, 0.50754, 0.50754), new CustomColor(0.508273, 0.508273, 0.508273),
				0.4 * 128, new CustomColor(), new CustomColor(0.19225, 0.19225, 0.19225));
		Material blackPlastic = new Material(new CustomColor(0.01, 0.01, 0.01), new CustomColor(0.50, 0.50, 0.50),
				0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0));
		Material cyanPlastic = new Material(new CustomColor(0.0, 0.50980392, 0.50980392), new CustomColor(0.50196078, 0.50196078, 0.50196078),
				0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.1, 0.06));
		Material greenPlastic = new Material(new CustomColor(0.1, 0.35, 0.1), new CustomColor(0.45, 0.55, 0.45),
				0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0));
		Material redPlastic = new Material(new CustomColor(0.5, 0.0, 0.0), new CustomColor(0.7, 0.6, 0.6),
				0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0));
		Material whitePlastic = new Material(new CustomColor(0.55, 0.55, 0.55), new CustomColor(0.70, 0.70, 0.70),
				0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0));
		Material yellowPlastic = new Material(new CustomColor(0.5, 0.5, 0.0), new CustomColor(0.60, 0.60, 0.50),
				0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0));
		Material blackRubber = new Material(new CustomColor(0.01, 0.01, 0.01), new CustomColor(0.4, 0.4, 0.4),
				0.078125 * 128, new CustomColor(), new CustomColor(0.02, 0.02, 0.02));
		Material cyanRubber = new Material(new CustomColor(0.4, 0.5, 0.5), new CustomColor(0.04, 0.7, 0.7),
				0.078125 * 128, new CustomColor(), new CustomColor(0.0, 0.05, 0.05));
		Material greenRubber = new Material(new CustomColor(0.4, 0.5, 0.4), new CustomColor(0.04, 0.7, 0.04),
				0.078125 * 128, new CustomColor(), new CustomColor(0.0, 0.05, 0.0));
		Material redRubber = new Material(new CustomColor(0.5, 0.4, 0.4), new CustomColor(0.7, 0.04, 0.04),
				0.078125 * 128, new CustomColor(), new CustomColor(0.05, 0.0, 0.0));
		Material whiteRubber = new Material(new CustomColor(0.5, 0.5, 0.5), new CustomColor(0.7, 0.7, 0.7),
				0.078125 * 128, new CustomColor(), new CustomColor(0.05, 0.05, 0.05));
		Material yellowRubber = new Material(new CustomColor(0.5, 0.5, 0.4), new CustomColor(0.7, 0.7, 0.04),
				0.078125 * 128, new CustomColor(), new CustomColor(0.05, 0.05, 0.0));

		Camera camera = new Camera();
//        camera.setCameraLocation(new Point(10, 10, 10), 0, 0, 0);
		camera.setEye(new Point(15, 15, 15));
		camera.setDistanceN(1000);
		camera.setN(new Vector(1, 1, 1));
		camera.setV(new Vector(1, 1, -2));
		camera.setU(new Vector(-1, 1, 0));
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(1, 10, 7), new CustomColor(1, 1, 1));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(10, 1, 3), new CustomColor(0.2, 0.2, 0.2));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(-5, -5, 5), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight3);

		// Easy testing
//        Material redMaterial = new Material(new CustomColor(0.8, 0.8, 0.8), new CustomColor(1, 1, 1),
//                16, new CustomColor(), new CustomColor(0, 0, 0));
//        world.addObject(new Sphere(2, 0, 0, 3,
//                new CustomColor(0.8, 0.2, 0.1), new CustomColor(1, 1, 1), 16, new CustomColor(), new CustomColor(0.1, 0.1, 0.1)));
//        world.addObject(new TaperedCylinder(0.3, 0, 0, 0, 5, 5, 5, 0, 0, 0,
//                new CustomColor(0.8, 0.2, 0.1), new CustomColor(1, 1, 1), 16, new CustomColor(), new CustomColor(0.1, 0.1, 0.1)));
//        world.addObject(new Cube(0, 0, 0, 5, 5, 5, 0, 0, 0, redMaterial));
//        world.addObject(new Plane(new CustomColor(0.8, 0.2, 0.1), new CustomColor(1, 1, 1), 16, new CustomColor(), new CustomColor(0.1, 0.1, 0.1)));

//        // Nice world layout:
		world.addObject(new Sphere(2, 10, 2, 1, emerald));
		world.addObject(new Plane(0, 0, 0, 0, 0, 0, jade));
		world.addObject(new Plane(0, -10, 0, -90, 0, 0, obsidian));
		world.addObject(new Plane(-10, 0, 0, 0, 90, 0, pearl));
		world.addObject(new Square(-5, -9.9, 5, 2, 1, -90, 0, 0, ruby));
		world.addObject(new TaperedCylinder(0, 7, 3, 0, 1, 1, 2, 0, 0, 0, turquoise));
		world.addObject(new TaperedCylinder(0, 7, 3, 3.7, 1, 1, -2, 0, 0, 0, brass));
		world.addObject(new TaperedCylinder(1, 3, 3, 0, 1, 1, 2, 0, 0, 0, bronze));
		world.addObject(new TaperedCylinder(0.5, 3, 7, 0, 1, 1, 2, 0, 0, 0, chrome));
		world.addObject(new Cube(2, 10, 1, 1, 1, 1, 0, 0, 30, copper));

		//Christmas tree
		world.addObject(new TaperedCylinder(1, -5, 0, 0, 1, 1, 2, 0, 0, 0, brownMaterial));
		world.addObject(new TaperedCylinder(0.5, -5, 0, 2, 3, 3, 2, 0, 0, 0, greenPlastic));
		world.addObject(new TaperedCylinder(0.3, -5, 0, 4, 2, 2, 1.7, 0, 0, 0, greenPlastic));
		world.addObject(new TaperedCylinder(0.2, -5, 0, 5.7, 1, 1, 1.3, 0, 0, 0, greenPlastic));
		world.addObject(new TaperedCylinder(0, -5, 0, 7, 0.5, 0.5, 1, 0, 0, 0, greenPlastic));

		Renderer renderer = new Renderer();
		renderer.renderFrame(world);
	}
}
