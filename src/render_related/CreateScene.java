package render_related;

import misc.*;
import objects.*;

import java.util.ArrayList;
import java.util.List;

public class CreateScene {
	public static World createSceneWithBooleansAndWater() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();

		Camera camera = new Camera();
		camera.setCameraLocation(new Point(17, 14, 13), new Point(2, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
//		PointLight pointLight = new PointLight(new Point(-10, -10, 15), new CustomColor(0.5, 0.5, 0.5));
//		world.addLight(pointLight);
//		PointLight pointLight2 = new PointLight(new Point(0, 0, 0), new CustomColor(0.7, 0.7, 0.7));
//		world.addLight(pointLight2);
//		PointLight pointLight3 = new PointLight(new Point(0, 0, 10), new CustomColor(0.5, 0.5, 0.5));
//		world.addLight(pointLight3);
//		PointLight pointLight4 = new PointLight(new Point(7, 10, 5), new CustomColor(0.7, 0.7, 0.7));
//		world.addLight(pointLight4);
//		PointLight pointLight5 = new PointLight(new Point(14, -16, 5), new CustomColor(0.7, 0.7, 0.7));
//		world.addLight(pointLight5);

		PointLight pointLight = new PointLight(new Point(-10, -10, 10), new CustomColor(0.01, 0.5, 0.1));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(0, 0, 10), new CustomColor(0.5, 0.01, 0.4));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(-10, 10, 10), new CustomColor(0.5, 0.05, 0.01));
		world.addLight(pointLight3);
		PointLight pointLight4 = new PointLight(new Point(10, 10, 10), new CustomColor(0.01, 0.05, 0.5));
		world.addLight(pointLight4);
		PointLight pointLight5 = new PointLight(new Point(10, -10, 10), new CustomColor(0.01, 0.3, 0.3));
		world.addLight(pointLight5);

		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getCyanRubber());
		GenericObject waterContainerCube = new Cube(10, -10, 0, 5, 5, 5, 0, 0, 0, Materials.getRedRubber());
		GenericObject groundPlane = new Plane(0, 0, -0.1, 180, 0, 0, Materials.getRedRubber());
		GenericObject floor = new BooleanUnion(groundPlane, waterContainerCube);
		world.addObject(boundingBoxCube);
		world.addObject(floor);
		world.addObject(new Water2(10, -10, -0.3, 5.1, 5.1, 0, 0, 0, Materials.getWaterMaterial(), 5, 20));
		world.addObject(new Cube(10, -10, -2.9, 2, 2, 2, 0, 0, 0, Materials.getGold()));

		// Booleans
		Cube glassCube = new Cube(0, 0, 0, 2, 2, 2, 0, 0, 0, Materials.getGlassMaterial());
		Sphere glassSphere = new Sphere(1, 0, 0, 2, Materials.getGlassMaterial());

		Material material = Materials.getCopper();
		material.setTexture(new NoiseTexture());
		Cube cube = new Cube(0, 0, 0, 2, 2, 2, 0, 0, 0, material);
		Sphere sphere = new Sphere(1, 0, 0, 2, material);
		TaperedCylinder cylinder = new TaperedCylinder(1, 0, 0, 0, 3, 1, 1, 90, 0, 0, material);

		BooleanObject booleanObject = new BooleanUnion(glassCube, glassSphere);
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
		GenericObject christmasTree = MiscObjectCreator.createTree();
		christmasTree.setTransformation(transformationBuilder.reset().translation(-10, -5, 0).create());
		christmasTree.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-10, -5, 0).create());
		world.addObject(christmasTree);

		return world;
	}

	public static World oceanScene() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();

		Camera camera = new Camera();
		camera.setCameraLocation(new Point(5, -20, 2), new Point(-9, 20, 1), new Vector(0, 0.5, 1));
