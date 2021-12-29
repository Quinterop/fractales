import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;

import org.apache.commons.math3.complex.Complex;

import java.awt.event.*;

public class View extends JFrame {
	//private Controller c;
	private Main m;
	private Calcul c;
	private JButton gen;

	//@wbp.parser.constructor
	private BufferedImage im;
	private JTextField plan;
	private JTextField imageSize;
	private JTextField gap;
	private JTextField complexRealPart;
	private JTextField complexImagPart;

	public View(Main m) {
		//@wbp.parser.constructor
		super("fractales");
		//frame
		this.m = m;

		JPanel params = new JPanel();
		params.setBorder(null);
		params.setLayout(new BoxLayout(params, BoxLayout.X_AXIS));
		//params.setMaximumSize((new Dimension(190-image.getHeight(),100-image.getWidth())));
		
		JPanel image = new JPanel();
		
/*
		ButtonGroup g = new ButtonGroup();
		JRadioButton jul = new JRadioButton("Julia");
		JRadioButton mandel = new JRadioButton("Mandelbrot");
		g.add(jul);
		g.add(mandel);
		*/
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new BevelBorder(BevelBorder.LOWERED));
		params.add(verticalBox);

		Box imparams = Box.createHorizontalBox();
		imparams.setBorder(new BevelBorder(BevelBorder.LOWERED));
		verticalBox.add(imparams);
		
		JLabel planL = new JLabel("Taille du plan complexe : ");
		imparams.add(planL);
		
		 plan = new JTextField("1");
		imparams.add(plan);
		
		JLabel gapL = new JLabel("Pas de calcul : ");
		imparams.add(gapL);
		
		 gap = new JTextField("0.01");
		imparams.add(gap);
		
		JLabel imageSizeL = new JLabel("Taille de l'image : ");
		imparams.add(imageSizeL);
		
		 imageSize = new JTextField("200");
		imparams.add(imageSize);
		
		JLabel info = new JLabel("laisser 1 case vide pour autocompletion");
		imparams.add(info);
		
		
		Box row2 = Box.createHorizontalBox();
		row2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox.add(row2);
		
		JLabel complex = new JLabel("Complexe C dans la fonction f(x)=x²+C");
		row2.add(complex);
		
		complexRealPart = new JTextField("-0.7269");
		row2.add(complexRealPart);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		row2.add(horizontalGlue);
		
		JLabel lblNewLabel = new JLabel("+");
		row2.add(lblNewLabel);
		
		complexImagPart = new JTextField("0.1889");
		row2.add(complexImagPart);
		
		JLabel lblNewLabel_1 = new JLabel("i");
		row2.add(lblNewLabel_1);
		
		gen = new JButton("Generer !");
		row2.add(gen);
		
		Box moveRow = Box.createHorizontalBox();
		moveRow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox.add(moveRow);
		
		JButton down = new JButton("↓");
		moveRow.add(down);
		JButton left = new JButton("←");
		moveRow.add(left);
		JButton right = new JButton("→");
		moveRow.add(right);
		JButton up = new JButton("↑");
		moveRow.add(up);
		
		JLabel MoveDistL = new JLabel("Distance du mouvement dans le plan");
		moveRow.add(MoveDistL);
		
		JTextField textField = new JTextField("0,0");
		moveRow.add(textField);
		
		
		
		Component horizontalStrut = Box.createHorizontalStrut(200);
		moveRow.add(horizontalStrut);
		//-image.getSize(null))
		//TODO: Mettre des listeners pour set la derniere valeur (se trigger quand on fait entrée)
		
		
		//JSlider plan = new JSlider();
		//params.add(plan);
		//JSlider imageSize = new JSlider();
		//params.add(imageSize);
	//	JSlider step = new JSlider();
		
		//JProgressBar generation = new JProgressBar();
		
		
		im = m.generate();
		
		
		gen.addActionListener(e -> {
			

			image.removeAll();
			changeImage();
			image.add(new JLabel(new ImageIcon(im)));

			image.invalidate();

			image.revalidate();

			image.repaint();
			this.pack();

		    System.out.println("Handled Lambda listener");
		    System.out.println("Have fun!");
		});
		
		image.add(new JLabel(new ImageIcon(im)));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//@wbp.parser.constructor
		
		
		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(params, BorderLayout.WEST);
		this.getContentPane().add(image, BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
	private void changeImage() {
		Calcul ca =m.genButton(Double.parseDouble(plan.getText()), 
				Integer.parseInt(imageSize.getText()), 
				Double.parseDouble(gap.getText()), 
				new Complex ((Double.parseDouble(complexRealPart.getText())), (Double.parseDouble(complexImagPart.getText()))));
		ImageGenerator imgen = new ImageGenerator(ca);
		imgen.calculate();
		im=imgen.image;

		this.repaint();
	}
	
	

		
		
	

}
