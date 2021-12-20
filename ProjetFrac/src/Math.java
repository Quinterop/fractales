import org.apache.commons.math3.complex.*;
import org.apache.commons.math3.util.MathArrays.Function;
import static java.lang.Math.sqrt;

public class Math {
	public Complex comp;
	public Function fun;
	public int [] [] colors;
	private final int matIter = 1000;
	private final double radius = 2.0;
	
	public Math() {
		
	}
	
	public static double square(double number){
		double t;
		double squareroot = number / 2;
		do {
			t = squareroot;
			squareroot = (t + (number / t)) / 2;
		}
		while ((t - squareroot) != 0);
		return squareroot;
	}
	public static void main(String[] args){
		double number = 16;
		double root;
		root = square(number);
		System.out.println("Number : "+number);
		System.out.println("Square Root : "+root);
	}
	
	double module(Complex z) {
		return Math.square(z.getReal()*z.getReal() + z.getImaginary()*z.getImaginary());
	}
	
	int divergenceIndex(Complex z0) {
		int ite = 0;
		Complex zn = z0;
		// sortie de boucle si divergence
		while (ite < matIter && module(zn) <= radius) {
			zn = fun(zn);
			ite ++;
		}
		return ite;
	}

	static int[][] calculate() {
		int [][] tmp = {{0,1},{1,2},{0,5},{1,9}};
		return tmp; //
	}
}
