package render_related;

import configuration.Configuration;
import misc.*;
import misc.Point;
import misc.Vector;
import objects.GenericObject;
import objects.Water;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Renderer {

	public Renderer() {
	}

	public void renderFrame(World world) { // todo optimize such that no pixel will be rendered twice
		// todo optimize such that, as soon as delta h or delta w = 1 this for will nog be ran.
		PixelPlotter pixelPlotter = new PixelPlotter();
		HitPointInfo hitPointInfo;
		int deltaW = Configuration.SCREEN_WIDTH;
		int deltaH = Configuration.SCREEN_HEIGHT;

		//create a BST object
		Map<Integer, Color> colors = new HashMap<Integer, Color>(Configuration.SCREEN_WIDTH * Configuration.SCREEN_HEIGHT, 1);

		while (deltaW > 1 || deltaH > 1) {
			deltaH = Integer.max(1, deltaH / 2); // an integer will always round toward 0.
			deltaW = Integer.max(1, deltaW / 2); // an integer will always round toward 0.
			for (int c = 0; c < Configuration.SCREEN_WIDTH; c += deltaW) {
				for (int r = 0; r < Configuration.SCREEN_HEIGHT; r += deltaH) {
					if(!colors.containsKey(calculateKey(c, r))) {
						Ray ray = Ray.createRay(world, c, r);
						hitPointInfo = world.calculateBestHitpoint(ray);
						Color color = colorPixel(world, ray, hitPointInfo);
						addPixelToRangeOfCanvas(pixelPlotter, color, r, r + deltaH, c, c + deltaW, colors);
						colors.put(calculateKey(c, r), color);
					}
				}
			}
			pixelPlotter.renderFrame();
		}
		System.out.println("Finished rendering");
	}

	private int calculateKey(int column, int row){
		return column + Configuration.SCREEN_WIDTH * row;
	}

	public int transformYCoordinate(int y) {
		return Configuration.SCREEN_HEIGHT - y - 1;
	}

	public Color colorPixel(World world, Ray ray, HitPointInfo hitPointInfo) {
		return calculateShadeAndHit(world, ray, hitPointInfo).checkBounds().toJavaColor();
	}

	/**
	 * This method will color all pixels within range width = [w1, w2[ and hight = [h1, h2[ todo check '['
	 * with the given pixel value.
	 *
	 * @param pixelPlotter the pixelPlotter where the Pixels will be colored onto
	 * @param color        the color of the pixel
	 * @param h1           lower bound of hight range
	 * @param h2           higher bound of hight range
	 * @param w1           lower bound of width range
	 * @param w2           higher bound of width range
	 */
	public void addPixelToRangeOfCanvas(PixelPlotter pixelPlotter, Color color, int h1, int h2, int w1, int w2,
										Map<Integer, Color> colors) {
		for (int column = w1; column < w2; column++) {
			for (int row = h1; row < h2; row++) { // todo check if < must become <=
				if (column < Configuration.SCREEN_WIDTH && row < Configuration.SCREEN_HEIGHT) {
					if(!colors.containsKey(calculateKey(column, row))) { // make sure that the correct pixels are not overwritten.
						pixelPlotter.addPixelToCanvas(
								new Pixel(column, transformYCoordinate(row), color)
						);
					}
				}
			}
		}
	}

	public CustomColor calculateShadeAndHit(World world, Ray ray, HitPointInfo hitPointInfo) {
		CustomColor customColor;
		if (hitPointInfo.isHit()) {
			customColor = shade(world, ray, hitPointInfo);
		} else {
			customColor = new CustomColor();
		}
		return customColor;
	}

	public CustomColor shade(World world, Ray ray, HitPointInfo hitPointInfo) {
		Vector v = Operations.pointSubstraction(world.getCamera().getEye(), hitPointInfo.getHitPoint()).normalize();
		CustomColor color = new CustomColor();

		Point texturePoint = new Point(hitPointInfo.getHitPoint());
		if(!hitPointInfo.getObject().getMaterial().getTexture().isWorldTexture()) {
			texturePoint = Operations.pointTransformation(hitPointInfo.getObject().getInverseTransformation(), texturePoint);
		}


		color = color.addColor(hitPointInfo.getObject().getMaterial().getEmission()); // add emmision
		if (!ray.getInsideObjectList().contains(hitPointInfo.getObject())) { // if inside object (refraction) then these should not be calculated.
			CustomColor ambientColor = CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getAmbient(), world.getAmbient());
			ambientColor.scalarProduct(hitPointInfo.getObject().getMaterial().getTexture().texture(texturePoint)); // todo controleer
			color = color.addColor(ambientColor); // add ambient colors
		}
		hitPointInfo.setNormal(hitPointInfo.getNormal().normalize()); // normalise the normal

		// check if the ray is inside the object, if so, reverse normals.
		if ((Operations.dotProduct(Operations.scalarVectorProduct(-1, ray.getDir()), hitPointInfo.getNormal()) < Configuration.ROUNDING_ERROR) && hitPointInfo.getObject().getClass() != Water.class) { // do not invert the normals for water.
			hitPointInfo.setNormal(Operations.scalarVectorProduct(-1, hitPointInfo.getNormal()));
		}

		// diffuse and specular components
		for (PointLight light : world.getLights()) {
			double inLight = inLight(world, light, hitPointInfo);
			if (inLight > Configuration.MIN_IN_LIGHT && !ray.getInsideObjectList().contains(hitPointInfo.getObject())) { // if not shadow //todo check
				Vector s = Operations.pointSubstraction(light.getLocation(), hitPointInfo.getHitPoint());
				if (s.norm() > Configuration.ROUNDING_ERROR) {
					s = s.normalize();
					double mDotS = Operations.dotProduct(s, hitPointInfo.getNormal()); // The lambert term
					if (mDotS > 0.0) { // Hitpoint is turned towards the light.
						CustomColor diffuseColor = CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getDiffuse(), light.getColor()).scalarProduct(mDotS);
						diffuseColor.scalarProduct(hitPointInfo.getObject().getMaterial().getTexture().texture(texturePoint)); // todo controleer
						color = color.addColor(diffuseColor.scalarProduct(inLight));
					}
					Vector h = Operations.vectorSum(v, s);
					if (h.norm() > Configuration.ROUNDING_ERROR) {
						h = h.normalize(); // The halfway vector
						double mDoth = Operations.dotProduct(h, hitPointInfo.getNormal());
						if (mDoth > 0.0) { // specular contribution
							double phong = Math.pow(mDoth, hitPointInfo.getObject().getMaterial().getSpecularExponent());
							CustomColor specColor = CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getSpecular(), light.getColor()).scalarProduct(phong);
							color = color.addColor(specColor.scalarProduct(inLight));
						}
					}
				}
			}
		}


		// Reflection and Transmission
		if (ray.getRecurseLevel() < Configuration.MAX_RECURSE_LEVEL) {
			// refraction
			boolean totalInternalReflection = false;
			if (hitPointInfo.getObject().getMaterial().getRefractionCoefficient() >= Configuration.MIN_TRANSPARENTNESS) { // Design policy 2 is chosen (book: Computer Graphics using OpenGL p. 680)
				double c1, c2;
				// Search for the highest priority object in the insideObjectList.
				GenericObject highestPriorityObject = ray.getHighestPriorityObject();
				if (hitPointInfo.isEntering()) {
					if (highestPriorityObject == null) { // If the ray is in no objects
						c1 = 1;
						c2 = hitPointInfo.getObject().getMaterial().getRelativeDensity();
					} else {
						// add the correct value to c1 and c2.
						c1 = highestPriorityObject.getMaterial().getRelativeDensity();
						if (hitPointInfo.getObject().getPriority() < highestPriorityObject.getPriority()) {
							c2 = hitPointInfo.getObject().getMaterial().getRelativeDensity();
						} else {
							c2 = c1;
						}
					}
					ray.getInsideObjectList().add(hitPointInfo.getObject());
				} else {
					if (highestPriorityObject == null) { // If the ray is in no objects, this can normally not occur but is a safety measure
						c1 = hitPointInfo.getObject().getMaterial().getRelativeDensity();
						c2 = 1;
					} else {
						// add the correct value to c1 and c2.
						c1 = highestPriorityObject.getMaterial().getRelativeDensity();
						ray.getInsideObjectList().remove(highestPriorityObject);
						GenericObject secondPriorityObject = ray.getHighestPriorityObject();
						if (secondPriorityObject == null) {
							c2 = 1;
						} else {
							c2 = secondPriorityObject.getMaterial().getRelativeDensity();
						}
					}
				}
				double ratio = c2 / c1;
				Vector inverseRayDir = Operations.scalarVectorProduct(-1, ray.getDir());
				double mDotDir = Operations.dotProduct(hitPointInfo.getNormal(), inverseRayDir);

				double cosineThetaSquare = 1 - Math.pow(ratio, 2) * (1 - Math.pow(mDotDir, 2));
				if (cosineThetaSquare > Configuration.ROUNDING_ERROR) {// if not a total internal reflection and not at critical angle
					double cosineTheta = Math.sqrt(cosineThetaSquare);
					double coefficient = ratio * mDotDir - cosineTheta;
					Vector refractionDir = Operations.vectorSum(
							Operations.scalarVectorProduct(ratio, ray.getDir()),
							Operations.scalarVectorProduct(coefficient, hitPointInfo.getNormal())
					);

					Ray refractedRay = new Ray(hitPointInfo.getHitPoint(), refractionDir.normalize(), ray.getRecurseLevel() + 1, new ArrayList<>(ray.getInsideObjectList()));
					CustomColor refractionColor = calculateShadeAndHit(world, refractedRay, world.calculateBestHitpoint(refractedRay)).scalarProduct(hitPointInfo.getObject().getMaterial().getRefractionCoefficient());
					color = color.addColor(refractionColor);
				} else { // if tot. internal reflection takes place.
					totalInternalReflection = true; // todo at critical angle itself, don't do this (ask first if ok)
					if (hitPointInfo.isEntering()) {
						ray.getInsideObjectList().remove(hitPointInfo.getObject());
					} else {
						if (highestPriorityObject != null) {
							ray.getInsideObjectList().add(highestPriorityObject);
						}
					}
				}
			}

			// Reflection
			if (hitPointInfo.getObject().getMaterial().getReflectionCoefficient() >= Configuration.MIN_SHININESS || totalInternalReflection) {
				Vector reflectionDir = Operations.vectorDifference(
						ray.getDir(),
						Operations.scalarVectorProduct(2 * Operations.dotProduct(ray.getDir(), hitPointInfo.getNormal()), hitPointInfo.getNormal())
				);
				Ray reflectedRay = new Ray(hitPointInfo.getHitPoint(), reflectionDir.normalize(), ray.getRecurseLevel() + 1, new ArrayList<>(ray.getInsideObjectList()));
				CustomColor reflectionColor;
				if (totalInternalReflection) { // todo check if everything is correct.
					reflectionColor = calculateShadeAndHit(world, reflectedRay, world.calculateBestHitpoint(reflectedRay)).scalarProduct(hitPointInfo.getObject().getMaterial().getRefractionCoefficient());
				} else {
					reflectionColor = calculateShadeAndHit(world, reflectedRay, world.calculateBestHitpoint(reflectedRay)).scalarProduct(hitPointInfo.getObject().getMaterial().getReflectionCoefficient());
				}
				color = color.addColor(reflectionColor);
			}
		}
		return color;
	}

	public double inLight(World world, PointLight light, HitPointInfo hitPointInfo) {
		Ray ray = new Ray(hitPointInfo.getHitPoint(), light.getLocation()); // todo change the inshadow boolean to a value between [0, 1].
		double inLight = 1;

		List<HitPointInfo> bestHitPoints = world.calculateAllHitPoints(ray, hitPointInfo.getObject()); // todo make sure to have all objects between t = [0, 1] and multiply the refractiveness.
		for (HitPointInfo bestHitPoint : bestHitPoints) {
			if (bestHitPoint.getHitTime() >= Configuration.ROUNDING_ERROR && bestHitPoint.getHitTime() <= (1 - Configuration.ROUNDING_ERROR) && bestHitPoint.isEntering()) {
				inLight = inLight * bestHitPoint.getObject().getMaterial().getRefractionCoefficient(); // The more refractive, the less shadows there are behind this object. // todo make sure that each object's shadow is only added once, not twice!
			}
		}
		return inLight;
	}
}
