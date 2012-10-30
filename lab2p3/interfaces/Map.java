package interfaces;

public interface Map<K, V>{
	V add(K key, V value);
	V get(K key);
	int size();
	int hashCode();
	interface Entry<K,V> {
		K getKey();
		V getValue();
		V setValue(V value);
		boolean equals(Object o);
		int hashCode();
	}
}
