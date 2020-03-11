package javautilities.meta;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;

public class Property {

	private Class<?> owner;
	private Class<?> type;
	private String name;
	
	private Method get;
	private Method set;
	private Field field;
	
	public Property(Class<?> owner, String name, Class<?> type) {
		this.owner = owner;
		this.name = name;
		this.type = type;
		this.get = MetaMethods.getGetter(owner, name);
		this.set = MetaMethods.getSetter(owner, name, type);
		try {
			this.field = owner.getField(name);
			if (field != null && !Modifier.isPublic(field.getModifiers())) {
				field = null;
			}
		} catch (NoSuchFieldException | SecurityException e) {
		}
	}
	
	public Object get(Object object) throws IllegalArgumentException, ReflectiveOperationException {
		if (get == null) {
			if (field == null) {
				if ("this".equals(name)) {
					return object;
				}
				throw new ReflectiveOperationException("There is no get method");
			}
			return field.get(object);
		}
		return get.invoke(object);
	}
	
	public Object set(Object object, Object value) throws IllegalArgumentException, ReflectiveOperationException {
		if (set == null) {
			if (field == null) {
				throw new ReflectiveOperationException("There is no set method");
			}
			field.set(object, value);
			return value;
		}
		return set.invoke(object, value);
	}
	
	public String getName() {
		return name;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public Class<?> getOwner() {
		return owner;
	}
	
	public boolean hasGet() {
		return get != null || field != null || !"this".equals(name);
	}
	
	public boolean hasSet() {
		return set != null || field != null;
	}
	
	public static Collection<Property> getProperties(Class<?> obj) {
		return MetaMethods.getProperties(obj);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
		Property other = (Property) obj;
		return name.equals(other.name) && owner.equals(other.owner) && type.equals(other.type);
	}
	
	@Override
	public String toString() {
		return "[(" + getClass().getName() + ") type: " + type.getName() + ", name: " + name + ", owner: " + owner.getName() + "" + ((get == null) ? "" : ", get") + ((set == null) ? "" : ", set") + "]";
	}
}
