package repository;

import domain.Grade;
import domain.Student;
import junit.framework.TestCase;

public class TestRepository extends TestCase {
	
	public void testGradesRepo() {
		GradesRepo grepo = new GradesRepo();
		Grade g = new Grade("info",8,123);
		grepo.addGrade(g);
		assertEquals(grepo.getRepo().size(),1);
		
		g.setNo(1234);
		
		grepo.addGrade(g);
		assertTrue(grepo.getAverageForId(1234)==8.0);
	}
	
	public  void testStudentsRepo() {
		StudentsRepo srepo = new StudentsRepo();
		Student s1 = new Student("damn","1234567890098",123,6);
		Student s2 = new Student("john","1434567490098",133,7);
		Student s3 = new Student("smith","1234734890098",143,8);
		Student s4 = new Student("neo","1234567800098",111,9);
		Student s5 = new Student("matrix","1234527890098",153,1);
		
		srepo.addStudent(s1);
		srepo.addStudent(s2);
		
		assertTrue(srepo.getRepo().size() == 2);
		
		srepo.addStudent(s3);
		srepo.addStudent(s4);
		srepo.addStudent(s5);
		
		assertTrue(srepo.getRepo().size() == 5); 
		
	}

}
