import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.MathArrays.Function;
import java.math.*;
import java.lang.Math;

public class Calcul {
	public Complex comp;
	public Function fun;
	public int[][] colors = new int[201][201];
	private final int matIter = 1000;
	private final double radius = 2.0;
	public Complex[][] graph = new Complex[201][201];

	double module(Complex z) {
		return Math.sqrt(z.getReal() * z.getReal() + z.getImaginary() * z.getImaginary());
	}

	int divergenceIndex(Complex z0) {
		int ite = 0;
		Complex zn = z0;
		// sortie de boucle si divergence
		while (ite < matIter && (Math.abs(module(zn))) <= radius) {
			zn = zn.multiply(zn);		
			zn=zn.add(comp);

			
			ite++;
		}
		System.out.println(ite);

		return ite;
	}

	Complex[][] fill() {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[0].length; j++) {
				graph[i][j] = new Complex(-1 + j * 0.01, 1 - i * 0.01);
			}
		}
		System.out.println(graph[100][100].getReal()+" "+graph[100][100].getImaginary());
		return graph;
	}

	int[][] calculate() {
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[0].length; j++) {
				colors[i][j] = divergenceIndex(graph[i][j]);
			}
		}
		return colors;
	}

}
