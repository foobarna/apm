
public class DaClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*6. Determina primele 10 numere naturale strict mai mari ca 2, care au proprietatea ca toate 
   			numerele naturale strict mai mici decat ele si care sunt prime cu ele sunt numere prime.
		 */
		int n = Integer.parseInt(args[0]);
		boolean ok = true;
		for (int i = 3,j = 1;j < n; i++){
			ok = true;
			for (int k=2; k < i; k++){
				if (prime_between_them(k,i)){
					if (!is_prime(k)) { ok = false; break; }
				}
			}
			if (ok) {j++; System.out.println(i); }
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

	public static boolean is_prime(int givenNumber) {

		if (givenNumber <= 1)
			return false;
		for (int dd = 2; dd <= givenNumber / 2; dd++)
			if (givenNumber % dd == 0)
				return false;
		return true;
	}

}
