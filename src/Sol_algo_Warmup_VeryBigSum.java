import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Warmup_VeryBigSum {

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

		aInput.add("5");
		aInput.add("1000000001 1000000002 1000000003 1000000004 1000000005");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		scan.nextInt(); // rien a fout
		long total = 0;
		while (scan.hasNext()) {
			total += scan.nextInt();
		}
		System.out.println(total);

		scan.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
