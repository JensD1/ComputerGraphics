import render_related.CreateScene;
import render_related.Renderer;
import render_related.World;

public class Main {

	public static void main(String[] args) {
//		Render the scene
		boolean saveImage = true;
		String titleOfImage = "ExamenDemo";

		// Test Scenes
//		World world = CreateScene.booleanDifferenceTest();
//		World world = CreateScene.booleanUnionTest();
//		World world = CreateScene.booleanIntersectionTest();
//		World world = CreateScene.simpleObjectTestScene();
//		World world = CreateScene.simpleBoundingBoxTest();
//		World world = CreateScene.simpleTexturesTest();
//		World world = CreateScene.water2Test();
//		World world = CreateScene.waterCubeTest();
//		World world = CreateScene.materialTest();
//		World world = CreateScene.testSave();

		// Finished Scenes
//		World world = CreateScene.createSceneWithBooleansAndWater();
//		World world = CreateScene.forestScene();
//		World world = CreateScene.ruby();
//		World world = CreateScene.lensScene();
//		World world = CreateScene.tableWithGlass();
//		World world = CreateScene.lightScene();
		World world = CreateScene.oceanScene();


		Renderer renderer = new Renderer();

		renderer.renderFrame(world, saveImage, titleOfImage);
	}
}
