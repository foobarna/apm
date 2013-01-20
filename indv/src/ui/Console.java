package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Ctrl;
import domain.Grade;
import domain.Student;

public class Console {
	Ctrl	c;
	Scanner	input	= new Scanner(System.in);

	public Console() {
		c = new Ctrl();
	}

	public Console(Ctrl c) {
		this.c = c;
	}

	public void run() throws IOException {
		int opt;
		while (true) {
			showMenu();
			opt = input.nextInt();
			switch (opt) {
				case 1:
					addStudent();
					break;
				case 2:
					addGrade();
					break;
				case 3:
					showStudents();
					break;
				case 4:
					showAllGradesForId();
					break;
				case 5:
					showStudentsByAverage();
					break;
				case 6:
					showDiscGradesForId();
					break;
				case 0:
					System.exit(0);
				default:
					System.out.println("No such option.");
			}

		}

	}

	private void showMenu() {
		System.out.print("1.Add student \n" 
				+ "2. Add grade \n"
				+ "3. Show students \n" 
				+ "4. Show grades for a student \n"
				+ "5. Show students by average\n"
				+ "6. Show grades for a student at a disc\n"
				+ "0. exit \n"
				+ "\n>");
	}

	public void addStudent() throws IOException {
		try {
			System.out.print("Name: ");
			String name = input.next();

			System.out.print("PIN: ");
			String pin = input.next();

			System.out.print("id: ");
			int id = input.nextInt();
			System.out.print("no: ");
			int no = input.nextInt();

			c.addStudent(name, pin, id, no);
		} catch (RuntimeException r) {
			System.out.println(r.getMessage());
		}
	}
	
	public void addGrade() throws IOException{
		try {
			System.out.print("Discipline: ");
			String disc = input.next();
			
			System.out.print("Grade: ");
			int grade = input.nextInt();
			
			System.out.print("id: ");
			int id = input.nextInt();
			
			c.addGrade(disc, grade, id);
		} catch (RuntimeException r) {
			System.out.println(r.getMessage());
		}
	}

	public void showStudents() {
		ArrayList<Student> l = c.getStudents();
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}
	}
	
	public void showAllGradesForId() {
		System.out.print("ID:");
		int id = input.nextInt();
		
		ArrayList<Grade> l = c.getGradesForId(id);
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}
		
	}
	
	public void showDiscGradesForId() {
		System.out.print("ID: ");
		int id = input.nextInt();
		
		System.out.print("Discipline: ");
		String disc = input.next();
		
		ArrayList<Grade> l = c.getGradesForIdAndDisc(id, disc);
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i));
		}
	}
	
	public void showStudentsByAverage() {
		ArrayList<Student> l = c.getStudentsByAverage();
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i) + " average: " + c.getAverageForId(l.get(i).getId()));
		}
	}
	
	
}
