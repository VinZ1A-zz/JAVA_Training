package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day20 {

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
		aInput.add("1 2 3");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
		}

		scan.close();

		// sort
		int totNbSwaps = 0;
		for (int i = 0; i < n; i++) {
			// Track number of elements swapped during a single array traversal
			int numberOfSwaps = 0;

			for (int j = 0; j < n - 1; j++) {
				// Swap adjacent elements if they are in decreasing order
				if (a[j] > a[j + 1]) {
					// swap(a[j], a[j + 1]);
					swap(j, j + 1, a);
					numberOfSwaps++;
				}
			}

			// If no elements were swapped during a traversal, array is sorted
			if (numberOfSwaps == 0) {
				break;
			}
			totNbSwaps += numberOfSwaps;
		}
		System.out.println("Array is sorted in " + totNbSwaps + " swaps.");
		System.out.println("First Element: " + a[0]);
		System.out.println("Last Element: " + a[a.length - 1]);
	}

	private static void swap(int idx1, int idx2, int[] a) {
		// not trying to be a smartass
		int c = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = c;
	}
}
