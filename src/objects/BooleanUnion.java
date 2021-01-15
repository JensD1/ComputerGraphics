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
			System.out.println("\n\nBegin:\nLeft\n" + leftHitPointList); // todo remove
			// sort hitpoints
			Collections.sort(rightHitPointList);
			System.out.println("\nRight\n" + rightHitPointList); // todo remove
			if(leftHitPointList.size()>1 && rightHitPointList.size()>1) // todo remove
				System.out.println();
			// initialize
			Iterator<HitPointInfo> lftIterator = leftHitPointList.iterator(); // make sure that iteration is possible over all Hitpoints
			Iterator<HitPointInfo> rtIterator = rightHitPointList.iterator();
			HitPointInfo lftHitPoint = lftIterator.next(); // Save the hitpoint such that the smalles hitpoint of the two can be found easily.
			HitPointInfo rtHitPoint = rtIterator.next();
			boolean lftInside = !lftHitPoint.isEntering();
			boolean rtInside = !rtHitPoint.isEntering();
			boolean combInside = rtInside || lftInside;
			HitPointInfo lastHitPoint; // save the last hitpoint object so it is easily to put into the unionList when necessary
			while (lftIterator.hasNext() && rtIterator.hasNext()) {
				if (lftHitPoint.getHitTime() < rtHitPoint.getHitTime()) {
					lftInside = lftHitPoint.isEntering();
					lastHitPoint = lftHitPoint;
					lftHitPoint = lftIterator.next();// set ready for next iteration
				} else {
					rtInside = rtHitPoint.isEntering();
					lastHitPoint = rtHitPoint;
					rtHitPoint = rtIterator.next();

				}
				boolean tempCombInside = rtInside || lftInside;
				if (tempCombInside != combInside){ // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
					combInside = tempCombInside;
				}
			}
			// go through the unconsumed lists.
			while (lftIterator.hasNext()){
				lftInside = lftHitPoint.isEntering();
				lastHitPoint = lftHitPoint;
				lftHitPoint = lftIterator.next();
				boolean tempCombInside = rtInside || lftInside;
				if (tempCombInside != combInside){ // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
				}
				combInside = tempCombInside;
			}
			while (rtIterator.hasNext()){
				rtInside = rtHitPoint.isEntering();
				lastHitPoint = rtHitPoint;
				rtHitPoint = rtIterator.next();
				boolean tempCombInside = rtInside || lftInside;
				if (tempCombInside != combInside){ // save the hitpoint if combInside changes state.
					unionList.add(lastHitPoint);
				}
				combInside = tempCombInside;
			}
		} else{
			if (!leftHitPointList.isEmpty()){
				unionList.addAll(leftHitPointList); // todo change
			}
			if (!rightHitPointList.isEmpty()){
				unionList.addAll(rightHitPointList); // todo change
			}
		}
		if(!unionList.isEmpty())
			System.out.println("\nUnionList:\n" + unionList); // todo remove
		return unionList;
	}
}
