package render_related;

import configuration.Configuration;
import misc.Matrix;
import misc.Operations;
import misc.Point;

import java.text.CompactNumberFormat;

public class WoodTexture extends Texture {

	private double minIntensity;
	private double maxIntensity;
	private double ringThickness;
	private double numberOfWobbles;
	private double skewStrength;
	private double zPhasecoef;
	private int totRatioNumber;
	private int lightPartRatio;
	private Matrix transformation;

	public WoodTexture(double minIntensity, double maxIntensity, double ringThickness, double numberOfWobbles,
					   double skewStrength, double zPhaseCoef) {
		super();
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = ringThickness;
		this.numberOfWobbles = numberOfWobbles;
		this.skewStrength = skewStrength;
		this.zPhasecoef = zPhaseCoef;
		this.totRatioNumber = 2;
		this.lightPartRatio = 1;
		this.transformation = Matrix.createIdentityMatrix();
	}

	public WoodTexture(double minIntensity, double maxIntensity, double ringThickness, double numberOfWobbles,
					   double skewStrength, double zPhaseCoef, int totRatioNumber, int lightPartRatio) {
		super();
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = ringThickness;
		this.numberOfWobbles = numberOfWobbles;
		this.skewStrength = skewStrength;
		this.zPhasecoef = zPhaseCoef;
		this.totRatioNumber = totRatioNumber;
		this.lightPartRatio = lightPartRatio;
		this.transformation = Matrix.createIdentityMatrix();
	}

	public WoodTexture(double minIntensity, double maxIntensity, double ringThickness, double numberOfWobbles,
					   double skewStrength, double zPhaseCoef, int totRatioNumber, int lightPartRatio, boolean isWorldTexture) {
		super(isWorldTexture);
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = ringThickness;
		this.numberOfWobbles = numberOfWobbles;
		this.skewStrength = skewStrength;
		this.zPhasecoef = zPhaseCoef;
		this.totRatioNumber = totRatioNumber;
		this.lightPartRatio = lightPartRatio;
		this.transformation = Matrix.createIdentityMatrix();
	}

	public WoodTexture(double minIntensity, double maxIntensity, double ringThickness, double numberOfWobbles,
					   double skewStrength, double zPhaseCoef, int totRatioNumber, int lightPartRatio, boolean isWorldTexture,
					   Matrix transformation) {
		super(isWorldTexture);
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = ringThickness;
		this.numberOfWobbles = numberOfWobbles;
		this.skewStrength = skewStrength;
		this.zPhasecoef = zPhaseCoef;
		this.totRatioNumber = totRatioNumber;
		this.lightPartRatio = lightPartRatio;
		this.transformation = transformation;
	}

	public WoodTexture() {
		super();
		this.minIntensity = 0;
		this.maxIntensity = 1;
		this.ringThickness = 1;
		this.numberOfWobbles = 1;
		this.skewStrength = 0;
		this.zPhasecoef = 0;
		this.totRatioNumber = 2;
		this.lightPartRatio = 1;
		this.transformation = Matrix.createIdentityMatrix();
	}

	public WoodTexture(double minIntensity, double maxIntensity) {
		super();
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = 1;
		this.numberOfWobbles = 1;
		this.skewStrength = 0;
		this.zPhasecoef = 0;
		this.totRatioNumber = 2;
		this.lightPartRatio = 1;
		this.transformation = Matrix.createIdentityMatrix();
	}

	public WoodTexture(double minIntensity, double maxIntensity, int totRatioNumber, int lightPartRatio) {
		super();
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = 1;
		this.numberOfWobbles = 1;
		this.skewStrength = 0;
		this.zPhasecoef = 0;
		this.totRatioNumber = totRatioNumber;
		this.lightPartRatio = lightPartRatio;
		this.transformation = Matrix.createIdentityMatrix();
	}

	public WoodTexture(double minIntensity, double maxIntensity, boolean isWorldTexture) {
		super(isWorldTexture);
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = 1;
		this.numberOfWobbles = 1;
		this.skewStrength = 0;
		this.zPhasecoef = 0;
		this.totRatioNumber = 2;
		this.lightPartRatio = 1;
		this.transformation = Matrix.createIdentityMatrix();
	}

	public WoodTexture(double minIntensity, double maxIntensity, int totRatioNumber, int lightPartRatio, boolean isWorldTexture) {
		super(isWorldTexture);
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = 1;
		this.numberOfWobbles = 1;
		this.skewStrength = 0;
		this.zPhasecoef = 0;
		this.totRatioNumber = totRatioNumber;
		this.lightPartRatio = lightPartRatio;
		this.transformation = Matrix.createIdentityMatrix();
	}

	public WoodTexture(double minIntensity, double maxIntensity, int totRatioNumber, int lightPartRatio, boolean isWorldTexture, Matrix transformation) {
		super(isWorldTexture);
		this.minIntensity = minIntensity;
		this.maxIntensity = maxIntensity;
		this.ringThickness = 1;
		this.numberOfWobbles = 1;
		this.skewStrength = 0;
		this.zPhasecoef = 0;
		this.totRatioNumber = totRatioNumber;
		this.lightPartRatio = lightPartRatio;
		this.transformation = transformation;
	}

	@Override
	public double texture(Point hitPoint) {
		Point transformedHitPoint = Operations.pointTransformation(this.transformation, hitPoint);

		double x = transformedHitPoint.getX(); // we need this more often
		double y = transformedHitPoint.getY();
		double r = Math.sqrt(Math.pow(x, 2) + Math.pow(transformedHitPoint.getY(), 2));
		double theta;
		if(x != 0) {
			theta = Math.atan2(y,  x);
		} else{
			theta = 0;
		}
		return advancedWood(r, theta, transformedHitPoint);
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
		int temp = ((int) r) % this.totRatioNumber;
		return (temp < this.lightPartRatio) ? 1:0;
	}

	public double getMinIntensity() {
		return minIntensity;
	}

	public void setMinIntensity(double minIntensity) {
		this.minIntensity = minIntensity;
	}

	public double getMaxIntensity() {
		return maxIntensity;
	}

	public void setMaxIntensity(double maxIntensity) {
		this.maxIntensity = maxIntensity;
	}

	public double getRingThickness() {
		return ringThickness;
	}

	public void setRingThickness(double ringThickness) {
		this.ringThickness = ringThickness;
	}

	public double getNumberOfWobbles() {
		return numberOfWobbles;
	}

	public void setNumberOfWobbles(double numberOfWobbles) {
		this.numberOfWobbles = numberOfWobbles;
	}

	public double getSkewStrength() {
		return skewStrength;
	}

	public void setSkewStrength(double skewStrength) {
		this.skewStrength = skewStrength;
	}

	public double getzPhasecoef() {
		return zPhasecoef;
	}

	public void setzPhasecoef(double zPhasecoef) {
		this.zPhasecoef = zPhasecoef;
	}

	public int getTotRatioNumber() {
		return totRatioNumber;
	}

	public void setTotRatioNumber(int totRatioNumber) {
		this.totRatioNumber = totRatioNumber;
	}

	public int getLightPartRatio() {
		return lightPartRatio;
	}

	public void setLightPartRatio(int lightPartRatio) {
		this.lightPartRatio = lightPartRatio;
	}

	public Matrix getTransformation() {
		return transformation;
	}

	public void setTransformation(Matrix transformation) {
		this.transformation = transformation;
	}
}
