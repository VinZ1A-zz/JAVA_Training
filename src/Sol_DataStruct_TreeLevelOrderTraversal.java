import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Vector;

public class Sol_DataStruct_TreeLevelOrderTraversal {

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

		// expects 3 5 2 1 4 6
		aInput.add("6"); // size first!
		aInput.add("3 5 2 1 4 6");

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
				// debugln("inserting " + data + " on left of " + root.data);
			} else {
				cur = insert(root.right, data);
				root.right = cur;
				// debugln("inserting " + data + " on right of " + root.data);
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

	static void LevelOrder(Node root) {
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(root);
		// debugln("start with " + root.data);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.print(cur.data + " ");
			if (cur != null) {
				if (cur.left != null) {
					queue.add(cur.left);
				}
				if (cur.right != null) {
					queue.add(cur.right);
				}
			}

		}
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
		debugln("height: " + height);

		LevelOrder(root);
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
