package render_related;

import configuration.Configuration;
import generic_objects.GenericObject;
import generic_objects.Sphere;
import misc.*;

import java.awt.*;

public class Renderer {

	public Renderer() {
	}

	public void renderFrame(World world) {
		PixelPlotter pixelPlotter = new PixelPlotter();
		HitPointInfo hitPointInfo;
		for (int c = 0; c < Configuration.SCREEN_WIDTH; c++) {
			for (int r = 0; r < Configuration.SCREEN_HEIGHT; r++) {
				Ray ray = Ray.createRay(world, c, r);
				hitPointInfo = world.calculateBestHitpoint(ray);
				pixelPlotter.addPixelToCanvas(
						new Pixel(c, transformYCoordinate(r), colorPixel(world, ray, hitPointInfo))
				);
			}
		}
		pixelPlotter.renderFrame();
	}

	public int transformYCoordinate(int y) {
		return Configuration.SCREEN_HEIGHT - y - 1;
	}

	public Color colorPixel(World world, Ray ray, HitPointInfo hitPointInfo) {
		return calculateShadeAndHit(world, ray, hitPointInfo).checkBounds().toJavaColor();
	}

	public CustomColor calculateShadeAndHit(World world, Ray ray, HitPointInfo hitPointInfo){
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
		color = color.addColor(hitPointInfo.getObject().getMaterial().getEmission()); // add emmision
		if(ray.getInsideObject() == null) { // if inside an object (refraction) then these should not be calculated.
			color = color.addColor( // add ambient colors
					CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getAmbient(), world.getAmbient())
			);
		}
		hitPointInfo.setNormal(hitPointInfo.getNormal().normalize()); // normalise the normal

		// check if the ray is inside the object, if so, reverse normals.
		if (Operations.dotProduct(Operations.scalarVectorProduct(-1, ray.getDir()), hitPointInfo.getNormal()) < Configuration.ROUNDING_ERROR) {
			hitPointInfo.setNormal(Operations.scalarVectorProduct(-1, hitPointInfo.getNormal()));
		}

