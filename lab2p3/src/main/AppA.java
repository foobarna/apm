package main;

import interfaces.*;
import domain.*;
import java.util.Scanner;

public class AppA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Map map = new Hashtable();
		while (true) {
			System.out
					.print("Choose an option from below:\n"
							+ "1. Check a word's plural\n"
							+ "2. Enter a pair of (word,word's plural)\n"
							+ "3. Exit\n");
			int cmd = input.nextInt();
			switch (cmd) {
			case 1:
				System.out.print("Give the word: ");
				String word = input.next();
				System.out.print("The plural is: " + map.get(word) + "\n");
				break;
			case 2:
				System.out.print("Type the singular: ");
				String singular = input.next();
				System.out.print("Type the plural: ");
				String plural = input.next();
				map.add(singular, plural);
				System.out.print("Words added!\n");
				break;
			case 3:
				System.out.print("Quiting...\n");
				System.exit(0);
			default:
				System.out.print("Wrong choice!!\n\n");
			}
		}
	}

}
