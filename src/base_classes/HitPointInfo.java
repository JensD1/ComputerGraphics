package base_classes;

public class HitPointInfo {
    private boolean isHit;
    private double hitTime;

    // todo plaats de nodige velden bij hit

    public HitPointInfo(){
        this.hitTime = 0;
        this.isHit = false;
    }
    public HitPointInfo(double hitTime){
        this.hitTime = hitTime;
        this.isHit = true;
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
}
