import java.awt.image.BufferedImage;
public class ImageGenerator {
	private final Calcul c;
	private final int tabX;
	private final int tabY;
	private int[][] colors;
//THREADS DANS CETTE CLASSE
	BufferedImage image = new BufferedImage(200,200,2); //placeholder
<<<<<<< ProjetFrac/src/ImageGenerator.java
	ImageGenerator (Calcul c) {
		this.c=c;
		tabX=c.getX();
		tabY=c.getY();
		colors = new int[tabX][tabY];
		colors=calculate();
		this.image = new BufferedImage(tabX, tabY, BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB
		for (int i = 0; i < tabX; i++) {
			for (int j = 0; j < tabY; j++) {
=======
	// monothread
	void imageDrawer(int[][] colors) {
		this.image = new BufferedImage(colors.length, colors[0].length, BufferedImage.TYPE_INT_RGB); // TYPE_INT_ARGB
		for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
>>>>>>> ProjetFrac/src/ImageGenerator.java
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
	}
}
