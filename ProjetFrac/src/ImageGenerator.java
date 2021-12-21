import java.awt.image.BufferedImage;
public class ImageGenerator {
	BufferedImage image = new BufferedImage(200,200,2); //placeholder
	// monothread
	void imageDrawer(int[][] colors) {
		this.image = new BufferedImage(colors.length, colors[0].length, BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
				image.setRGB(i,j,colors[i][j]*50*500);
			}
		}
		//File f = new File("MyFile.png");
		//ImageIO.write(image, "PNG", f);
	}
	
	
	
}
