package factory;

import adt.Dictionary;
import impl.ConcreteDictionary;

public class DataStructuresFactory {

	public static <KeyType, ValueType> Dictionary<KeyType, ValueType> createDictionary() {
		return new ConcreteDictionary<KeyType, ValueType>();
	}

}
