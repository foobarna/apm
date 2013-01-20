package main;

import interfaces.Map;
import java.util.Date;
import java.util.Scanner;

import domain.Delivery;
import domain.Hashtable;

public class AppB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Map map = new Hashtable();
		Date d;
		int qty;
		String prod;
		while (true) {
			System.out.print("\nChoose an option from below:\n"
					+ "1. Add delivery\n"
					+ "2. List delivery for a given product\n" + "3. Exit\n");
			int cmd = input.nextInt();

			switch (cmd) {
			case 1:
				System.out.print("Type the product's name: ");
				prod = input.next();
				System.out.print("Type the quantity: ");
				qty = input.nextInt();
				d = new Date();
				Delivery delivery = new Delivery(prod, qty, d);
				addDeliveryToMap(map, delivery);
				System.out.print("Delivery added @ " + d.toString() + "\n");
				break;
			case 2:
				System.out.print("Type the product's name: ");
				prod = input.next();
				printDeliveryForProduct(map, prod);
				break;
			case 3:
				System.out.print("Quiting...\n");
				System.exit(0);
			default:
				System.out.print("Wrong choice!!\n\n");
			}
		}
	}

	public static void addDeliveryToMap(Map m, Delivery d) {
		if (m.get(d.getProduct()) == null) {
			Delivery[] seq = new Delivery[10];
			seq[0] = d;
			m.add(d.getProduct(), seq);
		} else {
			Delivery[] seq = (Delivery[]) m.get(d.getProduct());
			int i = 0;
			for (; i < seq.length; i++) {
				if (!(seq[i] instanceof Delivery))
					break;
			}
			seq[i] = d;
			m.add(d.getProduct(), seq);
		}
	}

	public static void printDeliveryForProduct(Map m, String product) {
		Delivery[] seq = (Delivery[]) m.get(product);
		for (int i = 0; i < seq.length; i++) {
			if (!(seq[i] instanceof Delivery))
				break;
			System.out.print(seq[i] + "\n");
		}
	}
}
