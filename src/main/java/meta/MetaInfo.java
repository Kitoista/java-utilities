package meta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetaInfo {
	
	private static Class<?>[] primitives = { boolean.class, byte.class, char.class, short.class, int.class, long.class, float.class, double.class };
	
	public static Set<Class<?>> getDeepInterfaces(Class<?> c) {
		return getDeepInterfaces(c, new HashSet<Class<?>>());
	}
	private static Set<Class<?>> getDeepInterfaces(Class<?> c, Set<Class<?>> re) {
		if (c == null) {
			return re;
		}
		getDeepInterfaces(c.getSuperclass(), re);
		for (Class<?> interf: c.getInterfaces()) {
			re.add(interf);
			getDeepInterfaces(interf, re);
		}
		return re;
	}
	
	public static List<Class<?>> getSuperClasses(Class<?> c) {
		return getSuperClasses(c, new ArrayList<Class<?>>());
	}
	private static List<Class<?>> getSuperClasses(Class<?> c, List<Class<?>> re) {
		if (c == null || c.getSuperclass() == null) {
			return re;
		}
		re.add(c.getSuperclass());
		return getSuperClasses(c.getSuperclass(), re);
	}
	
	public static Class<?> toPrimitive(Class<?> c) {
		if (Boolean.class.equals(c)) return boolean.class;
		if (Byte.class.equals(c)) return byte.class;
		if (Character.class.equals(c)) return char.class;
		if (Short.class.equals(c)) return short.class;
		if (Integer.class.equals(c)) return int.class;
		if (Long.class.equals(c)) return long.class;
		if (Float.class.equals(c)) return float.class;
		if (Double.class.equals(c)) return double.class;
		return null;
	}
	
	public static boolean isPrimitive(Class<?> c) {
		for (Class<?> class1 : primitives) {
			if (class1.equals(c)) {
				return true;
			}
		}
		return false;
	}
}
