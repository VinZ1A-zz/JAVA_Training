import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

public class Solution_Day18 {

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

		aInput.add("racecar");

		return aInput;
	}

	private static void doIt() {

		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		scan.close();

		// Convert input String to an array of characters:
		char[] s = input.toCharArray();

		// Create a Solution object:
		SolutionX p = new SolutionX();

		// Enqueue/Push all chars to their respective data structures:
		for (char c : s) {
			p.pushCharacter(c);
			p.enqueueCharacter(c);
		}

		// Pop/Dequeue the chars at the head of both data structures and compare
		// them:
		boolean isPalindrome = true;
		for (int i = 0; i < s.length / 2; i++) {
			if (p.popCharacter() != p.dequeueCharacter()) {
				isPalindrome = false;
				break;
			}
		}

		// Finally, print whether string s is palindrome or not.
		System.out.println("The word, " + input + ", is " + ((!isPalindrome) ? "not a palindrome." : "a palindrome."));

	}
}

// Write your code here.
class SolutionX {

	class Node {
		char data;
		Node next = null;

		Node(char iC) {
			data = iC;
		}
	}

	static class Queue {
		Node _head = null;
		Node _tail = null;
		static Queue _queue = null;

		private Queue() {
		}

		static Queue getQueue() {
			if (_queue == null) {
				_queue = new Queue();
			}
			return _queue;
		}
	}

	public void enqueueCharacter(char c) {
		Queue q = Queue.getQueue();
		Node newNode = new Node(c);
		if (q._head == null) {
			q._head = newNode;
			q._tail = q._head;
		} else {
			newNode.next = q._tail;
			q._tail = newNode;
		}

	}

	public Character dequeueCharacter() {
		// TODO - cant be empty
		Queue q = Queue.getQueue();
		Node head = q._head;
		Node newHead = q._tail;
		while (newHead.next != head) {
			newHead = newHead.next;
		}
		q._head = newHead;
		// System.err.println("returning Q " + head.data);
		return head.data;
	}

	static class Stack {
		Node _top = null;
		Node _bottom = null;
		static Stack _stack = null;

		private Stack() {
		}

		static Stack getStack() {
			if (_stack == null) {
				_stack = new Stack();
			}
			return _stack;
		}
	}

	public void pushCharacter(char c) {
		Stack stk = Stack.getStack();
		Node newNode = new Node(c);

		if (stk._top == null) {
			stk._top = newNode;
			stk._bottom = newNode;
		} else {
			stk._top.next = newNode;
			stk._top = newNode;
		}
	}

	public Character popCharacter() {
		// TODO - cant be empty
		Stack stk = Stack.getStack();
		Node top = stk._top;
		Node newTop = stk._bottom;

		while (newTop.next != top) {
			newTop = newTop.next;
		}

		stk._top = newTop;
		// System.err.println("returning STK " + top.data);
		return top.data;
	}

}