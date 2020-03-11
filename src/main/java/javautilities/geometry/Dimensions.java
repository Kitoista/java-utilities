package javautilities.geometry;

import java.awt.Dimension;

public class Dimensions {

	public static Dimension sub(Dimension a, Dimension b) {
		return new Dimension(a.width - b.width, a.height - b.height);
	}
	
	public static Dimension add(Dimension a, Dimension b) {
		return new Dimension(a.width + b.width, a.height + b.height);
	}
	
}
