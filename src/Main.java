import render_related.*;

public class Main {

	public static void main(String[] args) {
//		Render the scene
//		World world = CreateScene.booleanDifferenceTest();
//		World world = CreateScene.booleanUnionTest();
//		World world = CreateScene.booleanIntersectionTest();
		World world = CreateScene.createSceneWithBooleansAndWater();
//		World world = CreateScene.simpleObjectTestScene();
//		World world = CreateScene.simpleBoundingBoxTest();
		Renderer renderer = new Renderer();
		renderer.renderFrame(world);
	}
}
