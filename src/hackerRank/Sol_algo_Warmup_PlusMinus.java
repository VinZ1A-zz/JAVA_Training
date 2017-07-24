package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// rank 325624
public class Sol_algo_Warmup_PlusMinus {

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

		aInput.add("6");
		aInput.add("-4 3 -9 0 4 1");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int nbOfItems = scan.nextInt(); // rien a fout
		int nbOfPos = 0;
		int nbOfZeros = 0;
		int nbOfNegs = 0;
		while (scan.hasNext()) {
			int val = scan.nextInt();
			if (val > 0)
				nbOfPos++;
			else if (val < 0)
				nbOfNegs++;
			else
				nbOfZeros++;

		}

		System.out.println((double) nbOfPos / nbOfItems);
		System.out.println((double) nbOfNegs / nbOfItems);
		System.out.print((double) nbOfZeros / nbOfItems);

		scan.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
