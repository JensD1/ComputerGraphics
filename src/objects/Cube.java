package objects;

import configuration.Configuration;
import misc.HitPointInfo;
import misc.Operations;
import misc.Ray;
import render_related.Material;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cube extends GenericObject {
	private List<Square> squares;

	public Cube() {
		super();
		this.squares = new ArrayList<>();
		this.squares.add(new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material)); // groundsquare
        this.squares.add(new Square(0, 0, 1, 1, 1, 0, 0, 0, this.material)); // upperSquare
        this.squares.add(new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material)); // backSquare
        this.squares.add(new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material)); // frontSquare
        this.squares.add(new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material)); // rightSquare
        this.squares.add(new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material)); // leftSquare
	}

	public Cube(Material material) {
		super(material);
        this.squares = new ArrayList<>();
        this.squares.add(new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material)); // groundsquare
        this.squares.add(new Square(0, 0, 1, 1, 1, 0, 0, 0, this.material)); // upperSquare
        this.squares.add(new Square(0, -1, 0, 1, 1, 90, 0, 0, this.material)); // backSquare
        this.squares.add(new Square(0, 1, 0, 1, 1, -90, 0, 0, this.material)); // frontSquare
        this.squares.add(new Square(-1, 0, 0, 1, 1, 0, -90, 0, this.material)); // rightSquare
        this.squares.add(new Square(1, 0, 0, 1, 1, 0, 90, 0, this.material)); // leftSquare
	}

	public Cube(double x, double y, double z,
				double scaleX, double scaleY, double scaleZ,
				double rotateX, double rotateY, double rotateZ,
				Material material) {
		super(x, y, z, scaleX, scaleY, scaleZ, rotateX, rotateY, rotateZ, material);
        this.squares = new ArrayList<>();
        this.squares.add(new Square(0, 0, -1, 1, 1, 180, 0, 0, this.material)); // groundsquare
        this.squares.add(new Square(0, 0, 1, 1, 1, 0, 0, 0, this.material)); // upperSquare
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

		if ( // if ray starts inside cube: go over all objects until one hitpoint is found
		        inverseRay.getOrigin().getX() < (1 - Configuration.ROUNDING_ERROR)
                && inverseRay.getOrigin().getX() > -(1 - Configuration.ROUNDING_ERROR)
                && inverseRay.getOrigin().getY() < (1 - Configuration.ROUNDING_ERROR)
                && inverseRay.getOrigin().getY() > -(1 - Configuration.ROUNDING_ERROR)
                && inverseRay.getOrigin().getZ() < (1 - Configuration.ROUNDING_ERROR)
                && inverseRay.getOrigin().getZ() > -(1 - Configuration.ROUNDING_ERROR)
        ){
            Iterator<Square> iterator = this.squares.listIterator();
            boolean foundHitPoint = false;
            while (iterator.hasNext() && !foundHitPoint){
                foundHitPoint = addHitPointToList(hitPointInfoList, iterator.next().calculateHitPoint(inverseRay));
            }
        } else {
            for(Square square: squares){
                addHitPointToList(hitPointInfoList, square.calculateHitPoint(inverseRay));
            }
        }
		return hitPointInfoList;
	}

	public boolean addHitPointToList(List<HitPointInfo> hitPointInfoList, List<HitPointInfo> toAddList) {
	    boolean hitObject = false;
		if (!toAddList.isEmpty()) {
			for (HitPointInfo hitPointInfo : toAddList) { // Change the parameters that needs to change
				hitPointInfo.setHitPoint(Operations.pointTransformation(this.transformation, hitPointInfo.getHitPoint()));
				hitPointInfo.setNormal(Operations.vectorTransformation(this.inverseTransformation.transpose(), hitPointInfo.getNormal()));
				hitPointInfo.setObject(this);
			}
			hitPointInfoList.addAll(toAddList);
			hitObject = true;
		}
		return hitObject;
	}

	public void setMaterial(Material material) {
		this.material = material;
		for(Square square: this.squares){
		    square.setMaterial(material);
        }
	}
}