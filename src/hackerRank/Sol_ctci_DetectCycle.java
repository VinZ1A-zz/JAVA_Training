package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Sol_ctci_DetectCycle {

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
		aInput.add("1");
		aInput.add("2");
		aInput.add("2");
		aInput.add("3");
		aInput.add("3");
		aInput.add("4");

		return aInput;
	}

	static class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}

	}

	public static void display(Node head) {
		Node start = head;
		while (start != null) {
			System.out.print(start.data + " ");
			start = start.next;
		}
	}

	public static Node insert(Node head, int data) {
		Node p = new Node(data);
		if (head == null)
			head = p;
		else if (head.next == null)
			head.next = p;
		else {
			Node start = head;
			while (start.next != null)
				start = start.next;
			start.next = p;

		}
		return head;
	}

	private static void doIt() {
		Scanner sc = new Scanner(System.in);
		Node head = null;
		int T = sc.nextInt();
		while (T-- > 0) {
			int ele = sc.nextInt();
			head = insert(head, ele);
		}
		// head = removeDuplicates(head);
		System.out.println(hasCycle(head));
		display(head);
		sc.close();

	}

	private static boolean hasCycle(Node head) {

		ArrayList<Node> aNodes = new ArrayList<Node>();
		Node curNode = head;

		while (curNode != null) {
			if (aNodes.contains(curNode)) {
				return true;
			}
			aNodes.add(curNode);
			curNode = curNode.next;
		}

		return false;
	}
}
