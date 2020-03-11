package javautilities.ui.defaults;

import javax.swing.JCheckBox;

public class KCheckBox extends JCheckBox {

	private static final long serialVersionUID = 1L;

	public KCheckBox() {
		super();
		init();
	}
	
	private void init() {
		setOpaque(false);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
}
