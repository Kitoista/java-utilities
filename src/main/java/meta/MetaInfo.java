package meta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetaInfo {
	
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
	
}
