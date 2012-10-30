package domain;

import interfaces.Map;
import adt.Dictionary;

public class Hashtable<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int count;
	private int threshold;
	private float loadFactor = 0.75f;
	private int modCount = 0;
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	public Hashtable(int initialCapacity, float loadFactor) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: "
					+ initialCapacity);
		if (loadFactor <= 0 || Float.isNaN(loadFactor))
			throw new IllegalArgumentException("Illegal Load: " + loadFactor);
		if (initialCapacity == 0)
			initialCapacity = 1;
		this.loadFactor = loadFactor;
		table = new Entry[initialCapacity];
		threshold = (int) (initialCapacity * loadFactor);
	}

	public Hashtable() {
		this(11, 0.75f);
	}

	public V add(K key, V value) {
		// Make sure the value is not null
		if (value == null) {
			throw new NullPointerException();
		}
		// Makes sure the key is not already in the hashtable.
		Entry<K, V> tab[] = table;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<K, V> e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && e.key.equals(key)) {
				V old = e.value;
				e.value = value;
				return old;
			}
		}
		modCount++;
		if (count >= threshold) {
			// Rehash the table if the threshold is exceeded
			rehash();
			tab = table;
			index = (hash & 0x7FFFFFFF) % tab.length;
		}
		// Creates the new entry.
		Entry<K, V> e = tab[index];
		tab[index] = new Entry<K, V>(hash, key, value, e);
		count++;
		return null;
	}

	public V get(K key) {
		Entry<K, V> tab[] = table;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<K, V> e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && e.key.equals(key)) {
				return e.value;
			}
		}
		return null;
	}

	public int size() {
		return count;
	}

	public  int hashCode() {
		int h = 0;
		if (count == 0 || loadFactor < 0)
			return h; // Returns zero
		loadFactor = -loadFactor; // Mark hashCode computation in progress
		Entry<K, V>[] tab = table;
		for (int i = 0; i < tab.length; i++)
			for (Entry<K, V> e = tab[i]; e != null; e = e.next)
				h += e.key.hashCode() ^ e.value.hashCode();
		loadFactor = -loadFactor; // Mark hashCode computation complete
		return h;
	}

	protected void rehash() {
		int oldCapacity = table.length;
		Entry<K, V>[] oldMap = table;
		// overflow-conscious code
		int newCapacity = (oldCapacity << 1) + 1;
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			if (oldCapacity == MAX_ARRAY_SIZE)
				// Keep running with MAX_ARRAY_SIZE buckets
				return;
			newCapacity = MAX_ARRAY_SIZE;
		}
		Entry<K, V>[] newMap = new Entry[newCapacity];
		modCount++;
		threshold = (int) (newCapacity * loadFactor);
		table = newMap;
		for (int i = oldCapacity; i-- > 0;) {
			for (Entry<K, V> old = oldMap[i]; old != null;) {
				Entry<K, V> e = old;
				old = old.next;
				int index = (e.hash & 0x7FFFFFFF) % newCapacity;
				e.next = newMap[index];
				newMap[index] = e;
			}
		}
	}

	private static class Entry<K, V> implements Map.Entry<K, V> {
		int hash;
		K key;
		V value;
		Entry<K, V> next;

		protected Entry(int hash, K key, V value, Entry<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		protected Object clone() {
			return new Entry<K, V>(hash, key, value, (next == null ? null
					: (Entry<K, V>) next.clone()));
		}

		// Map.Entry operations

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			if (value == null)
				throw new NullPointerException();

			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		public boolean equals(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			Map.Entry<K, V> e = (Map.Entry<K, V>) o;

			return (key == null ? e.getKey() == null : key.equals(e.getKey()))
					&& (value == null ? e.getValue() == null : value.equals(e
							.getValue()));
		}

		public int hashCode() {
			return hash ^ (value == null ? 0 : value.hashCode());
		}

		public String toString() {
			return key.toString() + "=" + value.toString();
		}
	}
}
