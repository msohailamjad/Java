package module3;

import java.util.Random;

public class Alphabet {
	private static Random rand = new Random();

	// generate random alphanumeric characters
	static char randomCharacter() {
		int irandom = rand.nextInt(128); // value in range 0-127 inclusive
		return (char) irandom;
	}

	public static void main(String[] args) {
		int numChars = 400;
		int sumInts = 0;
		int numExceptions = 0;
		StringBuilder s = new StringBuilder();

		// Generate numChar characters and attempt conversion
		for ( int i = 0 ; i < numChars ; i++){
			char randChar = randomCharacter();
			if (Character.isLetterOrDigit(randChar)) {
				s.append(randChar);
				// Try to convert character to integer
				try {
					// Integer.parseInt needs String
					int intChar = Integer.parseInt(Character.toString(randChar));
					// If conversion successful, add to sum of all integers
					sumInts += intChar;
				}
				catch (Exception e) {
					numExceptions++;
				}
			}
		}

		// Display all results
		System.out.format("All %d characters:%n",s.length());
		System.out.println(s);
		System.out.println();
		System.out.println("Sum of all integers found: "+sumInts);
		System.out.println("Total number of exceptions caught: "+numExceptions);
	}
}