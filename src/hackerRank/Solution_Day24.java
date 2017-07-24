package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day24 {

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

	public static Node removeDuplicates(Node head) {

		if (head == null)
			return head;
		Node curNode = head;
		int curInt = head.data;
		while (curNode.next != null) {
			Node nxtNode = curNode.next;
			if (nxtNode != null) {
				int nxtInt = nxtNode.data;
				if (nxtInt == curInt) {
					curNode.next = nxtNode.next; // skip it
				} else {
					curNode = nxtNode;
					curInt = curNode.data;
				}
			}
		}
		return head;
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

	public static void display(Node head) {
		Node start = head;
		while (start != null) {
			System.out.print(start.data + " ");
			start = start.next;
		}
	}

	private static void doIt() {
		Scanner sc = new Scanner(System.in);
		Node head = null;
		int T = sc.nextInt();
		while (T-- > 0) {
			int ele = sc.nextInt();
			head = insert(head, ele);
		}
		head = removeDuplicates(head);
		display(head);
		sc.close();
	}
}
