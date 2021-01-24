package render_related;

import misc.NoiseClass;
import misc.Point;

public class NoiseTexture extends Texture{

	NoiseClass noiseClass;
	double scale;

	public NoiseTexture(){
		this.noiseClass = new NoiseClass();
		this.scale = 1;
	}

	public NoiseTexture(double scale){
		this.noiseClass = new NoiseClass();
		this.scale = 1;
	}

	@Override
	public double texture(Point hitPoint) {
		return this.noiseClass.turbulence(this.scale, hitPoint);
//		return this.noiseClass.noise(this.scale, hitPoint);
	}

}
