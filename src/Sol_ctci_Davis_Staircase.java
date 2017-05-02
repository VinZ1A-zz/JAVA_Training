import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_Davis_Staircase {

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

		aInput.add("4"); // was 3
		aInput.add("1"); // 1
		aInput.add("3"); // 4
		aInput.add("4"); // 4
		aInput.add("7"); // 44

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int s = scan.nextInt();
		int max = Integer.MIN_VALUE;
		int[] aList = new int[s];
		int n;
		for (int a0 = 0; a0 < s; a0++) {
			n = scan.nextInt();
			if (max < n) {
				max = n;
			}
			aList[a0] = n;
		}

		long[] choices = new long[max + 1];
		for (int i = 4; i < max + 1; i++) {
			choices[i] = -1;
		}
		choices[1] = 1;
		choices[2] = 1 + choices[1] * 1;
		choices[3] = 1 * choices[2] + 2 * choices[1];
		long answer = nbOfChoices(max, choices);

		scan.close();

		System.err.println("max " + max + " : " + answer);

		for (int nb : aList) {
			System.out.println(choices[nb]);
		}

	}

	private static long nbOfChoices(int n, long[] choices) {

		if (n <= 3)
			return choices[n];
		if (choices[n] != -1) { // n <= choices.length - 1 does not work
			return choices[n];
		} else {
			long answer = choices[1] * nbOfChoices(n - 1, choices) + nbOfChoices(n - 2, choices)
					+ nbOfChoices(n - 3, choices);
			choices[n] = answer;
			return answer;
		}

	}
}
