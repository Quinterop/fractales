import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.util.MathArrays.Function;
import java.math.*;
import java.lang.Math;

public class Calcul {
	public Complex comp; 
	public Function fun;
	//public int[][] colors = new int[201][201];
	public int[][] colors;
	private final int matIter = 1000;
	private final double radius = 2.0;
	//public Complex[][] graph = new Complex[201][201];
	public Complex[][] graph;
	public Complex topLeftComplex;
	public double step;
	
	public Calcul(int x, int y, double h, Complex c) {
		colors = new int [x] [y];
		graph = new Complex [x] [y];
		step = h;
		topLeftComplex = c;
	}
	
	public void setSize(int x, int y) {
		colors = new int [x] [y];
		graph = new Complex [x] [y];
	}
	
	public void setStep(int h) {
		step = h;
	}
	
	public void setTopLeftComplex(Complex c) {
		topLeftComplex = c;
	}

	double module(Complex z) {
		return Math.sqrt(z.getReal() * z.getReal() + z.getImaginary() * z.getImaginary());
	}

	int divergenceIndex(Complex z0) {
		int ite = 0;
		Complex zn = z0;
		// sortie de boucle si divergence
		while (ite < matIter && (module(zn)) <= radius) {
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
				graph[i][j] = new Complex(topLeftComplex.getReal() + j * step, topLeftComplex.getImaginary() - i * step);
			}
		}
		//System.out.println(graph[100][100].getReal()+" "+graph[100][100].getImaginary());
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
