import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

//was 202282 with 106 pts
public class Sol_algo_Impl_MinMaxSum {

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

		aInput.add("1 2 3 4 5");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		long[] array = new long[5];
		for (int i = 0; i < 5; i++) {
			array[i] = scan.nextLong();
		}
		scan.close();
		Arrays.sort(array);

		long minSum = 0;
		long maxSum = 0;
		for (int i = 0; i < 5; i++) {
			if (i != 4) {
				minSum += array[i];
			}
			if (i != 0) {
				maxSum += array[i];
			}
		}

		System.out.println(minSum + " " + maxSum);

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
