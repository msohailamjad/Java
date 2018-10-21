package module2;

public class ThreeVector {

	// Private to avoid vector data being modified by other classes
		private double x = 0;
		private double y = 0;
		private double z = 0;

		// Constructs a ThreeVector with the given cartesian components
		public ThreeVector(double x, double y, double z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		// Vector magnitude
		double magnitude() {
			return Math.sqrt(scalarProduct(this,this));
		}

		// Unit vector parallel to this vector
		// Result has all components NaN if this is the zero vector.
		ThreeVector unitVector() {
			double mag = this.magnitude();
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
			// Calculate individual (i,j,k) elements of vector product
			double x = v1.y*v2.z - v1.z*v2.y;
			double y = v1.z*v2.x - v1.x*v2.z;
			double z = v1.x*v2.y - v1.y*v2.x;

			// Instantiate new ThreeVector object with vector product components
			return new ThreeVector(x,y,z);
		}

		// Vector addition v1 + v2
		static ThreeVector add(ThreeVector v1, ThreeVector v2) {
			// Add individual components for new vector
			double x = v1.x + v2.x;
			double y = v1.y + v2.y;
			double z = v1.z + v2.z;

			// Instantiate new ThreeVector object with vector product components
			return new ThreeVector(x,y,z);
		}

		// Angle between two vectors [radians]
		// Result is NaN if either input is the zero vector.
		static double angle(ThreeVector v1, ThreeVector v2) {
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
		double angle(ThreeVector v) {
			return angle(this, v);
		}
}
