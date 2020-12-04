package objects;

import misc.HitPointInfo;
import misc.Ray;

import java.util.ArrayList;
import java.util.List;

public class BooleanUnion extends BooleanObject{

	BooleanUnion(GenericObject leftObject, GenericObject rightObject) {
		super(leftObject, rightObject);
	}

	@Override
	public List<HitPointInfo> calculateHitPoint(Ray ray) {
		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		List<HitPointInfo> leftHitPointList = leftObject.calculateHitPoint(ray);
		if(!leftHitPointList.isEmpty()){
			List<HitPointInfo> rightHitPointList = rightObject.calculateHitPoint(ray);
			if(!rightHitPointList.isEmpty()){
				hitPointInfoList.addAll(leftHitPointList);
				hitPointInfoList.addAll(rightHitPointList);
			}
		}
		return hitPointInfoList;
	}
}
