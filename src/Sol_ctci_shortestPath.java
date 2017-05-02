import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class Sol_ctci_shortestPath {

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

		// expects 6 6 -1 (2 & 3 reachable by 1 vertex & 4 unreachable)
		// then -1 6
		// aInput.add("2");
		// aInput.add("4 2");
		// aInput.add("1 2");
		// aInput.add("1 3");
		// aInput.add("1");
		// aInput.add("3 1");
		// aInput.add("2 3");
		// aInput.add("2");

		// expects 6 6 -1 (2 & 3 reachable by 1 vertex & 4 unreachable)
		// aInput.add("1");
		// aInput.add("5 3"); // nodes then links
		// aInput.add("1 2");
		// aInput.add("1 3");
		// aInput.add("3 4");
		// aInput.add("1"); // start

		// expects 6 6 12 12 18 18 24 -1 -1
		// aInput.add("1");
		// aInput.add("10 9"); // nodes then links
		// aInput.add("1 2");
		// aInput.add("1 3");
		// aInput.add("2 5");
		// aInput.add("3 5");
		// aInput.add("3 4");
		// aInput.add("5 7");
		// aInput.add("4 6");
		// aInput.add("7 8");
		// aInput.add("6 8");
		// aInput.add("1"); // start

		// aInput.add("1");
		// aInput.add("8 9"); // nodes then links
		// aInput.add("1 2");
		// aInput.add("2 3");
		// aInput.add("4 3");
		// aInput.add("5 4");
		// aInput.add("8 1");
		// aInput.add("5 8");
		// aInput.add("1 6");
		// aInput.add("7 6");
		// aInput.add("5 7");
		// aInput.add("1"); // start

		// expects 6 -1 -1 -1 -1 -1 12 -1 12
		// aInput.add("1");
		// aInput.add("10 6");// nodes then links
		// aInput.add("3 1");
		// aInput.add("10 1");
		// aInput.add("10 1");
		// aInput.add("3 1");
		// aInput.add("1 8");
		// aInput.add("5 2");
		// aInput.add("3");// start

		// AH! -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1
		// -1 -1 -1 -1 -1 -1 -1
		aInput.add("1");
		aInput.add("30 1");
		aInput.add("9 20");
		aInput.add("8");

		return aInput;
	}

	static class Node {
		int _id;
		int _distFromStart = 0;
		Set<Node> _children = new HashSet<Node>();

		Node(int id) {
			_id = id;
		}
	}

	static final int LENGTHOFLINK = 6;

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int nbQueries = scan.nextInt();
		for (int query = 0; query < nbQueries; query++) {
			int nbNodes = scan.nextInt(); // note: nb links is read when
											// building graph
			Map<Integer, Node> knownNodes = new HashMap<>();
			createGraph(scan, knownNodes);

			int startNodeId = scan.nextInt();
			Node startNode = knownNodes.get(startNodeId); // could be null!

			// ******** POSSIBILITY # 1 ***********
			explore(startNode); // all nodes at once
			showAllNodes(knownNodes, startNodeId, nbNodes);

			// ************** POSSIBILITY # 2 (nah.) ************
			// getDistToAllNodes(startNode, knownNodes, nbNodes);

		}

		scan.close();

	}

	private static void showAllNodes(Map<Integer, Node> knownNodes, int startNodeId, int nbNodes) {
		// DEBUG display
		if (_debug) {
			for (Entry<Integer, Node> elems : knownNodes.entrySet()) {
				debug(elems.getValue()._id + " at dist " + elems.getValue()._distFromStart);
			}
		}
		// OFFICIAL display
		for (int i = 1; i <= nbNodes; i++) {
			if (i == startNodeId)
				continue;
			Node knownNode = knownNodes.get(i);
			if (knownNode != null && knownNode._distFromStart > 0) {
				System.out.print(knownNode._distFromStart * LENGTHOFLINK + " ");
			} else {
				System.out.print("-1 ");
			}
		}
		System.out.println();
	}

	private static void createGraph(Scanner scan, Map<Integer, Node> knownNodes) {
		int nbLinks = scan.nextInt();
		for (int link = 0; link < nbLinks; link++) {
			int linkA = scan.nextInt();
			int linkB = scan.nextInt();
			Node nodeA = knownNodes.get(linkA);
			Node nodeB = knownNodes.get(linkB);
			if (nodeA == null) {
				nodeA = new Node(linkA);
				knownNodes.put(linkA, nodeA);
			}
			if (nodeB == null) {
				nodeB = new Node(linkB);
				knownNodes.put(linkB, nodeB);
			}
			nodeA._children.add(nodeB);
			nodeB._children.add(nodeA);
		}
	}

	// calculate distance for each node individually (not great)
	private static void getDistToAllNodes(Node startNode, Map<Integer, Node> knownNodes, int nbNodes) {
		// calculus is per Node
		for (int i = 1; i <= nbNodes; i++) {
			if (i == startNode._id)
				continue;
			Node knownNode = knownNodes.get(i);
			if (knownNode != null) {
				int dist = getDistToNode(startNode, knownNode._id);
				System.out.print(dist * LENGTHOFLINK + " ");
			} else {
				System.out.print("-1 ");
			}
		}
		System.out.println();
	}

	private static int getDistToNode(Node startNode, int goal) {
		LinkedList<Node> nextToVisit = new LinkedList<Node>();
		HashSet<Integer> visited = new HashSet<Integer>();
		nextToVisit.add(startNode);
		while (!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove();
			if (node._id == goal) {
				return node._distFromStart;
			}
			if (visited.contains(node._id))
				continue; // skip already visited node
			visited.add(node._id);
			for (Node child : node._children) {
				nextToVisit.add(child);
				// add one to previous distance from start
				if (!visited.contains(child._id)) {
					child._distFromStart = node._distFromStart + 1;
				}
			}
		}
		return -1;
	}

	// get all distances in one shot
	private static void explore(Node startNode) {
		if (startNode == null) {
			return; // sneaky case where node is not linked to anything!
		}
		LinkedList<Node> nextToVisit = new LinkedList<Node>();
		HashSet<Integer> visited = new HashSet<>();
		nextToVisit.add(startNode);
		while (!nextToVisit.isEmpty()) {
			Node node = nextToVisit.remove();
			if (visited.contains(node._id)) {
				continue; // skip already visited node
			}
			visited.add(node._id);
			for (Node child : node._children) {
				nextToVisit.add(child);

				if (!visited.contains(child._id)) {
					// debug("N" + node._id + " : add in Child" + child._id + "
					// dist " + (node._distFromStart + 1));
					if (child._distFromStart == 0 || child._distFromStart > node._distFromStart) {
						// add one to previous distance from start if less than
						// existing value
						child._distFromStart = node._distFromStart + 1;
					}
				}
			}
		}
	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
