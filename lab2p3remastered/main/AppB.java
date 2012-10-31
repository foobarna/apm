package main;

import impl.*;

import java.util.Scanner;
import domain.*;
import java.util.Date;

import adt.Dictionary;

public class AppB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Dictionary<String, Delivery[]> map = new Hashtable<String, Delivery[]>();
		Date d;
		int qty;
		String prod;
		while (true) {
			System.out.print("\nChoose an option from below:\n"
							+ "1. Add delivery\n"
							+ "2. List delivery for a given product\n"
							+ "3. Exit\n");
			int cmd = input.nextInt();
			
			switch (cmd) {
			case 1:
				System.out.print("Type the product's name: "); prod = input.next();
				System.out.print("Type the quantity: "); qty = input.nextInt();
				d = new Date();
				Delivery delivery = new Delivery(prod, qty, d);
				addDeliveryToMap(map, delivery);
				System.out.print("Delivery added @ " + d.toString() + "\n");
				break;
			case 2:
				System.out.print("Type the product's name: "); prod = input.next();
				try {
					printDeliveryForProduct(map, prod);
				} catch (NullPointerException e) {
					System.out.print(e.getMessage());
				}
				break;
			case 3:
				System.out.print("Quiting...\n");
				System.exit(0);
			default:
				System.out.print("Wrong choice!!\n\n");
			}
		}
	}
	
	public static void addDeliveryToMap(Dictionary<String, Delivery[]> m, Delivery d ) {
		if(m.getValueByKey(d.getProduct()) == null) {
			Delivery[] seq = new Delivery[10];
			seq[0] = d;
			m.addNewEntry(d.getProduct(), seq);
		} else {
			Delivery[] seq = m.getValueByKey(d.getProduct()).clone();
			int i=0;
			for(; i < seq.length; i++){
				if (!(seq[i] instanceof Delivery)) break;
			}
			seq[i] = d;
			m.addNewEntry(d.getProduct(),seq);
		}
	}
	
	public static void printDeliveryForProduct(Dictionary<String, Delivery[]> m, String product) {
		Delivery[] seq = m.getValueByKey(product);
		if (seq == null) {
            throw new NullPointerException();
        }
		for(int i=0; i < seq.length; i++){
			if (!(seq[i] instanceof Delivery)) break;
			System.out.print(seq[i] + "\n");
		}
	}
}
