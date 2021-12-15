package javautilities.geometry;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class Vector implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public double x;
	public double y;
	
	
	// Constructors
	
	public Vector() {}
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public Vector(Point2D.Double p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public Vector(Vector v) {
		x = v.x;
		y = v.y;
	}
	
	
	// Converters
	
	public Point toPoint() {
		return new Point((int) x, (int) y);
	}
	
	
	// Addition
	
	public void add(Vector v) {
		x += v.x;
		y += v.y;
	}
	
	public void add(Point v) {
		add(new Vector(v));
	}
	
	public static Vector add(Vector a, Vector b) {
		Vector v = new Vector(a);
		v.add(b);
		return v;
	}
	
	
	// Subtraction
	
	public void sub(Vector v) {
		x -= v.x;
		y -= v.y;
	}
	
	public void sub(Point v) {
		sub(new Vector(v));
	}
	
	public static Vector sub(Vector a, Vector b) {
		Vector v = new Vector(a);
		v.sub(b);
		return v;
	}
	
	
	public void scale(double s) {
		x *= s;
		y *= s;
	}
	
	public static Vector scale(Vector v, double s) {
		Vector re = new Vector(v);
		re.scale(s);
		return re;
	}
	
	// Magnitude
	
	public double magnitude() {
		return Math.sqrt(x*x + y*y);
	}
	

	// Normalize
	
	public void normalize() {
		double magnitude = magnitude();
		x /= magnitude;
		y /= magnitude;
	}
	
	public static Vector normalize(Vector a) {
		Vector v = new Vector(a);
		v.normalize();
		return v;
	}
	
	// toString
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (!(other instanceof Vector)) {
			return false;
		}
		Vector o = (Vector) other;
		return x == o.x && y == o.y;
	}
	
}
