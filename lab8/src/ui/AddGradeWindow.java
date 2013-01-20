package ui;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.StudentCtrl;
import domain.Grade;
import domain.Student;

import repository.RepositoryException;
import repository.StudentRepository;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGradeWindow implements Observer{
	private JFrame	frame = new JFrame("Add Grade");
	private StudentCtrl ctrl;
	private JTextField textField;
	JList<Student> list = new JList<Student>();
	JScrollPane scrollPane = new JScrollPane(list);
	private JTextField textField_1;
	
	public AddGradeWindow(StudentCtrl c) {
		ctrl = c;
		initialize();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void initialize() {
		frame.setResizable(false);
		frame.setBounds(702, 100, 300, 350);
		frame.getContentPane().setLayout(null);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setBounds(50, 46, 197, 178);
		frame.getContentPane().add(scrollPane);
		
		
		JLabel lblNewLabel = new JLabel("Students");
		lblNewLabel.setBounds(12, 12, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Grade");
		lblNewLabel_1.setBounds(12, 242, 70, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(133, 236, 114, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Add grade");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String disc = textField_1.getText();
				int value = Integer.parseInt(textField.getText());
				try {
					ctrl.addGrade(list.getSelectedValue().getSid(),disc,value);
				} catch (RepositoryException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(12, 290, 117, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Disc");
		lblNewLabel_2.setBounds(12, 263, 70, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(133, 263, 114, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		update_list();
	}

	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public void update_list() {
		list.setModel(new AbstractListModel() {
			Vector<Student> values = ctrl.getAll();
			public int getSize() {
				return values.size();
			}
			public Object getElementAt(int index) {
				return values.get(index);
			}
		});
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		update_list();
	}

}
