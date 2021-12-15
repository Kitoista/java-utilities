package javautilities.ui.defaults;

import javax.swing.JSlider;

public class KSlider extends JSlider {

	private static final long serialVersionUID = 1L;
	
	public KSlider() {
		super();
		init();
	}
	
	public KSlider(int min, int max) {
		super(min, max);
		init();
	}
	
	public KSlider(int min, int max, int orientation) {
		super(min, max, orientation);
		init();
	}
	
	public KSlider(int orientation, int min, int max, int value) {
		super(orientation, min, max, value);
		init();
	}
	
	protected void init() {
		setOpaque(false);
		setSnapToTicks(true);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
}
