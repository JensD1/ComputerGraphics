package render_related;

import base_classes.HitPointInfo;
import base_classes.Ray;
import generic_objects.GenericObject;

import java.util.ArrayList;
import java.util.List;

public class World {

    private Camera camera;
    private List<GenericObject> objectList;

    public World(Camera camera, List<GenericObject> objectList){
        this.camera = camera;
        this.objectList = objectList;
    }

    public World(){
        this.camera = new Camera();
        this.objectList = new ArrayList<>();
    }

    public World(Camera camera){
        this.camera = camera;
        this.objectList = new ArrayList<>();
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

    /**
     * Calculates the best hitpoint. This is null in case no object is hit.
     * @param ray The ray to calculate hitpoints for
     * @return HitPointInfo the information about the hitpoint.
     */
    public HitPointInfo calculateBestHitpoint(Ray ray){
        HitPointInfo bestHitpoint = null;
        for(GenericObject object: this.objectList){
            HitPointInfo tempHitPoint = object.calculateHitPoint(ray);
            if(bestHitpoint == null || (bestHitpoint.getHitTime() > tempHitPoint.getHitTime() && tempHitPoint.getHitTime()>0)){ // && tempHitPoint.getHitTime() > getCamera().getDistanceN()
                bestHitpoint = tempHitPoint;
            }
        }
        return bestHitpoint;
    }
}
