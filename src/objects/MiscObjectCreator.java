package objects;

import configuration.Configuration;
import misc.TransformationBuilder;
import render_related.Material;
import render_related.Materials;
import render_related.NoiseTexture;

public class MiscObjectCreator {
	public static GenericObject createTree() {
//		Christmas tree
		Material treeMaterial = Materials.getGreenPlastic();
		treeMaterial.setTexture(new NoiseTexture(1, true, 0.5,1));
		GenericObject ct1 = new TaperedCylinder(1, 0, 0, 0, 1, 1, 2, 0, 0, 0, Materials.getBrownMaterial());
		GenericObject ct2 = new TaperedCylinder(0.5, 0, 0, 2, 3, 3, 2, 0, 0, 0, treeMaterial);
		GenericObject ct3 = new TaperedCylinder(0.3, 0, 0, 4, 2, 2, 1.7, 0, 0, 0, treeMaterial);
		GenericObject ct4 = new TaperedCylinder(0.2, 0, 0, 5.7, 1, 1, 1.3, 0, 0, 0, treeMaterial);
		GenericObject ct5 = new TaperedCylinder(0, 0, 0, 7, 0.5, 0.5, 1, 0, 0, 0, treeMaterial);
		GenericObject b1 = new BooleanUnion(ct1, ct2);
		GenericObject b2 = new BooleanUnion(ct3, ct4);
		GenericObject b3 = new BooleanUnion(b1, b2);
		return new BooleanUnion(b3, ct5);
	}

	public static GenericObject createCutDownTree() {
		GenericObject cil = new TaperedCylinder(1, 0, 0, 0, 1, 1, 1, 0, 0, 0, Materials.getBrownMaterial());
		GenericObject cil1 = new TaperedCylinder(1, 0, 0, -0.5, 0.9, 0.9, 2, 0, 0, 0, Materials.getBrownMaterial());
		GenericObject bark = new BooleanDifference(cil, cil1);
		GenericObject nerves = new TaperedCylinder(1, 0, 0, 0, 0.9, 0.9, 1, 0, 0, 0, Materials.getWoodMaterial());
		return new BooleanUnion(bark, nerves);
	}

	public static GenericObject createBracelet() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		GenericObject cube = new Cube(0, 0, 0, 1, 1, 1, 45, 35.26, 0, Materials.getRuby());
		GenericObject cube1 = new Cube(0, 0, 0, 1, 1, 1, 45, 35.26, 90, Materials.getRuby());
		GenericObject cube2 = new Cube(0, 0, 0, 1, 1, 1, 45, 35.26, 180, Materials.getRuby());
		GenericObject cube3 = new Cube(0, 0, 0, 1, 1, 1, 45, 35.26, 270, Materials.getRuby());
		GenericObject d1 = new BooleanIntersection(cube, cube1);
		GenericObject d2 = new BooleanIntersection(cube3, cube2);
		GenericObject d3 = new BooleanIntersection(d1, d2);
		GenericObject cube4 = new Cube(0, 0, -1.5, 2, 2, 2, 0, 0, 0, Materials.getRuby());
		GenericObject diamond = new BooleanIntersection(d3, cube4);
		diamond.setTransformation(transformationBuilder.reset().scaling(0.7, 0.7, 0.7).translation(0, 0, 4.6).create());
		diamond.setInverseTransformation(transformationBuilder.reset().inverseTranslation(0, 0, 4.6).inverseScaling(0.7, 0.7, 0.7).create());
		GenericObject outerBraceletBand = new Sphere(Materials.getGold());
		outerBraceletBand.setTransformation(transformationBuilder.reset().scaling(1.5, 4, 4).create());
		outerBraceletBand.setInverseTransformation(transformationBuilder.reset().inverseScaling(1, 4, 4).create());
		GenericObject d4 = new BooleanUnion(outerBraceletBand, diamond);

