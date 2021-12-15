package javautilities.games.basic.core;

import java.awt.geom.Rectangle2D;
import javautilities.games.basic.core.characters.CharacterFactory;
import javautilities.games.basic.core.characters.Character;

public class Game {

	float gravity = 1;
	
	public GameMap map = GameMap.baseMap();
	
	public Character addCharacter(String character) {
		Character re = CharacterFactory.create(character);
		map.movingMobs.add(re);
		return re;
	}
	
	public void tick() {
		
		for (Mob mob : map.movingMobs) {
			
			Collision hCollision = null;
			Collision vCollision = null;
			
			for (Mob other : map.movingMobs) {
				if (other == mob) {
					continue;
				}
				Collision collision = new Collision(mob, other);
				if (collision.type == Collision.HORIZONTAL) {
					if (hCollision == null || hCollision.diff > collision.diff) {
						hCollision = collision;
					}
				} else if (collision.type == Collision.VERTICAL) {
					if (vCollision == null || vCollision.diff > collision.diff) {
						vCollision = collision;
					}
				}
			}
			
			for (Mob wall : map.staticMobs) {
				Collision collision = new Collision(mob, wall);
				if (collision.type == Collision.HORIZONTAL) {
					if (hCollision == null || hCollision.diff > collision.diff) {
						hCollision = collision;
					}
				} else if (collision.type == Collision.VERTICAL) {
					if (vCollision == null || vCollision.diff > collision.diff) {
						vCollision = collision;
					}
				}
			}
			
			Rectangle2D.Float newBounds = new Rectangle2D.Float(mob.bounds.x, mob.bounds.y, mob.bounds.width, mob.bounds.height);
			newBounds.x += mob.velocity.x;
			newBounds.y += mob.velocity.y;
			
			if (hCollision != null) {
				hCollision.callbacks();
				newBounds.x -= Math.signum(mob.velocity.x) * hCollision.diff;
				hCollision.mob1.velocity.x = 0;
				hCollision.mob2.velocity.x = 0;
			}
			
			if (vCollision != null) {
				vCollision.callbacks();
				newBounds.y -= Math.signum(mob.velocity.y) * vCollision.diff;
				vCollision.mob1.velocity.y = 0;
				vCollision.mob2.velocity.y = 0;
			}
			
			mob.bounds = newBounds;

			mob.tick();
			mob.iconHandler.tick();
			
			if (mob.gravity) {
				mob.velocity.y += gravity;
			}
			
		}
	}
	
	
	
}
