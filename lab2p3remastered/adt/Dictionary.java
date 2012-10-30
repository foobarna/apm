package adt;

import java.util.Comparator;

public interface Dictionary<K, V>{
	void addNewEntry(K key, V value);
	V getValueByKey(K key);
	void changeExistingEntry(K key, V value);
	void removeExistingEntry(K key);
	void setComparator(Comparator<K> key);
	boolean containsKey(K key);
	boolean containsValue(V value);
	boolean isEmpty();
	int getSize();
}
