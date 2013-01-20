/**
 * 
 */
package controller;

import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;

import domain.Grade;
import domain.Student;

import repository.RepositoryException;
import repository.StudentRepository;

/**
 * @author blink
 *
 */
public class StudentCtrl {
	StudentRepository repo = new StudentRepository();
	
	public StudentCtrl() {
		repo = new StudentRepository();
	}
	
	public StudentCtrl(StudentRepository rep) {
		repo = rep;
	}
	
	public StudentCtrl(String filename) throws ClassNotFoundException, EOFException, ClassCastException, IOException {
		repo = new StudentRepository(filename);
	}
	
	public boolean addStudent(int sid, String first_name, String last_name, String phone, int group) {
		Student s = new Student(sid, first_name, last_name, phone, group);
		repo.add(s);
		return true;
	}
	
	public boolean addGrade(int sid, String disc, int value) throws RepositoryException {
		Grade g = new Grade(disc,value);
		repo.addGrade(sid, g);
		return true;
	}
	
	public boolean changeGroup(int sid, int group) throws RepositoryException {
		return repo.changeGroup(sid, group);
	}
	
	public Vector<Student> getAll() {
		return repo.getAll();
	}
	
	public Vector<Student> getAllByGroup(int group) {
		Vector<Student> tmp = repo.getAll();
		Vector<Student> s = new Vector<Student>();
		for(int i=0; i < tmp.size(); i++){
			if(tmp.get(i).getGroup() == group) {
				s.add(tmp.get(i));
			}
		}
		return s;
	}
	
	public int getSize() {
		return repo.getSize();
	}
	
	public StudentRepository getObserver() {
		return repo;
	}
}
