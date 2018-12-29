package geometry;

import java.awt.Dimension;
import java.awt.Point;

public class Positioner {

	public static Point placeInside(Point leftUp, Dimension size, Dimension containerSize) {
		Point re = new Point(leftUp);
		if ((leftUp.x + size.width) > containerSize.width) {
			if (leftUp.x - size.width >= 0) {
				re.x = leftUp.x - size.width;
			} else {
				re.x = containerSize.width - size.width;
				if (re.x < 0) {
					re.x = 0;
				}
			}
		}
		if ((leftUp.y + size.height) > containerSize.height) {
			if ((leftUp.y - size.height) >= 0) {
				re.y = leftUp.y - size.height;
			} else {
				re.y = containerSize.height - size.height;
				if (re.y < 0) {
					re.y = 0;
				}
			}
		}
		return re;
	}
	
}
