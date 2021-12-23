import java.util.Scanner;

import org.apache.commons.math3.complex.Complex;

public class Main {
	public int[] selectsize() {
		Scanner sc = new Scanner(System.in);
		System.out.println("select height");
		// String input
		int height = sc.nextInt();
		System.out.println("select length");
		int length = sc.nextInt();
		int[] rtn = {height, length};
		sc. close();
		return rtn;
	}
	public static int selectHeight() {
		Scanner sc = new Scanner(System.in);
		System.out.println("select height");
		// String input
		int ret = sc.nextInt();
		return ret;
	}
	public static int selectLength() {
		Scanner sc = new Scanner(System.in);
		System.out.println("select length");
		// String input
		int ret = sc.nextInt();
		
		return ret;
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
		double ret = sc.nextDouble();
		
		return ret;
	}
	public static int[] selectJulia() {
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir plus grand indice de x dans z");
		int tabsize = sc.nextInt()+1;
		int[] poly = new int[tabsize];
		for (int i = 0; i < tabsize; i++) {
			System.out.println("choisir le facteur de x^" + i);
			poly[i] = sc.nextInt();
		}
		
		return poly;
	}

	public static Complex selectJulia2() {
		Scanner sc = new Scanner(System.in);
		System.out.println("choisir la partie reelle de c dans la formule f(x)=z+c, z est un polynome de x");
		double real = sc.nextDouble();
		System.out.println("choisir la partie imaginaire");
		double imaginary = sc.nextDouble();
		Complex c = new Complex(real, imaginary);
		return c;

	}

	//public static void main(String[] args) {
		//Complex x = new Complex(-0.7269, 0.1889);
		//	-0,7269
		// 0,1889
		/*Calcul m = new Calcul();
		m.comp = selectJulia2();
		m.polynom = selectJulia();
		System.out.print("fonction: f(x)=");
		for (int i = m.polynom.length-1; i >= 0; i--)
			System.out.print(m.polynom[i]+""+"x"+"^"+i+"+ ");
		System.out.print(m.comp);
		m.fill();
		ImageGenerator g = new ImageGenerator();
		g.imageDrawer(m.calculate());
		View v = new View(g.image);*/
		public static void main(String[] args) {
			Complex x = new Complex(-0.7269, 0.1889);
			Calcul m = new Calcul(selectHeight(), selectLength(), selectStep(), selectLeftTopComplex());
			m.comp = selectJulia2();
			m.polynom=selectJulia();
			m.fill();
			ImageGenerator g = new ImageGenerator();
			g.imageDrawer(m.calculate());
			View v = new View(g.image);
	}
}