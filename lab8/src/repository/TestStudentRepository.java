package repository;

import java.io.IOException;

import domain.*;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class TestStudentRepository {

	@Test
	public void test1() throws IOException, ClassNotFoundException {
		StudentRepository r1 = new StudentRepository();
		r1.setFilename("test1StudentRepo.bin");
		r1.add(new Student(1234,"bau", "bau", "07453", 999));
		r1.add(new Student(3234,"bau-hau", "bu", "0734523", 199));
		r1.getAt(1).setFirst_name("alte-cele");
		r1.saveToFile();
		StudentRepository r2 = new StudentRepository("test1StudentRepo.bin");
		System.out.println(r2.getAt(1));
		assertEquals(r1,r2);
	}

}
