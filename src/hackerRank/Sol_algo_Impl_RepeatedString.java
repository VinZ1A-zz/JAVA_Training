package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

// got up to Points: 741.00 Rank: 35791

public class Sol_algo_Impl_RepeatedString {

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

		// aInput.add("aaa");
		// aInput.add("11");

		// 16481469408, got 16481469405
		aInput.add("epsxyyflvrrrxzvnoenvpegvuonodjoxfwdmcvwctmekpsnamchznsoxaklzjgrqruyzavshfbmuhdwwmpbkwcuomqhiyvuztwvq");
		aInput.add("549382313570");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		someImpl();

	}

	private static void someImpl() {
		Scanner scan = new Scanner(System.in);

		String txt = scan.next(); // rien a fout
		long totLn = scan.nextLong(); // divider
		long total = 0;
		scan.close();

		// entire repeated string
		int txtLn = txt.length();
		long nbOfEntiereStr = totLn / txtLn;
		debug("nbOfEntiereStr " + nbOfEntiereStr);

		// debug("nbOfOccurencesOf(txt, 'a') " + nbOfOccurencesOf(txt, 'a', 2));
		total = nbOfEntiereStr * nbOfOccurencesOf(txt, 'a');

		// remainder
		long remainder = totLn - (nbOfEntiereStr * txtLn);
		debug(remainder);
		// debug("total str len " + txt.length());
		total += nbOfOccurencesOf(txt, 'a', (int) remainder);

		System.out.println(total);

	}

	static int nbOfOccurencesOf(String txt, char a) {
		return nbOfOccurencesOf(txt, a, txt.length());
	}

	static int nbOfOccurencesOf(String txt, char a, int len) {

		int count = 0;
		for (int i = 0; i < len; i++) {
			if (txt.charAt(i) == a) {
				count++;
			}
		}

		return count;
	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}
}