//		camera.setCameraLocation(new Point(-2, -20, 1.5), new Point(-6, 0, 2), new Vector(0, 0.5, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(2, 10, 15), new CustomColor(0.3, 0.3, 0.3));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(20, 15, 10), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(10, 20, 13), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		GenericObject boundingBoxCube = new Cube(0, 0, 11, 30, 30, 10, 0, 0, 0, Materials.getCloudMaterial());
		GenericObject boundingBoxCube1 = new Cube(0, 0, -8, 29, 29, 10, 0, 0, 0, Materials.getGroundMaterial());
		GenericObject box = new BooleanUnion(boundingBoxCube1, boundingBoxCube);
		box.setTransformation(transformationBuilder.reset().translation(0, 0, 5).create());
		box.setInverseTransformation(transformationBuilder.reset().inverseTranslation(0, 0, 5).create());
		world.addObject(box);
		Material waterMaterial = Materials.getWaterMaterial();
		waterMaterial.setReflectionCoefficient(0.01);
		GenericObject waterSurface = new WaterCube(0, 0, -8.99, 31, 31, 9, 0, 0, 0, waterMaterial, 0.5, 100);

		GenericObject waveCube = new WaterCube(-16, 0, 0, 16, 31, 5, 0, 0, 0, waterMaterial, 0.5, 100);
		Material waterMaterial2 = Materials.getWaterMaterial();
		waterMaterial2.setReflectionCoefficient(0.001);
		waterMaterial2.setSpecular(waterMaterial2.getSpecular().scalarProduct(0.1));
		waterMaterial2.setTexture(new NoiseTexture(10, true, 0.5, 1));
		GenericObject waveCylinder = new TaperedCylinder(1, 0, 31.5, 2, 5, 5, 63, 90, 0, 0, waterMaterial2);
//		GenericObject waveCylinder = new TaperedCylinder(1, 0, 31.5, -3, 10, 10, 63, 90, 0, 0, waterMaterial2);
		GenericObject wave = new BooleanDifference(waveCube, waveCylinder);
		GenericObject water = new BooleanUnion(waterSurface, wave);
		world.addObject(water);


		GenericObject ship = MiscObjectCreator.createShip();
//		ship.setTransformation(transformationBuilder.reset().scaling(7, 7, 7).rotateX(10).rotateY(15).translation(-3, 0, -3).create());
//		ship.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-3, 0, -3).inverseRotateY(15).inverseRotateX(10).inverseScaling(7, 7, 7).create());
		ship.setTransformation(transformationBuilder.reset().scaling(7, 7, 7).rotateX(10).rotateY(20).translation(-5, 0, -1).create());
		ship.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-5, 0, -1).inverseRotateY(20).inverseRotateX(10).inverseScaling(7, 7, 7).create());

		world.addObject(ship);

		return world;
	}

	public static World lightScene() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();

		Camera camera = new Camera();
		camera.setCameraLocation(new Point(0, 10, 3), new Point(0, 0, 3), new Vector(-1, 0, 0));
//		camera.setCameraLocation(new Point(5, 5, 3), new Point(0, 0, 5), new Vector(0, 0, 1));
//		camera.setCameraLocation(new Point(10, 10, 3), new Point(0, 0, 3), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight2 = new PointLight(new Point(0, 10, 10), new CustomColor(0.1, 0.1, 0.1));
		world.addLight(pointLight2);


		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 40, 40, 20, 0, 0, 0, Materials.getCyanRubber());
		GenericObject groundPlane = new Plane(0, 0, -0.01, 180, 0, 0, Materials.getWoodMaterial(
				true,
				transformationBuilder.reset().scaling(0.05, 0.05, 0.05).rotateY(70).translation(0, 0.5, 0).create()
		));
//		GenericObject groundPlane = new Plane(0, 0, -0.01, 180, 0, 0, Materials.getGreenRubber());
		world.addObject(boundingBoxCube);
		world.addObject(groundPlane);

		// lamp with light (make sure that the same transformation is done to both of them!
		GenericObject lamp = MiscObjectCreator.createLamp();
		PointLight lampLight = new PointLight(new Point(0, 1.5, 4.95), new CustomColor(1, 0.8, 0.5));
		world.addLight(lampLight);
		world.addObject(lamp);

		return world;
	}

	public static World forestScene() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		NoiseClass posNoise = new NoiseClass();
		NoiseClass scaleNoise = new NoiseClass();

		Camera camera = new Camera();
