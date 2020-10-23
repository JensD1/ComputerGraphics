package generic_objects;

import misc.*;
import render_related.Material;

import java.awt.*;

public class Plane extends GenericObject {

	public Plane() {
		super();
	}

	public Plane(Material material) {
		super(material);
	}

	public Plane(double x, double y, double z,
				 double rotateX, double rotateY, double rotateZ,
				 Material material) {
		super(x, y, z, 1, 1, 1, rotateX, rotateY, rotateZ, material);
	}

	@Override
	public HitPointInfo calculateHitPoint(Ray ray) {
		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);
		HitPointInfo hitPointInfo;
		double hitTime = -inverseRay.getOrigin().getZ() / inverseRay.getDir().getZ();

		if (hitTime > 0) {
			hitPointInfo = new HitPointInfo(
					this,
					Operations.pointTransformation(
							this.transformation,
							Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()))
					),
					hitTime,
					Operations.vectorTransformation(this.transformation, new Vector(0, 0, 1))
			);
		} else {
			hitPointInfo = new HitPointInfo();
		}
		return hitPointInfo;
	}
}
