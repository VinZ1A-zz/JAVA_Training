import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_DataStruct_Trees_SwapNodes {

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
		aInput.add("11"); // n = nb nodes
		aInput.add("2 3"); // linked to node 1
		aInput.add("4 -1"); // linked to node 2
		aInput.add("5 -1"); // linked 3
		aInput.add("6 -1"); // 4
		aInput.add("7 8");// 5
		aInput.add("-1 9");// 6
		aInput.add("-1 -1");// 7
		aInput.add("10 11");// 8
		aInput.add("-1 -1");// 9
		aInput.add("-1 -1");// 10
		aInput.add("-1 -1");// 11
		aInput.add("2"); // t = nb of swaps
		aInput.add("2"); // k=2 means h=2 and 4 (6,8...)
		aInput.add("4"); // k=2 means h=4

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

	// for any binary tree
	public static void insert(int parentIdx, int dataLeft, int dataRight, Map<Integer, Node> iMap) {
		Node parent = iMap.get(parentIdx);
		if (parent == null)
			return;
		if (dataLeft != -1) {
			Node newNode = new Node(dataLeft);
			iMap.put(dataLeft, newNode);
			parent.left = newNode;
		}
		if (dataRight != -1) {
			Node newNode = new Node(dataRight);
			iMap.put(dataRight, newNode);
			parent.right = newNode;
		}
	}

	public static Node insertInBinarySearch(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (data <= root.data) {
				cur = insertInBinarySearch(root.left, data);
				root.left = cur;
				// debugln("inserting " + data + " on left of " + root.data);
			} else {
				cur = insertInBinarySearch(root.right, data);
				root.right = cur;
				// debugln("inserting " + data + " on right of " + root.data);
			}
			return root;
		}
	}

	static int height(Node root, Map<Integer, ArrayList<Node>> nodesPerLevel) {
		int depth = getMax(root, 1, nodesPerLevel);
		return depth;
	}

	static int getMax(Node node, int depth, Map<Integer, ArrayList<Node>> nodesPerLevel) {
		int max = 0;
		if (node != null) {
			max = Math.max(depth, getMax(node.left, depth + 1, nodesPerLevel));
			// do smthg (in-order trasversal)
			// debug(node.data + ",");
			max = Math.max(max, getMax(node.right, depth + 1, nodesPerLevel));
			// debugln("at node " + node.data + ", depth:" + depth);
			ArrayList<Node> nodesAtThatLev = nodesPerLevel.get(depth);
			if (nodesAtThatLev == null) {
				nodesPerLevel.put(depth, new ArrayList<Node>());
			}
			nodesPerLevel.get(depth).add(node);
		}
		return max;
	}

	static void inOrderTraversal(Node node) {
		if (node != null) {
			inOrderTraversal(node.left);
			// do smthg (in-order trasversal)
			print(node.data + " ");
			inOrderTraversal(node.right);
		}
	}

	static void LevelOrder(Node root) {
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(root);
		// debugln("start with " + root.data);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.err.print(cur.data + " ");
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
		int n = scan.nextInt(); // nb of Nodes
		Node root = new Node(1); // starts with 1
		Map<Integer, Node> aMap = new HashMap<>();
		aMap.put(1, root); // seed map
		for (int i = 1; i <= n; i++) {
			int dataLeft = scan.nextInt();
			int dataRight = scan.nextInt();
			insert(i, dataLeft, dataRight, aMap);
		}

		Map<Integer, ArrayList<Node>> nodesPerLevel = new HashMap<>();
		int nbOfLevels = height(root, nodesPerLevel);
		debugln("height: " + nbOfLevels);

		// ********* DEBUG ***********
		// for (Entry<Integer, ArrayList<Node>> a : nodesPerLevel.entrySet()) {
		// debug("at lev " + a.getKey() + ":");
		// for (Node node : a.getValue()) {
		// debug(node.data + ",");
		// }
		// debugln("");
		// }

		int t = scan.nextInt(); // nb of swaps
		for (int i = 1; i <= t; i++) {
			int k = scan.nextInt(); // swap all subtrees of nodes who are at
															// K,2K,3K...

			for (int swapLev = k; swapLev < nbOfLevels; swapLev += k) {
				// debugln("let's swap with h=" + swapLev);
				ArrayList<Node> parentNodes = nodesPerLevel.get(swapLev);
				for (Node node : parentNodes) {
					Node temp = node.left;
					node.left = node.right;
					node.right = temp;
				}
			}

			// result
			inOrderTraversal(root);
			println("");
		}

		scan.close();

		// LevelOrder(root);
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
