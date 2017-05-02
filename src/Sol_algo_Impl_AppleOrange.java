import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Vector;

//was 202282 with 106 pts
public class Sol_algo_Impl_AppleOrange {

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

		aInput.add("7 11"); // s, t (house edges)
		aInput.add("5 15"); // a, b (apple, orange tree pos)
		aInput.add("3 2"); // m, n (nb of apples/oranges)
		aInput.add("-2 2 1"); // each apple distance
		aInput.add("5 -6"); // each orange distance

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int s = in.nextInt();
		int t = in.nextInt();
		int a = in.nextInt();
		int b = in.nextInt();
		int m = in.nextInt();
		int n = in.nextInt();
		int[] apple = new int[m];
		int totApples = 0;
		for (int apple_i = 0; apple_i < m; apple_i++) {
			int app = in.nextInt();// apple[apple_i]
			app += a;
			if (app >= s && app <= t)
				totApples++;
		}
		int[] orange = new int[n];
		int totOranges = 0;
		for (int orange_i = 0; orange_i < n; orange_i++) {
			int ora = in.nextInt();// orange[orange_i]
			ora += b;
			if (ora >= s && ora <= t)
				totOranges++;
		}

		System.out.println(totApples);
		System.out.println(totOranges);

		in.close();

	}

	private static void debug(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}
}
