package objects;

import configuration.Configuration;
import misc.HitPointInfo;
import misc.Operations;
import misc.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BooleanIntersection extends BooleanObject{

	public BooleanIntersection(GenericObject leftObject, GenericObject rightObject) {
		super(leftObject, rightObject);
	}

	@Override
	public List<HitPointInfo> calculateHitPoint(Ray ray) {

		Ray inverseRay = new Ray(
				Operations.pointTransformation(this.inverseTransformation, ray.getOrigin()),
				Operations.vectorTransformation(this.inverseTransformation, ray.getDir())
		);

		List<HitPointInfo> unionList = new ArrayList<>();  // the list of combined hitpoints
		List<HitPointInfo> leftHitPointList = leftObject.calculateHitPoint(inverseRay);
		List<HitPointInfo> rightHitPointList = rightObject.calculateHitPoint(inverseRay); // todo check for improvements
		if (!leftHitPointList.isEmpty() && !rightHitPointList.isEmpty()) {
			// sort hitpoints
			Collections.sort(leftHitPointList);
			Collections.sort(rightHitPointList);

			// add hitpoints in infinity
			HitPointInfo infHitpoint = new HitPointInfo(Double.MAX_VALUE, null);
			infHitpoint.setEntering(leftHitPointList.get(leftHitPointList.size()-1).isEntering());
			leftHitPointList.add(infHitpoint);
			infHitpoint.setEntering(rightHitPointList.get(rightHitPointList.size()-1).isEntering());
			rightHitPointList.add(infHitpoint);

			// initialize
			Iterator<HitPointInfo> lftIterator = leftHitPointList.iterator(); // make sure that iteration is possible over all Hitpoints
			Iterator<HitPointInfo> rtIterator = rightHitPointList.iterator();
			boolean lftInside = !leftHitPointList.get(0).isEntering();
			boolean rtInside = !rightHitPointList.get(0).isEntering();
			boolean combInside = rtInside && lftInside;

			HitPointInfo lftHitPoint = lftIterator.next(); // Take two "imaginative" hitpoints before the real hitpoints.
			HitPointInfo rtHitPoint = rtIterator.next();

			HitPointInfo lastHitPoint; // save the last hitpoint object so it is easily to put into the unionList when necessary
			boolean tempCombInside;

			// create combined list
			while (lftIterator.hasNext() && rtIterator.hasNext()) {
				if (lftHitPoint.getHitTime() < rtHitPoint.getHitTime()) {
					lftInside = lftHitPoint.isEntering();
					lastHitPoint = lftHitPoint;
					lftHitPoint = lftIterator.next();
				} else {
					rtInside = rtHitPoint.isEntering();
					lastHitPoint = rtHitPoint;
					rtHitPoint = rtIterator.next();
				}
				tempCombInside = rtInside && lftInside;
				if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
					combInside = tempCombInside;
				}
			}
		}

		if(!unionList.isEmpty()) {
			for (HitPointInfo hitPointInfo : unionList) {
				hitPointInfo.setHitPoint(Operations.pointTransformation(this.transformation, hitPointInfo.getHitPoint()));
				hitPointInfo.setNormal(Operations.vectorTransformation(this.inverseTransformation.transpose(), hitPointInfo.getNormal()));
			}
		}
		return unionList;
	}
}
