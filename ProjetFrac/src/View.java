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
	// private Controller c;
	private Main m;
	private final static Dimension MAX_TEXT_SIZE = new Dimension(100, 20);

	// @wbp.parser.constructor
	private BufferedImage im;
	private JTextField plan;
	private JTextField gap;
	private JTextField complexRealPart;
	private JTextField complexImagPart;
	private JPanel image;
	private JTextField imageSize;
	private JSlider sliderR;
	private JSlider sliderV;
	private JSlider sliderB;

	public View(Main m) throws InterruptedException {
		// @wbp.parser.constructor
		super("fractales");
		// frame
		this.m = m;
		this.im = m.generateImage();
		JPanel params = new JPanel();
		params.setMaximumSize(MAX_TEXT_SIZE);
		params.setBorder(null);
		params.setLayout(new BoxLayout(params, BoxLayout.X_AXIS));
		// params.setMaximumSize((new
		// Dimension(190-image.getHeight(),100-image.getWidth())));

		image = new JPanel();

		/*
		 * ButtonGroup g = new ButtonGroup(); JRadioButton jul = new
		 * JRadioButton("Julia"); JRadioButton mandel = new JRadioButton("Mandelbrot");
		 * g.add(jul); g.add(mandel);
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
		plan.setMaximumSize(MAX_TEXT_SIZE);

		JLabel gapL = new JLabel("Pas de calcul : ");
		imparams.add(gapL);

		gap = new JTextField("0.003");
		imparams.add(gap);
		gap.setMaximumSize(MAX_TEXT_SIZE);

		JLabel info = new JLabel("laisser 1 case vide pour autocompletion");
		imparams.add(info);

		Box row2 = Box.createHorizontalBox();
		row2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox.add(row2);

		JLabel complex = new JLabel("Complexe C dans la fonction f(x) = x² +C ");
		row2.add(complex);

		complexRealPart = new JTextField("-0.7269");
		row2.add(complexRealPart);
		complexRealPart.setMaximumSize(MAX_TEXT_SIZE);

		JLabel lblNewLabel = new JLabel("+");
		row2.add(lblNewLabel);

		complexImagPart = new JTextField("0.1889");
		row2.add(complexImagPart);
		complexImagPart.setMaximumSize(MAX_TEXT_SIZE);

		JLabel lblNewLabel_1 = new JLabel("i ");
		row2.add(lblNewLabel_1);

		JLabel imageSizeL = new JLabel("Taille de l'image : ");
		row2.add(imageSizeL);

		imageSize = new JTextField("500");
		imageSize.setMaximumSize(new Dimension(100, 20));
		row2.add(imageSize);

		Box moveRow = Box.createHorizontalBox();
		moveRow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox.add(moveRow);

		JButton left = new JButton("←");
		moveRow.add(left);
		JButton right = new JButton("→");
		moveRow.add(right);
		JButton up = new JButton("↑");
		moveRow.add(up);
		JButton down = new JButton("↓");
		moveRow.add(down);

		JLabel moveDistL = new JLabel("Distance du mouvement dans le plan");
		moveRow.add(moveDistL);

		JTextField moveDist = new JTextField("0.1");
		moveRow.add(moveDist);
		moveDist.setMaximumSize(MAX_TEXT_SIZE);

		left.addActionListener(e -> {
			try {
				changeImage(4, (double) Double.parseDouble(moveDist.getText()));
			} catch (NumberFormatException | InterruptedException e4) {
				e4.printStackTrace();
			}
		});
		right.addActionListener(e -> {
			try {
				changeImage(3, (double) Double.parseDouble(moveDist.getText()));
			} catch (NumberFormatException | InterruptedException e3) {
				e3.printStackTrace();
			}
		});
		down.addActionListener(e -> {
			try {
				changeImage(2, (double) Double.parseDouble(moveDist.getText()));
			} catch (NumberFormatException | InterruptedException e2) {
				e2.printStackTrace();
			}
		});
		up.addActionListener(e -> {
			try {
				changeImage(1, (double) Double.parseDouble(moveDist.getText()));
			} catch (NumberFormatException | InterruptedException e2) {
				e2.printStackTrace();
			}
		});

		JTextField zoomVal = new JTextField();
		zoomVal.setText("20");
		moveRow.add(zoomVal);
		zoomVal.setColumns(10);

		JButton zoom = new JButton("Zoom");
		moveRow.add(zoom);

		JButton unzoom = new JButton("Unzoom");
		moveRow.add(unzoom);

		zoom.addActionListener(e -> {
			try {
				String str = Double
						.toString(Double.parseDouble(gap.getText()) / (Double.parseDouble(zoomVal.getText()) * 0.09));
				System.out.println("zoom : " + Double.parseDouble(zoomVal.getText()) * 0.9);
				System.out.println("str : " + str);
				gap.setText(str);
				changeImage(0,0);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		unzoom.addActionListener(e -> {

			image.removeAll();
			try {
				String str = Double
						.toString(Double.parseDouble(gap.getText()) / (Double.parseDouble(zoomVal.getText()) / 0.09));
				gap.setText(str);
				changeImage(0,0);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			image.add(new JLabel(new ImageIcon(im)));

			image.invalidate();

			image.revalidate();

			image.repaint();
			this.pack();

			System.out.println("Handled Lambda listener");
			System.out.println("Have fun!");
		});

		JLabel colorsLabel = new JLabel("Couleurs : R G B");
		verticalBox.add(colorsLabel);

		sliderR = new JSlider();
		sliderR.setValue(0);
		sliderR.setToolTipText("Rouge");
		sliderR.setSnapToTicks(true);
		sliderR.setPaintLabels(true);
		sliderR.setMinimum(1);
		sliderR.setMaximum(255);
		verticalBox.add(sliderR);

		sliderV = new JSlider();
		sliderV.setValue(0);
		sliderV.setToolTipText("Vert");
		sliderV.setSnapToTicks(true);
		sliderV.setPaintLabels(true);
		sliderV.setMinimum(1);
		sliderV.setMaximum(255);
		sliderV.setForeground(Color.GREEN);
		verticalBox.add(sliderV);

		sliderB = new JSlider();
		sliderB.setForeground(Color.BLUE);
		sliderB.setValue(0);
		sliderB.setToolTipText("Bleu");
		sliderB.setSnapToTicks(true);
		sliderB.setPaintLabels(true);
		sliderB.setMinimum(1);
		sliderB.setMaximum(255);
		verticalBox.add(sliderB);

		JButton gen = new JButton("Generer !");
		verticalBox.add(gen);

		Component verticalGlue = Box.createVerticalGlue();
		verticalBox.add(verticalGlue);
		// TODO: Mettre des listeners pour set la derniere valeur (se trigger quand on
		// fait entrée)

		gen.addActionListener(e -> {
			try {
				changeImage(0, 0);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});

		im = m.generateImage();

		JLabel placeholder = new JLabel("image goes here");
		image.add(placeholder);
		image.add(new JLabel(new ImageIcon(im)));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// @wbp.parser.constructor

		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(params, BorderLayout.WEST);
		this.getContentPane().add(image, BorderLayout.CENTER);

		this.pack();
		this.setVisible(true);
	}

	private int colorsToInt(int r, int g, int b) {
		int rgb = r;
		rgb = (rgb << 8) + g;
		rgb = (rgb << 8) + b;
		return rgb;
	}

	private void changeImage(int direction, double distance) throws InterruptedException {
		image.removeAll();
		int colors = colorsToInt(sliderR.getValue(), sliderV.getValue(), sliderB.getValue());
		Complex left = new Complex(0, distance);
		Complex right = new Complex(0, -distance);
		Complex movement = new Complex(0, 0);
		switch (direction) {
		case 1:
			movement = m.getOrigin().add(-distance);
			break;
		case 2:
			movement = m.getOrigin().add(distance);
			break;
		case 3:
			movement = m.getOrigin().add(right);
			break;
		case 4:
			movement = m.getOrigin().add(left);
			break;
		}
		m.genButton(Double.parseDouble(plan.getText()), Integer.parseInt(imageSize.getText()),
				Double.parseDouble(gap.getText()), new Complex((Double.parseDouble(complexRealPart.getText())),
						(Double.parseDouble(complexImagPart.getText()))),
				colors, movement);
		im = m.generateImage();
		image.add(new JLabel(new ImageIcon(im)));
		image.invalidate();
		image.revalidate();
		image.repaint();
		this.pack();
		this.repaint();
	}

}
