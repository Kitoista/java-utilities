package javautilities.ui.defaults;

import java.util.Iterator;

import javax.swing.JPanel;

public class KPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public KPanel() {
		super();
		init();
	}
	
	private void init() {
		setBackground(UI.panelColor);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
	public KLabel add(String text) {
		KLabel re = new KLabel(text); 
		add(re);
		return re;
	}
	
	public void add(String... texts) {
		for (int i = 0; i < texts.length; i++) {
			add(new KLabel(texts[i]));
		}
	}
	
}
