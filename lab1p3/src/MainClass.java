public class MainClass {
	public static void main(String[] args) {
		
		int[] givenArray = new int[args.length];
		for (int currentPosition = 0; currentPosition < args.length; currentPosition++) {
			int currentValue = Integer.parseInt(args[currentPosition]);
			givenArray[currentPosition] = currentValue;
		}
		
		int maxPrimeNumber = computeMaximumPrimeNumber(givenArray);
		int minPrimeNumber = computeMinimumPrimeNumber(givenArray);
		
		System.out.println("maxPrimeNumber = " + maxPrimeNumber);
		System.out.println("minPrimeNumber = " + minPrimeNumber);
	}

	public static int computeMaximumPrimeNumber(int[] givenNumbers) {

		int maximumPrimeNumber = 0;
		
		for (int currentIndex = 0; currentIndex < givenNumbers.length; currentIndex++) {
			int currentNumber = givenNumbers[currentIndex];
			if (isPrime(currentNumber)) {
				if (maximumPrimeNumber == 0)
					maximumPrimeNumber = currentNumber;
				else if (currentNumber > maximumPrimeNumber)
					maximumPrimeNumber = currentNumber;
			}
		}

//		for (int currentNumber : givenNumbers) {
//			if (isPrime(currentNumber)) {
//				if (maximumPrimeNumber == 0)
//					maximumPrimeNumber = currentNumber;
//				else if (currentNumber > maximumPrimeNumber)
//					maximumPrimeNumber = currentNumber;
//			}
//		}

		return maximumPrimeNumber;
	}

	public static int computeMinimumPrimeNumber(int[] givenNumbers) {

		int minimumPrimeNumber = 0;
		
		for (int currentIndex = 0; currentIndex < givenNumbers.length; currentIndex++) {
			int currentNumber = givenNumbers[currentIndex];
			if (isPrime(currentNumber)) {
				if (minimumPrimeNumber == 0)
					minimumPrimeNumber = currentNumber;
				else if (currentNumber < minimumPrimeNumber)
					minimumPrimeNumber = currentNumber;
			}
		}

//		for (int currentNumber : givenNumbers) {
//			if (isPrime(currentNumber)) {
//				if (minimumPrimeNumber == 0)
//					minimumPrimeNumber = currentNumber;
//				else if (currentNumber < minimumPrimeNumber)
//					minimumPrimeNumber = currentNumber;
//			}
//		}

		return minimumPrimeNumber;
	}

	public static boolean isPrime(int givenNumber) {

		if (givenNumber <= 1)
			return false;
		for (int dd = 2; dd <= givenNumber / 2; dd++)
			if (givenNumber % dd == 0)
				return false;
		return true;
	}
}
