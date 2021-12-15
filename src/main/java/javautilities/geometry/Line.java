package javautilities.geometry;

public class Line {
	
	public Vector point; // = new Vector();
	public Vector normalVector; // = new Vector();
	protected double computed;
	
	public Line(Vector point, Vector normalVector) {
		this.point = new Vector(point);
		this.normalVector = new Vector(normalVector);
		this.computed = normalVector.x * point.x + normalVector.x * point.y;
	}
	
	public Vector intersection(Line other) {
		
		return normalVector;
	}
	
	public boolean contains(Vector point) {
		return contains(point, 0.0000000000000001);
	}
	
	public boolean contains(Vector point, double epsilon) {
		double other = normalVector.x * point.x + normalVector.y * point.y;
		return Math.abs(other - computed) <= epsilon;
	}
	
}
