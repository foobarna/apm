package interfaces;

public interface Map {
	Object add(Object key, Object value);

	Object get(Object key);

	int size();

	interface Entry {
		Object getKey();

		Object getValue();

		Object setValue(Object value);
	}
}
