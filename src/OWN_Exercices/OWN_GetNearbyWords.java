package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class OWN_GetNearbyWords {

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

		// expects hi, tu
		aInput.add("gh"); // input word
		aInput.add("2"); // nb of chars having nearby chars
		aInput.add("g gfht"); // g has nearby chars g,f,h,t (should contain g)
		aInput.add("h ihju");
		aInput.add("hi tu"); // possible words

		return aInput;
	}

	static Map<Character, List<Character>> charToNearby = new HashMap<>();
	static Set<String> words = new HashSet<>();

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String in = scan.next();
		int nbCharsWithNearby = scan.nextInt();
		scan.nextLine(); // go to next line

		for (int i = 0; i < nbCharsWithNearby; i++) {
			String[] strs = scan.nextLine().split(" ");
			Character fromChar = strs[0].charAt(0);
			charToNearby.put(fromChar, new ArrayList<Character>());
			for (int k = 0; k < strs[1].length(); k++) {
				charToNearby.get(fromChar).add(strs[1].charAt(k));
			}
		}

		while (scan.hasNext()) {
			words.add(scan.next());
		}

		List<StringBuilder> perms = new ArrayList<>();
		perms = getPermutations(in, 0);

		for (StringBuilder str : perms) {
			if (words.contains(str.toString())) {
				System.out.println(str);
			}
		}

		scan.close();

	}

	private static List<StringBuilder> getPermutations(String in, int idx) {
		// end point
		if (idx == in.length()) {
			List<StringBuilder> emptyStr = new ArrayList<>();
			emptyStr.add(new StringBuilder());
			return emptyStr;
		}

		// recurse with already obtained result
		char first = in.charAt(idx);
		List<Character> simToFirst = getSimilar(first);
		List<StringBuilder> subStrings = getPermutations(in, idx + 1);
		List<StringBuilder> newStrings = new ArrayList<>();
		for (StringBuilder str : subStrings) {
			for (char simFirstChar : simToFirst) {
				newStrings.add(new StringBuilder("").append(simFirstChar).append(str));
			}
		}

		return newStrings;

	}

	private static List<Character> getSimilar(char first) {
		List<Character> sim = charToNearby.get(first);
		if (sim == null) {
			sim = new ArrayList<>();
			sim.add(first);
			return sim;
		}
		return sim;
	}

	// private static void debug(Object obj) {
	// if (_debug) {
	// System.err.print(obj.toString());
	// }
	// }
	//
	// private static void debugln(Object obj) {
	// if (_debug) {
	// System.err.println(obj.toString());
	// }
	// }
	//
	// private static void print(Object obj) {
	// System.out.print(obj.toString());
	// }
	//
	// private static void println(Object obj) {
	// System.out.println(obj.toString());
	// }

}
