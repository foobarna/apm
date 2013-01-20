package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.StudentCtrl;

import repository.StudentRepository;

public class AddStudentWindow implements Observer{
	private JFrame	frame = new JFrame("Add Student");
	private StudentCtrl ctrl;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public AddStudentWindow(StudentCtrl ctrl) {
		this.ctrl = ctrl;
		initialize();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void initialize() {
		frame.setResizable(false);
		frame.setBounds(400, 100, 300, 350);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(161, 12, 114, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(161, 54, 114, 30);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(161, 96, 114, 30);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(161, 138, 114, 30);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(161, 180, 114, 30);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Student\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sid = Integer.parseInt(textField.getText());
				String fname = textField_1.getText();
				String lname = textField_2.getText();
				String phone = textField_3.getText();
				int group = Integer.parseInt(textField_4.getText());
				ctrl.addStudent(sid, fname, lname, phone, group);
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		
		btnNewButton.setBounds(12, 228, 134, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("SID");
		lblNewLabel.setBounds(12, 14, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First name\n");
		lblNewLabel_1.setBounds(12, 56, 114, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(12, 98, 114, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone No.");
		lblNewLabel_3.setBounds(12, 140, 114, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Group");
		lblNewLabel_4.setBounds(12, 182, 70, 15);
		frame.getContentPane().add(lblNewLabel_4);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
 
	}
}

