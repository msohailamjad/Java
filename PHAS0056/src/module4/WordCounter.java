package module4;

import java.io.*; // For BufferedReader, InputStreamReader, InputStream
import java.net.*; // For URL
import java.util.*; // For Scanner

public class WordCounter {

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

	// Return BufferedReader from filename
	// The code that calls this method is responsible for ensuring the
	// BufferedReader
	// is closed after use.
	public static BufferedReader brFromFile(String fileName) throws IOException {

		// Instantiate FileReader object from filename
		FileReader fr = new FileReader(fileName);

		// Return BufferedReader object from FileReader
		BufferedReader br = new BufferedReader(fr);
		return br;

	}

	// Returns number of words in document at given URL.
	public static int countWordsInResource(String webAddress) throws IOException {

		// Initialise word count variable
		int countWords = 0;

		// Try-with-resources to ensure BufferedReader and underlying stream are
		// closed after use.
		try (BufferedReader b = brFromURL(webAddress); Scanner s = new Scanner(b);) {

			// Step through Scanner word by word
			while (s.hasNext()) {
				s.next();
				countWords++;
			}

		}

		return countWords;

	}

	public static void main(String[] args) {

		try {
			// Read data from specified URL
			String webAddress = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt";
			System.out.println("Loading data from the following URL:");
			System.out.println("  " + webAddress);

			// Count words in URL
			int countWords = WordCounter.countWordsInResource(webAddress);
			System.out.println("Number of words in URL: " + countWords);
		}
		// Catch URL and InputStream Exceptions
		catch (IOException e) {
			System.out.println("Error in WordCounter: ");
			e.printStackTrace();
		}

	}

}