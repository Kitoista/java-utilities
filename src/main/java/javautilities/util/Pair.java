package javautilities.util;

public class Pair<L, R> {
	
	protected L left;
	protected R right;
	
	public Pair(L a, R b) {
		this.left = a;
		this.right = b;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Pair)) {
			return false;
		}
		Pair<?, ?> o = (Pair<?, ?>) other;
		return (
				(this.left == null && o.left == null) || (this.left != null && this.left.equals(o.left))
			) && (
				(this.right == null && o.right == null) || (this.right != null && this.right.equals(o.right))
			) || (
					(this.left == null && o.right == null) || (this.left != null && this.left.equals(o.right))
			) && (
					(this.right == null && o.left == null) || (this.right != null && this.right.equals(o.left))
			);
	}
	
	public L getLeft() {
		return left;
	}
	
	public R getRight() {
		return right;
	}
	
	public String toString() {
		return "(" + left + ", " + right + ")";
	}
	
}