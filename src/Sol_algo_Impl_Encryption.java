import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

// Points: 813.00 Rank: 31599

public class Sol_algo_Impl_Encryption {

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

		// max length 81 chars
		// hae and via ecy
		// aInput.add("haveaniceday");

		// imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau
		aInput.add("if man was meant to stay on the ground god would have given us roots");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		someImpl();

	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);
		String phrase = scan.nextLine();
		scan.close();

		// remove spaces
		String noSpaces = phrase.replaceAll(" ", "");
		// debug(noSpaces);

		int length = noSpaces.length();
		int nbRows = (int) Math.floor(Math.sqrt(length));
		int nbCol = (int) Math.ceil(Math.sqrt(length));
		// debug("nbRows " + nbRows + " , nbCol " + nbCol);

		List<StringBuilder> aStrs = new ArrayList<StringBuilder>(nbCol);
		for (int i = 0; i < length;) {
			StringBuilder aStrI = null;
			if (i < nbCol) {
				aStrI = new StringBuilder(nbRows);
				aStrs.add(aStrI);
			} else {
				aStrI = aStrs.get(i % nbCol);
			}

			aStrI.append(noSpaces.charAt(i));

			i++;
		}

		for (StringBuilder a : aStrs) {
			System.out.print(a + " ");
		}
		System.out.println();

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
