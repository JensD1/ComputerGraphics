package render_related;

import misc.CustomColor;
import misc.Matrix;

public class Materials {
	// self created materials
	private static final Material redMaterial = new Material(new CustomColor(0.8, 0.2, 0.1), new CustomColor(0.5, 0.8, 0.5),
			100, new CustomColor(), new CustomColor(0.25, 0.15, 0.15), 0, 0, 1);
	private static final Material blueMaterial = new Material(new CustomColor(0, 0.7, 0.8), new CustomColor(0.8, 0.5, 0.5),
			100, new CustomColor(), new CustomColor(0.1, 0.1, 0.1), 0, 0, 1);
	private static final Material brownMaterial = new Material(new CustomColor(0.5450, 0.27058, 0.07451), new CustomColor(0.7, 0.6, 0.5),
			0.25 * 128, new CustomColor(), new CustomColor(0.05, 0.0, 0.0), 0.1, 0, 1);
	private static final Material yellowMaterial = new Material(new CustomColor(0.8, 0.8, 0), new CustomColor(0.6, 0.6, 0.3),
			100, new CustomColor(), new CustomColor(0.15, 0.15, 0.05), 0.6, 0, 1);
	private static final Material glassMaterial = new Material(new CustomColor(0, 0, 0), new CustomColor(0, 0, 0),
			100, new CustomColor(), new CustomColor(0, 0, 0), 0.1, 0.9, 0.55); // 0.04
	private static final Material mirrorMaterial = new Material(new CustomColor(0, 0, 0), new CustomColor(0.15, 0.15, 0.15),
			100, new CustomColor(), new CustomColor(0.1, 0.1, 0.1), 0.9, 0, 1);
	private static final Material testMaterial = new Material(new CustomColor(0.5, 0.5, 0.5), new CustomColor(0.5, 0.5, 0.5),
			100, new CustomColor(), new CustomColor(0.1, 0.1, 0.1), 0, 0, 1);
	private static final Material waterMaterial = new Material(new CustomColor(0.05, 0.1, 0.4), new CustomColor(0.5, 0.5, 0.6), //new CustomColor(0.08, 0.05, 0.05),
			30, new CustomColor(), new CustomColor(0.01, 0.01, 0.01), 0.1, 0.7, 0.75);
	private static final Material lightMaterial = new Material(new CustomColor(0, 0, 0), new CustomColor(0, 0, 0), //new CustomColor(0.08, 0.05, 0.05),
			30, new CustomColor(1, 0.8, 0.5), new CustomColor(0, 0, 0), 0, 0, 1);
	private static final Material cloudMaterial = new Material(new CustomColor(), new CustomColor(),
			0, new CustomColor(), new CustomColor(0.042, 0.91, 0.91), 0, 0, 1,
			new NoiseTexture(0.1, true, 1, 20));
	private static final Material groundMaterial = new Material(new CustomColor(), new CustomColor(),
			0, new CustomColor(), new CustomColor(0.5450, 0.27058, 0.07451), 0, 0, 1,
			new NoiseTexture(0.1, true, 0.2, 1.3));

