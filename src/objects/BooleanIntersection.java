package objects;

import configuration.Configuration;
import misc.HitPointInfo;
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
			boolean combInside = rtInside && lftInside;

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
				tempCombInside = rtInside && lftInside;
				if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
					combInside = tempCombInside;
				}
			}

			// go through the unconsumed lists.
			while (lftIterator.hasNext()) {
				if(lftHitPoint.getHitTime() < rtHitPoint.getHitTime()) {
					lftInside = lftHitPoint.isEntering();
					lastHitPoint = lftHitPoint;
					tempCombInside = rtInside && lftInside;
					if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
						unionList.add(lastHitPoint);
					}
					combInside = tempCombInside;
				}
				lftHitPoint = lftIterator.next();
			}
			while (rtIterator.hasNext()) {
				if(lftHitPoint.getHitTime() > rtHitPoint.getHitTime()) {
					rtInside = rtHitPoint.isEntering();
					lastHitPoint = rtHitPoint;

					tempCombInside = rtInside && lftInside;
					if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
						unionList.add(lastHitPoint);
					}
					combInside = tempCombInside;
				}
				rtHitPoint = rtIterator.next();
			}

			// last two updates are not evaluated ==> we evaluate it here:
			for(int i = 0; i<2; i++) {
				if (lftHitPoint.getHitTime() < rtHitPoint.getHitTime()) {
					lftInside = lftHitPoint.isEntering();
					lastHitPoint = lftHitPoint;
					lftHitPoint = new HitPointInfo(Double.MAX_VALUE, null);
				} else {
					rtInside = rtHitPoint.isEntering();
					lastHitPoint = rtHitPoint;
					rtHitPoint = new HitPointInfo(Double.MAX_VALUE, null);
				}
				tempCombInside = rtInside && lftInside;
				if (tempCombInside != combInside) { // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
					combInside = tempCombInside;
				}
			}
			if(unionList.size() < 2) // todo remove
				System.out.println("help");
		}
		return unionList;
	}
}
