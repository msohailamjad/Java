package module1;

public class AlgorithmControl {
	// AlgorithmControl class constructor
		public AlgorithmControl() { }

		// Count up in integer steps using for loop
		public void loop(int startVal, int endVal) {

			int i;
			System.out.println("Counting up from "+startVal+" to "+endVal+" in integer steps:");

			for ( i = startVal ; i <= endVal ; i++) {
				// Print out iterator at each step
				System.out.println("Iterator i = "+i);
			}
			System.out.println();
			
		}

		// Count down in integer steps using while loop
		public void decrement(double startVal, double minVal) {

			double i = startVal;
			System.out.println("Counting down from "+startVal+" to "+minVal+" in integer steps:");

			// Loop till iterator <= minVal
			while ( i >= minVal) {
				// Print out iterator at each step
				System.out.println("Iterator i = "+i);
				i-- ;			 
			}
			System.out.println();

		}

		// Count up in arbitrary steps using while loop
		public void increment(double startVal, double maxVal, double incVal) {

			double i = startVal;
			double limitVal = maxVal + (incVal)/2; // Set limiting value for loop to avoid rounding errors
			System.out.println("Counting up from "+startVal+" to "+maxVal+" in steps of "+incVal+":");

			// Loop till maxVal reached or exceeded
			while ( i < limitVal ) {
				// Print out iterator at each step
				System.out.format("Iterator i = %1.1f %n", i);
				//System.out.println("Iterator i = "+i);
				i += incVal;			 
			}
			System.out.println();

		}

		// Timer, displaying number of iterations every N steps 
		public int timer(long maxTime, int loopSteps) {

			// Get current system time in milliseconds
			long timeStart = System.currentTimeMillis();
			long timeNow = System.currentTimeMillis();
			long timeDiff = 0;
			int loopNum = 0 ;
			System.out.println("Running timer for "+maxTime+"ms, displaying number of completed loops every "+
					loopSteps+" steps");
			System.out.println();

			// Run while loop for maxTime milliseconds
			while ( timeDiff < maxTime) {
				timeNow = System.currentTimeMillis();
				timeDiff = timeNow - timeStart ;
				loopNum++ ;

				// Calculate whether to display loop number using remainder
				int iremainder = loopNum % loopSteps ;
				if (iremainder == 0) {
					//System.out.println("  Iteration = "+loopNum);
					System.out.println("  Iteration = "+loopNum+"; time since start = "+timeDiff+"ms");
					/** System.out.println("  Iteration = "+loopNum+"; current time = "+timeNow+
							"; time since start = "+timeDiff+"ms"); */
				} 
			}
			// Display summary data
			System.out.println();
			System.out.println("Ran timer for "+maxTime+"ms, displaying number of completed loops every "+
					loopSteps+" steps");
			System.out.println("Total duration of timer: "+timeDiff+"ms");
			System.out.println("Total number of iterations: "+loopNum);
			System.out.println();

			return loopNum;

		}

		public static void main(String[] args) {

			// Instantiate AlgorithmControl object alg
			AlgorithmControl alg = new AlgorithmControl();

			// Run loop method from 1 to 8
			alg.loop(1, 8);

			// Run decrement method from 10 to -5
			alg.decrement(10, -5);

			// Run increment method from 2.5 to 4.3 in steps of 0.1
			alg.increment(2.5, 4.3, 0.1);

			// Run timer method for 10s printing every 1,000 steps
			int loopNum1000 = alg.timer(10000, 1000) ;
			System.out.println(loopNum1000+" loops completed for timer displaying every 1,000 iterations.");

			// Run timer method for 10s printing every 50,000 steps
			int loopNum50000 = alg.timer(10000, 50000) ;
			System.out.println(loopNum50000+" loops completed for timer displaying every 50,000 iterations.");

			// Summarise results
			System.out.println();
			System.out.println(loopNum1000+" loops completed for timer displaying every 1,000 iterations.");
			System.out.println(loopNum50000+" loops completed for timer displaying every 50,000 iterations.");

			System.out.println();
			System.out.println("Number of loops completed for timer displaying every "+
					"50,000 iterations is much higher than for 1,000 iterations");
			System.out.println("This is because displaying text to the screen takes CPU resources,");
			System.out.println("delaying the timer each time a call to System.out.println is made.");

		}


}