//		camera.setCameraLocation(new Point(0, 0, 30), new Point(1, 0, 0), new Vector(0, 0, 1));
		camera.setCameraLocation(new Point(20, 15, 15), new Point(1, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(2, 10, 15), new CustomColor(0.3, 0.3, 0.3));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(20, 15, 10), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(10, 20, 13), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 30, 30, 20, 0, 0, 0, Materials.getCyanRubber());
		Material groundmaterial = Materials.getGreenRubber();
		groundmaterial.setTexture(new NoiseTexture(10, true, 0.5, 1));
		GenericObject groundPlane = new Water2(0, 0, -0.01, 31, 31, 0, 0, 0, groundmaterial, 2, 10);
		world.addObject(boundingBoxCube);
		world.addObject(groundPlane);

		List<GenericObject> forest = new ArrayList<>();

		double percentageGoodTrees = 0.7;
		double turbulenceScale = 1;
		int beginPosX = -25;
		int beginPosY = -25;
		int endPosX = 25;
		int endPosY = 25;
		int strideX = 10;
		int strideY = 10;
		double scaleNoiseScale = 1;
		double meanScale = 1.5;
		double divergence = 0.8;
		double posMeanScale = 0;
		double posDivergence = 4;

		for (int i = beginPosX; i < endPosX; i += strideX) {
			for (int j = beginPosY; j < endPosY; j += strideY) {
				double noise = posNoise.turbulence(turbulenceScale, new Point(i, j, 0));
				double scaleNoiseValNotAdjusted = scaleNoise.turbulence(scaleNoiseScale, new Point(i, j, 0));
				double scaleNoiseVal = -2 * divergence * scaleNoiseValNotAdjusted + (meanScale + divergence);
				double positioningNoiseX = -2 * posDivergence * noise + (posMeanScale + posDivergence);
				double positioningNoiseY = -2 * posDivergence * scaleNoiseValNotAdjusted + (posMeanScale + posDivergence);
				if (noise < percentageGoodTrees) {
					GenericObject tree = MiscObjectCreator.createTree();


					tree.setTransformation(
							transformationBuilder.reset().scaling(scaleNoiseVal, scaleNoiseVal, scaleNoiseVal)
									.translation(i + positioningNoiseX, j + positioningNoiseY, 0).create());
					tree.setInverseTransformation(
							transformationBuilder.reset().inverseTranslation(i + positioningNoiseX, j + positioningNoiseY, 0)
									.inverseScaling(scaleNoiseVal, scaleNoiseVal, scaleNoiseVal).create());
					forest.add(
							tree
					);
				} else {
					GenericObject tree = MiscObjectCreator.createCutDownTree();
					tree.setTransformation(
							transformationBuilder.reset().scaling(scaleNoiseVal, scaleNoiseVal, scaleNoiseVal)
									.translation(i + positioningNoiseX, j + positioningNoiseY, 0).create());
					tree.setInverseTransformation(
							transformationBuilder.reset().inverseTranslation(i + positioningNoiseX, j + positioningNoiseY, 0)
									.inverseScaling(scaleNoiseVal, scaleNoiseVal, scaleNoiseVal).create());
					forest.add(
							tree
					);
				}
			}
		}

		for (GenericObject tree : forest) {
			world.addObject(tree);
		}

		return world;
	}

	public static World lensScene() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();

		Camera camera = new Camera();
//		camera.setCameraLocation(new Point(8, 0, 0), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setCameraLocation(new Point(15, 15, 15), new Point(-10, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(2, 10, 15), new CustomColor(0.3, 0.3, 0.3));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(10, 0, 10), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(10, 3, 13), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 40, 40, 20, 0, 0, 0, Materials.getTurquoise());
		GenericObject groundPlane = new Plane(0, 0, -5, 180, 0, 0, Materials.getSilver());
		world.addObject(boundingBoxCube);
		world.addObject(groundPlane);


		GenericObject lensSphere1 = new Sphere(1, 0.25, 0, 0, Materials.getGlassMaterial());
		GenericObject lensSphere2 = new Sphere(1, -0.25, 0, 0, Materials.getGlassMaterial());
		GenericObject lens = new BooleanIntersection(lensSphere1, lensSphere2);

		// transform the lens
//		lens.setTransformation(transformationBuilder.reset().scaling(0.5, 1, 1).create());
//		lens.setInverseTransformation(transformationBuilder.reset().inverseScaling(0.5, 1, 1).create());
		lens.setTransformation(transformationBuilder.reset().scaling(0.2, 1, 1).create());
		lens.setInverseTransformation(transformationBuilder.reset().inverseScaling(0.2, 1, 1).create());

		GenericObject bracelet = MiscObjectCreator.createBracelet();
		bracelet.setTransformation(transformationBuilder.reset().translation(-21, 0, 0).create());
		bracelet.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-21, 0, 0).create());
		world.addObject(bracelet);

		world.addObject(lens);

		return world;
	}

	public static World ruby() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();

		Camera camera = new Camera();
		camera.setCameraLocation(new Point(10, 5, 3), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(2, 10, 15), new CustomColor(0.3, 0.3, 0.3));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(10, 0, 10), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(10, 3, 13), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 40, 40, 20, 0, 0, 0, Materials.getCyanRubber());
		Material greenGround = Materials.getGreenRubber();
		greenGround.setTexture(new NoiseTexture(1, true, 0.8, 1));
		GenericObject groundPlane = new Plane(0, 0, -0.01, 180, 0, 0, greenGround);
		world.addObject(boundingBoxCube);
		world.addObject(groundPlane);

		// specific objects
		GenericObject bracelet = MiscObjectCreator.createBracelet();
		bracelet.setTransformation(transformationBuilder.reset().rotateY(87).translation(0, 0, 0.63).create());
		bracelet.setInverseTransformation(transformationBuilder.reset().inverseTranslation(0, 0, 0.63).inverseRotateY(87).create());

		world.addObject(bracelet);

		return world;
	}

	public static World tableWithGlass() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();

		Camera camera = new Camera();
