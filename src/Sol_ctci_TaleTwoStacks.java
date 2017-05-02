import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

//https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks
public class Sol_ctci_TaleTwoStacks {

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

		aInput.add("10");
		aInput.add("1 42");
		aInput.add("2");
		aInput.add("1 14");
		aInput.add("3");
		aInput.add("1 28");
		aInput.add("3");
		aInput.add("1 60");
		aInput.add("1 78");
		aInput.add("2");
		aInput.add("2");

		return aInput;
	}

	static class MyQueue<T> {

		private Stack<T> inbox = new Stack<T>();
		private Stack<T> outbox = new Stack<T>();

		public void enqueue(T nextInt) {
			inbox.push(nextInt);

		}

		private void fillOutboxWhenEmpty() {
			if (outbox.isEmpty()) {
				while (!inbox.isEmpty()) {
					outbox.push(inbox.pop());
				}
			}
		}

		public T dequeue() {
			fillOutboxWhenEmpty();
			return outbox.pop();

		}

		public T peek() {
			fillOutboxWhenEmpty();
			return outbox.peek();
		}

	}

	private static void doIt() {
		MyQueue<Integer> queue = new MyQueue<Integer>();

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			int operation = scan.nextInt();
			if (operation == 1) { // enqueue
				queue.enqueue(scan.nextInt());
			} else if (operation == 2) { // dequeue
				queue.dequeue();
			} else if (operation == 3) { // print/peek
				System.out.println(queue.peek());
			}
		}
		scan.close();

	}
}
