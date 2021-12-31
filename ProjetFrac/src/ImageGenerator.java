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

	private final Fractale c;
	private final int tabX;
	private final int tabY;
	private int[][] colors;
	BufferedImage image = new BufferedImage(200, 200, 2); // placeholder
	private final int nombreThreads;

//THREADS DANS CETTE CLASSE 
	/*
	 * ImageGenerator (Fractale c) { this.c=c; tabX=c.getX(); tabY=c.getY(); colors =
	 * new int[tabX][tabY]; colors=calculate(); this.image = new BufferedImage(tabX,
	 * tabY, BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB for (int i = 0; i < tabX;
	 * i++) { for (int j = 0; j < tabY; j++) { image.setRGB(i,j,colors[i][j]); } }
	 * //File f = new File("MyFile.png"); //ImageIO.write(image, "PNG", f); }
	 * 
	 * int[][] calculate() { for (int i = 0; i < tabX; i++) { for (int j = 0; j <
	 * tabY; j++) { colors[i][j] = c.divergenceIndex(c.getGraph()[i][j]); } } return
	 * colors; }
	 */

	ImageGenerator(Fractale c, int nombreThreads) throws InterruptedException {
		this.nombreThreads = nombreThreads;
		//CountDownLatch latch = new CountDownLatch(nombreThreads);
		long start = System.currentTimeMillis();
		Thread[] threads = new Thread[nombreThreads];
		this.c = c;
		tabX = c.getX();
		tabY = c.getY();
		colors = new int[tabX][tabY];
		this.image = new BufferedImage(tabX, tabY, BufferedImage.TYPE_INT_RGB);
		// File f = new File("images/" + name + ".png");
		int div = tabX / nombreThreads;
		for (int i = 0; i < nombreThreads; i++) {
			final int k = i;
			//System.out.println("aaaaa"+i);
			//System.out.println(latch.getCount());
			//supprimer bloc if ?
			if (i == nombreThreads - 1 && div * nombreThreads != tabX) {
				System.out.println("test");
				threads[i] = new Thread(() -> {
					range(k * div, tabX)
					.parallel()
					.forEach(a -> range(0, tabY)
							.parallel()
							.forEach(j -> image.setRGB(a, j, (Color.HSBtoRGB((float)calculate(c, a, j)/1000+c.getCol(), 1f, 1f)))));
					//latch.countDown();
				});
				threads[i].start();
			}else {

				threads[k] = new Thread(() -> {
				range(k * div, div * (k + 1))
				.parallel()
						.forEach(a -> range(0, tabY)    
								.parallel()
								.forEach(j -> image.setRGB(a, j, (Color.HSBtoRGB((float)calculate(c, a, j)/1000+c.getCol(), 1f, 1f)))));
				//latch.countDown();
				//System.out.println(latch.getCount());
			});
				threads[i].start();
			}
			
		}
	//	latch.await(); 
		
		Stream.of(threads).forEach(k ->{
			try {
				k.join();
			} catch (InterruptedException e) {
				System.out.println("could not join");
				e.printStackTrace();
			}
		});
		long finish = System.currentTimeMillis();
		long timeElapsed = finish - start;
		System.out.println(timeElapsed);
	}

	public int calculate(Fractale c, int i, int j) {
		colors[i][j] = c.divergenceIndex(c.getGraph()[i][j]);
		return colors[i][j];
	}

}
