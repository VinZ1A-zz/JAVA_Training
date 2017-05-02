import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 246.00 Rank: 114332
public class Sol_algo_Sorting_Intro {

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

		aInput.add("4");
		aInput.add("6");
		aInput.add("1 4 5 7 9 12");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int toFind = scan.nextInt();
		int size = scan.nextInt();
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			// a[i] = scan.nextInt();
			int val = scan.nextInt();
			if (val == toFind) {
				System.out.println(i);
				break;
			}
		}

		scan.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
