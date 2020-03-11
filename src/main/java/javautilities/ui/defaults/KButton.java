package javautilities.ui.defaults;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class KButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	private Color hoverBackgroundColor = UI.lighterColor;
	private Color pressedBackgroundColor = UI.darkerColor;
	
	public KButton() {
		super();
		init();
	}
	
	public KButton(String str) {
		super(str);
		init();
	}
	
	private void init() {
		super.setContentAreaFilled(false);
		setFocusPainted(false);
		setBackground(UI.fillColor);
		setForeground(UI.fontColor);
		setFont(UI.normalFont);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isPressed()) {
			g.setColor(pressedBackgroundColor);
		} else if (getModel().isRollover()) {
			g.setColor(hoverBackgroundColor);
		} else {
			g.setColor(getBackground());
		}
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}
	
	@Override
	public void setContentAreaFilled(boolean b) {
	}
	
	public Color getHoverBackgroundColor() {
		return hoverBackgroundColor;
	}
	
	public void setHoverBackgroundColor(Color hoverBackgroundColor) {
		this.hoverBackgroundColor = hoverBackgroundColor;
	}
	
	public Color getPressedBackgroundColor() {
		return pressedBackgroundColor;
	}
	
	public void setPressedBackgroundColor(Color pressedBackgroundColor) {
		this.pressedBackgroundColor = pressedBackgroundColor;
	}
	
}
