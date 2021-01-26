package objects;

import configuration.Configuration;
import misc.*;
import render_related.Material;

import java.util.ArrayList;
import java.util.List;

public class Water extends GenericObject {

	private double amplitude;
	private double xNumberOfPeriods;
	private double yNumberOfPeriods;
	private double harmonicWaves;

	public Water() {
		super();
		this.amplitude = 1;
		this.xNumberOfPeriods = Math.PI;
		this.yNumberOfPeriods = Math.PI;
		this.harmonicWaves = 0;
	}

	public Water(Material material) {
		super(material);
		this.amplitude = 1;
		this.xNumberOfPeriods = Math.PI;
		this.yNumberOfPeriods = Math.PI;
		this.harmonicWaves = 0;
	}

	public Water(double x, double y, double z,
				 double scaleX, double scaleY,
				 double rotateX, double rotateY, double rotateZ,
				 Material material) {
		super(x, y, z, scaleX, scaleY, 1, rotateX, rotateY, rotateZ, material);
		this.amplitude = 1;
		this.xNumberOfPeriods = Math.PI;
		this.yNumberOfPeriods = Math.PI;
		this.harmonicWaves = 0;
	}

	public Water(double x, double y, double z,
				 double scaleX, double scaleY,
				 double rotateX, double rotateY, double rotateZ,
				 Material material, double amplitude, double xNumberOfPeriods,
				 double yNumberOfPeriods) {
		super(x, y, z, scaleX, scaleY, 1, rotateX, rotateY, rotateZ, material);
		this.amplitude = amplitude;
		this.xNumberOfPeriods = xNumberOfPeriods * Math.PI;
		this.yNumberOfPeriods = yNumberOfPeriods * Math.PI;
		this.harmonicWaves = 0;
	}

	public Water(double x, double y, double z,
				 double scaleX, double scaleY,
				 double rotateX, double rotateY, double rotateZ,
				 Material material, double amplitude, double xNumberOfPeriods,
				 double yNumberOfPeriods, double harmonicWaves) {
		super(x, y, z, scaleX, scaleY, 1, rotateX, rotateY, rotateZ, material);
		this.amplitude = amplitude;
		this.xNumberOfPeriods = xNumberOfPeriods * Math.PI;
		this.yNumberOfPeriods = yNumberOfPeriods * Math.PI;
		this.harmonicWaves = harmonicWaves;
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
				double xCoefficient = Math.sin(hitLocation.getX() * this.xNumberOfPeriods) * amplitude;
				for(int i = 1; i <= this.harmonicWaves; i++){
					xCoefficient += (1/Math.pow((i+1.0), 2)) * Math.sin(hitLocation.getX() * this.xNumberOfPeriods * (i+1)) * amplitude; // i represents the number of harmonic waves you want, but the first harmonic must have a frequency twice as high as the base wave.
				}
				double yCoefficient = Math.sin(hitLocation.getY() * this.yNumberOfPeriods) * amplitude;
				for(int i = 1; i <= this.harmonicWaves; i++){
					yCoefficient += (1/Math.pow((i+1.0), 2)) * Math.sin(hitLocation.getY() * this.yNumberOfPeriods * (i+1)) * amplitude;
				}
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
