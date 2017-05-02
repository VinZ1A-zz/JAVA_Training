import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day14 {

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

		aInput.add("3");
		aInput.add("1 2 5");

		return aInput;
	}

	class Difference {
		private int[] elements;
		public int maximumDifference = -1;

		// -------------- Added code here ----------------
		public Difference(int[] a) {
			elements = a;
		}

		public void computeDifference() {
			// absolutely not optimized.
			for (int i = 0; i < elements.length - 1; i++) {
				for (int j = i + 1; j < elements.length; j++) {
					int curMax = Math.abs(elements[i] - elements[j]);
					if (curMax > maximumDifference) {
						maximumDifference = curMax;
					}
				}
			}

		}
		// --------------- copy paste end ----------------
	} // End of Difference class

	private static void doIt() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		sc.close();

		Difference difference = new Solution_Day14().new Difference(a);

		difference.computeDifference();

		System.out.print(difference.maximumDifference);

	}
}
