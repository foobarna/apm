package impl;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.SortedSet;

import adt.Dictionary;

public class ConcreteDictionary<K, V> implements Dictionary<K, V> {
	private Entry<K, V>[] table;
	private int count = 0;
	private int threshold;
	private float loadFactor = 0.75f;
	private int modCount = 0;
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	private Comparator<K> comparator;

	public ConcreteDictionary(int initialCapacity, float loadFactor) {
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

	public ConcreteDictionary() {
		this(11, 0.75f);
	}

	public void addNewEntry(K key, V value) {
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
				// V old = e.value;
				e.value = value;
				// return old;
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
		// return null;
	}

	public V getValueByKey(K key) {
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

	public void changeExistingEntry(K key, V value) {
		Entry<K, V> tab[] = table;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<K, V> e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && e.key.equals(key)) {
				e.value = value;
			}
		}
	}

	public boolean containsKey(K key) {
		Entry<K, V> tab[] = table;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<K, V> e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && e.key.equals(key)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsValue(V value) {
		if (value == null) {
			throw new NullPointerException();
		}
		Entry<K, V> tab[] = table;
		for (int i = tab.length; i-- > 0;) {
			for (Entry<K, V> e = tab[i]; e != null; e = e.next) {
				if (e.value.equals(value)) {
					return true;
				}
			}
		}
		return false;
	}

	public void removeExistingEntry(K key) {
		Entry<K, V> tab[] = table;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry<K, V> e = tab[index], prev = null; e != null; prev = e, e = e.next) {
			if ((e.hash == hash) && e.key.equals(key)) {
				modCount++;
				if (prev != null) {
					prev.next = e.next;
				} else {
					tab[index] = e.next;
				}
				count--;
				e.value = null;
			}
		}
	}

	public int getSize() {
		return count;
	}

	public boolean isEmpty() {
		if (count == 0)
			return true;
		return false;
	}

	public SortedSet<K> returnSetOfKeys() {
		SortedSet<K> keys = new TreeSet<K>();
		Entry<K, V> tab[] = table;
		for (int i = tab.length; i-- > 0;) {
			for (Entry<K, V> e = tab[i]; e != null; e = e.next) {
				keys.add(e.getKey());
				}
			}
		return keys;
	}

	public void setComparator(Comparator<K> newKeyComparator) {
		if (comparator == null) {
			comparator = newKeyComparator;
		}
	}

	public Iterator<K> iterator() {
		Iterator<K> i = returnSetOfKeys().iterator();
		return i;
	}

	public int hashCode() {
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

	private static class Entry<K, V> {
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
			if (!(o instanceof Entry))
				return false;
			Entry<K, V> e = (Entry<K, V>) o;

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
