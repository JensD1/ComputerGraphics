package render_related;

import configuration.Configuration;
import misc.Point;

import java.text.CompactNumberFormat;

public class WoodTexture extends Texture {

	private double minIntensity;
	private double maxIntensity;
	private double ringThickness;
	private double numberOfWobbles;
	private double skewStrength;
	private double zPhasecoef;

	public WoodTexture(double minIntensity, double maxIntensity, double ringThickness, double numberOfWobbles,
					   double skewStrength, double zPhaseCoef) {
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = ringThickness;
		this.numberOfWobbles = numberOfWobbles;
		this.skewStrength = skewStrength;
		this.zPhasecoef = zPhaseCoef;
	}

	public WoodTexture() {
		this.minIntensity = 0;
		this.maxIntensity = 1;
		this.ringThickness = 1;
		this.numberOfWobbles = 1;
		this.skewStrength = 0;
		this.zPhasecoef = 0;
	}

	public WoodTexture(double minIntensity, double maxIntensity) {
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = 1;
		this.numberOfWobbles = 1;
		this.skewStrength = 0;
		this.zPhasecoef = 0;
	}

	@Override
	public double texture(Point hitPoint) {
		double r = Math.sqrt(Math.pow(hitPoint.getX(), 2) + Math.pow(hitPoint.getY(), 2));
		double x = hitPoint.getX();
		double theta;
		if(hitPoint.getX() != 0) {
			theta = Math.atan2(hitPoint.getY(),  x);
		} else{
			theta = 0;
		}
		return advancedWood(r, theta, hitPoint);
	}

	private double advancedWood(double r, double theta, Point point) {
		double ringsCalc = rings(
				(r / this.ringThickness) +
				(this.skewStrength * Math.sin(theta * numberOfWobbles + this.zPhasecoef * point.getZ()))
		);
		return this.minIntensity + ((this.maxIntensity - this.minIntensity) * ringsCalc);
	}

	private double simpleWood(double r) {
		return this.minIntensity + (this.maxIntensity - this.minIntensity) * rings(r / this.ringThickness);
	}

	private int rings(double r) {
		return ((int) r) % 2;
	}
}
