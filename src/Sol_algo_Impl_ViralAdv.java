import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_Impl_ViralAdv {

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

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int i = scan.nextInt();
		int nbOfLikes = 0;
		int totNbOfLikes = 0;
		int nbOfAds = 5;
		for (int day = 1; day <= i; day++) {
			nbOfLikes = (int) Math.floor(nbOfAds / 2);
			nbOfAds = nbOfLikes * 3;
			totNbOfLikes += nbOfLikes;

		}

		System.out.println(totNbOfLikes);
	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
