/**
 * 
 */
package main;

/**
 * @author blink
 *
 */
public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * 4. Determina toate numerele naturale mai mici decat un numar 
		 * natural nenul dat n si care sunt prime cu n.
		 */
		int n = Integer.parseInt(args[0]);
		for (int j = 2; j < n; j++ ){
			if (prime_between_them(j,n)){
				System.out.print(j + " ");
			}
		}
	}
	
	public static boolean prime_between_them(int a, int b) {
		if (a > b) {
			int aux = a;
			a = b;
			b = aux;
		}
		for (int i = 2; i <= a; i++) {
			if (a%i==0) 
				if (b%i==0) 
					return false;
		}
		return true;
	}

}
