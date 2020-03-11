package javautilities.geometry;

import java.awt.Dimension;

public class Resizer {

	public static Dimension setHeight(Dimension size, int height) {
		return new Dimension(height * size.width / size.height, height);
	}
	
	public static Dimension setWidth(Dimension size, int width) {
		return new Dimension(width, width * size.height / size.width);
	}
	
	public static Dimension inside(Dimension size, Dimension containerSize) {
		Dimension re = new Dimension(size);
		if (re.width > containerSize.width) {
			re = setWidth(re, containerSize.width);
		}
		if (re.height > containerSize.height) {
			re = setHeight(re, containerSize.height);
		}
		return re;
	}
	
}
