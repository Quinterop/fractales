import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.apache.commons.math3.complex.Complex;

public class Controller {
	private Fractale calcul;

	public Controller(Fractale defaultCalcul) throws InterruptedException, IOException {
		if (chooseView()) {
			Fractale.Builder b = maker();
			this.calcul = b.build();
			ImageGenerator img = new ImageGenerator(calcul,Runtime.getRuntime().availableProcessors());
			new View(img.image);
			File outputfile = new File("image.jpg");
			ImageIO.write(img.image, "jpg", outputfile);
		} else {
			this.calcul = defaultCalcul;
			this.initDefault();
		}
	}

	public boolean chooseView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("vue terminale ->1. vue IG -> 2");
		if (sc.nextInt() == 1)
			return true;
		return false;
	}

	public Fractale.Builder maker() {
		Complex x = selectJulia();
		// ImageGenerator g = new ImageGenerator();
		Scanner sc = new Scanner(System.in);
		System.out.println("select one :" + '\n' + 
				"1 : step and imagesize" + '\n' + 
				"2 : complex plan size and step"
				+ '\n' + "3 : imagesize and plan size");
		int choice = sc.nextInt();
		double step = 0.0;
		int size = 0;
		double planSize = 0.0;
		switch (choice) {
		case 1: {
			step = sc.nextDouble();
			size = sc.nextInt();
			break;
		}
		case 2: {
			planSize = sc.nextDouble();
			step = sc.nextDouble();
			break;
		}
		case 3: {
			size = sc.nextInt();
			planSize = sc.nextDouble();
			break;
		}
		}
		System.out.println("choisir le nombre d'iterations max");
		int maxIter = sc.nextInt();
		return new Fractale.Builder().step(step).plan(planSize).size(size,size).maxIter(maxIter);
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

	public static Complex selectJulia() {
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir la partie entiere de c dans la formule f(x)=z+c, z est un polynome de x");
		double real = sc.nextDouble();
		System.out.println("choisir la partie imaginaire");
		double imaginary = sc.nextDouble();
		Complex c = new Complex(real, imaginary);
		return c;
	}

	public Complex getOrigin() {
		return calcul.getOrigin();
	}

	public void generate() throws InterruptedException {
		Fractale c = new Fractale.Builder().plan(0.5).size(500, 500).build();
	}

	public void genButton(double plan, int size, double step, Complex c, int colors, Complex newOrigin) {
		Fractale calc = new Fractale.Builder().plan(plan).size(size, size).step(step).comp(c).col(colors)
				.graphOrigin(newOrigin).build();
		this.calcul = calc;

	}

	public BufferedImage generateImage() throws InterruptedException {
		ImageGenerator g = new ImageGenerator(calcul, Runtime.getRuntime().availableProcessors());
		return g.image;
	}

	public void initDefault() throws InterruptedException {
		new View(this);
	}

	public static void main(String[] args) throws InterruptedException, IOException {

		Fractale defaultCalcul = new Fractale.Builder().step(0.01).size(500, 500).build();
		Controller m = new Controller(defaultCalcul);
		Complex y = new Complex(-0.7269, 0.1889);

	}

	/*
	 * public static int[] selectJulia() { Scanner sc = new Scanner(System.in);
	 * System.out.println("choisir plus grand indice de x dans z"); int tabsize =
	 * sc.nextInt() + 1; int[] poly = new int[tabsize]; for (int i = 0; i < tabsize;
	 * i++) { System.out.println("choisir le facteur de x^" + i); poly[i] =
	 * sc.nextInt(); } return poly; }
	 */
	// Fractale c = new Fractale.Builder().size(2000,2000).step(0.001).build();
	// ImageGenerator g = new
	// ImageGenerator(c,Runtime.getRuntime().availableProcessors());

	/*
	 * Complex x = new Complex(-0.7269, 0.1889); Fractale m = new Fractale(); m.comp =
	 * selectJulia2(); m.polynome = selectJulia();
	 * System.out.print("fonction: f(x)="); for (int i = m.polynome.length - 1; i >=
	 * 0; i--) System.out.print(m.polynome[i] + "" + "x" + "^" + i + "+ ");
	 * System.out.print(m.comp); m.fill(); ImageGenerator g = new ImageGenerator();
	 * g.imageDrawer(m.calculate()); View v = new View(g.image); Fractale m = new
	 * Fractale(selectHeight(), selectLength(), selectStep(), selectLeftTopComplex());
	 * m.comp = x; m.fill(); g.imageDrawer(m.calculate()); View v = new
	 * View(g.image); <<<<<<< HEAD ======= public BufferedImage generate() { Fractale
	 * c = new Fractale.Builder().plan(1.0).size(500, 500).build(); ImageGenerator g =
	 * new ImageGenerator(c); return g.image; } public BufferedImage generate2() {
	 * Fractale c = new Fractale.Builder().plan(1.0).size(400, 400).build();
	 * ImageGenerator g = new ImageGenerator(c); return g.image; } >>>>>>> branch
	 * 'Stream' of
	 * https://gaufre.informatique.univ-paris-diderot.fr/sya/projetfract.git
	 * 
	 * 
	 * public static void main(String[] args) { /*
	 * System.out.println("vue terminal(1) ou GUI(2) ?"); Scanner sc = new
	 * Scanner(System.in); if (sc.nextInt() == 1) {
	 * 
	 * } else {
	 * 
	 * }
	 */

	// Fractale c = new Fractale.Builder().plan(1.0).size(200,500).build();
	// ImageGenerator g = new ImageGenerator(c);
	// View v = new View(g.image);

	/*
	 * // Complex x = new Complex(-0,7269, 0,1889); Fractale m = new Fractale(); m.comp
	 * = selectJulia2(); m.polynome = selectJulia();
	 * System.out.print("fonction: f(x)="); for (int i = m.polynome.length - 1; i >=
	 * 0; i--) System.out.print(m.polynome[i] + "" + "x" + "^" + i + "+ ");
	 * System.out.print(m.comp); m.fill(); ImageGenerator g = new ImageGenerator();
	 * g.imageDrawer(m.calculate()); View v = new View(g.image); Fractale m = new
	 * Fractale(selectHeight(), selectLength(), selectStep(), selectLeftTopComplex());
	 * m.comp = x; m.fill(); g.imageDrawer(m.calculate()); View v = new
	 * View(g.image);
	 * 
	 */

}