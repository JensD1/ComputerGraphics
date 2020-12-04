package objects;

import misc.HitPointInfo;
import misc.Ray;

import java.util.List;

public class BooleanIntersection extends BooleanObject{

	BooleanIntersection(GenericObject leftObject, GenericObject rightObject) {
		super(leftObject, rightObject);
	}

	@Override
	public List<HitPointInfo> calculateHitPoint(Ray ray) {


		return null;
	}
}
