package test;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import domain.Delivery;

import junit.framework.TestCase;
import adt.Dictionary;
import factory.DataStructuresFactory;
import repository.DeliveryRepository;

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

	public void testExceptionOnAddNewEntry() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();
		theDictionary.addNewEntry("aa", "aaa");

		try {
			theDictionary.addNewEntry("aa", "bbb");
			fail("expected RuntimeException not thrown");
		} catch (RuntimeException ex) {
			assertEquals("already existing key", ex.getMessage());
		}
	}

	public void testExceptionOnChangeExistingEntry() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();
		theDictionary.addNewEntry("aa", "aaa");

		try {
			theDictionary.changeExistingEntry("bb", "bbb");
			fail("expected RuntimeException not thrown");
		} catch (RuntimeException ex) {
			assertEquals("key not found for change", ex.getMessage());
		}
	}

	public void testExceptionOnRemoveExistingEntry() {
		Dictionary<String, String> theDictionary = DataStructuresFactory.createDictionary();
		theDictionary.addNewEntry("aa", "aaa");

		try {
			theDictionary.removeExistingEntry("bb");
			fail("expected RuntimeException not thrown");
		} catch (RuntimeException ex) {
			assertEquals("key not found for remove", ex.getMessage());
		}
	}

	public void testExternalizationWithStrings() throws IOException, ClassNotFoundException {
		Dictionary<String, Delivery> initialDictionary = DataStructuresFactory.createDictionary();
		initialDictionary.addNewEntry("aaa", new Delivery());
		initialDictionary.addNewEntry("bbb", new Delivery());
		initialDictionary.addNewEntry("ccc", new Delivery());

		OutputStream outputStream = new FileOutputStream("testDictionaryExternalizationWithStrings.bin");
//		BufferedOutputStream out = new BufferedOutputStream(outputStream);
//		for(String s:initialDictionary){
//		    out.write(s.getBytes(), 0, s.length());
//		    out.flush();
//		    outputStream.flush();
//		}
//		
//		outputStream.close();
//		out.close();
		
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		objectOutputStream.writeObject(initialDictionary);


		objectOutputStream.flush();
		outputStream.flush();
		objectOutputStream.close();
		outputStream.close();

		InputStream inputStream = new FileInputStream("testDictionaryExternalizationWithStrings.bin");
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		@SuppressWarnings("unchecked")
		Dictionary<String, Delivery> finalDictionary = (Dictionary<String, Delivery>) objectInputStream.readObject();
		
		objectInputStream.close();
		inputStream.close();

		assertEquals(initialDictionary, finalDictionary);
	}
	
	public void testRepository() {
	    Date d = new Date();
	    DeliveryRepository r1 = new DeliveryRepository();
	    r1.add(new Delivery("name1",23,d));
	    r1.add(new Delivery("name1",23, d));
	    r1.add(new Delivery("name5",43, d));
	    for(Delivery s:r1.getAll()) {
		System.out.println(s.getProduct());
	    }
	    assertEquals(r1.getSize(),3);
	    assertEquals(r1.get("name1").size(), 2);
	    
	    DeliveryRepository r2 = new DeliveryRepository();
	    r2.add(new Delivery("name1",23, d));
	    r2.add(new Delivery("name1",23, d));
	    r2.add(new Delivery("name5",43, d));
	    
	    assertEquals(r1.get("name1"),r2.get("name1"));
	}

	public void test12() throws IOException, ClassNotFoundException {
		Vector<String> v1 = new Vector<String>();
		v1.add("asd");
		v1.add("portocala");
		OutputStream outputStream = new FileOutputStream("test12.bin");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		objectOutputStream.writeObject(v1);


		objectOutputStream.flush();
		outputStream.flush();
		objectOutputStream.close();
		outputStream.close();

		InputStream inputStream = new FileInputStream("test12.bin");
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		@SuppressWarnings("unchecked")
		Vector<String> v2= (Vector<String>) objectInputStream.readObject();
		
		objectInputStream.close();
		inputStream.close();
		System.out.println(v2.get(1));
		
		assertEquals(v1,v2);
	}
}
