package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Anagram_Server {

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

		aInput.add("ABCD");
		// aInput.add("ABCEIEDF");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String str = scan.next();

		List<String> anagr = getAnagrams(str);

		for (String ana : anagr) {
			debugln(ana);
		}

		// check against Set<String> ... blabla

		scan.close();

	}

	static List<String> getAnagrams(String str) {
		List<String> anag = new ArrayList<>();

		char[] chars = str.toCharArray();
		anag = getAnagrams(chars);

		return anag;
	}

	static List<String> getAnagrams(char[] chars) {
		List<String> anag = new ArrayList<>();

		// end case
		if (chars.length == 0) {
			anag.add("");
			return anag;
		}
		for (char c : chars) {
			char[] subChars = new char[chars.length - 1];
			int i = 0;
			for (char subC : chars) {
				if (subC != c) {
					subChars[i] = subC;
					i++;
				}
			}
			List<String> list = getAnagrams(subChars);
			for (String subStr : list) {
				anag.add(new String(c + subStr));
			}
		}
		return anag;

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
