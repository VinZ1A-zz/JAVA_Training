package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// TODO
public class Sol_algo_Recurs_SimplifiedChessEngine {

	static final boolean _debug = "true".equals(System.getProperties().get("debug"));

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug with debug = " + System.getProperties().get("debug"));
			// _debug = true; // set as final
			// _fromEclipse = true;
			String aInput = "";
			for (String data : getData()) {
				aInput += data + "\r\n";
			}
			System.setIn(new ByteArrayInputStream(aInput.getBytes()));
		}

		doIt();

	}

	// debug method - discard!
	static private Vector<String> getData() {
		Vector<String> aInput = new Vector<String>();

		aInput.add("148 3");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String n = scan.next();

		scan.close();

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.print(obj.toString());
		}
	}

	private static void debugln(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}

	private static void print(Object obj) {
		System.out.print(obj.toString());
	}

	private static void println(Object obj) {
		System.out.println(obj.toString());
	}

}
