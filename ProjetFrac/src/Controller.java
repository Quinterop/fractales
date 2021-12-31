
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
			File outputfile = new File("fractale.jpg");
			ImageIO.write(img.image, "jpg", outputfile);
		} else {
			this.calcul = defaultCalcul;
			new View(this);
		}
	}

	public boolean chooseView() {
		Scanner sc = new Scanner(System.in);
		System.out.println("vue terminale ->1. vue IG -> 2");
		return sc.nextInt() == 1;
	}

	public Fractale.Builder maker() { //pour vue terminal
		Complex x = selectJulia();
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir un :" + '\n' + 
				"1 : spas et taille d'abcisse d'image" + '\n' + 
				"2 : taille du plan complexe et pas"
				+ '\n' + "3 : taille d'abcisse d'image et taille du plan complexe ");
		int choice = sc.nextInt();
		double step = 0.0;
		int size = 0;
		int size2 = selectHeight();
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
		System.out.println("choisir le radius (double)");
		int radius = sc.nextInt();
		Complex origin=selectOrigin();
		return new Fractale.Builder().step(step).plan(planSize).size(size,size2).maxIter(maxIter).radius(radius).graphOrigin(origin).comp(x);
	}

	public static int selectHeight() {
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir hauteur");
		// String input
		return sc.nextInt();
	}

	public static int selectLength() {
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir largeur");
		// String input
		return sc.nextInt();
	}

	public static Complex selectOrigin() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisissez le milieu de la vue (complexe)");
		System.out.println("partie reelle : ");
		int x = sc.nextInt();
		System.out.println("partie imaginaire : ");
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
	
	public BufferedImage generateImage() throws InterruptedException { //pour vue terminal
		ImageGenerator g = new ImageGenerator(calcul, Runtime.getRuntime().availableProcessors());
		return g.image;
	}

	public Complex getOrigin() { //passerelles entre la vue et le modèle
		return calcul.getOrigin();
	}

	public void genButton(double plan, int size, double step, Complex c, int colors, Complex newOrigin) {
		Fractale calc = new Fractale.Builder().plan(plan).size(size, size).step(step).comp(c).col(colors)
				.graphOrigin(newOrigin).build();
		this.calcul = calc;
	}

	public static void main(String[] args)  {
		Fractale defaultCalcul = new Fractale.Builder().step(0.01).size(500, 500).build();
		try {
			Controller m = new Controller(defaultCalcul);
		} catch (InterruptedException | IOException e) {		
			e.printStackTrace();
		}
	}

}
