package adt;

public abstract class Dictionary {
	/*
	 * Sole constructor
	 */
	public Dictionary(){
	}
	
	abstract public Object add(Object key, Object value);
	abstract public Object get(Object key);
	abstract public int size();
	abstract public int hashCode();
}
