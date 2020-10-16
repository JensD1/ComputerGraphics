package generic_objects;

import base_classes.HitPointInfo;
import base_classes.Ray;

public interface GenericObject {
    public HitPointInfo calculateHitPoint(Ray ray);
}
