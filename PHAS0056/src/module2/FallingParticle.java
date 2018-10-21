package module2;

public class FallingParticle {


	// Initialise variables
	// Private to avoid direct variable modification other than with appropriate methods
	private double m = 0;	// Particle mass (kg)
	private double d = 0;	// Drag coefficient (kg/m)
	private double z = 0;	// Particle height (m)
	private double t = 0;	// Simulation time (s)
	private double v = 0;	// Particle velocity (m/s) - positive is upwards
	private double h = 0;	// Initial simulation height (m)

	// Acceleration due to gravity (m/s^2)
	public static final double g = 9.80665;

	// Construct FallingParticle with given mass and drag coefficient, at rest at t = 0.
	public FallingParticle(double mass, double drag) {
		this.m = mass;
		this.d = drag;
		this.v = 0;
		this.t = 0;
	}

	// Set initial particle height 
	void setH(double height) {
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

	// Calculate single time step
	private void doTimeStep(double deltaT) {

		// Change sign of drag deceleration if particle moving upward
		double drag = this.d;

		if ( getV() > 0 ) {
			drag = -this.d;
		}

		// Calculate particle acceleration
		double accel = ((this.v*this.v*drag)/this.m) - FallingParticle.g;

		// Update particle velocity from acceleration
		double deltaV = accel*deltaT;
		this.v += deltaV;

		// Update particle position from velocity
		double deltaZ = this.v*deltaT;
		this.z += deltaZ;

		// Update particle time
		this.t += deltaT;
		
	}

	// Drop method to calculate descent of particle
	void drop(double deltaT) {

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
