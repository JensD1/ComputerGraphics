package generic_objects;

import misc.HitPointInfo;
import misc.Ray;

import java.awt.*;

public abstract class GenericObject {

    // todo zet kleur hier en plaats in HitPointInfo

    protected Color color;

    public GenericObject(){
        this.color = Color.BLACK;
    }

    public GenericObject(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract HitPointInfo calculateHitPoint(Ray ray);
}
