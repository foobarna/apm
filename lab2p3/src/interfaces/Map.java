package interfaces;

public interface Map {
	Object add(Object key, Object value);
	Object get(Object key);
	int size();
	int hashCode();
	interface Entry {
		Object getKey();
		Object getValue();
		Object setValue(Object value);
		boolean equals(Object o);
		int hashCode();
	}
}
