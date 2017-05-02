package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class OWN_FlipBinaryTree {

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

		aInput.add("5");
		aInput.add("7 3");
		aInput.add("7 1");
		aInput.add("5 7");
		aInput.add("5 2");

		return aInput;
	}

	static class Node {
		int id;
		Node left;
		Node right;

		Node(int id) {
			this.id = id;
		}
	}

	static class MonoNode {
		int id;
		MonoNode child;

		MonoNode(int id) {
			this.id = id;
		}
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int rootId = scan.nextInt();
		Map<Integer, Node> idToNode = new HashMap<>();
		while (scan.hasNext()) {
			int from = scan.nextInt();
			int to = scan.nextInt();
			Node fromNode = idToNode.get(from);
			Node toNode = idToNode.get(to);
			if (fromNode == null) {
				fromNode = new Node(from);
				idToNode.put(from, fromNode);
			}
			if (toNode == null) {
				toNode = new Node(to);
				idToNode.put(to, toNode);
			}
			if (fromNode.left == null) {
				fromNode.left = toNode;
			} else {
				fromNode.right = toNode;
			}
		}

		List<MonoNode> flippedTree = new ArrayList<>();
		flippedTree = flipTree(idToNode.get(rootId), null);

		for (MonoNode node : flippedTree) {
			debugln("id " + node.id + (node.child != null ? (" --> id " + node.child.id) : ""));
		}

		scan.close();

	}

	private static List<MonoNode> flipTree(Node root, MonoNode prev) {
		// end point
		if (root == null)
			return new ArrayList<MonoNode>();
		// usual case - in-order traversal
		debugln("creating MonoNode " + root.id + " with previous " + (prev != null ? prev.id : "N/A"));
		MonoNode node = new MonoNode(root.id);
		if (prev != null) {
			node.child = prev;
		}
		List<MonoNode> leftElems = flipTree(root.left, node);
		List<MonoNode> rightElems = flipTree(root.right, node);
		leftElems.add(node);
		leftElems.addAll(rightElems); // combine them
		return leftElems;
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
