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
		return rtn;
	}

	public static void main(String[] args) {
		Complex x = new Complex(-0.7269, 0.1889);
		Calcul m = new Calcul();
		m.comp=x;
		m.fill();
		ImageGenerator g = new ImageGenerator();
		g.imageDrawer(m.calculate());
		View v = new View(g.image);
	}
}