package ui.component.bind.imp;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import meta.Property;
import ui.component.bind.BindedProperty;

public class BindedString extends BindedProperty<Object> {

	private static final long serialVersionUID = 1L;
	
	protected JLabel label;
	protected JTextField textField1;
	protected JLabel error;
	
	protected BindedString(Property property, Object object) {
		super(property, object);
	}
	
	@Override
	protected void initGui() {
		label = new JLabel(property.getName());
		try {
			textField1 = new JTextField((object == null || property == null || property.get(object) == null) ? "" : property.get(object) + "");
			textField1.getDocument().addDocumentListener(new DocumentListener() {

				@Override
				public void changedUpdate(DocumentEvent arg0) {
					callOnGuiChanged();
				}

				@Override
				public void insertUpdate(DocumentEvent arg0) {
					callOnGuiChanged();
				}

				@Override
				public void removeUpdate(DocumentEvent arg0) {
					callOnGuiChanged();
				}

			});
		} catch (IllegalArgumentException | ReflectiveOperationException e) {
			e.printStackTrace();
		}
		textField1.setColumns(20);
		textField1.setEnabled(property.hasSet());
		error = new JLabel();
		error.setForeground(Color.red);
		this.add(label);
		this.add(textField1);
		this.add(error);
	}
	
	private void callOnGuiChanged() {
		try {
			onGuiChanged(convert(textField1.getText()));
			error.setText("");
		} catch (Exception e) {
			error.setText(e.getClass().getSimpleName());
		}
	}
	
	@Override
	protected void onMemChanged(Object obj) {
		if ((textField1.getText().equals("") && obj == null) || 
			textField1.getText().equals(obj + "")) {
			return;
		}
		textField1.setText(nullChanger(obj));
		super.onMemChanged(obj);
	}
	
	public static boolean canHandle(Class<?> type) {
		return type.equals(String.class);
	}
	
	protected Object convert(String value) throws Exception {
		return value;
	}
	
	protected String nullChanger(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj + "";
	}
	
}
