package javautilities.games.basic.mobs;

import java.awt.geom.Rectangle2D;

import javautilities.games.basic.core.Mob;

public class Wall extends Mob {
	
	public Wall(float x, float y, float width, float height) {
		this.bounds = new Rectangle2D.Float(x, y, width, height);
		this.localIconBounds = new Rectangle2D.Float(0, 0, width, height);
		this.name = "wall";
	}

}
