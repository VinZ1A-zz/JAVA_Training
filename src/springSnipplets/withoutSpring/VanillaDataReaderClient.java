package springSnipplets.withoutSpring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//slightly better design : using interface
public class VanillaDataReaderClient implements IReader {
	private FileReader fileReader = null;
	private Scanner scanner = null;
	private StringBuilder builder = null;

	// statically bound to a file system (default value)
	private String fileName = "src/springSnipplets/resources/someStuff.txt";

	public VanillaDataReaderClient(String iFileName) {
		if (iFileName == null) {
			System.err.println("using default file location " + fileName);
			iFileName = fileName;
		}
		try {
			fileReader = new FileReader(iFileName);
			scanner = new Scanner(new File(iFileName));
			builder = new StringBuilder();
		} catch (FileNotFoundException e) {
			System.out.println("Exception " + e.getMessage());
		}
	}

	public VanillaDataReaderClient() {
		this(null);
	}

	// known public "interface" -- bad design!
	@Override
	public String read() {
		return fetchAllWords();
	}

	private String fetchAllWords() {
		while (scanner.hasNext()) {
			builder.append(scanner.next());
			builder.append(",");
		}
		return builder.toString();
	}

	private String fetchFirstLine() {

		try {
			// fileReader.read() // from book - just. NO.
			BufferedReader reader = new BufferedReader(fileReader);
			return reader.readLine();
		} catch (IOException e) {
			System.out.println("Exception " + e.getMessage());
		}
		return null;
	}

	public static void main(String... str) {
		VanillaDataReaderClient fromFile = new VanillaDataReaderClient();
		System.out.println("firstLine read : " + fromFile.fetchFirstLine());
		System.out.println("all words read : " + fromFile.fetchAllWords());
	}
}
