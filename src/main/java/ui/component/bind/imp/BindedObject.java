package ui.component.bind.imp;

import javax.swing.JButton;
import javax.swing.JLabel;

import meta.Property;
import ui.component.bind.BindedProperty;

public class BindedObject extends BindedProperty<Object> {

	private static final long serialVersionUID = 1L;
	protected JLabel label;
	protected JButton changeButton;
	
	protected BindedObject(Property property, Object object) {
		super(property, object);
	}
	
	@Override
	protected void initGui() {
		label = new JLabel(property.getName());
		changeButton = new JButton("can't change");
		changeButton.setEnabled(false);
		this.add(label);
		this.add(changeButton);
	}
	
	public static boolean canHandle(Class<?> type) {
		return true;
	}
	
	@Override
	protected Thread generateMemThread() {
		return new Thread();
	}
	
}
