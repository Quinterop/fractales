import java.util.Scanner;

import org.apache.commons.math3.complex.Complex;

public class Main {
	
	/*public int[] selectsize() {
		Scanner sc = new Scanner(System.in);
		System.out.println("select height");
		// String input
		int height = sc.nextInt();
		System.out.println("select length");
		int length = sc.nextInt();
		int[] rtn = {height, length};
		return rtn;
	}*/
	
	public static int selectHeight() {
		Scanner sc = new Scanner(System.in);
		System.out.println("select height");
		// String input
		return sc.nextInt();
	}
	
	public static int selectLength() {
		Scanner sc = new Scanner(System.in);
		System.out.println("select length");
		// String input
		return sc.nextInt();
	}
	
	
	public static Complex selectLeftTopComplex() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose the top left point of the rectangle");
		System.out.println("Choose the real part : ");
		int x = sc.nextInt();
		System.out.println("Choose the imaginary part : ");
		int y = sc.nextInt();
		return new Complex(x,y);	
	}
	
	public static double selectStep() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose the step : ");
		return sc.nextDouble();
	}

	public static void main(String[] args) {
		Complex x = new Complex(-0.7269, 0.1889);
		Calcul m = new Calcul(selectHeight(), selectLength(), selectStep(), selectLeftTopComplex());
		m.comp = x;
		m.fill();
		ImageGenerator g = new ImageGenerator();
		g.imageDrawer(m.calculate());
		View v = new View(g.image);
	}
}