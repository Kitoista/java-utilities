package javautilities.games.basic.core;

import java.util.ArrayList;
import java.util.List;

import javautilities.games.basic.mobs.Wall;

public class GameMap {

	public List<Mob> staticMobs = new ArrayList<Mob>();
	public List<Mob> movingMobs = new ArrayList<Mob>();
	
	public static GameMap baseMap() {
		GameMap re = new GameMap();
		
		re.staticMobs.add(new Wall(250, 600, 500, 20));
		re.staticMobs.add(new Wall(250, 500, 20, 100));
		re.staticMobs.add(new Wall(730, 500, 20, 100));
		
		return re;
	}
	
}
