package render_related;

import misc.NoiseClass;
import misc.Point;

public class NoiseTexture extends Texture{

	NoiseClass noiseClass;
	double scale;
	double min;
	double max;

	public NoiseTexture(){
		super();
		this.noiseClass = new NoiseClass();
		this.scale = 1;
		this.min = 0;
		this.max = 1;
	}

	public NoiseTexture(double scale){
		super();
		this.noiseClass = new NoiseClass();
		this.scale = 1;
		this.min = 0;
		this.max = 1;
	}

	public NoiseTexture(boolean isWorldTexture){
		super(isWorldTexture);
		this.noiseClass = new NoiseClass();
		this.scale = 1;
		this.min = 0;
		this.max = 1;
	}

	public NoiseTexture(double scale, boolean isWorldTexture){
		super(isWorldTexture);
		this.noiseClass = new NoiseClass();
		this.scale = 1;
		this.min = 0;
		this.max = 1;
	}

	public NoiseTexture(double scale, boolean isWorldTexture, double min, double max){
		super(isWorldTexture);
		this.noiseClass = new NoiseClass();
		this.scale = 1;
		this.min = min;
		this.max = max;
	}

	@Override
	public double texture(Point hitPoint) {
		return min + (max - min) * this.noiseClass.turbulence(this.scale, hitPoint);
//		return this.noiseClass.noise(this.scale, hitPoint);
	}

	public NoiseClass getNoiseClass() {
		return noiseClass;
	}

	public void setNoiseClass(NoiseClass noiseClass) {
		this.noiseClass = noiseClass;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}
}
