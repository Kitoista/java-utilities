package javautilities.games.basic.core;

import java.awt.geom.Rectangle2D;

import javautilities.games.basic.core.icons.IconHandler;
import javautilities.games.basic.core.icons.StaticIcon;
import javautilities.geometry.Vector;

public class Mob {
	
	public static final int STATE_IDLE = 0;
	public static final int STATE_MOVING = 1;
	
	public int state = STATE_IDLE;
	
	public static final int LAYER_DEFAULT = 0;
	public static int NUM = 0;
	
	public int layer = LAYER_DEFAULT;
	
	public String name = "Mob";
	public IconHandler iconHandler = new StaticIcon(null);
	
	public boolean colliding = true;
	public boolean gravity = false;
	public boolean facingLeft = false;
	
	public float acceleration = 1f;
	public float friction = 1f;
	public float speed = 5;
	
	public float jumpHeight = 10;
	public float maxJumpCount = 2;
	public float jumpCounter = 0;
	
	public int id = NUM++;
	
	public Rectangle2D.Float bounds = new Rectangle2D.Float(400, 400, 29, 25);
	
	public Rectangle2D.Float localIconBounds = new Rectangle2D.Float(0, 0, bounds.width, bounds.height);
	
	public Vector velocity = new Vector();
	
	public boolean isCollidingWith(Mob other) {
		return this.layer == other.layer && this.colliding && other.colliding;
	}
	
	public Rectangle2D.Float getIconBounds() {
		return new Rectangle2D.Float(bounds.x + localIconBounds.x, bounds.y + localIconBounds.y, localIconBounds.width, localIconBounds.height);
	}
	
	public String toString() {
		Rectangle2D.Float iconBounds = getIconBounds();
		return id + " " + iconBounds.x + " " + iconBounds.y + " " + iconBounds.width + " " + iconBounds.height + " " + iconHandler.getIcon() + " " + facingLeft;
	}
	
	public void onCollision(Collision col) {
		if (col.top == this) {
			jumpCounter = 0;
		}
	}
	
	public void tick() {
		if (state == STATE_MOVING) {
			if (facingLeft) {
				if (velocity.x < -speed) {
					velocity.x = Math.max(velocity.x - acceleration, -speed);
				}
			} else if (velocity.x > speed) {
				velocity.x = Math.min(velocity.x + acceleration, speed);
			}
		} else if (state == STATE_IDLE) {
			if (velocity.x < 0) {
				velocity.x = Math.min(0, velocity.x + friction);
			} else if (velocity.x > 0) {
				velocity.x = Math.max(0, velocity.x - friction);
			}
		}
	}
	
	public void moveLeft() {
		state = STATE_MOVING;
		velocity.x = -speed;
		facingLeft = true;
	}
	
	public void moveRight() {
		state = STATE_MOVING;
		velocity.x = speed;
		facingLeft = false;
	}
	
	public void jump() {
		if (jumpCounter < maxJumpCount) {
			velocity.y = -jumpHeight;
			++jumpCounter;
		}
	}
	
	public void stopMoving() {
		state = STATE_IDLE;
	}
	
}
