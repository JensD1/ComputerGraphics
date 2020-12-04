package objects;

import configuration.Configuration;
import misc.*;
import render_related.Material;

import java.util.ArrayList;
import java.util.List;

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
	public List<HitPointInfo> calculateHitPoint(Ray ray) {

		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);

		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		double a = Operations.dotProduct(inverseRay.getDir(), inverseRay.getDir());
		double b = Operations.dotProduct(inverseRay.getOrigin(), inverseRay.getDir());
		double c = Operations.dotProduct(inverseRay.getOrigin(), inverseRay.getOrigin()) - 1;

		double discriminant = Math.pow(b, 2) - a * c;

		// if discriminate is negative, the standard hitPointInfo will be returned, which is non-hit
		if (Math.abs(discriminant) < Configuration.ROUNDING_ERROR) { // if discriminant is 0, there is 1 hitpoint.
			double hitTime = -b / a;
			addHitPointToList(inverseRay, hitPointInfoList, hitTime, false);
		} else if (discriminant > -Configuration.ROUNDING_ERROR) { // if there are 2 hitpoints
			// hitpoint 1:
			double hitTime = -(b / a) - (Math.sqrt(Math.abs(discriminant)) / a);
			if(hitTime > Configuration.ROUNDING_ERROR){
				addHitPointToList(inverseRay, hitPointInfoList, hitTime, true);
			}

			//hitpoint 2:
			hitTime = -(b / a) + (Math.sqrt(Math.abs(discriminant)) / a);
			if(hitTime > Configuration.ROUNDING_ERROR) {
				addHitPointToList(inverseRay, hitPointInfoList, hitTime, false);
			}
		}

		return hitPointInfoList;
	}

	private void addHitPointToList(Ray inverseRay, List<HitPointInfo> hitPointInfoList, double hitTime, boolean isEntering) {
		if (hitTime > Configuration.ROUNDING_ERROR) {
			Point hitPoint = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
			hitPointInfoList.add(
					new HitPointInfo(
							this,
							Operations.pointTransformation(
									this.transformation,
									hitPoint
							),
							hitTime,
							Operations.vectorTransformation(this.inverseTransformation.transpose(), Operations.pointSubstraction(hitPoint, new Point())),
							isEntering
					)
			);
		}
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
