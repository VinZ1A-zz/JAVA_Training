import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_Impl_JumpingOnCloudsRev {

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

		// 92
		aInput.add("8 2");
		aInput.add("0 0 1 0 0 1 1 0");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int nbClouds = scan.nextInt();
		int jumpDist = scan.nextInt();
		boolean[] clouds = new boolean[nbClouds];
		for (int i = 0; i < nbClouds; i++) {
			clouds[i] = (scan.nextInt() == 1);
		}

		int energy = 100;
		int pos = 0;
		// bourrin. sorry
		do {
			pos = (pos + jumpDist) % nbClouds;
			energy--;
			if (clouds[pos])
				energy = energy - 2;
		} while (pos != 0);

		System.out.println(energy);
	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
