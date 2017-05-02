package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Print_ReverseLinkedList {

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

		aInput.add("123 12 1 2 5 3 47 7 4");

		return aInput;
	}

	private static void doIt() {

		// recursiveImpl();

		baseNEnc();

	}

	// TODO
	private static void baseNEnc() {
		Node root = createLinkedList();

		displayOriginalList(root);

		// determine max

		// encode in N-base ?
		// XXX try with single digits first and 10-base enc.

	}

	private static void recursiveImpl() {
		Node root = createLinkedList();

		displayOriginalList(root);

		debugln(printRev(root).toString().trim());

	}

	private static Node createLinkedList() {
		Scanner scan = new Scanner(System.in);

		Node prev = null;
		Node root = null;
		while (scan.hasNext()) {
			int val = scan.nextInt();
			Node node = new Node(val);
			if (root == null) {
				root = node;
			}
			if (prev != null) {
				prev.next = node;
			}
			prev = node;
		}

		scan.close();
		return root;
	}

	private static void displayOriginalList(Node node) {
		while (node != null) {
			debug(node.data + " ");
			node = node.next;
		}
		debugln("");
	}

	private static StringBuilder printRev(Node root) {
		if (root == null)
			return new StringBuilder("");

		return (printRev(root.next).append(" ").append(root.data));
	}

	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
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
