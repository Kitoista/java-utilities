package ui.component.bind.imp;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import meta.Property;
import ui.component.bind.BindedProperty;
import ui.defaults.KCheckBox;
import ui.defaults.KLabel;

public class BindedBoolean extends BindedProperty<Object> {

	private static final long serialVersionUID = 1L;
	
	protected JLabel label;
	protected JCheckBox checkBox1;
	
	protected BindedBoolean(Property property, Object object) {
		super(property, object);
	}
	
	@Override
	protected void initGui() {
		label = new KLabel(property.getName());
		try {
			checkBox1 = new KCheckBox();
			checkBox1.setSelected((object == null || property == null || property.get(object) == null) ? false : (boolean) property.get(object));
			checkBox1.addActionListener(e -> {
				callOnGuiChanged();
			});
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			e.printStackTrace();
		}
		checkBox1.setEnabled(property.hasSet());
		this.add(label);
		this.add(checkBox1);
	}
	
	private void callOnGuiChanged() {
		try {
			onGuiChanged(checkBox1.isSelected());
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onMemChanged(Object obj) {
		if (obj.equals(checkBox1.isSelected())) {
			return;
		}
		checkBox1.setSelected((boolean) obj);
		super.onMemChanged(obj);
	}
	
	public static boolean canHandle(Class<?> type) {
		return type.equals(Boolean.class) || type.equals(boolean.class);
	}
	
}
