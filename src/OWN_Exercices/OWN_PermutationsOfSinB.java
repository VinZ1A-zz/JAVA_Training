package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class OWN_PermutationsOfSinB {

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

		aInput.add("abbcd");
		aInput.add("babcdbaefdbbacbddfae");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String str = scan.next();
		String main = scan.next();

		// determine hash of str (regardless of order)
		char[] arr = str.toCharArray();
		int hash = 0;
		for (char c : arr) {
			hash += c;
		}

		debugln("the hash " + hash);
		int len = str.length();

		int[] hashs = new int[main.length()];
		int prevHashAdded = 0;
		for (int i = 0; i < main.length(); i++) {
			if (i == 0) {
				prevHashAdded = main.charAt(i);
				hashs[i] = prevHashAdded;
			} else if (i < len) {
				prevHashAdded = main.charAt(i);
				hashs[i] = hashs[i - 1] + prevHashAdded;
			} else {
				// need to remove previous val and add new
				hashs[i] = hashs[i - 1] - prevHashAdded + main.charAt(i);
				prevHashAdded = main.charAt(i);
			}
			debug("At i=" + i + " , hash is " + hashs[i]);

			// determine if the hash is really an anagram
			if (hashs[i] == hash) {
				String candidate = main.substring(i - len + 1, i + 1);
				if (isAnagram(candidate, str)) {
					debug("  #########  : " + candidate);
				} else {
					debug("  #########  : " + candidate + " (almost) ");
				}
			}
			debugln("");
		}

		scan.close();

	}

	// improve: store hashSet of all possible anagrams...
	private static boolean isAnagram(String candidate, String str) {
		if (candidate.length() != str.length())
			return false;

		char[] strArr = str.toCharArray(); // improve: avoid sorting same arr
		char[] candArr = candidate.toCharArray();

		Arrays.sort(strArr);
		Arrays.sort(candArr);
		return Arrays.equals(strArr, candArr);

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
