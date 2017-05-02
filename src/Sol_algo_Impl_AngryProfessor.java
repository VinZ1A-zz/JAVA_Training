import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_Impl_AngryProfessor {

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

		aInput.add("2");
		aInput.add("4 3");// students & cancellation threshold
		aInput.add("-1 -3 4 2");
		aInput.add("4 2");
		aInput.add("	0 -1 2 1");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int nbQueries = scan.nextInt();

		for (int q = 0; q < nbQueries; q++) {
			int size = scan.nextInt(); // nb of students
			int threshold = scan.nextInt();
			int early = 0;
			for (int i = 0; i < size; i++) {
				if (scan.nextInt() <= 0) {
					early++;
				}
			}
			if (early < threshold)
				System.out.println("YES");
			else
				System.out.println("NO");

		}

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
