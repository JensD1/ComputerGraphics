package misc;

import java.util.Random;

public class NoiseClass {

	private double[] noiseTable;
	private int[] index;

	public NoiseClass() {
		int i;
		Random random = new Random();
		index = new int[256];
		for (i = 0; i < 256; i++) { // fill the array with indices
			index[i] = i;
		}
		for (i = 0; i < 256; i++) { // shuffle it
			int which = random.nextInt(256); // choose random place in array
			int tmp = index[which]; // swap them
			index[which] = index[i];
			index[i] = tmp;
		}
		this.noiseTable = new double[256];
		for (i = 0; i < 256; i++) {
			this.noiseTable[i] = random.nextDouble();
		}
	}

	public double turbulence(double scale, Point point){
		return 0.5 * noise(scale, point) + 0.25 * noise(2 * scale, point) + 0.125 * noise(4 * scale, point);
	}

	public double noise(double scale, Point point) { // linearly interpolated lattice noise
		double[][][] d = new double[2][2][2];
		Point pp = new Point();
		pp.setX(point.getX() * scale + 10000); // offset avoids negative values
		pp.setY(point.getY() * scale + 10000);
		pp.setZ(point.getZ() * scale + 10000);
		long ix = (long) pp.getX();
		long iy = (long) pp.getY();
		long iz = (long) pp.getZ();

		double tx, ty, tz, x0, x1, x2, x3, y0, y1;

		// fractional parts
		tx = pp.getX() - ix;
		ty = pp.getY() - iy;
		tz = pp.getZ() - iz;

		double mtx = 1.0 - tx;
		double mty = 1.0 - ty;
		double mtz = 1.0 - tz;

		// Get noise at 8 lattice points
		for (int k = 0; k <= 1; k++) {
			for (int j = 0; j <= 1; j++) {
				for (int i = 0; i <= 1; i++) {
					d[k][j][i] = latticeNoise((int)ix + i, (int)iy + j, (int)iz + k);
				}
			}
		}

		x0 = lerp(tx, d[0][0][0], d[0][0][1]);
		x1 = lerp(tx, d[0][1][0], d[0][1][1]);
		x2 = lerp(tx, d[1][0][0], d[1][0][1]);
		x3 = lerp(tx, d[1][1][0], d[1][1][1]);
		y0 = lerp(ty, x0, x1);
		y1 = lerp(ty, x2, x3);
		return lerp(tz, y0, y1);
	}

	private double latticeNoise(int i, int j, int k) { // return noise value on an integer lattice
		return noiseTable[index(i, j, k)];
	}

	private int perm(int x) {
		return index[(x) & 0xFF];
	}

	private int index(int ix, int iy, int iz) {
		return perm((ix) + perm((iy) + perm(iz)));
	}

	private double lerp(double f, double a, double b) {
		return a + (b - a) * f;
	}

}
