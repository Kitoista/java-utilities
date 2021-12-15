package javautilities.games.basic.core.icons;

public class StaticIcon implements IconHandler {

	String icon = null;
	
	public StaticIcon(String icon) {
		this.icon = icon;
	}
	
	@Override
	public String getIcon() {
		return icon;
	}

	@Override
	public void tick() {
	}
	
}
