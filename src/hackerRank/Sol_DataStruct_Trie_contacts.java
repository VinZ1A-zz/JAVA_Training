package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_DataStruct_Trie_contacts {

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

		// int dim = 7;
		// aInput.add(String.valueOf(dim));
		// aInput.add("3");
		// aInput.add("2");
		// aInput.add("5");
		// aInput.add("1");
		// aInput.add("4");
		// aInput.add("6");
		// aInput.add("7");

		// expects 9
		int dim = 15;
		aInput.add(String.valueOf(dim));
		aInput.add("1");
		aInput.add("3");
		aInput.add("2");
		aInput.add("5");
		aInput.add("4");
		aInput.add("6");
		aInput.add("7");
		aInput.add("9");
		aInput.add("8");
		aInput.add("11");
		aInput.add("13");
		aInput.add("12");
		aInput.add("10");
		aInput.add("15");
		aInput.add("14");

		return aInput;
	}

	static class Node {
		Node left;
		Node right;
		int data;

		Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	public static Node insert(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (data <= root.data) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	static int height(Node root) {
		int depth = inOrderTraversal(root, 0);
		return depth;
	}

	static int inOrderTraversal(Node node, int depth) {
		int max = 0;
		if (node != null) {
			max = Math.max(depth, inOrderTraversal(node.left, depth + 1));
			max = Math.max(max, inOrderTraversal(node.right, depth + 1));
			// debugln("at node " + node.data + ", depth:" + depth);
		}
		return max;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);
		int t = scan.nextInt();
		Node root = null;
		while (t-- > 0) {
			int data = scan.nextInt();
			root = insert(root, data);
		}
		scan.close();
		int height = height(root);
		System.out.println(height);

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
