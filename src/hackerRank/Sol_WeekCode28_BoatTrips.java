package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_WeekCode28_BoatTrips {

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

		// Yes
		aInput.add("5 2 2");
		aInput.add("1 2 1 4 3");

		// No
		// aInput.add("5 1 2");
		// aInput.add("1 2 1 4 1");

		return aInput;
	}

	private static void doIt() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int nbTrips = in.nextInt();
		int capa = in.nextInt();
		int nbBoats = in.nextInt();
		// int[] tourSize = new int[nbTrips];
		int overallCapa = capa * nbBoats;
		int tourSize;
		for (int i = 0; i < nbTrips; i++) {
			// tourSize[i] = in.nextInt();
			tourSize = in.nextInt();
			if (tourSize > overallCapa) {
				System.out.print("No");
				System.exit(0);
			}
		}

		// Arrays.sort(tourSize);
		// System.err.println(tourSize[nbTrips - 1]);
		//
		// if (tourSize[nbTrips - 1] > overallCapa) {
		// System.out.print("No");
		// } else
		System.out.print("Yes");

		// in.close();

	}
}
