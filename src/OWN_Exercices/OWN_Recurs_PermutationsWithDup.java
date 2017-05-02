package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class OWN_Recurs_PermutationsWithDup {

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

		// aInput.add("abbcdde");
		aInput.add("abcde");
		// aInput.add("abcc");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		String str = scan.next();

		List<String> perms = getPerms(str);

		debugln("perms no duplicates : " + perms);

		scan.close();

	}

	static List<String> getPerms(String str) {
		return getPerms(str, str.length());
	}

	static List<String> getPerms(String str, int idx) {
		// end case
		if (idx == 0) {
			List<String> perms = new ArrayList<>();
			perms.add(new Character(str.charAt(0)).toString());
			return perms;
		}
		// general case
		// get permutations for str[0..idx-2] of length idx-1
		List<String> subPerms = getPerms(str, idx - 1);
		Set<String> newPerms = new HashSet<>(); // avoids duplicates
		for (String subStr : subPerms) {
			// one more iteration than the length of the substring
			for (int insertIdx = 0; insertIdx < idx; insertIdx++) {
				String newStr = subStr.substring(0, insertIdx) + str.charAt(idx - 1) + subStr.substring(insertIdx, idx - 1);
				newPerms.add(newStr); // will not create dups as this is a Set
				debugln("added " + newStr);
			}
		}
		return new ArrayList<String>(newPerms);

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
