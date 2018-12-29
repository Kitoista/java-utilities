package ui.component.bind.imp;

import meta.Property;
import number.NumberParseException;
import number.Numbers;
import string.Strings;

public class BindedNumber extends BindedString {

	private static final long serialVersionUID = 1L;

	protected BindedNumber(Property property, Object object) {
		super(property, object);
	}
		
	public static boolean canHandle(Class<?> type) {
		return Number.class.isAssignableFrom(type) || 
				type.equals(byte.class) ||
				type.equals(short.class) ||
				type.equals(int.class) ||
				type.equals(long.class) ||
				type.equals(float.class) ||
				type.equals(double.class);
	}
	
	protected Object convert(String value) throws NumberParseException {
		if (Strings.isEmpty(value)) {
			return 0;
		}
		return Numbers.parse(value);
	}
	
	@Override
	protected void onMemChanged(Object obj) {
		if ((textField1.getText().equals("") && obj.equals(0)) || 
			(textField1.getText().equals("") && obj.equals(0.0))) {
			return;
		}
		super.onMemChanged(obj);
	}
	
}
