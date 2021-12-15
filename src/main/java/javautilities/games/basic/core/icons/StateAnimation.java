package javautilities.games.basic.core.icons;

import java.util.HashMap;
import java.util.Map;

public class StateAnimation implements IconHandler {

	Map<String, String[]> icons = new HashMap<>();
	public String state = "";
	int index = 0;
	
	int ticksSinceLastFrame = 0;
	int frameLength = 5;
	
	public StateAnimation(Map<String, String[]> icons) {
		this.icons = icons;
	}
	
	@Override
	public String getIcon() {
		if (!icons.containsKey(state)) {
			return null;
		}
		return icons.get(state)[index % icons.get(state).length];
	}

	@Override
	public void tick() {
		if (!icons.containsKey(state)) {
			return;
		}
		if (ticksSinceLastFrame >= frameLength) {
			ticksSinceLastFrame = 0;
			index = (index + 1) % icons.get(state).length;
		} else {
			++ticksSinceLastFrame;
		}
	}

}
