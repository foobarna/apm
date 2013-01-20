package ui;

import adt.Dictionary;
import controller.Controller;
import controller.StorageController;
import repository.DeliveryRepository;
import domain.Delivery;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Gui {
    Controller ctrl;
    JFrame frame = new JFrame("Managing deliveries since i was born");
    JList<Delivery> list_1 = new JList<Delivery>();
    JList<String> list_2 = new JList<String>();
    JList<Delivery> list_3 = new JList<Delivery>();
    JScrollPane scrollPane = new JScrollPane(list_1);
    JScrollPane scrollPane_1 = new JScrollPane(list_3);
    JTextField textField = new JTextField();
    JTextField textField_1 = new JTextField();
    JButton btnNewButton = new JButton("Add");


    public Gui(Controller c) {
	ctrl = c;
	this.initializize();
    }

    public void show() {
	frame.setVisible(true);
    }

    private void initializize() {
	frame.getContentPane().setLayout(null);
	frame.setBounds(400, 200, 500, 350);
	frame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(0);
	    }
	});

	JLabel label1 = new JLabel("All deliveries");
	JLabel label2 = new JLabel("All products");
	JLabel label3 = new JLabel("Deliveries fo'product");

	label1.setBounds(40, 24, 100, 15);
	label2.setBounds(189, 24, 100, 15);
	label3.setBounds(330, 24, 150, 15);

	frame.getContentPane().add(label1);
	frame.getContentPane().add(label2);
	frame.getContentPane().add(label3);

	list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list_3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	updateList1();
	updateList2();
	MySelectListener listen = new MySelectListener();
	list_1.addListSelectionListener(listen);

	MyClickListener click = new MyClickListener();
	btnNewButton.addActionListener(click);

	JLabel lblNewLabel_3 = new JLabel("Name of product");
	lblNewLabel_3.setBounds(49, 214, 150, 15);
	frame.getContentPane().add(lblNewLabel_3);

	JLabel lblNewLabel_4 = new JLabel("Qty of product");
	lblNewLabel_4.setBounds(222, 214, 108, 15);
	frame.getContentPane().add(lblNewLabel_4);

	this.setBounds();
	this.addAllComponents();

    }

    private void addAllComponents() {
	frame.getContentPane().add(scrollPane);
	frame.getContentPane().add(scrollPane_1);
	frame.getContentPane().add(list_2);
//	frame.getContentPane().add(list_3);
	frame.getContentPane().add(textField);
	frame.getContentPane().add(textField_1);
	frame.getContentPane().add(btnNewButton);
    }

    private void setBounds() {
	list_1.setBounds(39, 51, 114, 139);
	list_2.setBounds(192, 51, 114, 139);
	list_3.setBounds(345, 51, 114, 139);
	scrollPane.setBounds(39, 51, 114, 139);
	scrollPane_1.setBounds(345, 51, 114, 139);
	textField.setBounds(49, 233, 122, 27);
	textField_1.setBounds(222, 233, 122, 27);
	btnNewButton.setBounds(49, 277, 76, 27);

    }

    public void updateList1() {

	DefaultListModel<Delivery> dlm = new DefaultListModel<Delivery>();
	for (Delivery del : ctrl.getAll()) {
	    dlm.addElement(del);
	}
	list_1.setModel(dlm);
    }

    public void updateList2() {

	DefaultListModel<String> dlm = new DefaultListModel<String>();
	for (String product : ctrl) {
	    dlm.addElement(product);
	}
	list_2.setModel(dlm);
    }

    public void updateList3(String p) {

	DefaultListModel<Delivery> dlm = new DefaultListModel<Delivery>();
	for (Delivery product : ctrl.get(p)) {
	    dlm.addElement(product);
	}
	list_3.setModel(dlm);
    }
    
    class MySelectListener implements ListSelectionListener {

	@Override
	public void valueChanged(ListSelectionEvent e) {
	    // TODO Auto-generated method stub
	    if (e.getSource() == list_1) {
		updateList3(list_1.getModel().getElementAt(list_1.getSelectedIndex()).getProduct());
	    }	    
	}
	
    }
    
    class MyClickListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    // TODO Auto-generated method stub
	    if (e.getSource() == btnNewButton) {
		list_1.removeListSelectionListener(list_1.getListSelectionListeners()[0]);
		list_1.clearSelection();
		MySelectListener listen = new MySelectListener();
		list_1.addListSelectionListener(listen);
		try {
		    ctrl.add(textField.getText(), Integer.parseInt(textField_1.getText()));
		    updateList1();
		    updateList2();

		} catch (NumberFormatException e1) {
//		     TODO Auto-generated catch block
		    e1.printStackTrace();
		} catch (IOException e1) {
//		     TODO Auto-generated catch block
		    e1.printStackTrace();
		}
	    }
	}
    }

}
