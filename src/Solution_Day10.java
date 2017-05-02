import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day10 {

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

		// aInput.add("13"); // 1101
		aInput.add("439"); // expected 3

		return aInput;
	}

	// completement pas optimisé but not too shameful
	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();

		scan.close();

		// convert to binary
		ArrayList<Integer> aBin = new ArrayList<Integer>();
		int divRes = 0;

		do {
			divRes = n / 2;
			if (n - divRes * 2 == 0) { // no remainder
				aBin.add(0);
			} else
				aBin.add(1);
			n = divRes;
		} while (n != 0);

		int aNbConsecutiveOnes = 0;
		int maxNb = 0;
		for (int i = aBin.size() - 1; i >= 0; i--) {
			int aDigit = aBin.get(i);
			System.err.print(aDigit);
			if (aDigit == 1) {
				aNbConsecutiveOnes++;
			}
			if (aNbConsecutiveOnes > maxNb) {
				maxNb = aNbConsecutiveOnes;
			}
			if (aDigit == 0) {
				aNbConsecutiveOnes = 0;
			}
		}
		System.err.println();
		System.out.println(maxNb);
	}
}
