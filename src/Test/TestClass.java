package Test;

import misc.*;
import misc.Point;
import objects.Cube;
import objects.Sphere;
import render_related.Materials;
import render_related.Pixel;
import render_related.PixelPlotter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TestClass {
	public static void main(String[] args) {
		// see of the columns and rows are correct.
		// The anwser should be:
		// [15 14 13 12]
		// [11 10 9 8]
		// [7 6 5 4]
		// [3 2 1 0]
		double[][] elementsMatrix1 = {{15, 14, 13, 12}, {11, 10, 9, 8} ,{7, 6, 5, 4} ,{3, 2, 1, 0}};
		Matrix matrix1 = new Matrix(elementsMatrix1);

		System.out.println(matrix1);

		double[][] elementsMatrix2 = {{0, 1, 2, 3}, {4, 5, 6, 7} ,{8, 9, 10, 11} ,{12, 13, 14, 15}};
		Matrix matrix2 = new Matrix(elementsMatrix2);
		System.out.println(matrix2);

		Matrix matrix3 = Operations.matrixProduct(matrix1, matrix2);
		System.out.println(matrix3);

		Matrix matrix4 = Operations.matrixAddition(matrix1, matrix2);
		System.out.println(matrix4);

		// test transformations
		TransformationBuilder transformationBuilder = new TransformationBuilder();
		Matrix matrix5 = transformationBuilder.translation(1, 2, 3).create();
		System.out.println(matrix5);


		Matrix matrix6 = transformationBuilder.reset().translation(1, 2, 3).shearingXY(3).rotateY(60).create();
		System.out.println(matrix6);
		Matrix matrix7 = transformationBuilder.inverseRotateY(60).inverseShearingXY(3).inverseTranslation(1, 2, 3).create();
		System.out.println(matrix7);
		Vector vector1 = new Vector(1, 7, -5);
		System.out.println(vector1);
		Vector vector2 = Operations.vectorTransformation(matrix6, vector1);
		System.out.println(matrix6);
		System.out.println(vector2);

		double[][] elementsMatrix = {{6, 2, 0, 2}, {3, 7, 8, 3} ,{4, 6, 8, 1} ,{9, 4, 7, 3}};
		double[][] elementsMatrix3 = {{12, 4, 26, 5}, {7, 3, 5, 4} ,{3, 4, 45, 5} ,{7, 5, 3, 3}};
		Matrix matrix8 = new Matrix(elementsMatrix);
		Matrix matrix9 = new Matrix(elementsMatrix3);
		System.out.println(Operations.matrixProduct(matrix8, matrix9));

		Vector vector3 = new Vector(1, 2, 3);
		Vector vector4 = new Vector(-3, 2, 1);
		Vector vector5 = Operations.vectorCrossProduct(vector3, vector4);
		System.out.println(vector5);

		elementsMatrix = new double[][]{{6, 2, 0, 2}, {3, 7, 8, 3}, {4, 6, 8, 1}, {9, 4, 7, 3}};
		matrix1 = new Matrix(elementsMatrix);
		System.out.println(matrix1);
		System.out.println(matrix1.transpose());


//	 	test if a ray hits the translated sphere correctly
		Matrix trMatrix = transformationBuilder.reset().translation(2, 2, 2).create();
		Matrix invMatrix = transformationBuilder.reset().inverseTranslation(2, 2, 2).create();
		Ray ray = new Ray(new Point(-5, 2, 2), new Vector(1, 0, 0));
		Ray inverseRay = new Ray(
				Operations.pointTransformation(invMatrix, ray.getOrigin()),
				Operations.vectorTransformation(invMatrix, ray.getDir())
		);
		System.out.println(inverseRay.getDir());
		System.out.println(inverseRay.getOrigin());

		Sphere sphere = new Sphere(trMatrix, invMatrix, Materials.getCopper());
		List<HitPointInfo> hitPointInfos = sphere.calculateHitPoint(ray);
		System.out.println(hitPointInfos.get(0).getHitTime());
		System.out.println(hitPointInfos.get(0).isHit());

		ray = new Ray(new Point(5, 15, 10), new Vector(-1, -1, -1));
		sphere = new Sphere();
		sphere.setTransformation(transformationBuilder.reset().translation(-5, 5, 0).create());
		sphere.setInverseTransformation(transformationBuilder.reset().inverseTranslation(-5, 5, 0).create());
		System.out.println(sphere.calculateHitPoint(ray).get(0).getHitTime());

//	 	check if hitpoint list is sorted well
		List<HitPointInfo> list = new ArrayList<>();
		list.add(new HitPointInfo(-5, new Cube()));
		list.add(new HitPointInfo(13, new Cube()));
		list.add(new HitPointInfo(0, new Cube()));
		list.add(new HitPointInfo(5, new Cube()));
		list.add(new HitPointInfo(0, new Cube()));
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		Iterator<HitPointInfo> iterator = list.iterator();
		System.out.println(iterator.next());
	}
}
