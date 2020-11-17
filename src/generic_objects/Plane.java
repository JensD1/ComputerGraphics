package generic_objects;

import configuration.Configuration;
import misc.HitPointInfo;
import misc.Operations;
import misc.Ray;
import misc.Vector;
import render_related.Material;

import java.util.ArrayList;
import java.util.List;

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
	public List<HitPointInfo> calculateHitPoint(Ray ray) {
		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);

		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		double hitTime = -inverseRay.getOrigin().getZ() / inverseRay.getDir().getZ();

		if (hitTime > Configuration.ROUNDING_ERROR) {
			hitPointInfoList.add(
					new HitPointInfo(
							this,
							Operations.pointTransformation(
									this.transformation,
									Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()))
							),
							hitTime,
							Operations.vectorTransformation(this.transformation, new Vector(0, 0, 1))
					)
			);
		}
		return hitPointInfoList;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}
}
