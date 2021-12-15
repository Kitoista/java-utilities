package javautilities.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javautilities.ui.defaults.UI;

public class Images {

	public static BufferedImage resize(BufferedImage img, Dimension size) {
		return resize(img, size.width, size.height);
	}

	/** https://stackoverflow.com/questions/9417356/bufferedimage-resize */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		if (img == null)
			return null;
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	public static List<BufferedImage> splitImage(BufferedImage image, int splitWidth, int splitHeight, int xPadding,
			int yPadding) {
		List<BufferedImage> images = new ArrayList<>();
		int x = 0;
		int y = 0;
		while (y + splitHeight <= image.getHeight()) {
			while (x + splitWidth < image.getWidth()) {
				images.add(image.getSubimage(x, y, splitWidth, splitHeight));
				x += splitWidth + xPadding;
			}
			x = 0;
			y += splitHeight + yPadding;
		}

		return images;
	}

	public static boolean hasColor(BufferedImage img, Color color) {
		int colorRGB = color.getRGB();
		for (int i =0 ; i < img.getWidth(); ++i) {
			for (int j =0 ; j < img.getHeight(); ++j) {
				if (img.getRGB(i, j) == colorRGB) {					
					return true;
				}
			}
		}
		return false;
	}
	
	// http://alvinalexander.com/java/java-clipboard-image-copy-paste/
	public static Image getImageFromClipboard() {
		Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
			try {
				return (Image) transferable.getTransferData(DataFlavor.imageFlavor);
			} catch (UnsupportedFlavorException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("getImageFromClipboard: That wasn't an image!");
		}
		return null;
	}
	
	public static BufferedImage flipX (BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-img.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(img, null);
	}
	
	public static BufferedImage flipY (BufferedImage img) {
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -img.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(img, null);
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
		re.setBorder(BorderFactory.createLineBorder(UI.fillColor));
		re.setForeground(UI.fontColor);
		re.setSize(size);
		re.setPreferredSize(size);
		return re;
	}

}
