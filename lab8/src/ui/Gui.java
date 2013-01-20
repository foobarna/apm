package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.StudentCtrl;
import javax.swing.JButton;

import domain.Student;

import repository.StudentRepository;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;

public class Gui {
	private StudentCtrl ctrl;
	AddStudentWindow addStudentFrame ;
	AddGradeWindow addGradeFrame ;
	ChangeGroupWindow changeGroupFrame;
	ViewGroupWindow viewGroupFrame;
	
	private JFrame	frame = new JFrame("main frame");
	/**
	 * Create the application.
	 */
	
	public Gui(StudentCtrl ctrl) {
		this.ctrl = ctrl;
		addStudentFrame = new AddStudentWindow(ctrl);
		addGradeFrame = new AddGradeWindow(ctrl);
		changeGroupFrame = new ChangeGroupWindow(ctrl);
		viewGroupFrame = new ViewGroupWindow(ctrl);
		ctrl.getObserver().addObserver(addStudentFrame);
		ctrl.getObserver().addObserver(addGradeFrame);
		ctrl.getObserver().addObserver(changeGroupFrame);
		ctrl.getObserver().addObserver(viewGroupFrame);
		initialize();
	}
	
	public void show() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {	
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Add Student");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addStudentFrame.show();
			}
		});
		btnNewButton.setBounds(55, 45, 188, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Add grade");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGradeFrame.show();
			}
		});
		btnNewButton_1.setBounds(55, 115, 188, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Move Student");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeGroupFrame.show();
			}
		});
		btnNewButton_2.setBounds(55, 185, 188, 25);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View group");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewGroupFrame.show();
				if (ctrl.getSize() > 1) System.out.println(ctrl.getAll().get(0));
			}
		});
		btnNewButton_3.setBounds(55, 255, 188, 25);
		frame.getContentPane().add(btnNewButton_3);
	}	
}
