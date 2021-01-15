package objects;

import misc.HitPointInfo;
import misc.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BooleanUnion extends BooleanObject {

	public BooleanUnion(GenericObject leftObject, GenericObject rightObject) {
		super(leftObject, rightObject);
	}

	@Override
	public List<HitPointInfo> calculateHitPoint(Ray ray) {
		List<HitPointInfo> unionList = new ArrayList<>();  // the list of combined hitpoints
		List<HitPointInfo> leftHitPointList = leftObject.calculateHitPoint(ray);
		List<HitPointInfo> rightHitPointList = rightObject.calculateHitPoint(ray); // todo check for improvements
		if (!leftHitPointList.isEmpty() && !rightHitPointList.isEmpty()) {
			// sort hitpoints
			Collections.sort(leftHitPointList);
			Collections.sort(rightHitPointList);

			// initialize
			Iterator<HitPointInfo> lftIterator = leftHitPointList.iterator(); // make sure that iteration is possible over all Hitpoints
			Iterator<HitPointInfo> rtIterator = rightHitPointList.iterator();
			boolean lftInside = !leftHitPointList.get(0).isEntering();
			boolean rtInside = !rightHitPointList.get(0).isEntering();
			boolean combInside = rtInside || lftInside;

			HitPointInfo lftHitPoint = lftIterator.next(); // Take two "imaginative" hitpoints before the real hitpoints.
			HitPointInfo rtHitPoint = rtIterator.next();

			HitPointInfo lastHitPoint; // save the last hitpoint object so it is easily to put into the unionList when necessary
			boolean tempCombInside;

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
				tempCombInside = rtInside || lftInside;
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
				tempCombInside = rtInside || lftInside;
				if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
				}
				combInside = tempCombInside;
			}
			while (rtIterator.hasNext()) {
				rtInside = rtHitPoint.isEntering();
				lastHitPoint = rtHitPoint;
				rtHitPoint = rtIterator.next();
				tempCombInside = rtInside || lftInside;
				if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
				}
				combInside = tempCombInside;
			}

			// last point is not evaluated ==> we evaluate it here:
			lftInside = lftHitPoint.isEntering();
			rtInside = rtHitPoint.isEntering();
			tempCombInside = rtInside || lftInside;
			if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
				if (lftHitPoint.getHitTime() > rtHitPoint.getHitTime()) {
					unionList.add(lftHitPoint);
				} else {
					unionList.add(rtHitPoint);
				}
			}

		} else {
			if (!leftHitPointList.isEmpty()) {
				unionList.addAll(leftHitPointList); // todo change
			}
			if (!rightHitPointList.isEmpty()) {
				unionList.addAll(rightHitPointList); // todo change
			}
		}
		return unionList;
	}
}
