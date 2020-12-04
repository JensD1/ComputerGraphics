package objects;

import configuration.Configuration;
import misc.*;
import render_related.Material;

import java.util.ArrayList;
import java.util.List;

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
	public List<HitPointInfo> calculateHitPoint(Ray ray) {
		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);

		double hitTime = -inverseRay.getOrigin().getZ() / inverseRay.getDir().getZ();
		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		if (hitTime > Configuration.ROUNDING_ERROR && !(Math.abs(inverseRay.getDir().getZ()) < Configuration.ROUNDING_ERROR)) { // if not behind the eye and the ray is in the plane itself, the hitpoint can be calculated.
			Point hitLocation = Operations.pointVectorAddition(inverseRay.getOrigin(), Operations.scalarVectorProduct(hitTime, inverseRay.getDir()));
			if (Math.abs(hitLocation.getX()) <= (1.0 + Configuration.ROUNDING_ERROR) && Math.abs(hitLocation.getY()) <= (1.0 + Configuration.ROUNDING_ERROR)) {
				Vector normal = new Vector(0, 0, 1);
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
