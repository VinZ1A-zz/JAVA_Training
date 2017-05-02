import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_Anagrams {

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

		aInput.add("cde");
		aInput.add("abc");

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		String a = in.next();
		String b = in.next();
		in.close();
		System.out.println(numberNeeded(a, b));
	}

	static class Counters {
		int inStrA;
		int inStrB;

		Counters(int a, int b) {
			inStrA = a;
			inStrB = b;
		}

		void incremA() {
			inStrA++;
		}

		void incremB() {
			inStrB++;
		}
	}

	private static int numberNeeded(String a, String b) {
		HashMap<Character, Counters> aMap = new HashMap<Character, Counters>();

		for (char aChar : a.toCharArray()) {
			if (aMap.containsKey(aChar)) {
				aMap.get(aChar).incremA();
			} else {
				aMap.put(aChar, new Counters(1, 0));
			}
		}

		for (char aChar : b.toCharArray()) {
			if (aMap.containsKey(aChar)) {
				aMap.get(aChar).incremB();
			} else {
				aMap.put(aChar, new Counters(0, 1));
			}
		}

		int nbCharsToRemove = 0;
		for (Entry<Character, Counters> elem : aMap.entrySet()) {
			System.err.println(elem.getKey() + " - " + elem.getValue().inStrA + ":" + elem.getValue().inStrB);
			nbCharsToRemove += Math.abs(elem.getValue().inStrA - elem.getValue().inStrB);
		}

		return nbCharsToRemove;
	}

}
