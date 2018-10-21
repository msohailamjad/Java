package module2;
	//Class representing real and imaginary components of a complex number	
public class Complex {

	// Private to avoid data being modified by other classes
		private double realPart = 0;
		private double imagPart = 0;

		// Static variables for standard Complex objects
		public static final Complex ONE  = new Complex(1,0);
		public static final Complex ZERO = new Complex(0,0);
		public static final Complex I    = new Complex(0,1);

		// Constructs Complex with given real and imaginary parts
		public Complex(double real, double imag) {
			this.realPart = real;
			this.imagPart = imag;
		}

		// Return real part of Complex number
		double real() {
			return this.realPart;
		}

		// Return imaginary part of Complex number
		double imag() {
			return this.imagPart;
		}

		// Return Complex modulus = sqrt(Re^2 + Im^2)
		double modulus() {
			return Math.sqrt(realPart*realPart + imagPart*imagPart);
		}

		// Return Argand plane angle = arcctan(Im/Re)
		double angle() {
			return Math.atan2(imagPart, realPart);
		}

		// Return Complex object for conjugate = Re + i(-Im)
		Complex conjugate() {
			return new Complex(this.real(), -this.imag());
		}

		// Return Complex object for normalised Complex number (modulus = 1)
		Complex normalised() {
			double mod = this.modulus();
			return new Complex(this.real()/mod, this.imag()/mod);
		}

		// Check equality of two Complex numbers
		boolean equals(Complex c1) {

			// Boolean variables for real and imaginary parts
			boolean realGood = (this.real() == c1.real());
			boolean imagGood = (this.imag() == c1.imag());

			// If both real and imaginary parts match, numbers are equal
			return realGood && imagGood;
		}

		// Construct pretty formatting for display
		public String toString() {
			if (this.equals(ZERO)) return "( 0 )";

			// Initialise String objects
			String realString = "( ";
			String imagString = ")";

			// Construct real string if real part non-zero 
			if ( this.real() != 0 ) {
				realString = realString+this.real()+" ";
			}

			// Construct imaginary string if imaginary part non-zero 
			if ( this.imag() > 0 ) {
				imagString = this.imag()+"i "+imagString;
				if ( this.real() != 0 ) {
					imagString = "+ "+imagString;
				}
			} else if ( this.imag() < 0 ) {
				// Get spacing right if imaginary part is negative
				if ( this.real() != 0 ) {
					imagString = "- "+(-this.imag())+"i "+imagString;
				} else {
					imagString = this.imag()+"i "+imagString;				
				}
			}

			// Construct complete output string with real and imaginary parts correctly formatted
			return realString+imagString ;

		}

		// Define Complex number from modulus and angle on Argand plane
		void setFromModulusAngle(double mag, double ang) {
			this.realPart = mag*Math.cos(ang);
			this.imagPart = mag*Math.sin(ang);
		}

		// Addition of two Complex numbers:
		// (a + ib) + (c + id) = (a + b) + i(c + d)
		static Complex add(Complex c1, Complex c2) {
			return new Complex(c1.real()+c2.real(), c1.imag()+c2.imag());
		}

		// Subtraction of two Complex numbers:
		// (a + ib) - (c + id) = (a - b) + i(c - d)
		static Complex subtract(Complex c1, Complex c2) {
			return new Complex(c1.real()-c2.real(), c1.imag()-c2.imag());
		}

		// Multiplication of two Complex numbers:
		// (a + ib) * (c + id) = (a*c - b*d) + i(b*c + a*d)
		static Complex multiply(Complex c1, Complex c2) {
			double realVar = (c1.real()*c2.real()) - (c1.imag()*c2.imag());
			double imagVar = (c1.imag()*c2.real()) + (c1.real()*c2.imag());
			return new Complex(realVar, imagVar);
		}

		// Division of two Complex numbers: c1/c2
		static Complex divide(Complex c1, Complex c2) {
			// Using c1/c2 = (c1 c2*) / (c2 c2*)
			// so the denominator is real.
			Complex c2conj = c2.conjugate();
			Complex numer = multiply(c1, c2conj);
			double  denom = multiply(c2,c2conj).realPart; // c2 c2* is real, so ignore imagPart
			double realVar = numer.realPart / denom;
			double imagVar = numer.imagPart / denom;
			return new Complex(realVar, imagVar);
		}

}
