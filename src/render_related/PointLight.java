package render_related;

import misc.CustomColor;
import misc.Point;
import misc.Vector;

public class PointLight {
	private Point location;
	private CustomColor color;

	public PointLight(Point location, CustomColor color){
		this.location = location;
		this.color = color;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public CustomColor getColor() {
		return color;
	}

	public void setColor(CustomColor color) {
		this.color = color;
	}
}
