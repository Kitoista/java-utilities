package javautilities.ui.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import javautilities.image.Images;
import javautilities.ui.component.displayer.BufferedImageDisplayer;

public class MirrorPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Component original;
	Component element;
	
	Point offset = new Point();
	BufferedImage image;
	
	public MirrorPanel(Component original) {
		this.original = original;
		if (original == null) {
			throw new NullPointerException();
		}
		this.setOpaque(false);
	}
	
	protected void paintComponent(Graphics out) {
		BufferedImage panelImage = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
		original.printAll(panelImage.getGraphics());
		
		Dimension size = getSize();
		
		Rectangle cropBounds = new Rectangle(offset.x - size.width, offset.y, size.width, size.height);
		Rectangle originalBounds = original.getBounds();
		Rectangle subBounds = cropBounds.intersection(originalBounds);
		
		if (subBounds.width > 0 && subBounds.height > 0) {
			BufferedImage part = panelImage.getSubimage(subBounds.x, subBounds.y, subBounds.width, subBounds.height);
//			image = part;
			image = Images.flipX(part);
			out.drawImage(image, 0, 0, null);
//			BufferedImageDisplayer bid = new BufferedImageDisplayer();
//			if (element == null) {
//				element = bid.toComponent(image);
//				element.setSize(element.getPreferredSize());
//				this.add(element);
//			} else {
//				bid.updateComponent(element, image);
//				element.setSize(element.getPreferredSize());
//			}
		}
	}
	
	public Component getOriginal() {
		return original;
	}

	public Point getOffset() {
		return offset;
	}

	public void setOffset(Point offset) {
		this.offset = offset;
	}
	
	public void setSize(Dimension size) {
		super.setSize(size);
	}
	
	public void setSize(int x, int y) {
		super.setSize(x, y);
	}
	
	public void setBounds(Rectangle r) {
		super.setBounds(r);
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
	}
	
}