		GenericObject cylinder = new TaperedCylinder(1, -2.5, 0, 0, 3.5, 3.5, 5, 0, 90, 0, Materials.getGold());
		GenericObject band = new BooleanDifference(d4, cylinder);
		GenericObject cube5 = new Cube(0, 0, -3.5, 1.5, 1.5, 1.5, 45, 0, 0, Materials.getGold());
		return new BooleanDifference(band, cube5);
	}

	public static GenericObject createGlass() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		GenericObject topOutside = new TaperedCylinder(0, 0, 0, 2.7, 1, 1, -1, 0, 0, 0, Materials.getGlassMaterial());
		GenericObject mid = new TaperedCylinder(1, 0, 0, 0, 0.08, 0.08, 2, 0, 0, 0, Materials.getGlassMaterial());
		GenericObject glassOutside = new BooleanUnion(mid, topOutside);
		GenericObject topInside = new TaperedCylinder(0, 0, 0, 2.75, 1, 1, -1, 0, 0, 0, Materials.getGlassMaterial());
		GenericObject carvedGlassWithoutBottem = new BooleanDifference(glassOutside, topInside);
		GenericObject bottom = new TaperedCylinder(0, 0, 0, 0, 0.7, 0.7, 0.2, 0, 0, 0, Materials.getGlassMaterial());
		return new BooleanUnion(bottom, carvedGlassWithoutBottem);
	}

	public static GenericObject createWaterForFilledGlass() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		GenericObject waterCube = new WaterCube(0, 0, 2.18, 1, 1, 0.45, 0, 0, 0, Materials.getWaterMaterial(), 0.2, 10);
		GenericObject topInside = new TaperedCylinder(0, 0, 0, 2.745, 1, 1, -1, 0, 0, 0, Materials.getGlassMaterial());
		return new BooleanIntersection(topInside, waterCube);
	}

	public static GenericObject createFishBowl() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		GenericObject outside = new Sphere(1, 0, 0, 0.8, Materials.getGlassMaterial());
		GenericObject bottomCut = new Cube(0, 0, -1, 1, 1, 1, 0, 0, 0, Materials.getGlassMaterial());
		GenericObject outsideFishBowl = new BooleanDifference(outside, bottomCut);
		GenericObject inside = new Sphere(0.95, 0, 0, 0.8, Materials.getGlassMaterial());
		GenericObject bottomInsideCut = new Cube(0, 0, -0.95, 1, 1, 1, 0, 0, 0, Materials.getGlassMaterial());
		GenericObject insideFishBowl = new BooleanDifference(inside, bottomInsideCut);
		GenericObject fishBowlWithNoOpening = new BooleanDifference(outsideFishBowl, insideFishBowl);
		GenericObject cone = new TaperedCylinder(0, 0, 0, 1.8, 1, 1, -1, 0, 0, 0, Materials.getGlassMaterial());
		GenericObject fishBowl = new BooleanDifference(fishBowlWithNoOpening, cone);
		return fishBowl;
	}

	public static GenericObject createWaterForFishBowl() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		GenericObject waterCube = new WaterCube(0, 0, 0.2, 1, 1, 1, 0, 0, 0, Materials.getWaterMaterial(), 0.4, 10);
		GenericObject inside = new Sphere(0.96, 0, 0, 0.8, Materials.getWaterMaterial());
		GenericObject bottomInsideCut = new Cube(0, 0, -0.96, 1, 1, 1, 0, 0, 0, Materials.getWaterMaterial());
		GenericObject boundingSphere = new BooleanDifference(inside, bottomInsideCut);
		return new BooleanIntersection(waterCube, boundingSphere);
	}

	public static GenericObject createTable() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		double height = 1;
		double heightTableTop = 0.05;
		GenericObject tabletop = new Cube(0, 0, height - heightTableTop, 1, 1, heightTableTop, 0, 0, 0,
				Materials.getWoodMaterial(false, transformationBuilder.reset().rotateX(87).rotateY(10).rotateZ(5).scaling(1.5, 1, 0).translation(0, -0.5, 0).create()));
		GenericObject tableLeg = new Cube(0, 0, height/2, 0.15, 0.15, height/2 - Configuration.ROUNDING_ERROR, 0, 0, 0,
				Materials.getWoodMaterial(false, transformationBuilder.reset().rotateX(45).rotateZ(35.26).create()));
		GenericObject tableBottom = new Cube(0, 0, heightTableTop, 0.6, 0.6, heightTableTop, 0, 0, 90,
				Materials.getWoodMaterial(false, transformationBuilder.reset().rotateX(87).rotateY(10).rotateZ(5).scaling(1.5, 1, 0).translation(0, -0.5, 0).create()));
		GenericObject tabletemp = new BooleanUnion(tableLeg, tabletop);

		return new BooleanUnion(tabletemp, tableBottom);
	}

	public static GenericObject createLamp() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		GenericObject top = new Sphere(Materials.getBlackPlastic());
		GenericObject topRemover = new Cube(0, 0, -1, 1, 1, 1, 0, 0, 0, Materials.getChrome());
		GenericObject lampCapWithoutLight = new BooleanDifference(top, topRemover);
		GenericObject lampLight = new Cube(0, 0, 0, 0.5, 0.5, 0.01, 0, 0, 0, Materials.getLightMaterial());
		GenericObject lampCap = new BooleanUnion(lampCapWithoutLight, lampLight);
		lampCap.setTransformation(transformationBuilder.reset().scaling(0.5, 1, 0.7).create());
		lampCap.setInverseTransformation(transformationBuilder.reset().inverseScaling(0.5, 1, 0.7).create());

		GenericObject bar1 = new TaperedCylinder(1, -0.25, -1.6, -4.924, 0.05, 0.05, 5, -10, 0, 0, Materials.getChrome());
		GenericObject bar2 = new TaperedCylinder(1, 0.25, -1.6, -4.924, 0.05, 0.05, 5, -10, 0, 0, Materials.getChrome());
		GenericObject bars = new BooleanUnion(bar1, bar2);

		GenericObject barsWithCap = new BooleanUnion(bars, lampCap);
		barsWithCap.setTransformation(transformationBuilder.reset().translation(0, 1.5,5).create());
		barsWithCap.setInverseTransformation(transformationBuilder.reset().inverseTranslation(0, 1.5,5).create());

		GenericObject bottomSphere = new Sphere(Materials.getBlackPlastic());
		GenericObject bottomRemover = new Cube(0, 0, -1, 1, 1, 1, 0, 0, 0, Materials.getBlackPlastic());
		GenericObject bottom = new BooleanDifference(bottomSphere, bottomRemover);
		bottom.setTransformation(transformationBuilder.reset().scaling(0.7, 1.1, 1).translation(0, 0.6, 0).create());
		bottom.setInverseTransformation(transformationBuilder.reset().inverseTranslation(0, 0.6, 0).inverseScaling(0.7, 1.1, 1).create());

		return new BooleanUnion(bottom, barsWithCap);
	}

	public static GenericObject createShip() {
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		GenericObject sphere1 = new Sphere(1, 0.5, 0, 0, Materials.getWoodMaterial(
				false,
				transformationBuilder.reset().scaling(0.5, 0.7, 1).translation(-1, 0, 0).create()
		));
		GenericObject sphere2 = new Sphere(1, -0.5, 0, 0, Materials.getWoodMaterial(
				false,
				transformationBuilder.reset().scaling(0.5, 0.7, 1).translation(1, 0, 0).create()
		));
		GenericObject sphereComb = new BooleanIntersection(sphere1, sphere2);
		GenericObject cutTop = new TaperedCylinder(1, -1, 0, 1.25, 1.4, 1.4, 2, 0, 90, 0, Materials.getWoodMaterial(
				false,
				transformationBuilder.reset().scaling(0.7, 0.5, 1).create()
		));

		GenericObject base = new BooleanDifference(sphereComb, cutTop);
		base.setTransformation(transformationBuilder.reset().translation(0, 0, 0.5).create());
		base.setInverseTransformation(transformationBuilder.reset().inverseTranslation(0, 0, 0.5).create());

		GenericObject pole = new TaperedCylinder(1, 0, 0, 0.3, 0.03, 0.03, 1.4, 0, 0, 0, Materials.getWoodMaterial());
		GenericObject baseWithPole = new BooleanUnion(pole, base);

		GenericObject cloth = new Square(0, 0, 1, 1, 1, 0, 90, 0, Materials.getRedPlastic());
		GenericObject clothDiff = new Cube(0, 1, 2, 1.415, 1.415, 1.415, 45, 0, 0, Materials.getRedPlastic());

		GenericObject flag = new BooleanDifference(cloth, clothDiff);
		flag.setTransformation(transformationBuilder.reset().scaling(0.5, 0.5, 0.5).translation(0, 0.5, 0.7).create());
		flag.setInverseTransformation(transformationBuilder.reset().inverseTranslation(0, 0.5, 0.7).inverseScaling(0.5, 0.5, 0.5).create());

		GenericObject ship = new BooleanUnion(flag, baseWithPole);
		return ship;
	}
}
