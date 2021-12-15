package javautilities.ui.defaults;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class KTextPane extends JTextPane {

	private static final long serialVersionUID = 1L;
	
	SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	
	public KTextPane() {
		super();
		init();
	}
	
	private void init() {
		setOpaque(false);
		StyleConstants.setForeground(attributeSet, UI.fontColor);
		this.setParagraphAttributes(attributeSet, true);
	}
	
	public void setTextAlignment(int styleConstant) {
		StyleConstants.setAlignment(attributeSet, styleConstant);
		this.setParagraphAttributes(attributeSet, true);
	}
	
}
