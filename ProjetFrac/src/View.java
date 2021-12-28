import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.Color;

public class View {
	
	public View(BufferedImage img) {
		JFrame frame = new JFrame();
		//BufferedImage img = null;
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