//		camera.setCameraLocation(new Point(1, 1, 2), new Point(0, 0, 1), new Vector(0, 0, 1));
		camera.setCameraLocation(new Point(3, 3, 2), new Point(0, 0, 0.5), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(2, 5, 10), new CustomColor(0.3, 0.3, 0.3));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(5, 0, 5), new CustomColor(0.7, 0.7, 0.7));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(7, 3, 8), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 40, 40, 20, 0, 0, 0, Materials.getCyanRubber());
		Material greenGround = Materials.getGreenRubber();
		greenGround.setTexture(new NoiseTexture(1, true, 0.8, 1));
		GenericObject groundPlane = new Plane(0, 0, -0.01, 180, 0, 0, greenGround);
		world.addObject(boundingBoxCube);
		world.addObject(groundPlane);

//		objects:
		// glasses
//		GenericObject glass1 = MiscObjectCreator.createGlass();
//		GenericObject waterGlass1 = MiscObjectCreator.createWaterForFilledGlass();
//		Matrix transformGlass1 = transformationBuilder.reset().scaling(0.1, 0.1, 0.1).translation(0, 0, 1.01).create();
//		Matrix inverseTransformGlass1 = transformationBuilder.reset().inverseTranslation(0, 0, 1.01).inverseScaling(0.1, 0.1, 0.1).create();
//		glass1.setTransformation(transformGlass1);
//		glass1.setInverseTransformation(inverseTransformGlass1);
//		waterGlass1.setTransformation(transformGlass1);
//		waterGlass1.setInverseTransformation(inverseTransformGlass1);
//		world.addObject(glass1);
//		world.addObject(waterGlass1);
//
//		GenericObject glass2 = MiscObjectCreator.createGlass();
//		GenericObject waterGlass2 = MiscObjectCreator.createWaterForFilledGlass();
//		Matrix transformGlass2 = transformationBuilder.reset().scaling(0.1, 0.1, 0.1).translation(0.4, -0.1, 1.01).create();
//		Matrix inverseTransformGlass2 = transformationBuilder.reset().inverseTranslation(0.4, -0.1, 1.01).inverseScaling(0.1, 0.1, 0.1).create();
//		glass2.setTransformation(transformGlass2);
//		glass2.setInverseTransformation(inverseTransformGlass2);
//		waterGlass2.setTransformation(transformGlass2);
//		waterGlass2.setInverseTransformation(inverseTransformGlass2);
//		world.addObject(glass2);
//		world.addObject(waterGlass2);

		// Fish bowl
//		GenericObject fishBowl = MiscObjectCreator.createFishBowl();
//		GenericObject fishBowlWater = MiscObjectCreator.createWaterForFishBowl();
//		Matrix transformFishBowl = transformationBuilder.reset().scaling(0.3, 0.3, 0.3).translation(0, 0.5, 1.01).create();
//		Matrix inverseTransformFishBowl = transformationBuilder.reset().inverseTranslation(0, 0.5, 1.01).inverseScaling(0.3, 0.3, 0.3).create();
//		fishBowl.setTransformation(transformFishBowl);
//		fishBowl.setInverseTransformation(inverseTransformFishBowl);
//		fishBowlWater.setTransformation(transformFishBowl);
//		fishBowlWater.setInverseTransformation(inverseTransformFishBowl);
//		world.addObject(fishBowlWater);
//		world.addObject(fishBowl);

		// table
		GenericObject table = MiscObjectCreator.createTable();
		world.addObject(table);
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

	public static World simpleObjectTestScene() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(15, 15, 15), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(-15, 15, 15), new CustomColor(1, 1, 1));
		world.addLight(pointLight);

//		GenericObject object = new Sphere(5, 0, 0, 0, Materials.getObsidian());
//		GenericObject object = new Cube(0, 0, 0, 5, 5, 5, 0, 0, 0, Materials.getObsidian());
//		GenericObject object = new Plane(0, 0, 0, 0, 0, 0, Materials.getObsidian());
//		GenericObject object = new Square(0, 0, 0,5, 5, 0, 0, 0, Materials.getObsidian());
//		GenericObject object = new TaperedCylinder(0.5, 0, 0, 0, 5, 5, 5, 0, 0, 0, Materials.getObsidian());
		GenericObject object = new Water(0, 0, 0, 5, 5, 0, 0, 0, Materials.getObsidian(), 1, 10, 10, 2);
		world.addObject(object);

		return world;
	}

	public static World simpleBoundingBoxTest() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(15, 15, 15), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(0, 0, 0), new CustomColor(1, 1, 1));
		world.addLight(pointLight);

