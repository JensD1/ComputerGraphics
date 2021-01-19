package objects;

import misc.HitPointInfo;
import misc.Matrix;
import misc.Ray;
import render_related.Material;

import java.util.List;

public abstract class BooleanObject extends GenericObject {

	protected GenericObject rightObject;
	protected GenericObject leftObject;

	BooleanObject(GenericObject leftObject, GenericObject rightObject){
		this.leftObject = leftObject;
		this.rightObject = rightObject;
	}

	@Override
	public abstract List<HitPointInfo> calculateHitPoint(Ray ray);

	@Override
	public void setMaterial(Material material) {
		this.leftObject.setMaterial(material);
		this.rightObject.setMaterial(material);
	}
}
