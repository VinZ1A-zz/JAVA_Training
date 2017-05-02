import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/cfINTRO.html
// THIS IS AMAZING !!!!
public class Sol_WeekCode29_DiameterMinimization {

	// sets _debug with VM argument -Ddebug=true
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

		aInput.add("7 11");

		return aInput;
	}

	private static void doIt() {
		someImpl();
	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int x = scan.nextInt();
		int y = scan.nextInt();
		scan.close();

	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
	private static void print(Object obj) {
		System.out.print(obj.toString());
	}

	@SuppressWarnings("unused")
	private static void println(Object obj) {
		System.out.println(obj.toString());
	}

}
