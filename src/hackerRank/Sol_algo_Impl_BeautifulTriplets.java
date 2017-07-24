package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

//Points: 926.00 Rank: 26342
public class Sol_algo_Impl_BeautifulTriplets {

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

		aInput.add("7 3"); // len, d
		aInput.add("1 2 4 5 7 8 10");

		return aInput;
	}

	private static void doIt() {
		someImpl();

	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		int size = scan.nextInt();
		int d = scan.nextInt();
		// int[] nums = new int[size];
		Set<Integer> nums = new TreeSet<>();
		for (int i = 0; i < size; i++) {
			nums.add(scan.nextInt());
		}
		scan.close();

		int total = 0;
		for (int num : nums) {
			if (!nums.contains(num + d)) {
				continue;
			}
			if (nums.contains(num + 2 * d)) {
				total++;
			}
		}

		System.out.println(total);

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
