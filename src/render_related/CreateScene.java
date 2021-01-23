package render_related;

import misc.CustomColor;
import misc.Point;
import misc.TransformationBuilder;
import misc.Vector;
import objects.*;
import render_related.Camera;
import render_related.Materials;
import render_related.PointLight;
import render_related.World;

public class CreateScene {
	public static World createSceneWithBooleansAndWater(){
		TransformationBuilder transformationBuilder = new TransformationBuilder();

		Camera camera = new Camera();
		camera.setCameraLocation(new Point(17, 14, 13), new Point(0, 1, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(-10, -10, 15), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(0, 0, 0), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(0, 0, 10), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);
		PointLight pointLight4 = new PointLight(new Point(7, 10, 5), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight4);
		PointLight pointLight5 = new PointLight(new Point(14, -16, 5), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight5);

		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getCyanRubber());
		GenericObject waterContainerCube = new Cube(10, -10, 0, 5, 5, 5, 0, 0, 0, Materials.getRedRubber());
		GenericObject groundPlane = new Plane(0, 0, -0.1, 180, 0, 0, Materials.getRedRubber());
		GenericObject floor = new BooleanUnion(groundPlane, waterContainerCube);
		world.addObject(boundingBoxCube);
		world.addObject(floor);
		world.addObject(new Water(10, -10, -0.5, 6, 6, 0, 0, 0, Materials.getWaterMaterial(), 0.5, 10, 6, 2));
		world.addObject(new Cube(10, -10, -2.9, 2, 2, 2, 0, 0, 0, Materials.getGold()));

		// Booleans
		Cube glassCube = new Cube(0, 0, 0, 2, 2, 2, 0, 0, 0, Materials.getGlassMaterial());
		Sphere glassSphere = new Sphere(1, 0, 0, 2, Materials.getGlassMaterial());
		TaperedCylinder glassCylinder = new TaperedCylinder(1, 0, 0, 0, 3, 1, 1, 90, 0, 0,Materials.getGlassMaterial());

		Cube cube = new Cube(0, 0, 0, 2, 2, 2, 0, 0, 0, Materials.getCopper());
		Sphere sphere = new Sphere(1, 0, 0, 2, Materials.getCopper());
		TaperedCylinder cylinder = new TaperedCylinder(1, 0, 0, 0, 3, 1, 1, 90, 0, 0,Materials.getCopper());

		BooleanObject booleanObject = new BooleanUnion(new BooleanUnion(glassCube, glassCylinder), glassSphere);
		booleanObject.setTransformation(transformationBuilder.reset().translation(-5, 7, 2).create());
		booleanObject.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-5, 7, 2).create());

		BooleanObject booleanObject1 = new BooleanIntersection(cube, sphere);
		booleanObject1.setTransformation(transformationBuilder.reset().translation(8, -10, 2).create());
		booleanObject1.setInverseTransformation(transformationBuilder.reset().inverseTranslation(8, -10, 2).create());

		BooleanObject booleanObject2 = new BooleanIntersection(cylinder, cube);
		booleanObject2.setTransformation(transformationBuilder.reset().translation(4, -7, 2).create());
		booleanObject2.setInverseTransformation(transformationBuilder.reset().inverseTranslation(4, -7, 2).create());

		BooleanObject booleanObject3 = new BooleanDifference(new BooleanDifference(cube, sphere), cylinder);
		booleanObject3.setTransformation(transformationBuilder.reset().rotateX(-45).translation(-3, -7, 2).create());
		booleanObject3.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-3, -7, 2).inverseRotateX(-45).create());

		world.addObject(booleanObject);
		world.addObject(booleanObject1);
		world.addObject(booleanObject2);
		world.addObject(booleanObject3);

		//      Nice world layout:
		world.addObject(new Sphere(2, 10, 2, 2, Materials.getEmerald()));
		world.addObject(new Square(10, -19.9, 10, 20, 20, -90, 0, 0, Materials.getMirrorMaterial()));
		world.addObject(new TaperedCylinder(0, 7, 3, 0, 1, 1, 2, 0, 0, 0, Materials.getTurquoise()));
		world.addObject(new TaperedCylinder(0, 7, 3, 3.7, 1, 1, -2, 0, 0, 0, Materials.getBrass()));
		world.addObject(new TaperedCylinder(1, 3, 3, 0, 1, 1, 2, 0, 0, 0, Materials.getBronze()));
		world.addObject(new TaperedCylinder(0.5, 3, 7, 0, 1, 1, 2, 0, 0, 0, Materials.getSilver()));
		world.addObject(new Cube(2, 10, 1, 1, 1, 1, 0, 0, 30, Materials.getCopper()));

