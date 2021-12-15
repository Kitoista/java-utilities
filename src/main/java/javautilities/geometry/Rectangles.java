package javautilities.geometry;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javautilities.util.Pair;

public class Rectangles {

	public static final int NONE = 0;
	
	
	public static final int ALL_COMBINATIONS   = 0b11111111;
	public static final int SAME_SIDES         = 0b00001111;
	public static final int OPPOSITE_SIDES     = 0b11110000;
	
	public static final int LEFT_WITH_X        = 0b10001000;
	public static final int RIGHT_WITH_X       = 0b01000100;
	public static final int TOP_WITH_X         = 0b00100010;
	public static final int BOTTOM_WITH_X      = 0b00010001;
	
	public static final int X_WITH_LEFT        = 0b01001000;
	public static final int X_WITH_RIGHT       = 0b10000100;
	public static final int X_WITH_TOP         = 0b00010010;
	public static final int X_WITH_BOTTOM      = 0b00100001;
	
	public static final int LEFT_WITH_RIGHT    = 0b10000000;
	public static final int RIGHT_WITH_LEFT    = 0b01000000;
	
	public static final int TOP_WITH_BOTTOM    = 0b00100000;
	public static final int BOTTOM_WITH_TOP    = 0b00010000;
	
	public static final int LEFT_WITH_LEFT     = 0b00001000;
	public static final int RIGHT_WITH_RIGHT   = 0b00000100;
	
	public static final int TOP_WITH_TOP       = 0b00000010;
	public static final int BOTTOM_WITH_BOTTOM = 0b00000001;
	
	/** LEFT or RIGHT */
	public static final int HORIZONTAL         = 0b11001100;
	/** TOP or BOTTOM */
	public static final int VERTICAL           = 0b00110011;
	
	public static final int TOP_LEFT = 0;
	public static final int TOP_RIGHT = 1;
	public static final int BOTTOM_LEFT = 2;
	public static final int BOTTOM_RIGHT = 3;
	
	public static final int ALL_SIDES = 0b1111;

	public static final int LEFT      = 0b1000;
	public static final int RIGHT     = 0b0100;
	public static final int TOP       = 0b0010;
	public static final int BOTTOM    = 0b0001;
	
	
	// COMMON
	
	public static String sidesToString(int sides) {
		String re = "( ";
		if ((sides & ALL_COMBINATIONS) == ALL_COMBINATIONS) {
			sides &= ~ALL_COMBINATIONS;
			re += "ALL_COMBINATIONS ";
		}
		if ((sides & OPPOSITE_SIDES) == OPPOSITE_SIDES) {
			sides &= ~OPPOSITE_SIDES;
			re += "OPPOSITE_SIDES ";
		}
		if ((sides & SAME_SIDES) == SAME_SIDES) {
			sides &= ~SAME_SIDES;
			re += "SAME_SIDES ";
		}
		if ((sides & LEFT_WITH_X) == LEFT_WITH_X) {
			sides &= ~LEFT_WITH_X;
			re += "LEFT_WITH_X ";
		}
		if ((sides & RIGHT_WITH_X) == RIGHT_WITH_X) {
			sides &= ~RIGHT_WITH_X;
			re += "RIGHT_WITH_X ";
		}
		if ((sides & TOP_WITH_X) == TOP_WITH_X) {
			sides &= ~TOP_WITH_X;
			re += "TOP_WITH_X ";
		}
		if ((sides & BOTTOM_WITH_X) == BOTTOM_WITH_X) {
			sides &= ~BOTTOM_WITH_X;
			re += "BOTTOM_WITH_X ";
		}
		if ((sides & X_WITH_LEFT) == X_WITH_LEFT) {
			sides &= ~X_WITH_LEFT;
			re += "X_WITH_LEFT ";
		}
		if ((sides & X_WITH_RIGHT) == X_WITH_RIGHT) {
			sides &= ~X_WITH_RIGHT;
			re += "X_WITH_RIGHT ";
		}
		if ((sides & X_WITH_TOP) == X_WITH_TOP) {
			sides &= ~X_WITH_TOP;
			re += "X_WITH_TOP ";
		}
		if ((sides & X_WITH_BOTTOM) == X_WITH_BOTTOM) {
			sides &= ~X_WITH_BOTTOM;
			re += "X_WITH_BOTTOM ";
		}
		if ((sides & LEFT_WITH_RIGHT) != 0) {
			re += "LEFT_WITH_RIGHT ";
		}
		if ((sides & RIGHT_WITH_LEFT) != 0) {
			re += "RIGHT_WITH_LEFT ";
		}
		if ((sides & TOP_WITH_BOTTOM) != 0) {
			re += "TOP_WITH_BOTTOM ";
		}
		if ((sides & BOTTOM_WITH_TOP) != 0) {
			re += "BOTTOM_WITH_TOP ";
		}
		if ((sides & LEFT_WITH_LEFT) != 0) {
			re += "LEFT_WITH_LEFT ";
		}
		if ((sides & RIGHT_WITH_RIGHT) != 0) {
			re += "RIGHT_WITH_RIGHT ";
		}
		if ((sides & TOP_WITH_TOP) != 0) {
			re += "TOP_WITH_TOP ";
		}
		if ((sides & BOTTOM_WITH_BOTTOM) != 0) {
			re += "BOTTOM_WITH_BOTTOM ";
		}
		re += ")";
		return re;
	}
	
