import java.awt.image.BufferedImage;
import java.util.Scanner;

import org.apache.commons.math3.complex.Complex;

public class Main {
	public static void maker() {
		Complex x = new Complex(-0.7269, 0.1889);
		// ImageGenerator g = new ImageGenerator();
		Scanner sc = new Scanner(System.in);
		System.out.println("select one :" + '\n' + "1 : step and imagesize" + '\n' + "2 : complex plan size and step"
				+ '\n' + "3 : imagesize and plan size");
		int choice = sc.nextInt();
		switch (choice) {
		case 1: {
			double step = sc.nextDouble();
			int size = sc.nextInt();
			break;
		}
		case 2: {
			double planSize = sc.nextDouble();
			double step = sc.nextDouble();
			break;
		}
		case 3: {
			int size = sc.nextInt();
			double planSize = sc.nextDouble();
			break;
		}
		}

	}

	private void gen() {
		
	}

	private void zoom(double amount) {

	}

	private void move(double amount, int direction) {

	}

	public static int[] selectsize() {
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir hauteur");
		// String input
		int height = sc.nextInt();
		System.out.println("choisir largeur");
		int length = sc.nextInt();
		int[] rtn = { height, length };
		return rtn;
	}

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
		return new Complex(x, y);
	}

	public static double selectStep() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose the step : ");
		return sc.nextDouble();
	}

	public static int[] selectJulia() {
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir plus grand indice de x dans z");
		int tabsize = sc.nextInt() + 1;
		int[] poly = new int[tabsize];
		for (int i = 0; i < tabsize; i++) {
			System.out.println("choisir le facteur de x^" + i);
			poly[i] = sc.nextInt();
		}
		return poly;
	}

	public static Complex selectJulia2() {
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir la partie entiere de c dans la formule f(x)=z+c, z est un polynome de x");
		double real = sc.nextDouble();
		System.out.println("choisir la partie imaginaire");
		double imaginary = sc.nextDouble();
		Complex c = new Complex(real, imaginary);
		return c;

	}

	public BufferedImage generate() {
		Calcul c = new Calcul.Builder().plan(1.0).size(500, 500).build();
		ImageGenerator g = new ImageGenerator(c);
		return g.image;
	}
	public BufferedImage generate2() {
		Calcul c = new Calcul.Builder().plan(1.0).size(400, 400).build();
		ImageGenerator g = new ImageGenerator(c);
		return g.image;
	}
	
	public Calcul genButton(double plan, int size,double step, Complex c) {
		Calcul calc = new Calcul.Builder().plan(plan).size(size,size).step(step).comp(c).build();
		return calc;
	}

	public static void main(String[] args) {
		/*
		System.out.println("vue terminal(1) ou GUI(2) ?");
		Scanner sc = new Scanner(System.in);
		if (sc.nextInt() == 1) {

		} else {

		}
		*/
		Main m = new Main();
		new View(m);
		Complex x = new Complex(-0.7269, 0.1889);
		// Calcul c = new Calcul.Builder().plan(1.0).size(200,500).build();
		// ImageGenerator g = new ImageGenerator(c);
		// View v = new View(g.image);

		/*
		 * // Complex x = new Complex(-0,7269, 0,1889); Calcul m = new Calcul(); m.comp
		 * = selectJulia2(); m.polynome = selectJulia();
		 * System.out.print("fonction: f(x)="); for (int i = m.polynome.length - 1; i >=
		 * 0; i--) System.out.print(m.polynome[i] + "" + "x" + "^" + i + "+ ");
		 * System.out.print(m.comp); m.fill(); ImageGenerator g = new ImageGenerator();
		 * g.imageDrawer(m.calculate()); View v = new View(g.image); Calcul m = new
		 * Calcul(selectHeight(), selectLength(), selectStep(), selectLeftTopComplex());
		 * m.comp = x; m.fill(); g.imageDrawer(m.calculate()); View v = new
		 * View(g.image);
		 */
	}


}
