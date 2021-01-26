package render_related;

import misc.CustomColor;
import misc.Matrix;

public class Material {

	private CustomColor diffuse;
	private CustomColor specular;
	private double specularExponent;
	private CustomColor emission;
	private CustomColor ambient;
	private double reflectionCoefficient;
	private double refractionCoefficient;
	private double relativeLightSpeed;
	private Texture texture;

	public Material(){
		this.diffuse = new CustomColor(0, 0, 0);
		this.specular = new CustomColor(0, 0, 0);
		this.specularExponent = 1;
		this.emission = new CustomColor(0, 0, 0);
		this.ambient = new CustomColor(0, 0, 0);
		this.reflectionCoefficient = 0;
		this.refractionCoefficient = 0;
		this.relativeLightSpeed = 1;
		this.texture = new NoTexture();
	}

	public Material(CustomColor diffuse, CustomColor specular, double specularExponent,
					CustomColor emission, CustomColor ambient, double reflectionCoefficient,
					double refractionCoefficient, double relativeLightSpeed){
		this.diffuse = diffuse;
		this.specular = specular;
		this.specularExponent = specularExponent;
		this.emission = emission;
		this.ambient = ambient;
		this.reflectionCoefficient = reflectionCoefficient;
		this.refractionCoefficient = refractionCoefficient;
		this.relativeLightSpeed = relativeLightSpeed;
		this.texture = new NoTexture();
	}

	public Material(CustomColor diffuse, CustomColor specular, double specularExponent,
					CustomColor emission, CustomColor ambient, double reflectionCoefficient,
					double refractionCoefficient, double relativeLightSpeed, Texture texture){
		this.diffuse = diffuse;
		this.specular = specular;
		this.specularExponent = specularExponent;
		this.emission = emission;
		this.ambient = ambient;
		this.reflectionCoefficient = reflectionCoefficient;
		this.refractionCoefficient = refractionCoefficient;
		this.relativeLightSpeed = relativeLightSpeed;
		this.texture = texture;
	}

	public Material(Material material){
		this.diffuse = new CustomColor(material.diffuse);
		this.specular = new CustomColor(material.specular);
		this.specularExponent = material.specularExponent;
		this.emission = new CustomColor(material.emission);
		this.ambient = new CustomColor(material.ambient);
		this.reflectionCoefficient = material.reflectionCoefficient;
		this.refractionCoefficient = material.refractionCoefficient;
		this.relativeLightSpeed = material.relativeLightSpeed;
		Texture otherTexture = material.getTexture();
		if(otherTexture.getClass() == WoodTexture.class){
			WoodTexture woodTexture = (WoodTexture) otherTexture;
			this.texture = new WoodTexture(woodTexture.getMinIntensity(), woodTexture.getMaxIntensity(), woodTexture.getRingThickness(), woodTexture.getNumberOfWobbles(),
					woodTexture.getSkewStrength(), woodTexture.getzPhasecoef(), woodTexture.getTotRatioNumber(), woodTexture.getLightPartRatio(), woodTexture.isWorldTexture());
		}
		if(otherTexture.getClass() == NoiseTexture.class){
			NoiseTexture noiseTexture = (NoiseTexture)otherTexture;
			this.texture = new NoiseTexture(noiseTexture.getScale(), noiseTexture.isWorldTexture(), noiseTexture.getMin(), noiseTexture.getMax());
		}
		if(otherTexture.getClass() == NoTexture.class){
			this.texture = new NoTexture();
		}
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

	public double getReflectionCoefficient() {
		return reflectionCoefficient;
	}

	public void setReflectionCoefficient(double reflectionCoefficient) {
		this.reflectionCoefficient = reflectionCoefficient;
	}

	public double getRefractionCoefficient() {
		return refractionCoefficient;
	}

	public void setRefractionCoefficient(double refractionCoefficient) {
		this.refractionCoefficient = refractionCoefficient;
	}

	public double getRelativeDensity() {
		return relativeLightSpeed;
	}

	public void setRelativeDensity(double relativeLightSpeed) {
		this.relativeLightSpeed = relativeLightSpeed;
	}

	public double getRelativeLightSpeed() {
		return relativeLightSpeed;
	}

	public void setRelativeLightSpeed(double relativeLightSpeed) {
		this.relativeLightSpeed = relativeLightSpeed;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
