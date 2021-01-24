package objects;

import misc.HitPointInfo;
import misc.Operations;
import misc.Ray;
import render_related.Material;

import java.util.ArrayList;
import java.util.List;

public class WaterCube extends GenericObject {
	private List<GenericObject> squares;

	public WaterCube() {
		super();
		this.squares = new ArrayList<>();
		this.squares.add(new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material)); // groundsquare
		this.squares.add(new Water2(0, 0, 1, 1, 1, 0, 0, 0, this.material)); // upperSquare
		this.squares.add(new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material)); // backSquare
		this.squares.add(new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material)); // frontSquare
		this.squares.add(new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material)); // rightSquare
		this.squares.add(new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material)); // leftSquare
	}

	public WaterCube(Material material) {
		super(material);
		this.squares = new ArrayList<>();
		this.squares.add(new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material)); // groundsquare
		this.squares.add(new Water2(0, 0, 1, 1, 1, 0, 0, 0, this.material)); // upperSquare
		this.squares.add(new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material)); // backSquare
		this.squares.add(new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material)); // frontSquare
		this.squares.add(new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material)); // rightSquare
		this.squares.add(new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material)); // leftSquare
	}

	public WaterCube(double x, double y, double z,
					 double scaleX, double scaleY, double scaleZ,
					 double rotateX, double rotateY, double rotateZ,
					 Material material) {
		super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, material);
		this.squares = new ArrayList<>();
		this.squares.add(new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material)); // groundsquare
		this.squares.add(new Water2(0, 0, 1, 1, 1, 0, 0, 0, this.material)); // upperSquare
		this.squares.add(new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material)); // backSquare
		this.squares.add(new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material)); // frontSquare
		this.squares.add(new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material)); // rightSquare
		this.squares.add(new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material)); // leftSquare
	}

	public WaterCube(double x, double y, double z,
					 double scaleX, double scaleY, double scaleZ,
					 double rotateX, double rotateY, double rotateZ,
					 Material material, double amplitude, double scale) {
		super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, material);
		this.squares = new ArrayList<>();
		this.squares.add(new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material)); // groundsquare
		this.squares.add(new Water2(0, 0, 1, 1, 1, 0, 0, 0, this.material, amplitude, scale)); // upperSquare
		this.squares.add(new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material)); // backSquare
		this.squares.add(new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material)); // frontSquare
		this.squares.add(new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material)); // rightSquare
		this.squares.add(new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material)); // leftSquare
	}

	@Override
	public List<HitPointInfo> calculateHitPoint(Ray ray) {
		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);

		for (GenericObject square : squares) {
			addHitPointToList(hitPointInfoList, square.calculateHitPoint(inverseRay));
		}
		// when not inside and the ray is a tangent line, we take this point double in order to work properly with boolean objects.
		if (hitPointInfoList.size() == 1) {
			HitPointInfo hitPointInfo = new HitPointInfo(hitPointInfoList.get(0));
			hitPointInfo.setEntering(!hitPointInfo.isEntering());
			hitPointInfoList.add(hitPointInfo);
		}

		return hitPointInfoList;
	}

	public void addHitPointToList(List<HitPointInfo> hitPointInfoList, List<HitPointInfo> toAddList) {
		if (!toAddList.isEmpty()) {
			for (HitPointInfo hitPointInfo : toAddList) { // Change the parameters that needs to change
				hitPointInfo.setHitPoint(Operations.pointTransformation(this.transformation, hitPointInfo.getHitPoint()));
				hitPointInfo.setNormal(Operations.vectorTransformation(this.inverseTransformation.transpose(), hitPointInfo.getNormal()));
				hitPointInfo.setObject(this);
			}
			hitPointInfoList.addAll(toAddList);
		}
	}

	public void setMaterial(Material material) {
		this.material = material;
		for (GenericObject square : this.squares) {
			square.setMaterial(material);
		}
	}
}