//		Christmas tree
		GenericObject ct1 = new TaperedCylinder(1, 0, 0, 0, 1, 1, 2, 0, 0, 0, Materials.getBrownMaterial());
		GenericObject ct2 = new TaperedCylinder(0.5, 0, 0, 2, 3, 3, 2, 0, 0, 0, Materials.getGreenPlastic());
		GenericObject ct3 = new TaperedCylinder(0.3, 0, 0, 4, 2, 2, 1.7, 0, 0, 0, Materials.getGreenPlastic());
		GenericObject ct4 = new TaperedCylinder(0.2, 0, 0, 5.7, 1, 1, 1.3, 0, 0, 0, Materials.getGreenPlastic());
		GenericObject ct5 = new TaperedCylinder(0, 0, 0, 7, 0.5, 0.5, 1, 0, 0, 0, Materials.getGreenPlastic());
		GenericObject b1 = new BooleanUnion(ct1, ct2);
		GenericObject b2 = new BooleanUnion(ct3, ct4);
		GenericObject b3 = new BooleanUnion(b1, b2);
		GenericObject christmasTree = new BooleanUnion(b3, ct5);
		christmasTree.setTransformation(transformationBuilder.reset().translation(-10, -5, 0).create());
		christmasTree.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-10, -5, 0).create());
		world.addObject(christmasTree);


		return world;
	}

	public static World createSceneWithBooleansAndWaterTest(){
		TransformationBuilder transformationBuilder = new TransformationBuilder();

		Camera camera = new Camera();
		camera.setCameraLocation(new Point(0, 15, 15), new Point(10, -10, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(-10, -10, 15), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(0, 0, 0), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(0, 0, 10), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);
		PointLight pointLight4 = new PointLight(new Point(7, 10, 5), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight4);
		PointLight pointLight5 = new PointLight(new Point(14, -16, 5), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight5);

		GenericObject boundingBoxCube = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getCyanRubber());
		GenericObject waterContainerCube = new Cube(10, -10, 0, 5, 5, 5, 0, 0, 0, Materials.getObsidian());
		GenericObject groundBox = new Cube(0, 0, -10.1, 21, 21, 10, 0, 0, 0, Materials.getRedRubber());
		GenericObject boundingBox = new BooleanDifference(boundingBoxCube, groundBox);
		boundingBox = new BooleanUnion(boundingBox, waterContainerCube);
		world.addObject(boundingBox);
		return world;
	}

	public static World booleanDifferenceTest() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(-15, 15, 25), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(-15, 0, 15), new CustomColor(1, 1, 1));
		world.addLight(pointLight);

		Cube cube1 = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getCopper());
		Plane groundplane = new Plane(0, 0, -9, 0, 0, 0, Materials.getObsidian());
		world.addObject(cube1);
		world.addObject(groundplane);

		GenericObject cube = new Cube(0, 0, 5, 3, 3, 3, 0, 0, 0, Materials.getGold());
		GenericObject cylinder = new TaperedCylinder(1, 0, 0, 1, 1, 1, 8, 0, 0, 0, Materials.getSilver());

		GenericObject difference = new BooleanDifference(cube, cylinder);
		world.addObject(difference);

		return world;
	}

	public static World booleanUnionTest() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(-15, 15, 25), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(-15, 0, 15), new CustomColor(1, 1, 1));
		world.addLight(pointLight);

		Cube cube1 = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getCopper());
		Plane groundplane = new Plane(0, 0, -9, 0, 0, 0, Materials.getObsidian());
		world.addObject(cube1);
		world.addObject(groundplane);

		GenericObject cube = new Cube(0, 0, 5, 3, 3, 3, 0, 0, 0, Materials.getGlassMaterial());
		GenericObject cylinder = new TaperedCylinder(1, 0, 0, 1, 1, 1, 8, 0, 0, 0, Materials.getGlassMaterial());

		GenericObject union = new BooleanUnion(cube, cylinder);
		world.addObject(union);

		return world;
	}
	public static World booleanIntersectionTest() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(-10, 0, 15), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(-15, 0, 15), new CustomColor(1, 1, 1));
		world.addLight(pointLight);

		Cube cube1 = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getCopper());
		Plane groundplane = new Plane(0, 0, -9, 0, 0, 0, Materials.getObsidian());
		world.addObject(cube1);
		world.addObject(groundplane);

		GenericObject sphere = new Sphere(1, 0, 0, 5, Materials.getGlassMaterial());
		GenericObject sphere1 = new Sphere(1, 0, 0.5, 5, Materials.getGlassMaterial());

		GenericObject intersection = new BooleanIntersection(sphere, sphere1);
		world.addObject(intersection);

		return world;
	}
}
