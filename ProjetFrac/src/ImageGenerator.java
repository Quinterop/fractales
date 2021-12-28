import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.util.stream.IntStream.range;

import javax.imageio.ImageIO;
import java.awt.Color;

import org.apache.commons.math3.complex.Complex;

public class ImageGenerator {
	
	private final Calcul c;
	private final int tabX;
	private final int tabY;
	private int[][] colors;
	BufferedImage image = new BufferedImage(200,200,2); //placeholder
	
	
//THREADS DANS CETTE CLASSE 
	/*
	ImageGenerator (Calcul c) {
		this.c=c;
		tabX=c.getX();
		tabY=c.getY();
		colors = new int[tabX][tabY];
		colors=calculate();
		this.image = new BufferedImage(tabX, tabY, BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB
		for (int i = 0; i < tabX; i++) {
			for (int j = 0; j < tabY; j++) {
				image.setRGB(i,j,colors[i][j]);
			}
		}
		//File f = new File("MyFile.png");
		//ImageIO.write(image, "PNG", f);
	}
	
	int[][] calculate() {
		for (int i = 0; i < tabX; i++) {
			for (int j = 0; j < tabY; j++) {
				colors[i][j] = c.divergenceIndex(c.getGraph()[i][j]);
			}
		}
		return colors;
	}*/
	
	ImageGenerator(Calcul c) {
		this.c=c;
		tabX=c.getX();
		tabY=c.getY();
		colors = new int[tabX][tabY];
		this.image = new BufferedImage(tabX, tabY, BufferedImage.TYPE_INT_RGB);
		//File f = new File("images/" + name + ".png");
		range(0, c.getX())
                .parallel()
                .forEach(i -> range(0,c.getY())
                        .parallel()
                        .forEach(j -> image.setRGB(i,j,calculate(c,i,j))));
    }

    public int calculate (Calcul c, int i, int j){
    	colors[i][j] = c.divergenceIndex(c.getGraph()[i][j]);
    	return colors[i][j];
    }


}
