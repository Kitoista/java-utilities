package javautilities.ui.util;

import java.awt.Component;
import java.awt.image.BufferedImage;

public class Converter {

	public static BufferedImage componentToBufferedImage(Component comp) {
		BufferedImage re = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
		comp.printAll(re.getGraphics());
		return re;
	}
	
}
