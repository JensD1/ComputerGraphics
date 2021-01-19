package objects;

import misc.HitPointInfo;
import misc.Operations;
import misc.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BooleanDifference extends BooleanObject{
	public BooleanDifference(GenericObject leftObject, GenericObject rightObject) {
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
			boolean combInside = !rtInside && lftInside;

			HitPointInfo lftHitPoint = lftIterator.next(); // Take two "imaginative" hitpoints before the real hitpoints.
			HitPointInfo rtHitPoint = rtIterator.next();

			HitPointInfo lastHitPoint; // save the last hitpoint object so it is easy to put into the unionList when necessary
			boolean tempCombInside;

			// create combined list
			while (lftIterator.hasNext() && rtIterator.hasNext()) { // the two hitpoints at infinity will not be treated.
				if (lftHitPoint.getHitTime() < rtHitPoint.getHitTime()) {
					lftInside = lftHitPoint.isEntering();
					lastHitPoint = lftHitPoint;
					lftHitPoint = lftIterator.next();
				} else {
					rtInside = rtHitPoint.isEntering();
					lastHitPoint = rtHitPoint;
					rtHitPoint = rtIterator.next();
				}
				tempCombInside = !rtInside && lftInside;
				if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
					combInside = tempCombInside;
				}
			}

			// go through the unconsumed lists.
			while (lftIterator.hasNext()) {
				lftInside = lftHitPoint.isEntering();
				lastHitPoint = lftHitPoint;
				lftHitPoint = lftIterator.next();
				tempCombInside = !rtInside && lftInside;
				if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
				}
				combInside = tempCombInside;
			}

		} else {
			if (!leftHitPointList.isEmpty()) {
				// sort hitpoints
				Collections.sort(leftHitPointList);

				// add an hitpoint in infinity
				HitPointInfo infHitpoint = new HitPointInfo(Double.MAX_VALUE, null);
				infHitpoint.setEntering(leftHitPointList.get(leftHitPointList.size()-1).isEntering());
				leftHitPointList.add(infHitpoint);

				// initialize
				Iterator<HitPointInfo> lftIterator = leftHitPointList.iterator(); // make sure that iteration is possible over all Hitpoints
				boolean lftInside = !leftHitPointList.get(0).isEntering();
				boolean combInside = lftInside;

				HitPointInfo lftHitPoint = lftIterator.next(); // Take two "imaginative" hitpoints before the real hitpoints.

				HitPointInfo lastHitPoint; // save the last hitpoint object so it is easy to put into the unionList when necessary
				boolean tempCombInside;

				while (lftIterator.hasNext()) { // the two hitpoints at infinity will not be treated.
					lftInside = lftHitPoint.isEntering();
					lastHitPoint = lftHitPoint;
					lftHitPoint = lftIterator.next();
					tempCombInside = lftInside;
					if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
						unionList.add(lastHitPoint);
						combInside = tempCombInside;
					}
				}
			}
		}
		return unionList;
	}
}
