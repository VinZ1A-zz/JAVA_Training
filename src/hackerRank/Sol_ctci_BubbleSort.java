package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_BubbleSort {

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
		aInput.add("3 2 1");

		return aInput;
	}

	private static int getSwaps(int[] a) {
		int n = a.length;
		int totNumberOfSwaps = 0;
		for (int i = 0; i < n; i++) {
			// Track number of elements swapped during a single array traversal
			int numberOfSwaps = 0;

			for (int j = 0; j < n - 1; j++) {
				// Swap adjacent elements if they are in decreasing order
				if (a[j] > a[j + 1]) {
					swap(a, j, j + 1);
					numberOfSwaps++;
				}
			}

			// If no elements were swapped during a traversal, array is sorted
			if (numberOfSwaps == 0) {
				break;
			}
			totNumberOfSwaps += numberOfSwaps;
		}
		return totNumberOfSwaps;
	}

	private static void swap(int[] tab, int indexA, int indexB) {
		int temp = tab[indexA];
		tab[indexA] = tab[indexB];
		tab[indexB] = temp;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
		}
		scan.close();

		System.out.format("Array is sorted in %d swaps.\n", getSwaps(a));
		System.out.format("First Element: %d\n", a[0]);
		System.out.format("Last Element: %d", a[a.length - 1]);

	}
}
