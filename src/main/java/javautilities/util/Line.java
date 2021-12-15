package javautilities.util;

public class Line<L, R> {
	
	protected L left;
	protected R right;
	
	protected int direction;
	
	public static final int FROM_LEFT = -1;
	public static final int FROM_RIGHT = 1;
	public static final int BOTH = 0;
	
	public Line(L left, R right, int direction) {
		this.direction = direction;
		this.left = left;
		this.right = right;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Line)) {
			return false;
		}
		Line<?, ?> o = (Line<?, ?>) other;
		if (this.direction != o.direction) {
			return false;
		}
		boolean re = partsEquals(this.left, o.left) && partsEquals(this.right, o.right);
		if (direction == 0) {
			re = re || partsEquals(this.left, o.right) && partsEquals(this.right, o.left);
		}
		return re;
	}
	
	protected boolean partsEquals(Object left, Object right) {
		if (left == right) {
			return true;
		}
		if (left == null) {
			return false;
		}
		return left.equals(right);
	}
	
	public L getLeft() {
		return left;
	}
	
	public R getRight() {
		return right;
	}
	
	public String toString() {
		return "(" + left + (direction < 0 ? " <= " : direction > 0 ? " => " : ", ") + right + ")";
	}
	
}