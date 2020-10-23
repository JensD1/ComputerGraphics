package render_related;

import misc.CustomColor;
import misc.HitPointInfo;
import misc.Point;
import misc.Ray;
import generic_objects.GenericObject;

import java.util.ArrayList;
import java.util.List;

public class World {

    private Camera camera;
    private List<GenericObject> objectList;
    private List<PointLight> lights;
    private CustomColor ambient;

    public World(Camera camera, List<GenericObject> objectList, List<PointLight> lights, CustomColor ambient){
        this.camera = camera;
        this.objectList = objectList;
        this.lights = lights;
        this.ambient = ambient;
    }

    public World(){
        this.camera = new Camera();
        this.objectList = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.ambient = new CustomColor(0, 0, 0);
    }

    public World(Camera camera){
        this.camera = camera;
        this.objectList = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.ambient = new CustomColor(0, 0, 0);
    }

    public World(Camera camera, CustomColor ambient){
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

    public void addObject(GenericObject object){
        this.objectList.add(object);
    }

    public List<PointLight> getLights() {
        return lights;
    }

    public void setLights(List<PointLight> lights) {
        this.lights = lights;
    }

    public void addLight(PointLight pointLight){
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
     * @param ray The ray to calculate hitpoints for
     * @return HitPointInfo the information about the hitpoint.
     */
    public HitPointInfo calculateBestHitpoint(Ray ray){
        HitPointInfo bestHitpoint = new HitPointInfo();
        for(GenericObject object: this.objectList){
            HitPointInfo tempHitPoint = object.calculateHitPoint(ray);
            if((!bestHitpoint.isHit() && tempHitPoint.isHit()) || (bestHitpoint.getHitTime() > tempHitPoint.getHitTime() && tempHitPoint.getHitTime() > 0 && tempHitPoint.isHit())){ // && tempHitPoint.getHitTime() > getCamera().getDistanceN()
                bestHitpoint = tempHitPoint;
            }
        }
        return bestHitpoint;
    }
}
