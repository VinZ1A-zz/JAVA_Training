import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Warmup_Staircase {

	static boolean _debug = false;

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug");
			_debug = true;
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

		aInput.add("10");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int size = scan.nextInt();

		for (int i = 1; i <= size; i++) {
			for (int j = 1; j < size - i + 1; j++) {
				System.out.print(" ");
			}
			for (int j = 1; j < i + 1; j++) {
				System.out.print("#");
			}
			System.out.println();
		}

		scan.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
