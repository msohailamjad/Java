
package module1;

public class DataTypes {

	public static void main(String[] args) {
		
		// Assign the double variable doubVar the value 5
		double doubVar = 5.0 ;
		// Print the value of doubVar to the screen
		// System.out.println(doubVar);
		// Prefix the printing of the value with some descriptive information:
		System.out.println("The value of doubVar is "+doubVar);
		// Note the "" for any words
		// The + means append to the output going to the screen

		// Assign the float variable floatVar the value 5
		float floatVar = 5.0f ;
		// Print value of floatVar to the screen
		System.out.println("The value of floatVar is "+floatVar);

		// Assign the int variable intVar the value 5
		int intVar = 5 ;
		// Print value of intVar to the screen
		System.out.println("The value of intVar is "+intVar);

		// Assign the long variable longVar the value 5
		long longVar = 5 ;
		// Print value of intVar to the screen
		System.out.println("The value of longVar is "+longVar);

		// Assign the byte variable byteVar the value 5
		byte byteVar = 5 ;
		// Print value of byteVar to the screen
		System.out.println("The value of byteVar is "+byteVar);
		System.out.println();

		// Multiply each value by itself and print the result
		System.out.println("The value of doubVar squared is "+doubVar*doubVar);
		System.out.println("The value of floatVar squared is "+floatVar*floatVar);
		System.out.println("The value of intVar squared is "+intVar*intVar);
		System.out.println("The value of longVar squared is "+longVar*longVar);
		System.out.println("The value of byteVar squared is "+byteVar*byteVar);
		System.out.println();


		// Investigate mixing types
		// Add 10 to character variable charVar
		char charVar = 'c'+10;
		System.out.println("Adding 10 to letter c gives: "+charVar);
		System.out.println("This is 10 further on from c in ASCII alphabet");
		System.out.println();

		// Add 5.5 to character a
		// char charVar2 = 'a'+5.5;
		// System.out.println("Adding 5.5 to letter a gives: "+charVar2);
		System.out.println("Adding 5.5 to character variable gives an error as can't convert from double to char");
		System.out.println();

		// Remove 10 from character a
		char charVar3 = 'a'-10;
		System.out.println("Subtracting 10 from letter a gives: "+charVar3);
		System.out.println("This is 10 back from a in ASCII alphabet");
		System.out.println();

		// Investigating uninitialised variable j 
		int j = 1;
		// int i; j = i+1;
		System.out.println("The value of j is "+j);
		System.out.println("Trying to execute [ int i; j = i+1; ] gives an error:");
		System.out.println("local variable i not initialised i.e. not assigned value");
		System.out.println("Cannot perform an operation on a Null variable or object.");
		System.out.println();

		// Investigating double to int cast
		double castVar = 4.99 ;
		// Cast double variable castVar to an int
		System.out.println("The value of castVar is "+castVar);
		System.out.println("The value of castVar cast to an int is "+(int)castVar);
		System.out.println("Casting a double to an int only retains the integer part, so "+
				castVar+" becomes "+(int)castVar);
		
	}

}