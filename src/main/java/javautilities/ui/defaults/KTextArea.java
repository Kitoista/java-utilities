package javautilities.ui.defaults;

import javax.swing.JTextArea;

public class KTextArea extends JTextArea {

	private static final long serialVersionUID = 1L;
	
	public KTextArea() {
		super();
		init();
	}
	
	public KTextArea(String text) {
		super(text);
		init();
	}
	
	private void init() {
		setOpaque(false);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
}
