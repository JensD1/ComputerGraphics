package misc;

import java.awt.*;

public class CustomColor {

	private double[] color;

	public CustomColor(){
		this.color = new double[3];
		for(int i = 0; i<3; i++){
			this.color[i] = 0;
		}
	}

	public CustomColor(CustomColor otherColor){
		this.color = new double[3];
		System.arraycopy(otherColor.color, 0, this.color, 0, 3);
	}

	public CustomColor(double x, double y, double z){
		this.color = new double[3];
		this.color[0] = x;
		this.color[1] = y;
		this.color[2] = z;
	}

	public CustomColor(double[] color){
		if(color.length != 3)
			throw new IllegalArgumentException("base_classes.Vector is not of the correct size.");
		this.color = color;
	}

	public void setX(double value){
		this.color[0] = value;
	}

	public void setY(double value){
		this.color[1] = value;
	}

	public void setZ(double value){
		this.color[2] = value;
	}

	public double getX(){
		return this.color[0];
	}

	public double getY(){
		return this.color[1];
	}

	public double getZ(){
		return this.color[2];
	}

	public double getElement(int element){
		return this.color[element];
	}

	public void setElement(int element, double value){
		this.color[element] = value;
	}

	public CustomColor scalarProduct(double scalar){
		for(int i = 0; i<3; i++){
			this.setElement(i, scalar * this.getElement(i));
		}
		return new CustomColor(this);
	}

	/**
	 * This method will return a scalar that is the result of a dot product.
	 * @param color1
	 * @param color2
	 * @return
	 */
	public static CustomColor colorProduct(CustomColor color1, CustomColor color2) {
		CustomColor customColor = new CustomColor();
		for (int i = 0; i < 3; i++) {
			customColor.setElement(i, color1.getElement(i) * color2.getElement(i));
		}
		return customColor;
	}

	/**
	 * This method will return a Color that is the result of an addition of two colors
	 * @param color1
	 * @param color2
	 * @return
	 */
	public static CustomColor addColors(CustomColor color1, CustomColor color2) {
		CustomColor customColor = new CustomColor();
		for (int i = 0; i < 3; i++) {
			customColor.setElement(i, color1.getElement(i) + color2.getElement(i));
		}
		return customColor;
	}

	/**
	 * This method will return a Color that is the result of an addition of two colors
	 * @param color
	 * @return
	 */
	public CustomColor addColor(CustomColor color) {
		CustomColor returnColor = new CustomColor();
		for (int i = 0; i < 3; i++) {
			returnColor.setElement(i, this.getElement(i) + color.getElement(i));
		}
		return returnColor;
	}

	public CustomColor checkBounds(){
		for(int i = 0; i<3; i++){
			if(this.getElement(i) < 0)
				this.setElement(i, 0);
			if(this.getElement(i) > 1)
				this.setElement(i, 1);
		}
		return this;
	}

	@Override
	public String toString() {
		String returnString = "";
		for(int i = 0; i<3; i++){
			returnString = returnString.concat("["+color[i] +"]\n");
		}
		return returnString;
	}

	public Color toJavaColor(){
		return new Color((int)(this.getX() * 255), (int)(this.getY() * 255), (int)(this.getZ() * 255));
	}

}
