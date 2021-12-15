package javautilities.games.basic.core.characters;

import javautilities.games.basic.core.Mob;
import javautilities.games.basic.core.icons.StateAnimation;

public class Character extends Mob {
	
	public float hp = 100;
	public float meeleDmg = 15;
	
	public Character(String name) {
		this.name = name;
		this.gravity = true;
	}
	
	public StateAnimation stateAnimation() {
		return (StateAnimation) iconHandler;
	}
	
	public void tick() {
		super.tick();
		if (velocity.y != 0) {
			stateAnimation().state = "jumping";
		} else if (state == STATE_IDLE) {
			stateAnimation().state = "idle";
		} else {
			stateAnimation().state = "moving";
		}
	}
	
	public void meele() {
		
	}
	
}
