package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day23 {

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

		aInput.add("6");
		aInput.add("3");
		aInput.add("5");
		aInput.add("4");
		aInput.add("7");
		aInput.add("2");
		aInput.add("1");

		return aInput;
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

	private static void doIt() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		Node root = null;
		while (T-- > 0) {
			int data = sc.nextInt();
			root = insert(root, data);
		}
		levelOrder(root);
		sc.close();
	}

	private static void levelOrder(Node root) {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		// while there are nodes to process
		while (!queue.isEmpty()) {
			// dequeue next node
			Node subNode = queue.poll();

			// process tree's root;
			System.out.print(subNode.data + " ");

			// enqueue child elements from next level in order
			if (subNode.left != null) { // tree has non-empty left subtree
				queue.add(subNode.left); // left subtree of t
			}
			if (subNode.right != null) {
				queue.add(subNode.right);
			}
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
}
