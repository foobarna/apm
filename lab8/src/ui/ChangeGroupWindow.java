package ui;

import java.awt.EventQueue;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import repository.RepositoryException;

import controller.StudentCtrl;
import domain.Student;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeGroupWindow implements Observer{
	private JFrame	frame = new JFrame("Change Group");
	private StudentCtrl ctrl;
	private JTextField textField;
	JList<Student> list = new JList<Student>();
	JScrollPane scrollPane = new JScrollPane(list);
	
	public ChangeGroupWindow(StudentCtrl c) {
		ctrl =c ;
		initialize();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void initialize() {
		frame.setResizable(false);
		frame.setBounds(1050, 100, 300, 350);
		frame.getContentPane().setLayout(null);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setBounds(50, 46, 197, 178);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Students");
		lblNewLabel.setBounds(12, 12, 70, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Group");
		lblNewLabel_1.setBounds(12, 236, 70, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(133, 236, 114, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int group = Integer.parseInt(textField.getText());
				try {
					ctrl.changeGroup(list.getSelectedValue().getSid(), group);
				} catch (RepositoryException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(12, 277, 117, 25);
		frame.getContentPane().add(btnNewButton);
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
