package javautilities.util;

import java.util.HashMap;

public class SymmerticMap<T> extends HashMap<T, T> {

	private static final long serialVersionUID = 1L;
	
	public T put(T key, T value) {
		if (key == null || value == null) {
			throw new NullPointerException("SymmetricMap key or value can't be null");
		}
		super.put(value, key);
		return super.put(key, value); 
	}
	
}
