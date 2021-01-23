package render_related;

import configuration.Configuration;
import misc.CustomColor;
import misc.HitPointInfo;
import misc.Ray;
import objects.GenericObject;

import java.util.ArrayList;
import java.util.List;

public class World {

	private Camera camera;
	private List<GenericObject> objectList;
	private List<PointLight> lights;
	private CustomColor ambient;

	public World(Camera camera, List<GenericObject> objectList, List<PointLight> lights, CustomColor ambient) {
		this.camera = camera;
		this.objectList = objectList;
		this.lights = lights;
		this.ambient = ambient;
	}

	public World() {
		this.camera = new Camera();
		this.objectList = new ArrayList<>();
		this.lights = new ArrayList<>();
		this.ambient = new CustomColor(0, 0, 0);
	}

	public World(Camera camera) {
		this.camera = camera;
		this.objectList = new ArrayList<>();
		this.lights = new ArrayList<>();
		this.ambient = new CustomColor(0, 0, 0);
	}

	public World(Camera camera, CustomColor ambient) {
		this.camera = camera;
		this.objectList = new ArrayList<>();
		this.lights = new ArrayList<>();
		this.ambient = ambient;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public List<GenericObject> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<GenericObject> objectList) {
		this.objectList = objectList;
	}

	public void addObject(GenericObject object) {
		this.objectList.add(object);
	}

	public List<PointLight> getLights() {
		return lights;
	}

	public void setLights(List<PointLight> lights) {
		this.lights = lights;
	}

	public void addLight(PointLight pointLight) {
		this.lights.add(pointLight);
	}

	public CustomColor getAmbient() {
		return ambient;
	}

	public void setAmbient(CustomColor ambient) {
		this.ambient = ambient;
	}

	/**
	 * Calculates the best hitpoint. This is null in case no object is hit.
	 *
	 * @param ray The ray to calculate hitpoints for
	 * @return HitPointInfo the information about the hitpoint.
	 */
	public HitPointInfo calculateBestHitpoint(Ray ray) {
		// Find all hitpoints with all objects
		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		for (GenericObject object : this.objectList) {
			List<HitPointInfo> tempList = object.calculateHitPoint(ray);
			if (!tempList.isEmpty()) {
				hitPointInfoList.addAll(tempList);
			}
		}

		// Find the best hitpoint of this list
		HitPointInfo bestHitpoint = new HitPointInfo();
		for (HitPointInfo hitPointInfo : hitPointInfoList) {
			if (((hitPointInfo.getHitTime() > Configuration.ROUNDING_ERROR) && hitPointInfo.isHit()) &&
					(!bestHitpoint.isHit() || bestHitpoint.getHitTime() > hitPointInfo.getHitTime())) {
				bestHitpoint = hitPointInfo;
			}
		}
		return bestHitpoint;
	}

	/**
	 * Calculates the best hitpoint exclusive an object. This is null in case no object is hit.
	 *
	 * @param ray             The ray to calculate hitpoints for
	 * @param exclusiveObject The object that is not allowed to be a bestHitPoint
	 * @return HitPointInfo the information about the hitpoint.
	 */
	public HitPointInfo calculateBestHitpoint(Ray ray, GenericObject exclusiveObject) {
		// Find all hitpoints with all objects
		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		for (GenericObject object : this.objectList) {
			if (!object.equals(exclusiveObject)) {
				List<HitPointInfo> tempList = object.calculateHitPoint(ray);
				if (!tempList.isEmpty()) {
					hitPointInfoList.addAll(tempList);
				}
			}
		}

		// Find the best hitpoint of this list
		HitPointInfo bestHitpoint = new HitPointInfo();
		for (HitPointInfo hitPointInfo : hitPointInfoList) {

			if (((hitPointInfo.getHitTime() > Configuration.ROUNDING_ERROR) && hitPointInfo.isHit()) &&
					(!bestHitpoint.isHit() || bestHitpoint.getHitTime() > hitPointInfo.getHitTime())) {
				bestHitpoint = hitPointInfo;
			}
		}
		return bestHitpoint;
	}

	/**
	 * Calculates all hitpoints exclusive an object. This is null in case no object is hit.
	 *
	 * @param ray             The ray to calculate hitpoints for
	 * @param exclusiveObject The object that is not allowed to be a bestHitPoint
	 * @return HitPointInfo the information about the hitpoint.
	 */
	public List<HitPointInfo> calculateAllHitPoints(Ray ray, GenericObject exclusiveObject) {
		// Find all hitpoints with all objects
		List<HitPointInfo> hitPointInfoList = new ArrayList<>();
		for (GenericObject object : this.objectList) {
			if (!object.equals(exclusiveObject)) {
				List<HitPointInfo> tempList = object.calculateHitPoint(ray);
				if (!tempList.isEmpty()) {
					hitPointInfoList.addAll(tempList);
				}
			}
		}

		return hitPointInfoList;
	}
}
