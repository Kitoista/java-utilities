package javautilities.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SymmetricGraph<T> {

	Map<T, Set<T>> lines = new HashMap<>();
	
	public void addPoint(T point) {
		if (!lines.containsKey(point)) {
			lines.put(point, new HashSet<T>());
		}
	}
	
	public boolean containsPoint(T point) {
		return lines.containsKey(point);
	}
	
	public void removePoint(T point) {
		lines.remove(point);
		for (T a : lines.keySet()) {
			lines.get(a).remove(point);
		}
	}
	
	public void addLine(T a, T b) {
		addPoint(a);
		addPoint(b);
		lines.get(a).add(b);
		lines.get(b).add(a);
	}
	
	public void removeLine(T a, T b) {
		if (!containsPoint(a)) {
			return;
		}
		lines.get(a).remove(b);
		if (!containsPoint(b)) {
			return;
		}
		lines.get(b).remove(a);
	}
	
	public boolean containsLine(T a, T b) {
		if (!containsPoint(a)) {
			return false;
		}
		return lines.get(a).contains(b);
	}
	
	public Set<T> getPoints() {
		return lines.keySet();
	}
	
	public Set<T> getNeighbours(T point) {
		if (!containsPoint(point)) {
			return new HashSet<T>();
		}
		return lines.get(point);
	}
	
	public Set<Line<T, T>> getLines() {
		Set<Line<T, T>> re = new HashSet<>();
		for (T a : lines.keySet()) {
			for(T b : lines.get(a)) {
				if (a.hashCode() <= b.hashCode()) {
					re.add(new Line<T, T>(a, b, Line.BOTH));
				}
			}
		}
		return re;
	}
	
	public String toString() {
		return getLines().toString();
	}
}
