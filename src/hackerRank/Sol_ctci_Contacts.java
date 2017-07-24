package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_Contacts {

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

		aInput.add("whateva"); // nb of operations (not read)
		aInput.add("add hacker");
		aInput.add("add hacknow");
		aInput.add("add hat");
		// aInput.add("find ha");
		// aInput.add("find hat");
		// aInput.add("find hac");
		aInput.add("find hak");

		// aInput.add("4");
		// aInput.add("add hack");
		// aInput.add("add hackerrank");
		// aInput.add("find hac");
		// aInput.add("find hak");

		return aInput;
	}

	static class Node {
		Map<Character, Node> _map;
		long _nbWords;
		char _char = '*'; // DEBUG

		Node() {
			_map = new HashMap<>();
			_nbWords = 0;
		}
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		scan.nextLine(); // skipping nb of operations
		Node aRoot = new Node();
		while (scan.hasNext()) {
			String op = scan.next();
			String word = scan.next();
			if (op.equals("add")) {
				add(aRoot, word);
			} else if (op.equals("find")) {
				System.out.println(find(aRoot, word));
			}
		}

		scan.close();

	}

	static private void add(Node root, String word) {
		Node cur = root;
		// debug("**** adding " + word + " ****");
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			Node container = cur._map.get(letter);
			if (container == null) {
				container = new Node();
				container._nbWords = 1;
				container._char = letter;
				cur._map.put(letter, container);
				// debug("adding new " + letter + " to " + cur._char + "(count:"
				// + cur._nbWords + ")");
			} else {
				container._nbWords++; // path will lead to one more word
				// debug("existing " + letter + " in " + cur._char + "(count:" +
				// cur._nbWords + ")");
			}
			cur = container;
		}

	}

	static private long find(Node root, String word) {
		Node cur = root;
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			Node container = cur._map.get(letter);
			if (container != null) {
				cur = container;
			} else {
				return 0;
			}
		}

		return cur._nbWords;
	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
