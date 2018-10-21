package module4;

import java.io.*; // For BufferedReader, InputStreamReader, InputStream, FileReader 
import java.net.URL; // For URL
import java.util.Scanner; // For Scanner

public class NumericalReader {

	// Private variables to keep track of values read in
	private double maxValue, minValue, sumOfValues, meanValue;
	private int nValues;
	private String fileName;

	// Return keyboard input as string using BufferedReader
	private static String getOutputDirectory() {

		String saveDir = null;
		System.out.println("Please enter directory to store data:");
		try {
			// Input directory to store data
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			saveDir = br.readLine(); // Read single line from BufferedReader
		}
		// Catch InputStream Exception and select default directory
		catch (IOException e) {
			System.out.println("Error reading directory location: " + e);
		}

		// If saveDir is empty or an error has occurred, use
		// user home directory.
		if (saveDir == null || saveDir.isEmpty()) {
			saveDir = System.getProperty("user.home");
			System.out.println("Using default directory location: " + saveDir);
		}

		return saveDir;

	}

	// Return BufferedReader from URL
	// The code that calls this method is responsible for ensuring the
	// BufferedReader
	// is closed after use.
	public static BufferedReader brFromURL(String urlName) throws IOException {

		// Create InputStream from URL object
		URL u = new URL(urlName);
		InputStream is = u.openStream();

		// Create BufferedReader from InputStreamReader object
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		return br;

	}

	// Start number analysis: create file and initialise variables
	private void analysisStart(String dataFile) throws IOException {

		// Store data file in user home directory
		this.fileName = dataFile;

		// Create file
		System.out.println("Creating file " + this.fileName);
		System.out.println();

		// Just creating file: don't actually need to use FileWriter once
		// created.
		try ( FileWriter f = new FileWriter(this.fileName); ) { }

		// Initialise variables
		this.maxValue = 0;
		this.minValue = 0;
		this.nValues = 0;
		this.sumOfValues = 0;
		this.meanValue = 0;

	}

	// Analyse data for each line
	private void analyseData(String lineIn) throws Exception {

		// Check if line actually has any data in it
		if (lineIn.isEmpty()) {
			return;
		}
		// Check if line starts with a letter and is commented out
		else if (Character.isLetter(lineIn.charAt(0))) {
			return;
		}
		// Line has content and is not commented out
		else {

			// Instantiate FileWriter, BufferedWriter and PrintWriter objects
			// for writing data to file
			try (
					FileWriter f = new FileWriter(this.fileName, true);
					BufferedWriter b = new BufferedWriter(f);
					PrintWriter pw = new PrintWriter(b);

					// Instantiate Scanner with line data String
					Scanner s = new Scanner(lineIn);
			) {

				// Step through numbers in line
				while (s.hasNextDouble()) {

					// Read out next number
					double curValue = s.nextDouble();

					// If no values read, initialise minValue and maxValue
					if (this.nValues == 0) {
						this.maxValue = curValue;
						this.minValue = curValue;
					}

					// Update running total for nValues, sumOfValues
					this.nValues++;
					this.sumOfValues += curValue;

					// Only update maxValue if curValue is bigger than previous
					// maxValue
					if (curValue > this.maxValue) {
						this.maxValue = curValue;
					}

					// Only update minValue if curValue is smaller than previous
					// minValue
					if (curValue < this.minValue) {
						this.minValue = curValue;
					}

					// Display value on screen
					System.out.println(curValue);

					// Write number to file
					pw.println(curValue);

				}

			}

		}

	}

	// Display final values
	private void analysisEnd() {

		this.meanValue = this.sumOfValues / this.nValues;
		System.out.println();
		System.out.println("Final data values:");
		System.out.println("Maximum Value: " + this.maxValue);
		System.out.println("Minimum Value: " + this.minValue);
		System.out.println("Number of Values: " + this.nValues);
		System.out.println("Mean Value: " + this.meanValue);
		System.out.println("Sum of all Values: " + this.sumOfValues);
		System.out.println();

	}

	// Analyse data from given URL and write results to file
	private void analyseDataFromURL(String webAddress, String dataFile, String saveDir) {

		// Concatenate String with full directory location of data file
		String saveFile = (saveDir + File.separator + dataFile);

		try {

			// initialize values and create data file
			analysisStart(saveFile);

			// Return BufferedReader from URL
			BufferedReader reader = brFromURL(webAddress);
			String line = "";
			System.out.println("Reading data values:");

			// Step through data line by line
			while ((line = reader.readLine()) != null) {
				analyseData(line); // analyze lines, check for comments etc.
			}

			analysisEnd(); // print min, max, etc.

		} catch (Exception e) {
			System.out.println("Error reading data in NumericalReader: ");
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// Location of input data files
		String dataUrlRoot = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/";

		// Select output directory for writing files
		String saveDir = getOutputDirectory();

		// Instantiate NumericalReader object for input file, and run analysis
		NumericalReader nr = new NumericalReader();
		nr.analyseDataFromURL(dataUrlRoot+"module4_data1.txt", "numbers1.txt", saveDir);

		NumericalReader nr2 = new NumericalReader();
		nr.analyseDataFromURL(dataUrlRoot+"module4_data2.txt", "numbers2.txt", saveDir);
	}

}