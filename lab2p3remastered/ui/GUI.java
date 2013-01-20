package ui;

import java.io.IOException;
import java.util.ArrayList;

import controller.StorageController;
import repository.DeliveryRepository;
import domain.Delivery;
import adt.Dictionary;
import impl.ConcreteDictionary;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUI {

    private JFrame frame;
    private StorageController ctrl;
    private JTextField textField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    DeliveryRepository repo = new DeliveryRepository("allmydeliveries.bin");
		    StorageController ctrl = new StorageController(repo);
		    GUI window = new GUI(ctrl);
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public GUI(StorageController c) {
	ctrl = c;
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setResizable(false);
	frame.setTitle("Was born making this");
	frame.setBounds(200, 200, 500, 350);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Deliveries\n");
	lblNewLabel.setBounds(49, 24, 100, 15);
	frame.getContentPane().add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("Items");
	lblNewLabel_1.setBounds(198, 24, 100, 15);
	frame.getContentPane().add(lblNewLabel_1);
	
	JLabel lblNewLabel_2 = new JLabel("Deliveries fo'item");
	lblNewLabel_2.setBounds(347, 24, 100, 15);
	frame.getContentPane().add(lblNewLabel_2);
	
	
	JList list_1 = new JList();
	list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list_1.setBounds(192, 51, 114, 139);
	frame.getContentPane().add(list_1);
	
	JList list_2 = new JList();
	list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list_2.setBounds(345, 51, 114, 139);
	frame.getContentPane().add(list_2);
	
	JButton btnNewButton = new JButton("Add");
	btnNewButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
		    
		}
	});
	btnNewButton.setBounds(49, 277, 76, 27);
	frame.getContentPane().add(btnNewButton);
	
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(37, 51, 96, 139);
	frame.getContentPane().add(scrollPane);
	
	final JList list = new JList();
	scrollPane.setViewportView(list);
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	list.setModel(new AbstractListModel() {
		public ArrayList<Delivery> values = ctrl.getAll();
		public int getSize() {
			return values.size();
		}
		public Object getElementAt(int index) {
			return values.get(index);
		}
	});
	
	textField = new JTextField();
	textField.setBounds(49, 233, 122, 27);
	frame.getContentPane().add(textField);
	textField.setColumns(10);
	
	textField_1 = new JTextField();
	textField_1.setBounds(222, 233, 122, 27);
	frame.getContentPane().add(textField_1);
	textField_1.setColumns(10);
	
	JLabel lblNewLabel_3 = new JLabel("Name of product");
	lblNewLabel_3.setBounds(49, 214, 100, 15);
	frame.getContentPane().add(lblNewLabel_3);
	
	JLabel lblNewLabel_4 = new JLabel("Qty of product");
	lblNewLabel_4.setBounds(222, 214, 108, 15);
	frame.getContentPane().add(lblNewLabel_4);
    }
}
