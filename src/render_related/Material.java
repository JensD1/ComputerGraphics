package render_related;

import misc.CustomColor;

public class Material {

	private CustomColor diffuse;
	private CustomColor specular;
	private double specularExponent;
	private CustomColor emission;
	private CustomColor ambient;

	public Material(){
		this.diffuse = new CustomColor(0, 0, 0);
		this.specular = new CustomColor(0, 0, 0);
		this.specularExponent = 1;
		this.emission = new CustomColor(0, 0, 0);
		this.ambient = new CustomColor(0, 0, 0);
	}

	public Material(CustomColor diffuse, CustomColor specular, double specularExponent,
					CustomColor emission, CustomColor ambient){
		this.diffuse = diffuse;
		this.specular = specular;
		this.specularExponent = specularExponent;
		this.emission = emission;
		this.ambient = ambient;
	}

	public CustomColor getDiffuse() {
		return diffuse;
	}

	public void setDiffuse(CustomColor diffuse) {
		this.diffuse = diffuse;
	}

	public CustomColor getSpecular() {
		return specular;
	}

	public void setSpecular(CustomColor specular) {
		this.specular = specular;
	}

	public double getSpecularExponent() {
		return specularExponent;
	}

	public void setSpecularExponent(double specularExponent) {
		this.specularExponent = specularExponent;
	}

	public CustomColor getEmission() {
		return emission;
	}

	public void setEmission(CustomColor emission) {
		this.emission = emission;
	}

	public CustomColor getAmbient() {
		return ambient;
	}

	public void setAmbient(CustomColor ambient) {
		this.ambient = ambient;
	}
}