		// diffuse and specular components
		for (PointLight light : world.getLights()) {
			if (!inShadow(world, light, hitPointInfo) && ray.getInsideObject() == null) { // if in shadow or if inside an object (refraction) then these should not be calculated.
				Vector s = Operations.pointSubstraction(light.getLocation(), hitPointInfo.getHitPoint()).normalize();
				double mDotS = Operations.dotProduct(s, hitPointInfo.getNormal()); // The lambert term
				if (mDotS > 0.0) { // Hitpoint is turned towards the light.
					CustomColor diffuseColor = CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getDiffuse(), light.getColor()).scalarProduct(mDotS);
					color = color.addColor(diffuseColor);
				}
				Vector h = Operations.vectorSum(v, s).normalize(); // The halfway vector
				double mDoth = Operations.dotProduct(h, hitPointInfo.getNormal());
				if (mDoth > 0.0) { // specular contribution
					double phong = Math.pow(mDoth, hitPointInfo.getObject().getMaterial().getSpecularExponent());
					CustomColor specColor = CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getSpecular(), light.getColor()).scalarProduct(phong);
					color = color.addColor(specColor);
				}
			}
		}


		// Reflection and Transmission
		if (ray.getRecurseLevel() < Configuration.MAX_RECURSE_LEVEL) {
			// refraction
			boolean totalInternalReflection = false;
			if(hitPointInfo.getObject().getMaterial().getRefractionCoefficient() >= Configuration.MIN_TRANSPARENTNESS) { // Design policy 1 is chosen (book: Computer Graphics using OpenGL p. 680)
				double c1, c2;
				if (ray.getInsideObject() == null){
					c1 = 1;
					c2 = hitPointInfo.getObject().getMaterial().getRelativeDensity();
				}
				else {
					c1 = hitPointInfo.getObject().getMaterial().getRelativeDensity();
					c2 = 1;
				}
				double cosineThetaSquare = 1 - Math.pow(c2/c1, 2) * (1 - Math.pow(Operations.dotProduct(hitPointInfo.getNormal(), ray.getDir()), 2));
				if (cosineThetaSquare > Configuration.ROUNDING_ERROR) {// if not a total internal reflection and not at critical angle
					double cosineTheta = Math.sqrt(cosineThetaSquare); // todo if in square is negative ==> tot internal reflection.
					double coefficient = (c2 / c1) * Operations.dotProduct(hitPointInfo.getNormal(), ray.getDir()) - cosineTheta;
					Vector refractionDir = Operations.vectorSum(
							Operations.scalarVectorProduct(c2 / c1, ray.getDir()),
							Operations.scalarVectorProduct(coefficient, hitPointInfo.getNormal())
					);
//					if (!(Math.abs(Operations.dotProduct(refractionDir, hitPointInfo.getNormal())) < Configuration.ROUNDING_ERROR) && Operations.dotProduct(refractionDir, hitPointInfo.getNormal()) < Configuration.ROUNDING_ERROR) { // if the ray was at critical angle or beyond do not calculate the refraction! // todo verwijder aangezien dit gedaan wordt met behulp van de costheta
						Ray refractedRay = new Ray(hitPointInfo.getHitPoint(), refractionDir.normalize(), ray.getRecurseLevel() + 1);
						if (ray.getInsideObject() == null) {
							refractedRay.setInsideObject(hitPointInfo.getObject());
						} else if (ray.getInsideObject() != hitPointInfo.getObject()) { // het heeft een ander object eerst geraakt
							refractedRay.setInsideObject(hitPointInfo.getObject());
						}// else the inside object is already null
						CustomColor refractionColor = calculateShadeAndHit(world, refractedRay, world.calculateBestHitpoint(refractedRay)).scalarProduct(hitPointInfo.getObject().getMaterial().getRefractionCoefficient());
						color = color.addColor(refractionColor);
//					} // todo remove together with if above in comments
				} else if (ray.getInsideObject() != null){ // if the ray is inside an object and tot. internal reflection takes place.
					totalInternalReflection = true; // todo at critical angle itself, don't do this (ask first if ok)
				}
			}

			// Reflection
			if(hitPointInfo.getObject().getMaterial().getReflectionCoefficient() >= Configuration.MIN_SHININESS || totalInternalReflection) {
				Vector reflectionDir = Operations.vectorDifference(
						ray.getDir(),
						Operations.scalarVectorProduct(2 * Operations.dotProduct(ray.getDir(), hitPointInfo.getNormal()), hitPointInfo.getNormal())
				);
				Ray reflectedRay = new Ray(hitPointInfo.getHitPoint(), reflectionDir.normalize(), ray.getRecurseLevel() + 1);
				CustomColor reflectionColor;
				if(totalInternalReflection) { // todo check if everything is correct.
					reflectedRay.setInsideObject(ray.getInsideObject());
					reflectionColor= calculateShadeAndHit(world, reflectedRay, world.calculateBestHitpoint(reflectedRay)).scalarProduct(hitPointInfo.getObject().getMaterial().getRefractionCoefficient());
				} else {
					reflectionColor = calculateShadeAndHit(world, reflectedRay, world.calculateBestHitpoint(reflectedRay)).scalarProduct(hitPointInfo.getObject().getMaterial().getReflectionCoefficient());
				}
				color = color.addColor(reflectionColor);
			}
		}
		return color;
	}

	public boolean inShadow(World world, PointLight light, HitPointInfo hitPointInfo) {
		Ray ray = new Ray(hitPointInfo.getHitPoint(), light.getLocation()); // todo change the inshadow boolean to a value between [0, 1].
		boolean inShadow = false;

		HitPointInfo bestHitPoint = world.calculateBestHitpoint(ray, hitPointInfo.getObject());
		if (bestHitPoint.getHitTime() >= Configuration.ROUNDING_ERROR && bestHitPoint.getHitTime() <= (1-Configuration.ROUNDING_ERROR)) {
			if(bestHitPoint.getObject().getMaterial().getRefractionCoefficient() < Configuration.MIN_TRANSPARENTNESS) { // todo vraag of ok
				inShadow = true;
			}
		}
		return inShadow;
	}

}
