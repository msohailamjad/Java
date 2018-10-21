package module3;

public class ThreeVector {
	

	// Initialise variables
	// Private to avoid vector data being modified by other classes
	private double x = 0;
	private double y = 0;
	private double z = 0;

	// Constructors
	public ThreeVector() {}

	public ThreeVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// Vector magnitude
	double magnitude() {
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
	}

	// Unit vector parallel to this vector
	// Result has all components NaN if this is the zero vector.
	ThreeVector unitVector() throws Exception {
		double mag = this.magnitude();
        // Check for null vector to prevent returning NaN's
        if (mag==0) {
            throw new Exception("Unable to calculate unit vector for vector "+this+": modulus = 0.");
        }

		return new ThreeVector(this.x/mag, this.y/mag, this.z/mag);
	}

	// Nicely formatted output string for displaying vector data
	public String toString() {
		return "["+this.x+" "+this.y+" "+this.z+"]" ;
	}

	// Dot product v1.v2
	static double scalarProduct(ThreeVector v1, ThreeVector v2) {
		return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;
	}

	// Vector product v1 x v2
	static ThreeVector vectorProduct(ThreeVector v1, ThreeVector v2) {
		// Initialise double array for new vector data
		double[] crossProduct = new double[3];

		// Calculate individual (i,j,k) elements of vector product
		crossProduct[0] = v1.y*v2.z - v1.z*v2.y;
		crossProduct[1] = v1.z*v2.x - v1.x*v2.z;
		crossProduct[2] = v1.x*v2.y - v1.y*v2.x;

		// Instantiate new ThreeVector object with vector product components
		return new ThreeVector(crossProduct[0],crossProduct[1],crossProduct[2]);
	}

	// Vector addition v1 + v2
	static ThreeVector add(ThreeVector v1, ThreeVector v2) {

		// Initialise double array for new vector data
		double[] vectorAdd = new double[3];

		// Add individual components for new vector
		vectorAdd[0] = v1.x + v2.x;
		vectorAdd[1] = v1.y + v2.y;
		vectorAdd[2] = v1.z + v2.z;

		// Instantiate new ThreeVector object with new vector components
		return new ThreeVector(vectorAdd[0],vectorAdd[1],vectorAdd[2]);
	}

	// Angle between two vectors [radians]
	// Result is NaN if either input is the zero vector.
	static double angle(ThreeVector v1, ThreeVector v2) throws Exception {
        if (v1.magnitude() == 0) {
            throw new Exception("Unable to calculate angle with vector "+v1+": modulus of v1 = 0.");
        }
        if (v2.magnitude() == 0) {
            throw new Exception("Unable to calculate angle with vector "+v2+": modulus of v2 = 0.");
        }
		// arccos(v1.v2)/(|v1|*|v2|)
		double dotProd = ThreeVector.scalarProduct(v1, v2);
		return Math.acos(dotProd/(v1.magnitude()*v2.magnitude()));
	}

	// Non-static versions of methods

	// Dot product of this vector with v
	double scalarProduct(ThreeVector v) {
		return scalarProduct(this, v);
	}

	// Vector product of this vector with v
	ThreeVector vectorProduct(ThreeVector v) {
		return vectorProduct(this, v);
	}

	// Sum of this vector and v
	ThreeVector add(ThreeVector v) {
		return add(this, v);
	}

	// Angle between this vector and v [radians]
	// Result is NaN if either input is the zero vector.
	double angle(ThreeVector v) throws Exception {
		return angle(this, v);
	}

}
