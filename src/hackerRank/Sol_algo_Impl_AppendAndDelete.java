package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//Points: 496.00 Rank: 56855
public class Sol_algo_Impl_AppendAndDelete {

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

		// aInput.add("hackerhappy");
		// aInput.add("hackerrank");
		// aInput.add("9");
		// aInput.add("aba");
		// aInput.add("aba");
		// aInput.add("7");
		aInput.add("qwerasdf");
		aInput.add("qwerbsdf");
		aInput.add("6");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String s1 = scan.next();
		String s2 = scan.next();
		int nbOfOps = scan.nextInt();

		int i = 0;
		// beginning of s2 is in s1
		int minLength = Math.min(s1.length(), s2.length());
		while (i < minLength && s1.charAt(i) == s2.charAt(i)) {
			i++;
		}
		i--;
		debug("i : " + i);
		boolean canDeleteEverything = (s1.length() + s2.length()) <= nbOfOps;
		debug("canDeleteEverything " + canDeleteEverything);

		// size of whatever has to be deleted
		int toDelete = s1.length() - i - 1;
		int toAdd = s2.length() - i - 1;
		int minOps = toDelete + toAdd;
		debug("" + minOps);
		debug("" + nbOfOps);
		if (nbOfOps >= minOps && (canDeleteEverything || (nbOfOps - minOps) % 2 == 0)) {
			System.out.println("Yes");
			System.exit(0);
		}

		System.out.println("No");

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
