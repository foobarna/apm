package domain;

import interfaces.Map;
import adt.Dictionary;

public class Hashtable extends Dictionary implements Map {
	private Entry[] table;
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

	@Override
	public Object add(Object key, Object value) {
		// Make sure the value is not null
		if (value == null) {
			throw new NullPointerException();
		}
		// Makes sure the key is not already in the hashtable.
		Entry tab[] = table;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && e.key.equals(key)) {
				Object old = e.value;
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
		Entry e = tab[index];
		tab[index] = new Entry(hash, key, value, e);
		count++;
		return null;
	}

	@Override
	public Object get(Object key) {
		Entry tab[] = table;
		int hash = key.hashCode();
		int index = (hash & 0x7FFFFFFF) % tab.length;
		for (Entry e = tab[index]; e != null; e = e.next) {
			if ((e.hash == hash) && e.key.equals(key)) {
				return e.value;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public int hashCode() {
		int h = 0;
		if (count == 0 || loadFactor < 0)
			return h; // Returns zero
		loadFactor = -loadFactor; // Mark hashCode computation in progress
		Entry[] tab = table;
		for (int i = 0; i < tab.length; i++)
			for (Entry e = tab[i]; e != null; e = e.next)
				h += e.key.hashCode() ^ e.value.hashCode();
		loadFactor = -loadFactor; // Mark hashCode computation complete
		return h;
	}

	protected void rehash() {
		int oldCapacity = table.length;
		Entry[] oldMap = table;
		// overflow-conscious code
		int newCapacity = (oldCapacity << 1) + 1;
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			if (oldCapacity == MAX_ARRAY_SIZE)
				// Keep running with MAX_ARRAY_SIZE buckets
				return;
			newCapacity = MAX_ARRAY_SIZE;
		}
		Entry[] newMap = new Entry[newCapacity];
		modCount++;
		threshold = (int) (newCapacity * loadFactor);
		table = newMap;
		for (int i = oldCapacity; i-- > 0;) {
			for (Entry old = oldMap[i]; old != null;) {
				Entry e = old;
				old = old.next;
				int index = (e.hash & 0x7FFFFFFF) % newCapacity;
				e.next = newMap[index];
				newMap[index] = e;
			}
		}
	}

	private static class Entry implements Map.Entry {
		int hash;
		Object key;
		Object value;
		Entry next;

		protected Entry(int hash, Object key, Object value, Entry next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		@Override
		protected Object clone() {
			return new Entry(hash, key, value, (next == null ? null
					: (Entry) next.clone()));
		}

		// Map.Entry operations

		@Override
		public Object getKey() {
			return key;
		}

		@Override
		public Object getValue() {
			return value;
		}

		@Override
		public Object setValue(Object value) {
			if (value == null)
				throw new NullPointerException();

			Object oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			Map.Entry e = (Map.Entry) o;

			return (key == null ? e.getKey() == null : key.equals(e.getKey()))
					&& (value == null ? e.getValue() == null : value.equals(e
							.getValue()));
		}

		@Override
		public int hashCode() {
			return hash ^ (value == null ? 0 : value.hashCode());
		}

		@Override
		public String toString() {
			return key.toString() + "=" + value.toString();
		}
	}
}