	// Materials from http://devernay.free.fr/cours/opengl/materials.html
	private static final Material emerald = new Material(new CustomColor(0.07568, 0.61424, 0.07568), new CustomColor(0.633, 0.727811, 0.633),
			0.6 * 128, new CustomColor(), new CustomColor(0.0215, 0.1745, 0.0215), 0.1, 0.2, 0.5);
	private static final Material jade = new Material(new CustomColor(0.54, 0.89, 0.63), new CustomColor(0.316228, 0.316228, 0.316228),
			0.1 * 128, new CustomColor(), new CustomColor(0.135, 0.2225, 0.1575), 0.05, 0, 1);
	private static final Material obsidian = new Material(new CustomColor(0.18275, 0.17, 0.22525), new CustomColor(0.332741, 0.328634, 0.346435),
			0.3 * 128, new CustomColor(), new CustomColor(0.05375, 0.05, 0.06625), 0.05, 0, 1);
	private static final Material pearl = new Material(new CustomColor(1, 0.829, 0.829), new CustomColor(0.296648, 0.296648, 0.296648),
			0.088 * 128, new CustomColor(), new CustomColor(0.25, 0.20725, 0.20725), 0.05, 0, 1);
	private static final Material ruby = new Material(new CustomColor(0.61424, 0.04136, 0.04136), new CustomColor(0.727811, 0.626959, 0.626959),
			0.6 * 128, new CustomColor(), new CustomColor(0.1745, 0.01175, 0.01175), 0.1, 0.25, 0.5);
	private static final Material turquoise = new Material(new CustomColor(0.396, 0.74151, 0.69102), new CustomColor(0.297254, 0.30829, 0.306678),
			0.1 * 128, new CustomColor(), new CustomColor(0.1, 0.18725, 0.1745), 0, 0, 1);
	private static final Material brass = new Material(new CustomColor(0.780392, 0.568627, 0.113725), new CustomColor(0.992157, 0.941176, 0.807843),
			0.21794872 * 128, new CustomColor(), new CustomColor(0.329412, 0.223529, 0.027451), 0.1, 0, 1);
	private static final Material bronze = new Material(new CustomColor(0.714, 0.4284, 0.18144), new CustomColor(0.393548, 0.271906, 0.166721),
			0.2 * 128, new CustomColor(), new CustomColor(0.2125, 0.1275, 0.054), 0.05, 0, 1);
	private static final Material chrome = new Material(new CustomColor(0.4, 0.4, 0.4), new CustomColor(0.774597, 0.774597, 0.774597),
			0.6 * 128, new CustomColor(), new CustomColor(0.25, 0.25, 0.25), 0.2, 0, 1);
	private static final Material copper = new Material(new CustomColor(0.7038, 0.27048, 0.0828), new CustomColor(0.256777, 0.137622, 0.086014),
			0.1 * 128, new CustomColor(), new CustomColor(0.19125, 0.0735, 0.0225), 0, 0, 1);
	private static final Material gold = new Material(new CustomColor(0.75164, 0.60648, 0.22648), new CustomColor(0.628281, 0.555802, 0.366065),
			0.4 * 128, new CustomColor(), new CustomColor(0.24725, 0.1995, 0.0745), 0.15, 0, 1);
	private static final Material silver = new Material(new CustomColor(0.50754, 0.50754, 0.50754), new CustomColor(0.508273, 0.508273, 0.508273),
			0.4 * 128, new CustomColor(), new CustomColor(0.19225, 0.19225, 0.19225), 0.15, 0, 1);
	private static final Material blackPlastic = new Material(new CustomColor(0.01, 0.01, 0.01), new CustomColor(0.50, 0.50, 0.50),
			0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0), 0, 0, 1);
	private static final Material cyanPlastic = new Material(new CustomColor(0.0, 0.50980392, 0.50980392), new CustomColor(0.50196078, 0.50196078, 0.50196078),
			0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.1, 0.06), 0, 0, 1);
	private static final Material greenPlastic = new Material(new CustomColor(0.1, 0.35, 0.1), new CustomColor(0.45, 0.55, 0.45),
			0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0), 0, 0, 1);
	private static final Material redPlastic = new Material(new CustomColor(0.5, 0.0, 0.0), new CustomColor(0.7, 0.6, 0.6),
			0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0), 0, 0, 1);
	private static final Material whitePlastic = new Material(new CustomColor(0.55, 0.55, 0.55), new CustomColor(0.70, 0.70, 0.70),
			0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0), 0, 0, 1);
	private static final Material yellowPlastic = new Material(new CustomColor(0.5, 0.5, 0.0), new CustomColor(0.60, 0.60, 0.50),
			0.25 * 128, new CustomColor(), new CustomColor(0.0, 0.0, 0.0), 0, 0, 1);
	private static final Material blackRubber = new Material(new CustomColor(0.01, 0.01, 0.01), new CustomColor(0.4, 0.4, 0.4),
			0.078125 * 128, new CustomColor(), new CustomColor(0.02, 0.02, 0.02), 0, 0, 1);
	private static final Material cyanRubber = new Material(new CustomColor(0.4, 0.5, 0.5), new CustomColor(0.04, 0.7, 0.7),
			0.078125 * 128, new CustomColor(), new CustomColor(0.0, 0.05, 0.05), 0, 0, 1);
	private static final Material greenRubber = new Material(new CustomColor(0.4, 0.5, 0.4), new CustomColor(0.04, 0.7, 0.04),
			0.078125 * 128, new CustomColor(), new CustomColor(0.0, 0.05, 0.0), 0, 0, 1);
	private static final Material redRubber = new Material(new CustomColor(0.5, 0.4, 0.4), new CustomColor(0.7, 0.04, 0.04),
			0.078125 * 128, new CustomColor(), new CustomColor(0.05, 0.0, 0.0), 0, 0, 1);
	private static final Material whiteRubber = new Material(new CustomColor(0.5, 0.5, 0.5), new CustomColor(0.7, 0.7, 0.7),
			0.078125 * 128, new CustomColor(), new CustomColor(0.05, 0.05, 0.05), 0, 0, 1);
	private static final Material yellowRubber = new Material(new CustomColor(0.5, 0.5, 0.4), new CustomColor(0.7, 0.7, 0.04),
			0.078125 * 128, new CustomColor(), new CustomColor(0.05, 0.05, 0.0), 0, 0, 1);
	private static final Material woodMaterial = new Material(new CustomColor(0.365234375, 0.251953125, 0.126953125), new CustomColor(0.1, 0.1, 0.1),
			200, new CustomColor(), new CustomColor(0.187, 0.129, 0.065), 0, 0, 1,
			new WoodTexture(0.8, 1, 0.01, 15, 0.3, 10, 10, 7));

	public static Material getRedMaterial() {
		return new Material(redMaterial);
	}

	public static Material getBlueMaterial() {
		return new Material(blueMaterial);
	}

	public static Material getBrownMaterial() {
		return new Material(brownMaterial);
	}

	public static Material getYellowMaterial() {
		return new Material(yellowMaterial);
	}

	public static Material getGlassMaterial() {
		return new Material(glassMaterial);
	}

	public static Material getMirrorMaterial() {
		return new Material(mirrorMaterial);
	}

	public static Material getTestMaterial() {
		return new Material(testMaterial);
	}

	public static Material getWaterMaterial() {
		return new Material(waterMaterial);
	}

	public static Material getEmerald() {
		return new Material(emerald);
	}

	public static Material getJade() {
		return new Material(jade);
	}

	public static Material getObsidian() {
		return new Material(obsidian);
	}

	public static Material getPearl() {
		return new Material(pearl);
	}

	public static Material getRuby() {
		return new Material(ruby);
	}

	public static Material getTurquoise() {
		return new Material(turquoise);
	}

	public static Material getBrass() {
		return new Material(brass);
	}

	public static Material getBronze() {
		return new Material(bronze);
	}

	public static Material getChrome() {
		return new Material(chrome);
	}

	public static Material getCopper() {
		return new Material(copper);
	}

	public static Material getGold() {
		return new Material(gold);
	}

	public static Material getSilver() {
		return new Material(silver);
	}

	public static Material getBlackPlastic() {
		return new Material(blackPlastic);
	}

	public static Material getCyanPlastic() {
		return new Material(cyanPlastic);
	}

	public static Material getGreenPlastic() {
		return new Material(greenPlastic);
	}

	public static Material getRedPlastic() {
		return new Material(redPlastic);
	}

	public static Material getWhitePlastic() {
		return new Material(whitePlastic);
	}

	public static Material getYellowPlastic() {
		return new Material(yellowPlastic);
	}

	public static Material getBlackRubber() {
		return new Material(blackRubber);
	}

	public static Material getCyanRubber() {
		return new Material(cyanRubber);
	}

	public static Material getGreenRubber() {
		return new Material(greenRubber);
	}

	public static Material getRedRubber() {
		return new Material(redRubber);
	}

	public static Material getWhiteRubber() {
		return new Material(whiteRubber);
	}

	public static Material getYellowRubber() {
		return new Material(yellowRubber);
	}

	public static Material getWoodMaterial() {
		return new Material(woodMaterial);
	}

	public static Material getWoodMaterial(boolean isworldTexture, Matrix transformation) {
		return new Material(
				new CustomColor(0.365234375, 0.251953125, 0.126953125),
				new CustomColor(0.1, 0.1, 0.1),
				200,
				new CustomColor(), new CustomColor(0.187, 0.129, 0.065),
				0, 0, 1,
				new WoodTexture(0.8, 1, 0.01, 15, 0.3,
						10, 10, 7, isworldTexture, transformation));
	}

	public static Material getLightMaterial() {
		return new Material(lightMaterial);
	}

	public static Material getCloudMaterial() {
		return new Material(cloudMaterial);
	}

	public static Material getGroundMaterial() {
		return new Material(groundMaterial);
	}
}
