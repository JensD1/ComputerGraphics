package misc;

import generic_objects.GenericObject;

public class HitPointInfo {
	private boolean isHit;
	private double hitTime;
	private GenericObject object;
	private Vector normal;
	private Point hitPoint;
	private boolean inShadow;

	public HitPointInfo() {
		this.hitTime = -1;
		this.isHit = false;
		this.object = null;
		this.normal = null;
		this.hitPoint = null;
		this.inShadow = false;
	}

	public HitPointInfo(double hitTime, GenericObject object) {
		this.hitTime = hitTime;
		this.isHit = true;
		this.object = object;
		this.normal = null;
		this.hitPoint = null;
		this.inShadow = false;
	}

	public HitPointInfo(GenericObject object, Point hitPoint, double hitTime,
						Vector normal) {
		this.hitTime = hitTime;
		this.isHit = true;
		this.object = object;
		this.normal = normal;
		this.hitPoint = hitPoint;
		this.inShadow = false;
	}

	public HitPointInfo(GenericObject object, Point hitPoint, double hitTime,
						Vector normal, boolean inShadow) {
		this.hitTime = hitTime;
		this.isHit = true;
		this.object = object;
		this.normal = normal;
		this.hitPoint = hitPoint;
		this.inShadow = inShadow;
	}

	public double getHitTime() {
		return hitTime;
	}

	public void setHitTime(double hitTime) {
		this.hitTime = hitTime;
	}

	public boolean isHit() {
		return isHit;
	}

	public void setHit(boolean hit) {
		isHit = hit;
	}

	public GenericObject getObject() {
		return object;
	}

	public void setObject(GenericObject object) {
		this.object = object;
	}

	public Vector getNormal() {
		return normal;
	}

	public void setNormal(Vector normal) {
		this.normal = normal;
	}

	public Point getHitPoint() {
		return hitPoint;
	}

	public void setHitPoint(Point hitPoint) {
		this.hitPoint = hitPoint;
	}

	public boolean isInShadow() {
		return inShadow;
	}

	public void setInShadow(boolean inShadow) {
		this.inShadow = inShadow;
	}

	@Override
	public String toString() {
		return "HitPointInfo{" +
				"isHit=" + isHit +
				", hitTime=" + hitTime +
				", object=" + object +
				", normal=" + normal +
				", hitPoint=" + hitPoint +
				", inShadow=" + inShadow +
				'}';
	}
}
