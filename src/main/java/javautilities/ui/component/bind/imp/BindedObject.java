package javautilities.ui.component.bind.imp;

import javax.swing.JButton;
import javax.swing.JLabel;

import javautilities.meta.Property;
import javautilities.ui.component.bind.BindedProperty;
import javautilities.ui.defaults.KButton;
import javautilities.ui.defaults.KLabel;

public class BindedObject extends BindedProperty<Object> {

	private static final long serialVersionUID = 1L;
	protected JLabel label;
	protected JButton changeButton;
	
	protected BindedObject(Property property, Object object) {
		super(property, object);
	}
	
	@Override
	protected void initGui() {
		label = new KLabel(property.getName());
		changeButton = new KButton("can't change");
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
