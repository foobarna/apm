package factory;

import impl.ConcreteDictionary;
import adt.Dictionary;

public class DataStructuresFactory {

	public static <KeyType, ValueType> Dictionary<KeyType, ValueType> createDictionary() {
		return new ConcreteDictionary<KeyType, ValueType>();
	}

}
