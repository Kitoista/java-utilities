package javautilities.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<L, R> {

	Map<L, Set<R>> fromLeft = new HashMap<>();
	Map<R, Set<L>> fromRight = new HashMap<>();
	
	// Points
	
	public boolean leftContainsPoint(L leftPoint) {
		return fromLeft.containsKey(leftPoint);
	}
	
	public boolean rightContainsPoint(R rightPoint) {
		return fromRight.containsKey(rightPoint);
	}
	
	
	public void leftAddPoint(L leftPoint) {
		if (!leftContainsPoint(leftPoint)) {
			fromLeft.put(leftPoint, new HashSet<R>());
		}
	}
	
	public void rightAddPoint(R rightPoint) {
		if (!rightContainsPoint(rightPoint)) {
			fromRight.put(rightPoint, new HashSet<L>());
		}
	}
	
	
	
	public void leftRemovePoint(L a) {
		fromLeft.remove(a);
		for (R b : fromRight.keySet()) {
			fromRight.get(b).remove(a);
		}
	}
	
	public void rightRemovePoint(R b) {
		fromRight.remove(b);
		for (L a : fromLeft.keySet()) {
			fromLeft.get(a).remove(b);
		}
	}
	
	
	// Lines
	
	public void addLineFromLeft(L a, R b) {
		leftAddPoint(a);
		rightAddPoint(b);
		fromLeft.get(a).add(b);
	}
	
	public void addLineFromRight(L a, R b) {
		leftAddPoint(a);
		rightAddPoint(b);
		fromRight.get(b).add(a);
	}
	
	public void addLine(L a, R b) {
		addLineFromLeft(a, b);
		addLineFromRight(a, b);
	}
	
	
	public void removeLineFromLeft(L a, R b) {
		if (!leftContainsPoint(a)) {
			return;
		}
		fromLeft.get(a).remove(b);
	}
	
	public void removeLineFromRight(L a, R b) {
		if (!rightContainsPoint(b)) {
			return;
		}
		fromRight.get(b).remove(a);
	}
	
	public void removeLine(L a, R b) {
		removeLineFromLeft(a, b);
		removeLineFromRight(a, b);
	}
	
	
	public boolean containsLineFromLeft(L a, R b) {
		if (!leftContainsPoint(a)) {
			return false;
		}
		return fromLeft.get(a).contains(b);
	}
	
	public boolean containsLineFromRight(L a, R b) {
		if (!rightContainsPoint(b)) {
			return false;
		}
		return fromRight.get(b).contains(a);
	}
	
	public boolean containsLine(L a, R b) {
		return containsLineFromLeft(a, b) && containsLineFromRight(a, b);
	}
	

	public Set<L> getLeftPoints() {
		return fromLeft.keySet();
	}
	
	public Set<R> getRightPoints() {
		return fromRight.keySet();
	}
	
	public Set<R> getNeighboursOfLeftPoint(L a) {
		if (!leftContainsPoint(a)) {
			return new HashSet<R>();
		}
		return fromLeft.get(a);
	}
	
	public Set<L> getNeighboursOfRightPoint(R b) {
		if (!rightContainsPoint(b)) {
			return new HashSet<L>();
		}
		return fromRight.get(b);
	}
	
	public Set<Line<L, R>> getLines() {
		Set<Line<L, R>> re = new HashSet<>();
		for (L a : fromLeft.keySet()) {
			for(R b : fromLeft.get(a)) {
				if (fromRight.get(b).contains(a)) {
					re.add(new Line<L, R>(a, b, Line.BOTH));
				} else {
					re.add(new Line<L, R>(a, b, Line.FROM_LEFT));
				}
			}
		}
		for (R b : fromRight.keySet()) {
			for(L a : fromRight.get(b)) {
				if (!fromLeft.get(a).contains(b)) {
					re.add(new Line<L, R>(a, b, Line.FROM_RIGHT));
				}
			}
		}
		return re;
	}
	
	public String toString() {
		return getLines().toString();
	}
}
