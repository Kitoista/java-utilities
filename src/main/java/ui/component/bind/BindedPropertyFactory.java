package ui.component.bind;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Stack;

import meta.MetaMethods;
import meta.Property;
import ui.component.bind.imp.BindedBoolean;
import ui.component.bind.imp.BindedBufferedImage;
import ui.component.bind.imp.BindedNumber;
import ui.component.bind.imp.BindedObject;
import ui.component.bind.imp.BindedString;

public class BindedPropertyFactory {

	private static Stack<Class<?>> knownComponents = new Stack<>();
	
	public static BindedProperty<?> create(Property property) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		return create(property);
	}

	public static BindedProperty<?> create(Property property, Object... args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Class<?>[] constructorArgs;
		constructorArgs = new Class<?>[args.length + 1];
		Object[] allArgs = new Object[args.length + 1];
		constructorArgs[0] = Property.class;
		allArgs[0] = property;
		for (int i = 0; i < args.length; ++i) {
			allArgs[i + 1] = args[i];
			constructorArgs[i + 1] = args[i] == null ? Object.class : args[i].getClass();
		}
		Class<?>[] knownComponents = new Class<?>[getKnownComponents().size()];
		getKnownComponents().toArray(knownComponents);
		for (int i = knownComponents.length - 1; i >= 0; --i) {
			Class<?> class1 = knownComponents[i];
			Method canHandle = class1.getMethod("canHandle", Class.class);
			if (((Boolean) canHandle.invoke(null, property.getType()))) {
				Constructor<?> constructor = MetaMethods.getConstructor(class1, constructorArgs);
				if (constructor == null) {
					continue;
				}
				constructor.setAccessible(true);
				System.out.println("Created a " + class1.getSimpleName());
				return (BindedProperty<?>) constructor.newInstance(allArgs);
			}
		}
		throw new ClassNotFoundException();
	}

	public static Collection<Class<?>> getKnownComponents() {
		if (knownComponents.isEmpty()) {
			knownComponents.add(BindedObject.class);
			knownComponents.add(BindedString.class);
			knownComponents.add(BindedNumber.class);
			knownComponents.add(BindedBoolean.class);
			knownComponents.add(BindedBufferedImage.class);
		}
		return knownComponents;
	}
	
}
