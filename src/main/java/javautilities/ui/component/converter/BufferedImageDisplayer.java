package javautilities.ui.component.converter;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import javautilities.image.Images;
import javautilities.ui.defaults.KLabel;

public class BufferedImageDisplayer implements ObjectDisplayer, CollectionDisplayer {

	@Override
	public Component toComponent(Object obj, Dimension size) {
		if (!(obj instanceof BufferedImage)) {
			return null;
		}
		BufferedImage img = (BufferedImage) obj;
		if (size != null) {
			img = Images.resize(img, size);
		}
		JLabel label = new KLabel();
		if (img != null) {
			label.setIcon(new ImageIcon(img));
			label.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		}
		return label;
	}

	@Override
	public Collection<Component> toComponents(Collection<?> collection, Dimension size) {
		Collection<Component> re = new ArrayList<>();
		for (Object object : collection) {
			re.add(toComponent(object, size));
		}
		return re;
	}
}
