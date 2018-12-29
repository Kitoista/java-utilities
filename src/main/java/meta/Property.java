package meta;

import java.lang.reflect.Method;
import java.util.Collection;

public class Property {

	private Class<?> owner;
	private Class<?> type;
	private String name;

	private Method get;
	private Method set;
	
	public Property(Class<?> owner, String name, Class<?> type) {
		this.owner = owner;
		this.name = name;
		this.type = type;
		this.get = MetaMethods.getGetter(owner, name);
		this.set = MetaMethods.getSetter(owner, name, type);
	}
	
	public Object get(Object object) throws IllegalArgumentException, ReflectiveOperationException {
		if (get == null) {
			throw new ReflectiveOperationException("There is no get method");
		}
		return get.invoke(object);
	}
	
	public Object set(Object object, Object value) throws IllegalArgumentException, ReflectiveOperationException {
		if (set == null) {
			throw new ReflectiveOperationException("There is no set method");
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
		return get != null;
	}
	
	public boolean hasSet() {
		return set != null;
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
