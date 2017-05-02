import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

//present as well in Algorithms - DP
//Points: 216.00 Rank: 127330
public class Sol_ctci_DP_CoinChange {

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

		// expects 4
		aInput.add("4 3");
		aInput.add("1 2 3 ");

		return aInput;
	}

	private static void doIt() {
		Scanner scan = new Scanner(System.in);

		int money = scan.nextInt(); // nb of dollars
		int m = scan.nextInt(); // nb of values
		int coins[] = new int[m];
		for (int coins_i = 0; coins_i < m; coins_i++) {
			coins[coins_i] = scan.nextInt();
		}
		scan.close();

		Arrays.sort(coins); // must sort coins first
		System.out.println(getCombinations(coins, money));

	}

	public static long getCombinations(int[] coins, int money) {
		int combsSize = money + 1;
		long[] combs = new long[combsSize];
		combs[0] = (long) 1; // n == 0 case.
		// get combinations by adding one coin at a time
		for (int coinRef = 0; coinRef < coins.length; coinRef++) { // dual loop
																	// is
																	// O(MxN)
			int coin = coins[coinRef];
			// System.err.println("coin = " + coins[i]);
			// get the nb of combinations for each total starting from smallest
			// possible (coin value)
			for (int amount = coin; amount < combsSize; amount++) {
				// idea: nb of combinations using "up to this coin" is:
				// - nb of combinations using all previous coins only
				// - AND nb of combinations using only last coin (which is also
				// the nb of combinations to get [amount - coin] using all
				// coins)

				// System.err.print("DP @ j=" + j + " = DP[j] (" + combs[j] + ")
				// + DP[j-coin(" + (j - coin) + ")] ("
				// + combs[j - coin] + ")");
				combs[amount] = combs[amount] + combs[amount - coin];
				// System.err.println(" = " + combs[amount]);
			}
		}
		return combs[money];
	}
}
