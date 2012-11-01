package test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import adt.Dictionary;
import factory.DataStructuresFactory;

public class DictionaryTest extends TestCase {

	public void testCreateWithStrings() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();

		assertTrue(theDictionary.isEmpty());
		assertEquals(0, theDictionary.getSize());
	}

	public void testIsEmptyWithStrings() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();

		theDictionary.addNewEntry("aa", "aaa");
		theDictionary.addNewEntry("bb", "bbb");
		theDictionary.addNewEntry("cc", "ccc");
		assertFalse(theDictionary.isEmpty());
	}

	public void testGetSizeWithStrings() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();

		theDictionary.addNewEntry("aa", "aaa");
		assertEquals(1, theDictionary.getSize());

		theDictionary.addNewEntry("bb", "bbb");
		assertEquals(2, theDictionary.getSize());

		theDictionary.addNewEntry("cc", "ccc");
		assertEquals(3, theDictionary.getSize());
	}

	public void testContainsEntryWithStrings() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();

		assertFalse(theDictionary.containsKey("aa"));
		assertFalse(theDictionary.containsValue("aaa"));

		theDictionary.addNewEntry("aa", "aaa");
		assertTrue(theDictionary.containsKey("aa"));
		assertTrue(theDictionary.containsValue("aaa"));
		assertEquals("aaa", theDictionary.getValueByKey("aa"));

		theDictionary.removeExistingEntry("aa");
		assertFalse(theDictionary.containsKey("aa"));
		assertFalse(theDictionary.containsValue("aaa"));

		assertTrue(theDictionary.isEmpty());
		assertEquals(0, theDictionary.getSize());
	}

	public void testAddChangeAndRemoveWithStrings() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();

		theDictionary.addNewEntry("aa", "aaa");
		theDictionary.addNewEntry("bb", "bbb");
		theDictionary.addNewEntry("cc", "ccc");
		theDictionary.addNewEntry("dd", "ddd");
		assertFalse(theDictionary.isEmpty());
		assertEquals(4, theDictionary.getSize());

		assertTrue(theDictionary.containsKey("aa"));
		assertTrue(theDictionary.containsKey("bb"));
		assertTrue(theDictionary.containsKey("cc"));
		assertTrue(theDictionary.containsKey("dd"));

		assertTrue(theDictionary.containsValue("aaa"));
		assertTrue(theDictionary.containsValue("bbb"));
		assertTrue(theDictionary.containsValue("ccc"));
		assertTrue(theDictionary.containsValue("ddd"));

		assertEquals("aaa", theDictionary.getValueByKey("aa"));
		assertEquals("bbb", theDictionary.getValueByKey("bb"));
		assertEquals("ccc", theDictionary.getValueByKey("cc"));
		assertEquals("ddd", theDictionary.getValueByKey("dd"));

		theDictionary.changeExistingEntry("aa", "AAA");
		theDictionary.changeExistingEntry("bb", "BBB");
		theDictionary.removeExistingEntry("cc");
		theDictionary.removeExistingEntry("dd");

		assertTrue(theDictionary.containsKey("aa"));
		assertTrue(theDictionary.containsKey("bb"));
		assertFalse(theDictionary.containsKey("cc"));
		assertFalse(theDictionary.containsKey("dd"));

		assertTrue(theDictionary.containsValue("AAA"));
		assertTrue(theDictionary.containsValue("BBB"));
		assertFalse(theDictionary.containsValue("ccc"));
		assertFalse(theDictionary.containsValue("ddd"));

		assertEquals("AAA", theDictionary.getValueByKey("aa"));
		assertEquals("BBB", theDictionary.getValueByKey("bb"));
		assertNull("ccc", theDictionary.getValueByKey("cc"));
		assertNull("ddd", theDictionary.getValueByKey("dd"));
	}

	public void testIteratorWithStringsAndSorted() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();
	
		theDictionary.addNewEntry("dd", "ddd");
		theDictionary.addNewEntry("bb", "bbb");
		theDictionary.addNewEntry("aa", "aaa");
		theDictionary.addNewEntry("cc", "ccc");
	
		assertEquals(4, theDictionary.getSize());
		List<String> iteratedValues = new ArrayList<String>();
		for (String currentValue : theDictionary)
			iteratedValues.add(currentValue);
		assertEquals(4, theDictionary.getSize());
	
		assertEquals(4, iteratedValues.size());
		assertEquals("aa", iteratedValues.get(0));
		assertEquals("bb", iteratedValues.get(1));
		assertEquals("cc", iteratedValues.get(2));
		assertEquals("dd", iteratedValues.get(3));
	}

	public void testIteratorWithStringsAndUnsorted() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();

		theDictionary.addNewEntry("dd", "ddd");
		theDictionary.addNewEntry("bb", "bbb");
		theDictionary.addNewEntry("aa", "aaa");
		theDictionary.addNewEntry("cc", "ccc");

		assertEquals(4, theDictionary.getSize());
		List<String> iteratedValues = new ArrayList<String>();
		for (String currentValue : theDictionary)
			iteratedValues.add(currentValue);
		assertEquals(4, theDictionary.getSize());

		assertEquals(4, iteratedValues.size());
		assertTrue(iteratedValues.contains("aa"));
		assertTrue(iteratedValues.contains("bb"));
		assertTrue(iteratedValues.contains("cc"));
		assertTrue(iteratedValues.contains("dd"));
	}

}
