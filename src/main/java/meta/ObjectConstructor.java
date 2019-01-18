package meta;

public interface ObjectConstructor<T> {

	T construct(Object... args);
	
}
