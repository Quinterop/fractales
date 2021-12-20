import java.util.Scanner;

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
		int[][] test = new int[5][5];
		ImageGenerator imggen = new ImageGenerator();
		imggen.imageDrawer(Math.calculate());
		new View(imggen.image);
	}
}