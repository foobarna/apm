package mains;

import java.io.IOException;

import repository.GradesRepo;
import repository.StudentsRepo;
import controller.Ctrl;
import junit.framework.TestCase;

public class TestApp extends TestCase {
	public void testSmall() {

		try {
			StudentsRepo s = new StudentsRepo("studentFile.bin");
			GradesRepo g = new GradesRepo("gradeFile.bin");
			Ctrl ctrl = new Ctrl(g, s);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException r) {
			r.printStackTrace();
		}
		
		
	}
}
