package javautilities.games.basic.core;

import java.awt.geom.Rectangle2D;

public class Collision {

	public static final int NONE = 0;
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;

	public int type = NONE;
	public float diff;
	public Mob mob1;
	public Mob mob2;
	
	public Mob left = null;
	public Mob right = null;
	public Mob top = null;
	public Mob bottom = null;
	
	public Collision(Mob mob1, Mob mob2) {
		this.mob1 = mob1;
		this.mob2 = mob2;
		if (!mob2.isCollidingWith(mob1)) {
			this.type = NONE;
			return;
		}
		
		Rectangle2D.Float newBounds = new Rectangle2D.Float(mob1.bounds.x, mob1.bounds.y, mob1.bounds.width, mob1.bounds.height);
		newBounds.x += mob1.velocity.x;
		newBounds.y += mob1.velocity.y;
		
		if (!newBounds.intersects(mob2.bounds)) {
			this.type = NONE;
			return;
		}
		
		float hDiff = (float) Math.min(
				Math.abs(newBounds.getMaxX() - mob2.bounds.getMinX()),
				Math.abs(newBounds.getMinX() - mob2.bounds.getMaxX())
				);
		float vDiff = (float) Math.min(
				Math.abs(newBounds.getMaxY() - mob2.bounds.getMinY()),
				Math.abs(newBounds.getMinY() - mob2.bounds.getMaxY())
				);
		
		if (hDiff < vDiff) {
			type = HORIZONTAL;
			diff = hDiff;
			if (Math.abs(newBounds.getMaxX() - mob2.bounds.getMinX()) < 
					Math.abs(newBounds.getMinX() - mob2.bounds.getMaxX())) {
				left = mob1;
				right = mob2;
			} else {
				left = mob2;
				right = mob1;
			}
		} else {
			type = VERTICAL;
			diff = vDiff;
			if (Math.abs(newBounds.getMaxY() - mob2.bounds.getMinY()) < 
					Math.abs(newBounds.getMinY() - mob2.bounds.getMaxY())) {
				top = mob1;
				bottom = mob2;
			} else {
				top = mob2;
				bottom = mob1;
			}
		}
	}
	
	public void callbacks() {
		mob1.onCollision(this);
		mob2.onCollision(this);
	}
}
