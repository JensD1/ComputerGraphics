package render_related;

import generic_objects.GenericObject;
import misc.*;
import configuration.Configuration;

import java.awt.*;

public class Renderer{

    public Renderer() {
    }

    public void renderFrame(World world) {
        PixelPlotter pixelPlotter = new PixelPlotter();
        HitPointInfo hitPointInfo;
        for (int c = 0; c < Configuration.SCREEN_WIDTH; c++) {
            for (int r = 0; r < Configuration.SCREEN_HEIGHT; r++) {
                Ray ray = Ray.createRay(world, c, r);
                hitPointInfo = world.calculateBestHitpoint(ray);
                pixelPlotter.addPixelToCanvas(
                        new Pixel(c, r , colorPixel(world, ray, hitPointInfo))
                );
            }
        }
        pixelPlotter.renderFrame();
    }

    public Color colorPixel(World world, Ray ray, HitPointInfo hitPointInfo){
        CustomColor customColor;
        if(hitPointInfo.isHit()){
            customColor = shade(world, ray, hitPointInfo);
        } else {
            customColor = new CustomColor();
        }
        return customColor.checkBounds().toJavaColor();
    }

    public CustomColor shade(World world, Ray ray, HitPointInfo hitPointInfo){
        Vector v = Operations.scalarVectorProduct(-1, ray.getDir()).normalize();
        CustomColor color = new CustomColor();
        color = color.addColor(hitPointInfo.getObject().getMaterial().getEmission()); // add emmision
        color = color.addColor( // add ambient colors
                CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getAmbient(), world.getAmbient())
        );
        hitPointInfo.setNormal(hitPointInfo.getNormal().normalize()); // normalise the normal
        if(Operations.dotProduct(v, hitPointInfo.getNormal()) < 0){
            hitPointInfo.setNormal(Operations.scalarVectorProduct(-1, hitPointInfo.getNormal()));
        }
        for(PointLight light: world.getLights()){
            if(!inShadow(world, light, hitPointInfo)) {
                Vector s = Operations.pointSubstraction(light.getLocation(), hitPointInfo.getHitPoint()).normalize();
                double mDotS = Operations.dotProduct(s, hitPointInfo.getNormal()); // The lambert term
                if (mDotS > 0.0) { // Hitpoint is turned towards the light.
                    CustomColor diffuseColor = CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getDiffuse(), light.getColor()).scalarProduct(mDotS);
                    color = color.addColor(diffuseColor);
                }
                Vector h = Operations.vectorSum(v, s).normalize(); // The halfway vector
                double mDoth = Operations.dotProduct(h, hitPointInfo.getNormal());
                if (mDoth > 0.0) { // specular contribution // todo vraag aan prof of dit binnen de andere if moet
                    double phong = Math.pow(mDoth, hitPointInfo.getObject().getMaterial().getSpecularExponent());
                    CustomColor specColor = CustomColor.colorProduct(hitPointInfo.getObject().getMaterial().getSpecular(), light.getColor()).scalarProduct(phong);
                    color = color.addColor(specColor);
                }
            }
        }
        return color;
    }

    public boolean inShadow(World world, PointLight light, HitPointInfo hitPointInfo){

        Ray ray = new Ray(hitPointInfo.getHitPoint(), light.getLocation());
        boolean inShadow = false;
        for(GenericObject object: world.getObjectList()){
            if(!object.equals(hitPointInfo.getObject())){
                HitPointInfo hitPointInfo1 = object.calculateHitPoint(ray);
                if(hitPointInfo1.getHitTime() >= 0 && hitPointInfo1.getHitTime() <= 1) {
                    inShadow = true;
                    break;
                }
            }
        }
        return inShadow;
    }

}
