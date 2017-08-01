package springSnipplets.withoutSpring;

// Good design : can use any generic reader (file, FTP,...)
public class DataReaderClient {
	private IReader reader = null;
	// not so good : still hard-wired!
	private static String fileName = "src/springSnipplets/resources/someStuff.txt";

	// resolved at runtime using polymorphism
	public DataReaderClient(IReader reader) {
		this.reader = reader;
	}

	private String fetchData() {
		return reader.read();
	}

	public static void main(String[] args) {
		// from book - but NO.
		// try {
		// FileReader fileReader = new FileReader(fileName);
		// DataReaderClient client = new DataReaderClient(fileReader);

		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }

		// still a prob here: still hard-wiring file name in main()
		VanillaDataReaderClient fieldReader = new VanillaDataReaderClient(fileName);
		DataReaderClient genericReader = new DataReaderClient(fieldReader);
		System.out.println("reading from generic reader: " + genericReader.fetchData());
	}
}