	// Rectangle
	
	public static Point topLeft(Rectangle r) {
		return r.getLocation();
	}
	
	public static Point bottomLeft(Rectangle r) {
		return new Point(r.x, r.y + r.height);
	}
	
	public static Point bottomRight(Rectangle r) {
		return new Point(r.x + r.width, r.y + r.height);
	}
	
	public static Point topRight(Rectangle r) {
		return new Point(r.x + r.width, r.y);
	}
	
	/** topLeft, topRight, bottomLeft, bottomRight */
	public static Point[] corners(Rectangle r) {
		Point[] re = new Point[4];
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				re[i + 2 * j] = (new Point(r.x + i * r.width, r.y + j * r.height));
			}
		}
		return re;
	}
	
	/** is a between b and c horizontally */
	protected static boolean isBetweenX(Point a, Point b, Point c) {
		return (a.x >= b.x && a.x <= c.x) || (a.x <= b.x && a.x >= c.x);
	}
	/** is a between b and c vertically */
	protected static boolean isBetweenY(Point a, Point b, Point c) {
		return (a.y >= b.y && a.y <= c.y) || (a.y <= b.y && a.y >= c.y);
	}
	
	public static int frameContains(Rectangle r, Point p) {
		int re = NONE;
		Point[] corners = Rectangles.corners(r);
		
		if (r.getMinX() == p.x && isBetweenY(p, corners[TOP_LEFT], corners[BOTTOM_LEFT])) {
			re |= LEFT;
		}
		if (r.getMaxX() == p.x && isBetweenY(p, corners[TOP_RIGHT], corners[BOTTOM_RIGHT])) {
			re |= RIGHT;
		}
		if (r.getMinY() == p.x && isBetweenX(p, corners[TOP_LEFT], corners[TOP_RIGHT])) {
			re |= TOP;
		}
		if (r.getMaxX() == p.x && isBetweenX(p, corners[BOTTOM_LEFT], corners[BOTTOM_RIGHT])) {
			re |= BOTTOM;
		}
		return re;
	}
	
	public static int shareSide(Rectangle r1, Rectangle r2, boolean sameAllowed) {
		int re = NONE;
		Point[] corners1 = Rectangles.corners(r1);
		Point[] corners2 = Rectangles.corners(r2);
				
		if (r1.getMinX() == r2.getMaxX() && (
				isBetweenY(corners1[TOP_LEFT], corners2[TOP_RIGHT], corners2[BOTTOM_RIGHT]) ||
				isBetweenY(corners2[TOP_RIGHT], corners1[TOP_LEFT], corners1[BOTTOM_LEFT])
		)) {
			re |= LEFT_WITH_RIGHT;
		}
		if (r1.getMaxX() == r2.getMinX() && (
				isBetweenY(corners1[TOP_RIGHT], corners2[TOP_LEFT], corners2[BOTTOM_LEFT]) ||
				isBetweenY(corners2[TOP_LEFT], corners1[TOP_RIGHT], corners1[BOTTOM_RIGHT])
		)) {
			re |= RIGHT_WITH_LEFT;
		}
		if (r1.getMinY() == r2.getMaxY() && (
				isBetweenX(corners1[TOP_LEFT], corners2[BOTTOM_LEFT], corners2[BOTTOM_RIGHT]) ||
				isBetweenX(corners2[BOTTOM_LEFT], corners1[TOP_LEFT], corners1[TOP_RIGHT])
		)) {
			re |= TOP_WITH_BOTTOM;
		}
		if (r1.getMaxY() == r2.getMinY() && (
				isBetweenX(corners1[BOTTOM_LEFT], corners2[TOP_LEFT], corners2[TOP_RIGHT]) ||
				isBetweenX(corners2[TOP_LEFT], corners1[BOTTOM_LEFT], corners1[BOTTOM_RIGHT])
		)) {
			re |= BOTTOM_WITH_TOP;
		}
		if (sameAllowed) {
			if (r1.getMinX() == r2.getMinX() && (
					isBetweenY(corners1[TOP_LEFT], corners2[TOP_LEFT], corners2[BOTTOM_LEFT]) ||
					isBetweenY(corners2[TOP_LEFT], corners1[TOP_LEFT], corners1[BOTTOM_LEFT])
			)) {
				re |= LEFT_WITH_LEFT;
			}
			if (r1.getMaxX() == r2.getMaxX() && (
					isBetweenY(corners1[TOP_RIGHT], corners2[TOP_RIGHT], corners2[BOTTOM_RIGHT]) ||
					isBetweenY(corners2[TOP_RIGHT], corners1[TOP_RIGHT], corners1[BOTTOM_RIGHT])
			)) {
				re |= RIGHT_WITH_RIGHT;
			}
			if (r1.getMinY() == r2.getMinY() && (
					isBetweenX(corners1[TOP_LEFT], corners2[TOP_LEFT], corners2[TOP_RIGHT]) ||
					isBetweenX(corners2[TOP_LEFT], corners1[TOP_LEFT], corners1[TOP_RIGHT])
			)) {
				re |= TOP_WITH_TOP;
			}
			if (r1.getMaxY() == r2.getMaxY() && (
					isBetweenX(corners1[BOTTOM_LEFT], corners2[BOTTOM_LEFT], corners2[BOTTOM_RIGHT]) ||
					isBetweenX(corners2[BOTTOM_LEFT], corners1[BOTTOM_LEFT], corners1[BOTTOM_RIGHT])
			)) {
				re |= BOTTOM_WITH_BOTTOM;
			}
		}
		return re;
	}
	
	public static Pair<Integer, Double> closestSide(Rectangle r1, Rectangle r2, int mask) {
		int smallestDir = NONE;
		double smallestValue = Double.MAX_VALUE;
		double value;
		value = Math.abs(r1.getMinX() - r2.getMinX());
		if (value < smallestValue && (mask & LEFT_WITH_LEFT) != 0) {
			smallestDir = LEFT_WITH_LEFT;
			smallestValue = value;
		}
		value = Math.abs(r1.getMaxX() - r2.getMaxX());
		if (value < smallestValue && (mask & RIGHT_WITH_RIGHT) != 0) {
			smallestDir = RIGHT_WITH_RIGHT;
			smallestValue = value;
		}
		value = Math.abs(r1.getMinY() - r2.getMinY());
		if (value < smallestValue && (mask & TOP_WITH_TOP) != 0) {
			smallestDir = TOP_WITH_TOP;
			smallestValue = value;
		}
		value = Math.abs(r1.getMaxY() - r2.getMaxY());
		if (value < smallestValue && (mask & BOTTOM_WITH_BOTTOM) != 0) {
			smallestDir = BOTTOM_WITH_BOTTOM;
			smallestValue = value;
		}
		value = Math.abs(r1.getMinX() - r2.getMaxX());
		if (value < smallestValue && (mask & LEFT_WITH_RIGHT) != 0) {
			smallestDir = LEFT_WITH_RIGHT;
			smallestValue = value;
		}
		value = Math.abs(r1.getMaxX() - r2.getMinX());
		if (value < smallestValue && (mask & RIGHT_WITH_LEFT) != 0) {
			smallestDir = RIGHT_WITH_LEFT;
			smallestValue = value;
		}
		value = Math.abs(r1.getMinY() - r2.getMaxY());
		if (value < smallestValue && (mask & TOP_WITH_BOTTOM) != 0) {
			smallestDir = TOP_WITH_BOTTOM;
			smallestValue = value;
		}
		value = Math.abs(r1.getMaxY() - r2.getMinY());
		if (value < smallestValue && (mask & BOTTOM_WITH_TOP) != 0) {
			smallestDir = BOTTOM_WITH_TOP;
			smallestValue = value;
		}
		return new Pair<>(smallestDir, smallestValue);
	}
	
	public static Point minMoveOut(Rectangle r1, Rectangle r2, int mask) {
		Point re = r1.getLocation();
		int smallestDir = closestSide(r1, r2, mask & OPPOSITE_SIDES).getLeft();
		if (smallestDir == LEFT_WITH_RIGHT) {
			re.x = r2.x + r2.width;
		} else if (smallestDir == RIGHT_WITH_LEFT) {
			re.x = r2.x - r1.width;
		} else if (smallestDir == TOP_WITH_BOTTOM) {
			re.y = r2.y + r2.height;
		} else {
			re.y = r2.y - r1.height;
		}
		return re;
	}
	
	public static Point2D.Double topLeft(Rectangle2D.Double r) {
		return new Point2D.Double(r.x, r.y);
	}
	
	public static Point2D.Double bottomLeft(Rectangle2D.Double r) {
		return new Point2D.Double(r.x, r.y + r.height);
	}
	
	public static Point2D.Double bottomRight(Rectangle2D.Double r) {
		return new Point2D.Double(r.x + r.width, r.y + r.height);
	}
	
	public static Point2D.Double topRight(Rectangle2D.Double r) {
		return new Point2D.Double(r.x + r.width, r.y);
	}
	
	/** topLeft, topRight, bottomLeft, bottomRight */
	public static Point2D.Double[] corners(Rectangle2D.Double r) {
		Point2D.Double[] re = new Point2D.Double[4];
		for (int i = 0; i < 2; ++i) {
			for (int j = 0; j < 2; ++j) {
				re[i + 2 * j] = (new Point2D.Double(r.x + i * r.width, r.y + j * r.height));
			}
		}
		return re;
	}
	
	/** is a between b and c horizontally */
	protected static boolean isBetweenX(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
		return (a.x >= b.x && a.x <= c.x) || (a.x <= b.x && a.x >= c.x);
	}
	/** is a between b and c vertically */
	protected static boolean isBetweenY(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
		return (a.y >= b.y && a.y <= c.y) || (a.y <= b.y && a.y >= c.y);
	}
	
	public static int frameContains(Rectangle2D.Double r, Point2D.Double p) {
		int re = NONE;
		Point2D.Double[] corners = Rectangles.corners(r);
		
		if (r.getMinX() == p.x && isBetweenY(p, corners[TOP_LEFT], corners[BOTTOM_LEFT])) {
			re |= LEFT;
		}
		if (r.getMaxX() == p.x && isBetweenY(p, corners[TOP_RIGHT], corners[BOTTOM_RIGHT])) {
			re |= RIGHT;
		}
		if (r.getMinY() == p.x && isBetweenX(p, corners[TOP_LEFT], corners[TOP_RIGHT])) {
			re |= TOP;
		}
		if (r.getMaxX() == p.x && isBetweenX(p, corners[BOTTOM_LEFT], corners[BOTTOM_RIGHT])) {
			re |= BOTTOM;
		}
		return re;
	}
	
	public static int shareSide(Rectangle2D.Double r1, Rectangle2D.Double r2, boolean sameAllowed) {
		int re = NONE;
		Point2D.Double[] corners1 = Rectangles.corners(r1);
		Point2D.Double[] corners2 = Rectangles.corners(r2);
		if (r1.getMinX() == r2.getMaxX() && (
				isBetweenY(corners1[TOP_LEFT], corners2[TOP_RIGHT], corners2[BOTTOM_RIGHT]) ||
				isBetweenY(corners2[TOP_RIGHT], corners1[TOP_LEFT], corners1[BOTTOM_LEFT])
		)) {
			re |= LEFT_WITH_RIGHT;
		}
		if (r1.getMaxX() == r2.getMinX() && (
				isBetweenY(corners1[TOP_RIGHT], corners2[TOP_LEFT], corners2[BOTTOM_LEFT]) ||
				isBetweenY(corners2[TOP_LEFT], corners1[TOP_RIGHT], corners1[BOTTOM_RIGHT])
		)) {
			re |= RIGHT_WITH_LEFT;
		}
		if (r1.getMinY() == r2.getMaxY() && (
				isBetweenX(corners1[TOP_LEFT], corners2[BOTTOM_LEFT], corners2[BOTTOM_RIGHT]) ||
				isBetweenX(corners2[BOTTOM_LEFT], corners1[TOP_LEFT], corners1[TOP_RIGHT])
		)) {
			re |= TOP_WITH_BOTTOM;
		}
		if (r1.getMaxY() == r2.getMinY() && (
				isBetweenX(corners1[BOTTOM_LEFT], corners2[TOP_LEFT], corners2[TOP_RIGHT]) ||
				isBetweenX(corners2[TOP_LEFT], corners1[BOTTOM_LEFT], corners1[BOTTOM_RIGHT])
		)) {
			re |= BOTTOM_WITH_TOP;
		}
		if (sameAllowed) {
			if (r1.getMinX() == r2.getMinX() && (
					isBetweenY(corners1[TOP_LEFT], corners2[TOP_LEFT], corners2[BOTTOM_LEFT]) ||
					isBetweenY(corners2[TOP_LEFT], corners1[TOP_LEFT], corners1[BOTTOM_LEFT])
			)) {
				re |= LEFT_WITH_LEFT;
			}
			if (r1.getMaxX() == r2.getMaxX() && (
					isBetweenY(corners1[TOP_RIGHT], corners2[TOP_RIGHT], corners2[BOTTOM_RIGHT]) ||
					isBetweenY(corners2[TOP_RIGHT], corners1[TOP_RIGHT], corners1[BOTTOM_RIGHT])
			)) {
				re |= RIGHT_WITH_RIGHT;
			}
			if (r1.getMinY() == r2.getMinY() && (
					isBetweenX(corners1[TOP_LEFT], corners2[TOP_LEFT], corners2[TOP_RIGHT]) ||
					isBetweenX(corners2[TOP_LEFT], corners1[TOP_LEFT], corners1[TOP_RIGHT])
			)) {
				re |= TOP_WITH_TOP;
			}
			if (r1.getMaxY() == r2.getMaxY() && (
					isBetweenX(corners1[BOTTOM_LEFT], corners2[BOTTOM_LEFT], corners2[BOTTOM_RIGHT]) ||
					isBetweenX(corners2[BOTTOM_LEFT], corners1[BOTTOM_LEFT], corners1[BOTTOM_RIGHT])
			)) {
				re |= BOTTOM_WITH_BOTTOM;
			}
		}
		return re;
	}
	
	/** 
	 * re.left - integer - based on DIRECTION_WITH_DIRECTION
	 * re.right - double - the actual distance
	 */
	public static Pair<Integer, Double> closestSide(Rectangle2D.Double r1, Rectangle2D.Double r2, int mask) {
		int smallestDir = NONE;
		double smallestValue = Double.MAX_VALUE;
		double value;
		value = Math.abs(r1.getMinX() - r2.getMinX());
		if (value < smallestValue && (mask & LEFT_WITH_LEFT) != 0) {
			smallestDir = LEFT_WITH_LEFT;
			smallestValue = value;
		}
		value = Math.abs(r1.getMaxX() - r2.getMaxX());
		if (value < smallestValue && (mask & RIGHT_WITH_RIGHT) != 0) {
			smallestDir = RIGHT_WITH_RIGHT;
			smallestValue = value;
		}
		value = Math.abs(r1.getMinY() - r2.getMinY());
		if (value < smallestValue && (mask & TOP_WITH_TOP) != 0) {
			smallestDir = TOP_WITH_TOP;
			smallestValue = value;
		}
		value = Math.abs(r1.getMaxY() - r2.getMaxY());
		if (value < smallestValue && (mask & BOTTOM_WITH_BOTTOM) != 0) {
			smallestDir = BOTTOM_WITH_BOTTOM;
			smallestValue = value;
		}
		value = Math.abs(r1.getMinX() - r2.getMaxX());
		if (value < smallestValue && (mask & LEFT_WITH_RIGHT) != 0) {
			smallestDir = LEFT_WITH_RIGHT;
			smallestValue = value;
		}
		value = Math.abs(r1.getMaxX() - r2.getMinX());
		if (value < smallestValue && (mask & RIGHT_WITH_LEFT) != 0) {
			smallestDir = RIGHT_WITH_LEFT;
			smallestValue = value;
		}
		value = Math.abs(r1.getMinY() - r2.getMaxY());
		if (value < smallestValue && (mask & TOP_WITH_BOTTOM) != 0) {
			smallestDir = TOP_WITH_BOTTOM;
			smallestValue = value;
		}
		value = Math.abs(r1.getMaxY() - r2.getMinY());
		if (value < smallestValue && (mask & BOTTOM_WITH_TOP) != 0) {
			smallestDir = BOTTOM_WITH_TOP;
			smallestValue = value;
		}
		return new Pair<>(smallestDir, smallestValue);
	}
	
	public static Point2D.Double minMoveOut(Rectangle2D.Double r1, Rectangle2D.Double r2, int mask) {
		Point2D.Double re = new Point2D.Double(r1.x, r1.y);
		int smallestDir = closestSide(r1, r2, mask & OPPOSITE_SIDES).getLeft();
		if (smallestDir == LEFT_WITH_RIGHT) {
			re.x = r2.x + r2.width;
		} else if (smallestDir == RIGHT_WITH_LEFT) {
			re.x = r2.x - r1.width;
		} else if (smallestDir == TOP_WITH_BOTTOM) {
			re.y = r2.y + r2.height;
		} else {
			re.y = r2.y - r1.height;
		}
		return re;
	}
	
}
