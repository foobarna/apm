package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.StudentCtrl;
import domain.Student;
import repository.StudentRepository;
import ui.Gui;

public class TheMain {

	public static void main(String[] args) {
		
		StudentRepository rep = new StudentRepository();
		rep.add(new Student(123,"first","last","052442",223));
		StudentCtrl ctrl = new StudentCtrl(rep);
		Gui clicky = new Gui(ctrl);
		clicky.show();
		
	}

}
