package hackerRank;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_ArraysLeftRotation {

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

		aInput.add("5 4"); // 5 integers, rotate by 4
		aInput.add("1 2 3 4 5"); // array

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int d = in.nextInt();
		int a[] = new int[n];
		int rotA[] = new int[n];
		int offset = n - d;
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
			if (i < d) {
				rotA[i + offset] = a[i];
			} else {
				rotA[i - d] = a[i];
			}
		}

		for (int i = 0; i < n; i++) {
			System.out.print(rotA[i] + " ");
		}
	}
}
