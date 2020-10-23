package generic_objects;

import configuration.Configuration;
import misc.Point;
import misc.*;
import render_related.Material;

import java.awt.*;

public class Sphere extends GenericObject {

	public Sphere() {
		super();
	}

	public Sphere(Material material) {
		super(material);
	}

	public Sphere(double radius, double x, double y, double z, Material material) {
		super(x, y, z, radius, radius, radius, 0, 0, 0, material);
	}

	public Sphere(Matrix transformation, Matrix inverseTransformation, Material material) {
		super(material);
		this.transformation = transformation;
		this.inverseTransformation = inverseTransformation;
	}

	@Override
	public HitPointInfo calculateHitPoint(Ray ray) {

		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);

		HitPointInfo hitPointInfo;
		double a = Operations.dotProduct(inverseRay.getDir(), inverseRay.getDir());
		double b = Operations.dotProduct(inverseRay.getOrigin(), inverseRay.getDir());
		double c = Operations.dotProduct(inverseRay.getOrigin(), inverseRay.getOrigin()) - 1;

		double discriminant = Math.pow(b, 2) - a * c;

		// if discriminate is negative, the standard hitPointInfo will be returned, which is non-hit
		if (Math.abs(discriminant) < Configuration.ROUNDING_ERROR) { // if discriminant is 0
			double hitTime = -b / a;
			Point hitPoint = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
			hitPointInfo = new HitPointInfo(
					this,
					Operations.pointTransformation(
							this.transformation,
							hitPoint
					),
					hitTime,
					Operations.vectorTransformation(this.transformation, Operations.pointSubstraction(hitPoint, new Point()))
			);
		} else if (discriminant > 0) {
			double hitTime = -(b / a) - (Math.sqrt(discriminant) / a);
			Point hitPoint = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
			hitPointInfo = new HitPointInfo(
					this,
					Operations.pointTransformation(
							this.transformation,
							hitPoint
					),
					hitTime,
					Operations.vectorTransformation(this.transformation, Operations.pointSubstraction(hitPoint, new Point()))
			);
		} else {
			hitPointInfo = new HitPointInfo();
		}

		return hitPointInfo;
	}
}
