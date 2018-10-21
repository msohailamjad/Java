package module3;

public class TestExceptions {


	// Test that exceptions are thrown when needed in the modified classes.
	public static void main(String[] args) {
		System.out.println("------------------------------------------------------");
		testComplex();
		System.out.println("------------------------------------------------------");
		testThreeVector();
		System.out.println("------------------------------------------------------");
		testFallingParticle();
		System.out.println("------------------------------------------------------");
	}

	// Test that exceptions are thrown when needed in the Complex class.
	private static void testComplex() {
		System.out.println("Testing Exceptions for Complex class...");
		System.out.println();

		boolean ok = true;
		Complex c1 = new Complex(-1,2);
		Complex c2 = new Complex(2,-1);

		System.out.println("The following operations should not throw any exceptions:");
		try {
			System.out.println("  c1: "+c1);
			System.out.println("  c2: "+c2);
			System.out.println("  c1 modulus: "+c1.modulus());
			System.out.println("  c1 angle: "+c1.angle()+" rads");
			System.out.println("  c1 conjugate: "+c1.conjugate());
			System.out.println("  c1 normalised: "+c1.normalised());
			System.out.println("  c1 normalised modulus: "+c1.normalised().modulus());
			System.out.println("  c1*c2: "+Complex.multiply(c1, c2));
			System.out.println("  c1/c2: "+Complex.divide(c1, c2));
			System.out.println("  c1*I: "+Complex.multiply(c1, Complex.I));
			System.out.println("  ZERO/c1: "+Complex.divide(Complex.ZERO, c1));
		}
		catch (Exception e) {
			System.out.println(  "Unexpected exception: "+e.getMessage());
			ok = false;
		}

		System.out.println("The following operations SHOULD throw exceptions:");
		try {
			System.out.println("c1/ZERO: "+Complex.divide(c1, Complex.ZERO));
			System.out.println("c1/ZERO failed to throw an exception.");
			ok = false;
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}
		try {
			System.out.println("Complex ZERO normalised: "+Complex.ZERO.normalised());
			System.out.println("ZERO.normalised() failed to throw an exception.");
			ok = false;
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}

		System.out.println();
		String result = ok ? "OK: all tests passed." : "FAILED at least one test";
		System.out.println(result);
	}

	// Test that exceptions are thrown when needed in the ThreeVector class.
	private static void testThreeVector() {
		System.out.println("Testing Exceptions for ThreeVector class...");
		System.out.println();

		boolean ok = true;
		ThreeVector v0 = new ThreeVector(0,0,0);
		ThreeVector v1 = new ThreeVector(3,5,2);
		ThreeVector v2 = new ThreeVector(2,4,1);

		System.out.println("The following operations should not throw any exceptions:");
		try {
			System.out.println("  ThreeVector v0: "+v0);
			System.out.println("  ThreeVector v1: "+v1);
			System.out.println("  ThreeVector v2: "+v2);
			System.out.println("  ThreeVector v1 unit vector: "+v1.unitVector());
			System.out.println("  ThreeVector v2 unit vector: "+v2.unitVector());
			System.out.println("  Angle between v1 & v2: "+ThreeVector.angle(v1, v2)+" rads");
		}
		catch (Exception e) {
			System.out.println(  "Unexpected exception: "+e.getMessage());
			ok = false;
		}

		System.out.println("The following operations SHOULD throw exceptions:");
		try {
			System.out.println("ThreeVector v0 unit vector: "+v0.unitVector());
			System.out.println("v0.unitVector() failed to throw an exception.");
			ok = false;
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}
		try {
			System.out.println("Angle between v1 & v0: "+ThreeVector.angle(v1, v0)+" rads");
			System.out.println("angle(v1, v0) failed to throw an exception.");
			ok = false;
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}
		try {
			System.out.println("Angle between v1 & v0: "+v1.angle(v0)+" rads");
			System.out.println("v1.angle(v0) failed to throw an exception.");
			ok = false;
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}
		try {
			System.out.println("Angle between v0 & v1: "+v0.angle(v1)+" rads");
			System.out.println("v0.angle(v1) failed to throw an exception.");
			ok = false;
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}

		System.out.println();
		String result = ok ? "OK: all tests passed." : "FAILED at least one test";
		System.out.println(result);
	}

	// Test that exceptions are thrown when needed in the FallingParticls class.
	private static void testFallingParticle() {
		System.out.println("Testing Exceptions for FallingParticle class...");
		System.out.println();

		boolean ok = true;

		System.out.println("The following operations should not throw any exceptions:");
		try {
			// Zero drag coefficient, and zero initial height are both OK.
			FallingParticle dropPart = new FallingParticle(1.3, 0.0);
			dropPart.setH(0);
			dropPart.drop(0.1);
		}
		catch (Exception e) {
			System.out.println(  "Unexpected exception: "+e.getMessage());
			ok = false;
		}

		System.out.println("The following operations SHOULD throw exceptions:");
		try {
			@SuppressWarnings("unused")
			FallingParticle dropPart = new FallingParticle(0, 2.1);
			System.out.println("Creating particle with zero mass failed to throw an exception.");
			ok = false;
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}

		try {
			@SuppressWarnings("unused")
			FallingParticle dropPart = new FallingParticle(2.1, -1);
			System.out.println("Creating particle with negative drag coefficient failed to throw an exception.");
			ok = false;
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}

		try {
			FallingParticle dropPart = new FallingParticle(5.3, 2.1);
			dropPart.setH(-1);
			System.out.println("Setting negative height failed to throw an exception.");
			ok = false;
		}
		catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}
		try {
			FallingParticle dropPart = new FallingParticle(5.3, 2.1);
			dropPart.drop(-1);
			System.out.println("Negative time-step failed to throw an exception.");
		} catch (Exception e) {
			System.out.println("  "+e.getMessage());
		}

		System.out.println();
		String result = ok ? "OK: all tests passed." : "FAILED at least one test";
		System.out.println(result);
	}
	
}
