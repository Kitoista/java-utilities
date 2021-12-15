package javautilities.games.basic.core.icons;

public class Animation implements IconHandler {

	String[] icons = new String[1];
	int index = 0;
	
	int ticksSinceLastFrame = 0;
	int frameLength = 5;
	
	public Animation(String[] icons) {
		this.icons = icons;
	}
	
	@Override
	public String getIcon() {
		return icons[index];
	}

	@Override
	public void tick() {
		if (ticksSinceLastFrame >= frameLength) {
			ticksSinceLastFrame = 0;
			index = (index + 1) % icons.length;
		} else {
			++ticksSinceLastFrame;
		}
	}

}
