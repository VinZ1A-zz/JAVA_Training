package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class OWN_CheckIfBSTIsValid {

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

		// order does not matter except first line
		// as the first line defines the root node (8 here)
		// NO DUPLICATES ALLOWED!!
		aInput.add("8 4 10");
		aInput.add("4 2 6");
		aInput.add("10 na 20");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		Map<Integer, Node> allNodes = new HashMap<>();
		Node root = null;
		while (scan.hasNext()) {
			String str = scan.nextLine();
			String[] lineContent = str.split(" ");
			Integer nodeVal = Integer.valueOf(lineContent[0]);
			Node node = null;
			if (allNodes.containsKey(nodeVal)) {
				node = allNodes.get(nodeVal);
				debugln(nodeVal + " already there");
			} else {
				node = new Node(nodeVal);
				allNodes.put(nodeVal, node);
			}
			if (root == null) { // initialize first node found
				root = node;
			}
			// ****** init and link left and right nodes
			Node leftNode = null, rightNode = null;
			try {
				leftNode = new Node(Integer.valueOf(lineContent[1]));
				if (allNodes.containsKey(leftNode.data)) {
					leftNode = allNodes.get(leftNode.data);
					debugln(rightNode.data + " (L) already there");
				} else {
					allNodes.put(leftNode.data, leftNode);
				}
			} catch (NumberFormatException e) {
			}

			try {
				rightNode = new Node(Integer.valueOf(lineContent[2]));
				if (allNodes.containsKey(rightNode.data)) {
					rightNode = allNodes.get(rightNode.data);
					debugln(rightNode.data + " (R) already there");
				} else {
					allNodes.put(rightNode.data, rightNode);
				}
			} catch (NumberFormatException e) {
			}

			node.left = leftNode;
			node.right = rightNode;

		}

		// Node root = new Node(1);
		List<Integer> content = new ArrayList<>();

		// in-order traversal --> get nbs in order (if it's a BST!)
		traverseTree(root, content);

		debugln(content);

		debugln("this is " + (isSorted(content) ? "" : "NOT ") + "a BST");

		// second technique : recurse on left and right, keeping current
		// max and min (null if not applicable (eg. from root and on the 'side'
		// nodes)
		debugln("this is definitely " + (checkBST(root) ? "" : "NOT ") + "a BST");

		int k = 4;
		int[] smallestKth = new int[2]; // used to return integers
		// idx 0 is used as counter
		// idx 1 is the winning value
		getNthSmallestElement(root, k, smallestKth);
		debugln("smallest " + k + " + th element is " + smallestKth[1]);

		scan.close();

	}

	static void getNthSmallestElement(Node node, int k, int[] beg) {
		int ret = -1;
		if (node != null) {
			getNthSmallestElement(node.left, k, beg);
			beg[0]++;
			debugln(beg[0] + " : " + node.data);
			if (beg[0] == k) {
				debugln("found! " + node.data);
				beg[1] = node.data;
			}

			if (ret == -1) {
				getNthSmallestElement(node.right, k, beg);
			}
		}
	}

	static boolean isSorted(List<Integer> content) {
		int prev = 0;
		for (int i = 0; i < content.size(); i++) {
			if (i == 0) {
				prev = content.get(i);
				continue;
			}
			if (prev > content.get(i))
				return false;
			prev = content.get(i);
		}

		return true;
	}

	static class Node {
		Integer data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Node))
				return false;
			Node oNode = (Node) o;
			return oNode.data.equals(this.data);
		}

		@Override
		public int hashCode() {
			int result = data.hashCode();
			result = 31 * result; // + smthgElse
			return result;
		}

		@Override
		public String toString() {
			return data + " " + (left == null ? "na" : left.data) + " " + (right == null ? "na" : right.data);
		}

	}

	// in-order traversal
	// in a binary tree: returns element IN ORDER
	private static void traverseTree(Node node, List<Integer> content) {
		if (node != null) {
			// traverse left
			traverseTree(node.left, content);
			// this node
			content.add(node.data);
			traverseTree(node.right, content);
		}
	}

	// technique 2
	private static boolean checkBST(Node node) {
		return checkBST(node, null, null);
	}

	private static boolean checkBST(Node node, Integer min, Integer max) {
		if (node == null)
			return true;

		if ((min != null && node.data <= min) || (max != null && node.data > max)) {
			return false;
		}

		if (!checkBST(node.left, min, node.data) || !checkBST(node.right, node.data, max)) {
			return false;
		}

		return true;
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
