package controller;

import java.io.IOException;

import junit.framework.TestCase;
import domain.Grade;
import domain.Student;

public class TestController extends TestCase{
	
	public void testCtrl() throws IOException {
		Ctrl c = new Ctrl();
		
		Student s1 = new Student("damn","1234567890098",123,6);
		Student s2 = new Student("john","1434567490098",133,7);
		Student s3 = new Student("smith","1234734890098",143,8);
		Student s4 = new Student("neo","1234567800098",111,9);
		Student s5 = new Student("matrix","1234527890098",153,1);
		
		Grade g1 = new Grade("info",8,123);
		Grade g2 = new Grade("mate",4,123);
		Grade g3 = new Grade("info",5,143);
		Grade g4 = new Grade("info",8,153);
		Grade g5 = new Grade("info",8,113);
		
		c.addStudent(s1);
		c.addStudent(s2);
		c.addStudent(s3);
		c.addStudent(s4);
		
		assertTrue(c.getStudents().size() == 4);
		
		c.addGrade(g1);
		c.addGrade(g2);
		
		assertTrue(c.getGrades().size() == 2);
	}

}
