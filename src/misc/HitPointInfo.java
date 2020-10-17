package misc;

import java.awt.*;

public class HitPointInfo {
    private boolean isHit;
    private double hitTime;
    private Color color;

    public HitPointInfo(){
        this.hitTime = 0;
        this.isHit = false;
        this.color = Color.BLACK;
    }
    public HitPointInfo(double hitTime){
        this.hitTime = hitTime;
        this.isHit = true;
        this.color = Color.BLACK;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
