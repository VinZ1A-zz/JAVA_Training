import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_IsBinTree {

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

		// aInput.add("6");
		// aInput.add("3");
		// aInput.add("5");
		// aInput.add("2");
		// aInput.add("1");
		// aInput.add("4");
		// aInput.add("6");

		aInput.add("3");
		aInput.add("1");
		aInput.add("2");
		aInput.add("4");
		// aInput.add("3");
		// aInput.add("5");
		// aInput.add("6");
		// aInput.add("7");

		return aInput;
	}

	private static void doIt() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		Node root = null;
		while (T-- > 0) {
			int data = sc.nextInt();
			root = insertNonBST(root, data);
		}
		System.out.println(getHeight(root));
		System.out.println(checkBST(root) ? "Is BST" : "Is Not BST");
		sc.close();
	}

	static boolean checkBST(Node root) {
		// left< parent
		// right > parent
		// no duplicates (consequence)

		return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

	}

	static boolean isValidBST(Node root, int minVal, int maxVal) {

		if (root == null) {
			return true;
		}

		return (root.data > minVal && root.data < maxVal && isValidBST(root.left, minVal, root.data)
				&& isValidBST(root.right, root.data, maxVal));

	}

	private static Node insert(Node root, int data) {
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

	private static Node insertNonBST(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (root.left == null) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	static class Node {
		Node left, right;
		int data;

		Node(int data) {
			this.data = data;
			left = right = null;
		}
	}

	public static int getHeight(Node root) {
		// ------------------- ze code ------------------------
		if (root == null) {
			return -1;
		}
		return 1 + Math.max(getHeight(root.left), getHeight(root.right));
		// --------------------- end ze code ------------------
	}
}
