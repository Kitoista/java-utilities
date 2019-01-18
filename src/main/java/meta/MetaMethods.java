package meta;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import string.Strings;

public class MetaMethods {
	
	public static Collection<Method> getGetters(Class<?> c) {
		Collection<Method> re = new ArrayList<>();
		for (Method method : c.getMethods()) {
			if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
				re.add(method);
			}
		}
		return re;
	}
	
	public static Collection<Method> getSetters(Class<?> c) {
		Collection<Method> re = new ArrayList<>();
		for (Method method : c.getMethods()) {
			if (method.getName().startsWith("set") && method.getParameterTypes().length == 1) {
				re.add(method);
			}
		}
		return re;
	}
	
	public static Collection<Property> getProperties(Class<?> c) {
		Collection<Property> re = new ArrayList<>();
		Collection<String> names = new HashSet<>();
		Collection<Method> getters = getGetters(c);
		for (Method method : getters) {
			Property prop = new Property(c, toFieldName(method.getName()), method.getReturnType());
			re.add(prop);
			names.add(prop.getName());
		}
		Collection<Method> setters = getSetters(c);
		for (Method method : setters) {
			Property prop = new Property(c, toFieldName(method.getName()), method.getParameters()[0].getType());
			if (!re.contains(prop)) {
				re.add(prop);
				names.add(prop.getName());
			}
		}
		for (Field field : c.getFields()) {
			if (!names.contains(field.getName())) {
				re.add(new Property(c, field.getName(), field.getType()));
			}
		}
		return re;
	}
	
	public static String toFieldName(String getterSetter) {
		if (getterSetter.startsWith("get")) {
			return Strings.lowerCaseFirst(getterSetter.replaceFirst("get", ""));
		} else if (getterSetter.startsWith("set")) {
			return Strings.lowerCaseFirst(getterSetter.replaceFirst("set", ""));
		}
		throw new RuntimeException(getterSetter + " is not a getter or setter name");
	}
	
	public static String toGetterName(String fieldName, boolean isBoolean) {
		if (isBoolean) {
			return "is" + Strings.capitalizeFirst(fieldName);
		}
		return "get" + Strings.capitalizeFirst(fieldName);
	}
	
	public static String toSetterName(String fieldName) {
		return "set" + Strings.capitalizeFirst(fieldName);
	}
	
	public static Method getGetter(Class<?> c, String name) {
		Method re = getMethod(c, toGetterName(name, false));
		if (re == null) {
			return getMethod(c, toGetterName(name, true));
		}
		return re;
	}
	
	public static Method getSetter(Class<?> c, String name, Class<?> fieldType) {
		return getMethod(c, toSetterName(name), fieldType);
	}
	
	public static Method getMethod(Class<?> c, String name, Class<?>... params) {
		for (Method method : c.getMethods()) {
			Class<?>[] paramTypes = method.getParameterTypes();
			if (method.getName().equals(name) && paramTypes.length == params.length) {
				boolean isGood = true;
				int i = 0;
				for (Class<?> paramType : params) {
					if (!paramTypes[i].isAssignableFrom(paramType)) {
						isGood = false;
						break;
					}
					++i;
				}
				if (isGood) {
					return method;
				}
			}
		}
		return null;
	}
	
	public static Constructor<?> getConstructor(Class<?> c, Class<?>... inputParams) {
		for (Constructor<?> constructor : c.getDeclaredConstructors()) {
			Class<?>[] paramTypes = constructor.getParameterTypes();
			if (paramTypes.length == inputParams.length) {
				boolean isGood = true;
				int i = 0;
				for (Class<?> paramType : paramTypes) {
					boolean paramTypeIsObject = !MetaInfo.isPrimitive(inputParams[i]);
					boolean primitiveNull = paramType == null && MetaInfo.isPrimitive(inputParams[i]);
					boolean notAssignableObject = !inputParams[i].isAssignableFrom(paramType) && paramTypeIsObject; 
					if (primitiveNull || notAssignableObject) {
						isGood = false;
						break;
					}
					++i;
				}
				if (isGood) {
					return constructor;
				}
			}
		}
		return null;
	}
	
	public static Constructor<?> getConstructor(Class<?> c, Object... args) {
		Class<?>[] params = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			if (args[i] == null) {
				params[i] = null;
			}
			params[i] = args[i].getClass();
		}
		return getConstructor(c, params);
	}
	
	public static Object newInstance(Class<?> c, Object... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> constructor = getConstructor(c, args);
		if (constructor == null) {
			throw new IllegalArgumentException("There is no constructor with params: " + Arrays.toString(args));
		}
		return constructor.newInstance(args);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> ObjectConstructor<T> generateDefaultObjectConstructor(Class<T> type) {
		return args -> {
			try {
				return (T) newInstance(type, args);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				System.err.println("An error happened while trying to construct a " + type.getName());
				System.err.println("Given arguments: " + Arrays.toString(args));
				System.err.println("Found constructor: " + getConstructor(type, args));
				e.printStackTrace();
			}
			return null;
		};
	}
	
}
