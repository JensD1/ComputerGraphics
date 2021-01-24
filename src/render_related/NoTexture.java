package render_related;

import misc.Point;

public class NoTexture extends Texture {

	public NoTexture() {
	}

	@Override
	public double texture(Point hitPoint) {
		return 1;
	}

}
