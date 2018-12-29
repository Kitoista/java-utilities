package image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Images {

	public static BufferedImage resize(BufferedImage img, Dimension size) {
		return resize(img, size.width, size.height);
	}
	
	/** https://stackoverflow.com/questions/9417356/bufferedimage-resize */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		if (img == null) return null;
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

	    return dimg;
	}
	
	public static JLabel placeholderComponent(int width, int height) {
		return placeholderComponent(width, height, null);
	}
	
	public static JLabel placeholderComponent(Dimension size) {
		return placeholderComponent(size, null); 
	}
	
	public static JLabel placeholderComponent(int width, int height, String text) {
		return placeholderComponent(new Dimension(width, height), text);
	}
	
	public static JLabel placeholderComponent(Dimension size, String text) {
		JLabel re = new JLabel((text == null) ? size.width + " x " + size.height : text, SwingConstants.CENTER);
		re.setBorder(BorderFactory.createLineBorder(Color.gray));
		re.setForeground(Color.gray);
		re.setSize(size);
		re.setPreferredSize(size);
		return re;
	}
	
}
