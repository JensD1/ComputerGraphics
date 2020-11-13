package generic_objects;

import configuration.Configuration;
import misc.Point;
import misc.*;
import render_related.Material;

import java.awt.*;

public class Square extends GenericObject {

	public Square() {
		super();
	}

	public Square(Material material) {
		super(material);
	}

	public Square(double x, double y, double z,
				  double scaleX, double scaleY,
				  double rotateX, double rotateY, double rotateZ,
				  Material material) {
		super(x, y, z, scaleX, scaleY, 1, rotateX, rotateY, rotateZ, material);
	}

	@Override
	public HitPointInfo calculateHitPoint(Ray ray) {
		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);
		double hitTime = -inverseRay.getOrigin().getZ() / inverseRay.getDir().getZ();
		HitPointInfo hitPointInfo;
		if (hitTime > Configuration.ROUNDING_ERROR) {
			Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
			if (Math.abs(hitLocation.getX()) <= (1.0 + Configuration.ROUNDING_ERROR)  && Math.abs(hitLocation.getY()) <= (1.0 + Configuration.ROUNDING_ERROR)) {
				hitPointInfo = new HitPointInfo(
						this,
						Operations.pointTransformation(this.transformation, hitLocation),
						hitTime,
						Operations.vectorTransformation(this.transformation, new Vector(0, 0, 1))
				);
			} else {
				hitPointInfo = new HitPointInfo();
			}
		} else {
			hitPointInfo = new HitPointInfo();
		}

		return hitPointInfo;
	}
}
