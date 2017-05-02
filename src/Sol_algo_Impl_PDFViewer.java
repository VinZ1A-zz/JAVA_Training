import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

//was 202282 with 106 pts
public class Sol_algo_Impl_PDFViewer {

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

		aInput.add("1 3 1 3 1 4 1 3 2 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5");
		aInput.add("abc");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int[] heights = new int[26];
		for (int i = 0; i < 26; i++) {
			heights[i] = scan.nextInt();
		}
		String text = scan.next();
		int[] selHeights = new int[text.length()];
		int asciiForA = (int) 'a';
		for (int i = 0; i < text.length(); i++) {
			selHeights[i] = heights[(int) text.charAt(i) - asciiForA];
		}
		Arrays.sort(selHeights);

		System.out.println(selHeights[text.length() - 1] * text.length());

		scan.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
