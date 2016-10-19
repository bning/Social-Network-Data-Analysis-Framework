package edu.cmu.cs.cs214.hw5.gui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * the helper class to add a image from file to create a jpanel
 * 
 * @author Jiyu Zhu
 *
 */
public class AddImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage img;

	/**
	 * the constrctor of the class
	 * 
	 * @param path
	 *            the path of the image
	 */
	public AddImagePanel(String path) {
		try {
			img = ImageIO.read(new File(path));
			int width = img.getWidth(this);
			int height = img.getHeight(this);
			System.out.println(width + "  " + height);
			this.setPreferredSize(new Dimension(width, height));

		} catch (IOException e) {
			System.out.println("Image file read failure!");
		}
	}
}