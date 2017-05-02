package OWN_Exercices;
import java.io.ByteArrayInputStream;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class OWN_Recurs_TowersHanoi {

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

		aInput.add("3");

		return aInput;
	}

	public int singleNumber(final List<Integer> a) {
		Map<Integer, Integer> counter = new HashMap<>();

		for (int i : a) {
			Integer count = counter.get(i);
			if (count == null) {
				counter.put(i, 1);
			} else {
				counter.remove(i);
			}

		}
		return (int) counter.keySet().toArray()[0];
	}

	static public int numSetBits(long a) {

		int ret = 0;
		do {
			ret += a & 1;
			a = a >> 1;
		} while (a > 0);

		return 0;
	}

	static class ListNode {
		public int val;
		public ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode detectCycle(ListNode a) {
		if (a == null)
			return null;
		ListNode beg = a;

		ListNode ptrSlow = a;
		ListNode ptrFast = a;
		while (ptrSlow.next != null && ptrFast.next != null && ptrFast.next.next != null) {
			ptrSlow = ptrSlow.next;
			ptrFast = ptrFast.next.next;
			if (ptrSlow == ptrFast)
				break;
		}
		if (ptrSlow == ptrFast) { // we do have a cycle
			while (ptrSlow != beg) {
				ptrSlow = ptrSlow.next;
				beg = beg.next;
			}
			return ptrSlow;
		}

		return null;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		scan.close();

		print(numSetBits(11));

		Deque<Integer>[] towers = new Deque[3];
		for (int i = 0; i < 3; i++) {
			towers[i] = new LinkedList<>();
		}
		for (int k = 0; k < n; k++) { // stack n elems in first tower
			towers[0].push(n - k);
		}

		debugln("INIT ---");
		// display(towers, 0);

		// solve(towers);

	}

	private static void display(Deque<Integer>[] towers, int depth) {
		int i = 0;
		for (Deque<Integer> tow : towers) {
			for (int dep = 0; dep < depth; dep++) {
				debug("  ");
			}
			debugln("tow #" + i + " : " + tow);
			i++;
		}
	}

	private static void solve(Deque<Integer>[] towers) {
		move(towers, towers[0].size(), 0, 2, 1, 0); // from 0 to 2 using temp=1
	}

	private static void move(Deque<Integer>[] towers, int height, int from, int to, int temp, int depth) {
		// End case
		if (height == 0) {
			return; // nothing to move
		}

		// general case
		// move height-1 disks from FROM to TEMP
		move(towers, height - 1, from, temp, to, depth + 1);
		for (int dep = 0; dep < depth; dep++) {
			debug("  ");
		}
		debugln("1 --- after move from " + from + " to " + to + " (depth " + depth + ")");
		display(towers, depth);
		// move last disk from FROM to TO
		towers[to].push(towers[from].pop());
		for (int dep = 0; dep < depth; dep++) {
			debug("  ");
		}
		debugln("2 --- after move from element " + from + " to " + to + " (depth " + depth + ")");
		display(towers, depth);
		// move height-1 disks from TEMP to TO
		move(towers, height - 1, temp, to, from, depth + 1);
		for (int dep = 0; dep < depth; dep++) {
			debug("  ");
		}
		debugln("3 --- after move from " + temp + "(temp) to " + to + " (depth " + depth + ")");
		display(towers, depth);

	}

	private static void debugln(Object obj) {
		if (_debug) {
			System.err.println(obj.toString());
		}
	}

	private static void debug(Object obj) {
		if (_debug) {
			System.err.print(obj.toString());
		}
	}

	private static void print(Object obj) {
		System.out.print(obj.toString());
	}

	private static void println(Object obj) {
		System.out.println(obj.toString());
	}

}
