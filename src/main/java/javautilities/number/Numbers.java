package javautilities.number;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javautilities.meta.MetaMethods;

public class Numbers {

	public static Number parse(String value, Class<?> type) throws NumberParseException {
		Method method = MetaMethods.getMethod(type, getParseNumMethodName(type.getSimpleName()), String.class);
		if (method == null) {
			throw new RuntimeException("cant find: " + type.getSimpleName() + getParseNumMethodName(type.getSimpleName()) + "()");
		}
		try {
			return (Number) method.invoke(null, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new NumberParseException(e);
		}
	}
	
	private static String getParseNumMethodName(String className) {
		if (className.equals("Integer")) {
			return "parseInt";
		} else {
			return "parse" + className;
		}
	}
	
	public static Object parse(String value) {
		if (value.contains(".")) {
			return parse(value, Double.class);
		}
		return parseOrBigger(value, Byte.class);
	}
	
	private static Object parseOrBigger(String value, Class<?> type) {
		try {
			return parse(value, type);
		} catch (NumberParseException e) {
			Class<?> biggerClass = type.equals(Byte.class) ? Short.class : 
								   type.equals(Short.class) ? Integer.class :
								   type.equals(Integer.class) ? Long.class : null;
			if (biggerClass == null) {
				throw e;
			}
			return parseOrBigger(value, biggerClass);
		}
	}
	
	public static boolean isNumber(Class<?> type) {
		return Number.class.isAssignableFrom(type) || 
				type.equals(byte.class) ||
				type.equals(short.class) ||
				type.equals(int.class) ||
				type.equals(long.class) ||
				type.equals(float.class) ||
				type.equals(double.class);
	}
	
	public static boolean isNumber(Object value) {
		return isNumber(value.getClass());
	}
	

	// isNumber Class
	
	public static boolean isByte(Class<?> type) {
		return Byte.class.equals(type) || byte.class.equals(type);
	}
	
	public static boolean isShort(Class<?> type) {
		return Short.class.equals(type) || short.class.equals(type);
	}
	
	public static boolean isInteger(Class<?> type) {
		return Integer.class.equals(type) || int.class.equals(type);
	}
	
	public static boolean isLong(Class<?> type) {
		return Long.class.equals(type) || long.class.equals(type);
	}
	
	public static boolean isFloat(Class<?> type) {
		return Float.class.equals(type) || float.class.equals(type);
	}
	
	public static boolean isDouble(Class<?> type) {
		return Double.class.equals(type) || double.class.equals(type);
	}
	
	
	// isNumber Object
	
	public static boolean isByte(Object value) {
		return isByte(value.getClass());
	}
	
	public static boolean isShort(Object value) {
		return isShort(value.getClass());
	}
	
	public static boolean isInteger(Object value) {
		return isInteger(value.getClass());
	}
	
	public static boolean isLong(Object value) {
		return isLong(value.getClass());
	}
	
	public static boolean isFloat(Object value) {
		return isFloat(value.getClass());
	}
	
	public static boolean isDouble(Object value) {
		return isDouble(value.getClass());
	}
	
}
