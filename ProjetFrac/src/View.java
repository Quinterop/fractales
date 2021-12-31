
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
	private Controller main;
	private static final Dimension MAX_TEXT_SIZE = new Dimension(100, 20);
	private BufferedImage im;
	private JTextField gap;
	private JTextField complexRealPart;
	private JTextField complexImagPart;
	private JPanel image;
	private JTextField imageSize;
	private JTextField zoomVal;
	private JSlider sliderR;
	private JSlider sliderV;
	private JSlider sliderB;
	private JLabel displayR;
	private JLabel displayG;
	private JLabel displayB;

	public View(BufferedImage im) {
		super("fractales");
		this.getContentPane().add(new JLabel(new ImageIcon(im)));
		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}


	public View(Controller main) throws InterruptedException {
		super("fractales");
		this.main = main;
		this.im = main.generateImage();
		JPanel params = new JPanel();
		params.setMaximumSize(MAX_TEXT_SIZE);
		params.setBorder(null);
		params.setLayout(new BoxLayout(params, BoxLayout.X_AXIS));
		image = new JPanel();


		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new BevelBorder(BevelBorder.LOWERED));
		params.add(verticalBox);

		Box imparams = Box.createHorizontalBox();
		imparams.setBorder(new BevelBorder(BevelBorder.LOWERED));
		verticalBox.add(imparams);

		JLabel gapL = new JLabel("Pas de calcul : ");
		imparams.add(gapL);

		gap = new JTextField("0.003");
		imparams.add(gap);
		gap.setMaximumSize(MAX_TEXT_SIZE);
		
		JLabel imageSizeL = new JLabel("Taille de l'image : ");
		imparams.add(imageSizeL);
		
		imageSize = new JTextField("500");
		imageSize.setMaximumSize(new Dimension(100, 20));
		imparams.add(imageSize);

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

		JLabel labl1 = new JLabel("i ");
		row2.add(labl1);

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
				changeImage(4, Double.parseDouble(moveDist.getText()));
			} catch (NumberFormatException | InterruptedException e4) {
				e4.printStackTrace();
			}
		});
		right.addActionListener(e -> {
			try {
				changeImage(3, Double.parseDouble(moveDist.getText()));
			} catch (NumberFormatException | InterruptedException e3) {
				e3.printStackTrace();
			}
		});
		down.addActionListener(e -> {
			try {
				changeImage(2, Double.parseDouble(moveDist.getText()));
			} catch (NumberFormatException | InterruptedException e2) {
				e2.printStackTrace();
			}
		});
		up.addActionListener(e -> {
			try {
				changeImage(1, Double.parseDouble(moveDist.getText()));
			} catch (NumberFormatException | InterruptedException e2) {
				e2.printStackTrace();
			}
		});
		
		Box zoomRow = Box.createHorizontalBox();
		zoomRow.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		verticalBox.add(zoomRow);
		
		JButton zoom = new JButton("Zoom");
		zoomRow.add(zoom);
		
		JButton unzoom = new JButton("Unzoom");
		zoomRow.add(unzoom);
		
		zoomVal = new JTextField();
		zoomVal.setText("10");
		zoomVal.setMaximumSize(new Dimension(100, 20));
		zoomVal.setColumns(10);
		zoomRow.add(zoomVal);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		zoomRow.add(horizontalGlue);
		
		Box boxR = Box.createHorizontalBox();
		verticalBox.add(boxR);
		
		sliderR = new JSlider();
		sliderR.setValue(0);
		sliderR.setToolTipText("Rouge");
		sliderR.setSnapToTicks(true);
		sliderR.setPaintLabels(true);
		sliderR.setMinimum(0);
		sliderR.setMaximumSize(new Dimension(180, 20));
		sliderR.setMaximum(360);
		boxR.add(sliderR);
		
		displayR = new JLabel("R : "+sliderR.getValue());
		boxR.add(displayR);
		
		Component horizontalGlue_3 = Box.createHorizontalGlue();
		boxR.add(horizontalGlue_3);
	
		


		JButton gen = new JButton("Generer !");
		verticalBox.add(gen);

		Component verticalGlue = Box.createVerticalGlue();
		verticalBox.add(verticalGlue);
		
		sliderR.addChangeListener(e -> updateColorsLabel(1));


		gen.addActionListener(e -> {
			try {
				changeImage(0, 0);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		});
		zoom.addActionListener(e -> {
            try {
                String str = Double
                    .toString(Double.parseDouble(gap.getText()) / ((Double.parseDouble(zoomVal.getText()) + 12) * 0.09));
                double st = Double.parseDouble(gap.getText()) / ((Double.parseDouble(zoomVal.getText())+ 12) * 0.09);
                if (st < Double.parseDouble(gap.getText())){
                    gap.setText(str);
                    changeImage(0,0);
                }
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        });
        
        unzoom.addActionListener(e -> {
            try {
                String str = Double
                            .toString(Double.parseDouble(gap.getText()) * ((Double.parseDouble(zoomVal.getText())+12) * 0.09));
                    double st = Double.parseDouble(gap.getText()) * ((Double.parseDouble(zoomVal.getText())+ 12) * 0.09);
                    if (st > Double.parseDouble(gap.getText())){
                        gap.setText(str);
                        changeImage(0,0);
                    }
                } catch (InterruptedException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
    

        });
        
        JButton saveButton = new JButton("Sauvegarder");
        verticalBox.add(saveButton);
        
        saveButton.addActionListener(e -> {
            try {
            	 BufferedImage img = main.generateImage();
                File outputfile = new File("image.jpg");
                ImageIO.write(img, "jpg", outputfile);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

		im = main.generateImage();
		image.add(new JLabel(new ImageIcon(im)));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// @wbp.parser.constructor

		this.getContentPane().setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(params, BorderLayout.WEST);
		this.getContentPane().add(image, BorderLayout.CENTER);

		this.pack();
		this.setVisible(true);
	}

	
	
	

	private void changeImage(int direction, double distance) throws InterruptedException {
		image.removeAll();
		int colors = sliderR.getValue();
		Complex left = new Complex(0, distance);
		Complex right = new Complex(0, -distance);
		Complex movement = new Complex(0, 0);
		switch (direction) {
		case 1:
			movement = Complex.ZERO.add(-distance);
			break;
		case 2:
			movement = Complex.ZERO.add(distance);
			break;
		case 3:
			movement = right;
			break;
		case 4:
			movement = left;
			break;
		}
//TODO: ajouter thread pour le calcul+ bouton stop
		main.genButton(0, Integer.parseInt(imageSize.getText()),
				Double.parseDouble(gap.getText()), new Complex((Double.parseDouble(complexRealPart.getText())),
						(Double.parseDouble(complexImagPart.getText()))),
				colors, movement.add(main.getOrigin()));
		im = main.generateImage();
		image.add(new JLabel(new ImageIcon(im)));
		image.invalidate();
		image.revalidate();
		image.repaint();
		this.pack();
		this.repaint();
	}
	
	private void updateColorsLabel(int slider) {
		switch (slider) {
		case 1: displayR.setText("R : "+String.valueOf(sliderR.getValue())); break;
		case 2: displayG.setText("V : "+String.valueOf(sliderV.getValue())); break;
		case 3: displayB.setText("B : "+String.valueOf(sliderB.getValue())); break;
		}
	}

}
