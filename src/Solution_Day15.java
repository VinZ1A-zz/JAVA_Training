import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day15 {

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

		aInput.add("4");
		aInput.add("2");
		aInput.add("3");
		aInput.add("4");
		aInput.add("1");

		return aInput;
	}

	static class Solution {

		static class Node {
			int data;
			Node next;

			Node(int d) {
				data = d;
				next = null;
			}
		}

		// ----- added code ------------

		public static Node insert(Node head, int data) {
			Node newNode = new Node(data);
			if (head == null) {
				return newNode;
			}

			Node tail = head;
			while (tail.next != null) {
				tail = tail.next;
			}

			tail.next = newNode;
			return head;
		}

		// ----- end added code ------------

		public static void display(Node head) {
			Node start = head;
			while (start != null) {
				System.out.print(start.data + " ");
				start = start.next;
			}
		}

		public static void main(String args[]) {
			Scanner sc = new Scanner(System.in);
			Node head = null;
			int N = sc.nextInt();

			while (N-- > 0) {
				int ele = sc.nextInt();
				head = insert(head, ele);
			}
			display(head);
			sc.close();
		}
	}

	private static void doIt() {
		Solution.main(null);
	}
}
