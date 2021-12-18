package projetfract;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageGenerator {
	public void example() {
		var img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		int r = 64;
		int g = 224;
		int b = 208; // turquoise
		int col = (r << 16) | (g << 8) | b;
		img.setRGB(30, 40, col);
		File f = new File("MyFile.png");
		try {
			ImageIO.write(img, "PNG", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage CreateImageWithTab(int[][] arr,int size) {
		var img = new BufferedImage(size,size, BufferedImage.TYPE_INT_RGB);
		for (var i = 0; i <size; i++) {
			for (var j = 0; j < size; j++) {
				img.setRGB(i,j, arr[i][j]);
			}
		}
		return img;
	}
}