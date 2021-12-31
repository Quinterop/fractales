
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.util.stream.IntStream.range;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
import java.awt.Color;

import org.apache.commons.math3.complex.Complex;

public class ImageGenerator {

	private final Fractale frac;
	private final int tabX;
	private final int tabY;
	private int[][] colors;
	BufferedImage image = new BufferedImage(200, 200, 2); // placeholder
	private final int nombreThreads;

	ImageGenerator(Fractale frac, int nombreThreads) {
		this.nombreThreads = nombreThreads;
		long start = System.currentTimeMillis();
		Thread[] threads = new Thread[nombreThreads];
		this.frac = frac;
		tabX = frac.getX();
		tabY = frac.getY();
		colors = new int[tabX][tabY];
		this.image = new BufferedImage(tabX, tabY, BufferedImage.TYPE_INT_RGB);
		int div = tabX / nombreThreads;
		for (int i = 0; i < nombreThreads; i++) {
			final int k = i;

			if (i == nombreThreads - 1 && div * nombreThreads != tabX) { //cas ou les threads ne couvrent pas uniformément l'image
				threads[i] = new Thread(() -> {
					range(k * div, tabX).parallel().
					forEach(a -> range(0, tabY).parallel()
							.forEach(j -> image.setRGB(a,j, (Color.HSBtoRGB((float) calculate(frac, a, j) / 1000, 1f, 1f) + frac.getCol()))));
				});
			} else {
				threads[k] = new Thread(() -> {
					range(k * div, div * (k + 1)).parallel()
					.forEach(a -> range(0, tabY).parallel()
							.forEach(j -> image
							.setRGB(a, j, ((Color.HSBtoRGB((float) calculate(frac, a, j) / 1000, 1f, 1f) + frac.getCol())))));
					// .forEach(j -> image.setRGB(a, j, (calculate(frac, a, j))*frac.getCol())));
					// .forEach(j -> image.setRGB(a, j, (calculate(frac, a, j))*frac.getCol())));
					// méthodes de calcul de couleur alternatives
				});
			}
			threads[i].start();
		}

		Stream.of(threads).forEach(k -> { //attente que l'image soit finie
			try {
				k.join();
			} catch (InterruptedException e) {
				System.out.println("could not join");
				e.printStackTrace();
			}
		});
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		//System.out.println(timeElapsed);
	}

	public int calculate(Fractale frac, int i, int j) {
		colors[i][j] = frac.divergenceIndex(frac.getGraph()[i][j]);
		return colors[i][j];
	}

}
