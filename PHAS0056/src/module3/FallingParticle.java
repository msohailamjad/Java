package module3;

public class FallingParticle {

	// Initialise variables
	// Private to avoid direct variable modification other than with appropriate methods
	private double m = 0;	// Particle mass (kg)
	private double d = 0;	// Drag coefficient (kg/m)
	private double z = 0;	// Particle height (m)
	private double t = 0;	// Simulation time (s)
	private double v = 0;	// Particle velocity (m/s) - positive is upwards
	private double h = 0;	// Initial simulation height (m)

	// Gravity variable
	public static final double g = 9.80665;

	// Class constructors
	public FallingParticle() {}

	public FallingParticle(double mass, double drag) throws Exception {
		if (mass<=0 || drag<0) {
			String message = String.format(
					"Mass (%f) must be positive and drag coefficient (%f) must not be negative",
					mass, drag);
			throw new Exception(message);
		}
		this.m = mass;
		this.d = drag;
		this.v = 0;
		this.t = 0;
	}

	// Set initial particle height.
	// Height must not be negative, since this class only models particles falling as
	// far as the base of the container at z=0.
	void setH(double height) throws Exception {
		if (height<0) {
			String message = String.format("Height (%f) must not be negative",
					height);
			throw new Exception(message);
		}
		this.h = height;
	}

	// Return particle position
	double getZ() {
		return this.z;
	}

	// Set particle velocity
	void setV(double velocity) {
		this.v = velocity;
	}

	// Return particle velocity
	double getV() {
		return this.v;
	}

	// Return current time
	double getT() {
		return this.t;
	}

	// Calculate single time step.
	// Time step must be positive, but we assume this has already been checked in
	// the drop() method. If this method were not private this would be unsafe.
	private void doTimeStep(double deltaT) {

		// Initialise necessary variables
		double deltaV = 0;
		double deltaZ = 0;
		double accel = 0;
		double drag = this.d;

		// Change sign of drag deceleration if particle moving upward
		if ( getV() > 0 ) {
			drag = -this.d;
		}

		// Calculate particle acceleration
		accel = ((this.v*this.v*drag)/this.m) - FallingParticle.g;

		// Update particle velocity from acceleration
		deltaV = accel*deltaT;
		this.v += deltaV;

		// Update particle position from velocity
		deltaZ = this.v*deltaT;
		this.z += deltaZ;

		// Update particle time
		this.t += deltaT;

	}

	// Drop method to calculate descent of particle
	void drop(double deltaT) throws Exception {
		if (deltaT<=0) {
			String message = String.format("Time step (%f) must be positive",
					deltaT);
			throw new Exception(message);
		}

		// Set initial parameters at start of drop
		this.z = this.h ;
		this.t = 0;
		setV(0);

		// Loop over timesteps till particle reaches z = 0;
		while ( this.z > 0){
			doTimeStep(deltaT);
		}

		// Display final particle data
		System.out.println("Time step: "+deltaT+" s; Final position: "+getZ()+" m");
		System.out.println("Final time = "+getT()+" s; Final velocity = "+getV()+" m/s");
		System.out.println();

	}
}