//		GenericObject object = new Sphere(30, 0, 0, 0, Materials.getObsidian());
//		GenericObject object = new Cube(0, 0, 0, 20, 20, 20, 0, 0, 0, Materials.getObsidian());
		GenericObject object = new TaperedCylinder(1, 0, 0, -10, 40, 40, 40, 0, 0, 0, Materials.getObsidian());
		world.addObject(object);

		return world;
	}

	public static World simpleTexturesTest() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(15, 15, 15), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(15, 15, 15), new CustomColor(2, 2, 2));
		world.addLight(pointLight);

		TransformationBuilder transformationBuilder = new TransformationBuilder();

		GenericObject object = new TaperedCylinder(0.5, 1, 0, -2, 4, 4,
				4, 0, 0, 0,
				Materials.getWoodMaterial(
						false,
						transformationBuilder.reset().scaling(5, 5, 5).rotateX(90).create()
				)
		);
		world.addObject(object);

		return world;
	}

	public static World simpleTexturesTestNoise() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(15, 15, 15), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(15, 15, 15), new CustomColor(2, 2, 2));
		world.addLight(pointLight);

		Texture texture = new NoiseTexture(1);
		Material material = Materials.getGold();
		material.setTexture(texture);
		GenericObject object = new Plane(0, 0, 0, 0, 0, 0, Materials.getGold());
		world.addObject(object);

		return world;
	}

	public static World water2Test() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(15, 15, 15), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(-10, -10, 15), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(0, 0, 0), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(0, 0, 10), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		GenericObject object = new Water2(0, 0, 0, 30, 30, 0, 0, 0, Materials.getWaterMaterial(), 5, 20);
		world.addObject(object);
		world.addObject(new Cube(0, 0, -5, 3, 3, 3, 0, 0, 0, Materials.getGold()));

		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getObsidian());
		world.addObject(boundingBoxCube);

		return world;
	}

	public static World waterCubeTest() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(15, 15, 15), new Point(0, 0, 0), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(-10, -10, 15), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(0, 0, 0), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(0, 0, 10), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		GenericObject object = new WaterCube(0, 0, -10, 11, 11, 11, 0, 0, 0, Materials.getWaterMaterial(), 5, 20);
		world.addObject(object);
		world.addObject(new Cube(0, 0, -5, 3, 3, 3, 0, 0, 0, Materials.getGold()));

		//		Bounding box with plane so the floor is of another material
		GenericObject boundingBoxCube = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getObsidian());
		world.addObject(boundingBoxCube);

		return world;
	}

	public static World materialTest() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(15, 0, 3), new Point(0, 0, -3), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(0, 15, 15), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(0, 0, 0), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(0, 0, 10), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		GenericObject object = new Sphere(3, 0, 0, 0, Materials.getSilver());
		world.addObject(object);

		GenericObject boundingBoxCube = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getCyanRubber());
		GenericObject floor = new Square(0, 0, -3.01, 21, 21, 0, 0, 0, Materials.getWoodMaterial());
		world.addObject(boundingBoxCube);
		world.addObject(floor);

		return world;
	}

	public static World testSave() {
		Camera camera = new Camera();
		camera.setCameraLocation(new Point(15, 0, 3), new Point(0, 0, -3), new Vector(0, 0, 1));
		camera.setDistanceN(1000);
		World world = new World(camera, new CustomColor(1, 1, 1));
		PointLight pointLight = new PointLight(new Point(0, 15, 15), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight);
		PointLight pointLight2 = new PointLight(new Point(0, 0, 0), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight2);
		PointLight pointLight3 = new PointLight(new Point(0, 0, 10), new CustomColor(0.5, 0.5, 0.5));
		world.addLight(pointLight3);

		GenericObject object = new Sphere(3, 0, 0, 0, Materials.getGreenPlastic());
		world.addObject(object);

		GenericObject boundingBoxCube = new Cube(0, 0, 10, 20, 20, 20, 0, 0, 0, Materials.getCyanRubber());
		GenericObject floor = new Square(0, 0, -3.01, 21, 21, 0, 0, 0, Materials.getCyanPlastic());
		world.addObject(boundingBoxCube);
		world.addObject(floor);

		return world;
	}
}
