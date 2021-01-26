package objects;

import configuration.Configuration;
import misc.*;
import render_related.Material;

import java.util.ArrayList;
import java.util.List;

public class Water2 extends GenericObject {

	private double amplitude;
	private NoiseClass noiseClassX;
	private NoiseClass noiseClassY;
	double scale;

	public Water2() {
		super();
		this.amplitude = 1;
		this.noiseClassX = new NoiseClass();
		this.noiseClassY = new NoiseClass();
		this.scale = 1;
	}

	public Water2(Material material) {
		super(material);
		this.amplitude = 1;
		this.noiseClassX = new NoiseClass();
		this.noiseClassY = new NoiseClass();
		this.scale = 1;
	}

	public Water2(double x, double y, double z,
				  double scaleX, double scaleY,
				  double rotateX, double rotateY, double rotateZ,
				  Material material) {
		super(x, y, z, scaleX, scaleY, 1, rotateX, rotateY, rotateZ, material);
		this.amplitude = 1;
		this.noiseClassX = new NoiseClass();
		this.noiseClassY = new NoiseClass();
		this.scale = 1;
	}

	public Water2(double x, double y, double z,
				  double scaleX, double scaleY,
				  double rotateX, double rotateY, double rotateZ,
				  Material material, double amplitude) {
		super(x, y, z, scaleX, scaleY, 1, rotateX, rotateY, rotateZ, material);
		this.amplitude = amplitude;
		this.noiseClassX = new NoiseClass();
		this.noiseClassY = new NoiseClass();
		this.scale = 1;
	}

	public Water2(double x, double y, double z,
				  double scaleX, double scaleY,
				  double rotateX, double rotateY, double rotateZ,
				  Material material, double amplitude, double scale) {
		super(x, y, z, scaleX, scaleY, 1, rotateX, rotateY, rotateZ, material);
		this.amplitude = amplitude;
		this.noiseClassX = new NoiseClass();
		this.noiseClassY = new NoiseClass();
		this.scale = scale;
	}

	@Override
	public List<HitPointInfo> calculateHitPoint(Ray ray) {
		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);

		double hitTime = -inverseRay.getOrigin().getZ() / inverseRay.getDir().getZ();
		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		if (!(Math.abs(inverseRay.getDir().getZ()) < Configuration.ROUNDING_ERROR)) { // if not behind the eye and the ray is in the plane itself, the hitpoint can be calculated.
			Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
			if (Math.abs(hitLocation.getX()) <= (1.0 + Configuration.ROUNDING_ERROR) && Math.abs(hitLocation.getY()) <= (1.0 + Configuration.ROUNDING_ERROR)) {
				double xCoefficient = this.noiseClassX.turbulence(this.scale, hitLocation) * 2 * amplitude - amplitude; // the range of xCoeficient is [-A, A]
				double yCoefficient = this.noiseClassY.turbulence(this.scale, hitLocation) * 2 * amplitude - amplitude; // the range of xCoeficient is [-A, A]
				Vector normal = new Vector(xCoefficient, yCoefficient, 1).normalize();
				hitPointInfoList.add(new HitPointInfo(
								this,
								Operations.pointTransformation(
										this.transformation,
										Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()))
								),
								hitTime,
								Operations.vectorTransformation(this.inverseTransformation.transpose(), normal),
								(Operations.dotProduct(Operations.scalarVectorProduct(-1, inverseRay.getDir()), normal) >= 0) // isEntering if the corner between -inverseRay and the normal are smaller than 90°
						)
				);
			}
		}

		return hitPointInfoList;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
