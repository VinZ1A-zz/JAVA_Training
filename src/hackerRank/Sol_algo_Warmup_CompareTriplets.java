package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_algo_Warmup_CompareTriplets {

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

		aInput.add("5 6 7");
		aInput.add("3 6 10");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int[][] score = new int[2][3];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				score[i][j] = scan.nextInt();
			}
		}

		int total0 = 0, total1 = 0;
		for (int i = 0; i < 3; i++) {
			if (score[0][i] > score[1][i]) {
				total0++;
			} else if (score[0][i] < score[1][i]) {
				total1++;
			}
		}

		System.out.println(total0 + " " + total1);

		scan.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
