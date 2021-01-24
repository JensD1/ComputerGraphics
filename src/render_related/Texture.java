package render_related;

import misc.Point;

public abstract class Texture {
	private boolean isWorldTexture;

	Texture(){
		this.isWorldTexture = false;
	}

	Texture(boolean isWorldTexture){
		this.isWorldTexture = isWorldTexture;
	}

	public abstract double texture(Point hitPoint);

	public boolean isWorldTexture() {
		return isWorldTexture;
	}

	public void setWorldTexture(boolean worldTexture) {
		isWorldTexture = worldTexture;
	}
}
