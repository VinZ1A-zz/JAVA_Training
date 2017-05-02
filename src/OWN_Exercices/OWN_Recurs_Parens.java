package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

// find all combinations of balanced parenthesis
public class OWN_Recurs_Parens {

	static final boolean _debug = "true".equals(System.getProperties().get("debug"));

	public static void main(String[] args) {

		if (args.length != 0) {
			System.err.println("in debug with debug = " + System.getProperties().get("debug"));
			// _debug = true; // set as final
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

		int nbParens = scan.nextInt();

		List<String> combs = new ArrayList<>();

		combs = getCombs(nbParens);

		debug(combs);

		scan.close();

	}

	private static List<String> getCombs(int n) {
		// base case
		if (n == 0) {
			List<String> combs = new ArrayList<>();
			combs.add("");
			return combs;
		}
		// recurs pattern
		List<String> prevCombs = getCombs(n - 1);
		Set<String> newCombs = new HashSet<>(); // avoids dups
		for (String str : prevCombs) {
			newCombs.add("(" + str + ")");
			newCombs.add("()" + str);
			newCombs.add(str + "()");
		}
		return new ArrayList<String>(newCombs);

	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.print(obj.toString());
		}
	}

	private static void debugln(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}

	private static void print(Object obj) {
		System.out.print(obj.toString());
	}

	private static void println(Object obj) {
		System.out.println(obj.toString());
	}

}
