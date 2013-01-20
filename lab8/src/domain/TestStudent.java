package domain;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Vector;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestStudent {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStudent1() throws IOException, ClassNotFoundException, StudentException {
		Student s1 = new Student(1234,"bau", "bau", "07453", 999);
		s1.addGrade(new Grade("info",2));
		s1.addGrade(new Grade("mate", 5));
		OutputStream outputStream = new FileOutputStream("test12.bin");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

		objectOutputStream.writeObject(s1);


		objectOutputStream.flush();
		outputStream.flush();
		objectOutputStream.close();
		outputStream.close();

		InputStream inputStream = new FileInputStream("test12.bin");
		ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		Student s2 = (Student) objectInputStream.readObject();
		System.out.println(s2.getAverage());
		
		objectInputStream.close();
		inputStream.close();
		
		assertEquals(s1,s2);
	}

}
