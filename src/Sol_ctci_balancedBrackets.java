import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Sol_ctci_balancedBrackets {

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

		aInput.add("3");
		aInput.add("{[()]}"); // YES
		aInput.add("{[(])}"); // NO
		aInput.add("{(){[[(())]]}}"); // YES

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int a0 = 0; a0 < t; a0++) {
			String expression = in.next();
			System.out.println((isBalanced(expression)) ? "YES" : "NO");
		}
		in.close();

	}

	private static boolean isBalanced(String expression) {

		if (expression == null || expression.length() % 2 != 0)
			return false;

		char[] charArray = expression.toCharArray();
		Stack<Character> charStk = new Stack<>();

		for (char aChar : charArray) {
			switch (aChar) {

			case '(':
				charStk.push(')');
				break;
			case '[':
				charStk.push(']');
				break;
			case '{':
				charStk.push('}');
				break;

			default:
				if (charStk.empty() || aChar != charStk.pop())
					return false;
				// charStk.pop();
			}
		}

		return charStk.empty();

	}

	// not understanding the problem ! :(
	private static boolean isBalanced2(String expression) {

		if (expression == null)
			return false;

		char[] charArray = expression.toCharArray();

		// length can only be odd
		if (charArray.length == 0 || charArray.length % 2 != 0)
			return false;
		// second half must be closed chars
		// if (isOpen(charArray[charArray.length / 2 + 1])) {
		// return false;
		// }

		int startOfClosure = charArray.length / 2;
		// int startOfClosure = 0;
		// for (char aChar : charArray) {
		// if (isOpen(aChar)) {
		// startOfClosure++;
		// System.err.println(aChar + " open");
		// } else
		// break;
		// }

		for (int i = startOfClosure; i < charArray.length; i++) {
			System.err.println("comparing " + charArray[i] + " (closed?) with "
					+ charArray[startOfClosure - (i - startOfClosure + 1)]);
			if (!areOpposed(charArray[i], charArray[startOfClosure - (i - startOfClosure + 1)])) {
				return false;
			}
		}

		return true;
	}

	private static boolean areOpposed(char c, char d) {
		return ((c == ']' && d == '[') || (c == '}' && d == '{') || (c == ')' && d == '('));

	}

	private static boolean isOpen(char iChar) {
		return (iChar == '[' || iChar == '{' || iChar == '(');
	}
}
