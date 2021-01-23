package objects;

import configuration.Configuration;
import misc.*;
import render_related.Material;

import java.util.ArrayList;
import java.util.List;

public class TaperedCylinder extends GenericObject {

	private double s;
	Plane groundPlane;
	Plane upperPlane; // todo kijk overal na of er this. staat

	public TaperedCylinder() {
		super();
		this.s = 1;
		this.groundPlane = new Plane(0, 0, 0, 180, 0, 0, this.material);
		this.upperPlane = new Plane(0, 0, 1, 0, 0, 0, this.material);
	}

	public TaperedCylinder(double s, Material material) {
		super(material);
		this.s = s;
		this.groundPlane = new Plane(0, 0, 0, 180, 0, 0, this.material);
		this.upperPlane = new Plane(0, 0, 1, 0, 0, 0, this.material);
	}

	public TaperedCylinder(double s, double x, double y, double z, double scaleX,
						   double scaleY, double scaleZ, double rotateX, double rotateY,
						   double rotateZ, Material material) {
		super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ,
				material);
		this.s = s;
		this.groundPlane = new Plane(0, 0, 0, 180, 0, 0, this.material);
		this.upperPlane = new Plane(0, 0, 1, 0, 0, 0, this.material);
	}

	@Override
	public List<HitPointInfo> calculateHitPoint(Ray ray) {
		List<HitPointInfo> hitPointInfoList = new ArrayList<>();

		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);

		// test the mantle
		double d = (this.s - 1) * inverseRay.getDir().getZ();
		double f = 1 + (this.s - 1) * inverseRay.getOrigin().getZ();
		double a = Math.pow(inverseRay.getDir().getX(), 2) + Math.pow(inverseRay.getDir().getY(), 2) - Math.pow(d, 2);
		double b = inverseRay.getOrigin().getX() * inverseRay.getDir().getX() + inverseRay.getOrigin().getY() * inverseRay.getDir().getY() - f * d;
		double c = Math.pow(inverseRay.getOrigin().getX(), 2) + Math.pow(inverseRay.getOrigin().getY(), 2) - Math.pow(f, 2);

		double discriminant = Math.pow(b, 2) - a * c;

		// if discriminate is negative, the standard hitPointInfo will be returned, which is non-hit
		if (Math.abs(discriminant) < Configuration.ROUNDING_ERROR) { // if discriminant is 0, there is 1 hitpoint
			double hitTime = -b / a;
			addHitPointToList(hitPointInfoList, inverseRay, hitTime);
		} else if (discriminant > -Configuration.ROUNDING_ERROR) { // there are two hitpoints
			// First hitpoint:
			double hitTime = -(b / a) - (Math.sqrt(discriminant) / a);
			addHitPointToList(hitPointInfoList, inverseRay, hitTime);

			// Second hitpoint:
			hitTime = -(b / a) + (Math.sqrt(discriminant) / a);
			addHitPointToList(hitPointInfoList, inverseRay, hitTime);
		}

		// test and add groundplane hitpoint
		addHitPointToList(hitPointInfoList, groundPlane.calculateHitPoint(inverseRay), 1); // we set s to one so the ground plane is a normal circle

		// test Upper plane
		addHitPointToList(hitPointInfoList, upperPlane.calculateHitPoint(inverseRay), this.s);

		if (hitPointInfoList.size() == 1) { // todo does not work when inside taperedcylinder
			HitPointInfo hitPointInfo = new HitPointInfo(hitPointInfoList.get(0));
			hitPointInfo.setEntering(!hitPointInfo.isEntering());
			hitPointInfoList.add(hitPointInfo);
		}

		return hitPointInfoList;
	}

	private void addHitPointToList(List<HitPointInfo> hitPointInfoList, Ray inverseRay, double hitTime) {
		Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
		if (hitLocation.getZ() >= -Configuration.ROUNDING_ERROR && hitLocation.getZ() <= (1 + Configuration.ROUNDING_ERROR)) {
			Vector normal = new Vector(
					hitLocation.getX(),
					hitLocation.getY(),
					(1 - this.s) * (1 + (this.s - 1) * hitLocation.getZ())
			);
			if (normal.norm() > Configuration.ROUNDING_ERROR) { // at the top of the cone (s=0), the norm of the normal will be 0.
				hitPointInfoList.add(
						new HitPointInfo(
								this,
								Operations.pointTransformation(this.transformation, hitLocation),
								hitTime,
								Operations.vectorTransformation(this.inverseTransformation.transpose(), normal),
								(Operations.dotProduct(Operations.scalarVectorProduct(-1, inverseRay.getDir()), normal) >= 0) // isEntering if the corner between -inverseRay and the normal are smaller than 90°
						)
				);
			}
		}
	}

	public void addHitPointToList(List<HitPointInfo> hitPointInfoList, List<HitPointInfo> toAddList, double s) {
		if (!toAddList.isEmpty()) {
			for (HitPointInfo hitPointInfo : toAddList) { // Change the parameters that needs to change
				Point hitLocation = hitPointInfo.getHitPoint();
				if (((Math.pow(hitLocation.getX(), 2) + Math.pow(hitLocation.getY(), 2)) <= (Math.pow(s, 2) + Configuration.ROUNDING_ERROR)) && hitPointInfo.isHit()) {
					hitPointInfo.setHitPoint(Operations.pointTransformation(this.transformation, hitPointInfo.getHitPoint()));
					hitPointInfo.setNormal(Operations.vectorTransformation(this.inverseTransformation.transpose(), hitPointInfo.getNormal()));
					hitPointInfo.setObject(this);
					hitPointInfoList.add(hitPointInfo);
				}
			}
		}

	}

	public void setMaterial(Material material) {
		this.material = material;
		this.groundPlane.setMaterial(material);
		this.upperPlane.setMaterial(material);
	}
}
