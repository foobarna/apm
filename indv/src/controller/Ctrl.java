package controller;

import java.io.IOException;
import java.util.ArrayList;

import domain.*;
import repository.*;

public class Ctrl {
    private GradesRepo grades;
    private StudentsRepo students;
    
    public Ctrl(){
	grades = new GradesRepo();
	students = new StudentsRepo();
    }
    
    public Ctrl(GradesRepo gRepo, StudentsRepo sRepo) {
	grades = gRepo;
	students = sRepo;
    }
    
    public void addGrade(String disc, int grade, int id) throws RuntimeException, IOException  {
	Grade g = new Grade (disc,grade,id);
	grades.addGrade(g);
	save();
    }
    
    public void addGrade(Grade g) throws RuntimeException, IOException {
	if (!isId(g.getNo())) throw new RuntimeException("Cant assign grade, cuz student dosent exists!");
	grades.addGrade(g);
	save();
    }
    
    public void addStudent(String name, String pin, int id, int no) throws RuntimeException, IOException {
	Student s = new Student(name, pin, id, no);
	students.addStudent(s);
	save();
    }
    
    public void addStudent(Student s) throws IOException {
    	students.addStudent(s);
    	save();
    }
    
    public ArrayList<Student> getStudents() {
	return students.getRepo();
    }
    
    public ArrayList<Grade> getGrades() {
	return grades.getRepo()	;
    }
    
    public boolean isStudent(Student s) {
	ArrayList<Student> sts = students.getRepo();
	for(int i = 0; i < sts.size(); i++){
	    if (sts.get(i).equals(s)) return true;
	}
	return false;
    }
    
    public float getAverageForId(int id) {
    	return grades.getAverageForId(id);
    }
    
    public ArrayList<Grade> getGradesForId(int id) {
    	return grades.getGradesForId(id);
    }
    
    public ArrayList<Grade> getGradesForIdAndDisc(int id, String disc) {
    	return grades.getGradesForIdAndDisc(id, disc);
    }
    
    public ArrayList<Student> getStudentsByAverage() {
    	ArrayList<Student> tmp = new ArrayList<Student>();
    	tmp = students.getRepo();
    	Student aux;
    	for (int i=0; i < tmp.size() - 1; i++ ) {
    		for (int j = i+1; j < tmp.size(); j++) {
    			if (grades.getAverageForId(tmp.get(i).getId()) < grades.getAverageForId(tmp.get(j).getId())) {
    				aux = tmp.get(i);
    				tmp.set(i, tmp.get(j));
    				tmp.set(j,aux);
    			}
    		}
    	}
    	return tmp;
    }
    
    public boolean isId(int id) {
	ArrayList<Student> sts = students.getRepo();
	for(int i = 0; i < sts.size(); i++){
	    if (sts.get(i).getId() == id) return true;
	}
	return false;
    }
    
    public void save() throws IOException {
    	if (students.getFilename() == null) return;
    	students.save();
    	grades.save();
    }
}
