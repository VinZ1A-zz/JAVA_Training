package OWN_Exercices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DRAFT2 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// reverse bits of 32 bits integer
		{
			// debugln("reverse : " + reverse(4294967295L));
		}

		// Compare two version numbers
		{
			// debugln("comp : " + compareVersion("1.0.3", "1.000.3.00000"));
		}

		// IP address
		{
			// ArrayList<String> poss = restoreIpAddresses("25525511135");
			// expects 0.10.0.100 0.100.10.0
			// ArrayList<String> poss = restoreIpAddresses("0100100");
			// debugln("poss : " + poss);
		}

		// zigzag text
		{
			// debugln("zigzag text : " + convert("PAYPALISHIRING", 3));
			// debugln("zigzag text : " + convert("B", 1));
		}

		// length of last word
		{
			// debugln("length of last word : " + lengthOfLastWord(" "));
		}

		// remove duplicates from sorted array
		{
			ArrayList<Integer> arr = new ArrayList(Arrays.asList(1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3));
			// ArrayList<Integer> arr = new ArrayList(Arrays.asList(0, 0, 0, 1,
			// 1, 1, 1, 1, 2, 3, 4, 5, 5, 5));
			//
			int newsize = removeDuplicates(arr);
			debugln("remove dups : " + newsize);
			debugln("new arr without duplicates " + arr.subList(0, newsize));
		}

		// largest area
		{

			// ArrayList<Integer> arr = new ArrayList(Arrays.asList(1, 5, 4,
			// 3));

			// expects 6962572
			ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(3273, 4325, 7503, 3709, 9257, 6554, 5467,
					1052, 6396, 74, 2097, 3640, 720, 9016, 9862, 7735, 2743, 4050, 9353, 5451, 1346, 6622, 6923, 1067,
					4076, 4208, 4639, 9415, 4784, 799, 9391, 1806, 924, 1737, 1000, 3097, 8580, 3321, 3529, 7705, 8852,
					2440, 3184, 9171, 6451, 4474, 3150, 3387, 5897, 5238, 1272, 6012, 762, 7902, 4941, 7633, 926, 2767,
					3, 9320, 1279, 7819, 2510, 8137, 742, 1734, 8829, 6174, 7041, 2506, 3710, 6649, 2306, 6147, 5418,
					147, 6021, 6415, 8679, 9450, 2166, 615, 1740, 3444, 7465, 299, 4742, 8771, 7068, 9288, 548, 2248,
					6757, 4410, 2822, 7126, 8836, 8088, 8855, 6985, 4759, 8971, 6231, 1354, 3307, 1856, 5317, 5352,
					7512, 5213, 244, 5312, 2321, 5755, 7902, 5140, 2861, 1022, 97, 5530, 2464, 9248, 1, 8455, 2264,
					8185, 9405, 6535, 8214, 4548, 8255, 492, 1305, 2738, 78, 3511, 4718, 1137, 1691, 5071, 1462, 4190,
					2119, 7903, 9629, 2842, 7236, 7732, 2440, 3813, 4776, 5554, 7434, 7376, 666, 4347, 6159, 1229, 6615,
					4078, 9701, 1714, 4532, 2953, 9571, 2908, 4821, 743, 3181, 6638, 2874, 9646, 2827, 7446, 6797, 7443,
					1520, 5373, 2471, 1803, 9284, 9748, 1926, 7633, 3535, 1044, 8660, 9031, 3698, 803, 1356, 4758, 7779,
					7648, 4666, 8264, 4302, 8315, 141, 5225, 8547, 1428, 3706, 7574, 9659, 6173, 8292, 7440, 9534, 6588,
					7535, 5345, 3302, 2241, 7402, 6450, 6469, 4456, 9960, 6277, 3634, 9369, 3383, 8139, 3589, 1154, 678,
					5536, 1635, 4424, 639, 9552, 3340, 3616, 3353, 1693, 1691, 3846, 2859, 8197, 4916, 199, 6444, 9454,
					2360, 9213, 4586, 8371, 2765, 3829, 3839, 6498, 3227, 5666, 8311, 6180, 8330, 1302, 4504, 9321, 691,
					3038, 3489, 934, 6068, 981, 1283, 2468, 5860, 6331, 1924, 9375, 5796, 9572, 4109, 1204, 9443, 8961,
					5384, 7912, 4811, 2058, 3515, 184, 9258, 9613, 6821, 4087, 6000, 462, 2796, 3903, 6689, 4048, 4447,
					589, 7527, 6685, 1617, 6785, 3607, 9018, 8063, 7891, 5361, 30, 8007, 9819, 1055, 6227, 5313, 5351,
					7275, 8850, 9731, 7113, 8830, 290, 5271, 3940, 7980, 4028, 986, 6448, 8236, 6152, 9353, 8092, 938,
					2680, 7973, 7525, 7952, 1004, 1510, 7060, 9983, 6400, 1712, 3791, 7595, 6391, 6062, 5815, 7934,
					1168, 6897, 2736, 4161, 4536, 4024, 538, 8725, 8031, 483, 8037, 5791, 9084, 9496, 1887, 665, 8409,
					2745, 7393, 8544, 8837, 4307, 5340, 4418, 5598, 2747, 5253, 1545, 2993, 7263, 895, 7522, 8284, 8915,
					8065, 6696, 3629, 9093, 4867, 40, 4898, 918, 9579, 4198, 5545, 534, 8777, 4343, 981, 4160, 4542,
					2586, 2014, 5666, 4371, 5967, 9316, 3940, 8084, 7375, 1275, 3171, 9775, 3171, 7889, 2061, 251, 8610,
					4357, 6166, 2670, 4608, 3302, 7597, 3895, 5333, 1311, 9629, 2748, 6831, 694, 9996, 6611, 7742, 8912,
					30, 5673, 1027, 4946, 9017, 4455, 7050, 8163, 1057, 6548, 685, 9311, 8839, 8356, 1824, 1418, 3784,
					2237, 3553, 4498, 90, 9671, 5160, 5357, 3999, 3175, 4321, 21, 2473, 4835, 7026, 3451, 5489, 2494,
					4906, 9930, 766, 4907, 1741, 7053, 8739, 8540, 4537, 3374, 7361, 7055, 5461, 5199, 6477, 5600, 8487,
					1149, 962, 4807, 714, 7426, 1347, 1402, 5737, 7281, 5920, 2739, 7896, 2306, 3575, 5220, 9491, 467,
					7163, 6702, 587, 1901, 4967, 3799, 3460, 1044, 6317, 4876, 5889, 5978, 6636, 339, 8888, 1129, 4089,
					7987, 6029, 9899, 5922, 9963, 1707, 9030, 6531, 6466, 6340, 1561, 3910, 1867, 8516, 8411, 2722, 848,
					9811, 8402, 4065, 1302, 7213, 4045, 8923, 7608, 126, 4673, 8610, 74, 5861, 3879, 3160, 7772, 2344,
					4981, 2419, 8528, 6263, 8702, 1527, 533, 2272, 7270, 6590, 4376, 748, 2962, 7208, 9646, 9004, 3224,
					5792, 8898, 7578, 2101, 8122, 853, 922, 3128, 8988, 1467, 7247, 5633, 4166, 1126, 8467, 9755, 2816,
					9043, 6125, 6631, 6030, 1249, 8217, 9134, 921, 4252, 796, 8535, 8863, 7810, 309, 6263, 5443, 4611,
					7211, 1560, 6453, 8032, 4941, 6127, 6614, 5857, 6730, 1862, 9754, 8773, 4173, 4503, 9044, 9327,
					6848, 7385, 217, 5478, 2372, 3940, 3215, 2305, 5002, 6333, 8750, 4854, 14, 3308, 4303, 8757, 80,
					4595, 5791, 4994, 7552, 3226, 3631, 9980, 7531, 2111, 726, 1013, 6085, 5710, 7582, 4281, 4040, 8145,
					1940, 3596, 1605, 960, 9867, 5283, 650, 8342, 7101, 3485, 8767, 689, 9487, 8651, 6590, 3470, 7978,
					3460, 7322, 6633, 2506, 4392, 8105, 6091, 8037, 1653, 4442, 8865, 9015, 6763, 318, 7482, 7468, 1738,
					6131, 9188, 3355, 8953, 747, 269, 5361, 3191, 3719, 4752, 3623, 2722, 9381, 5210, 2791, 8291, 7962,
					7925, 1482, 3775, 7067, 9518, 4253, 1626, 1907, 1411, 4092, 557, 8931, 8175, 6613, 6443, 1214, 6307,
					1534, 8496, 9916, 8833, 6969, 9152, 8575, 6317, 4391, 8463, 5460, 6430, 2362, 1282, 6718, 254, 3940,
					7719, 8612, 8945, 2313, 2137, 8405, 7101, 488, 6968, 1932, 5383, 4569, 5258, 112, 4817, 7005, 3283,
					1300, 7258, 304, 5582, 2156, 2538, 5396));

			// debugln("max area : " + maxArea(arr));
		}

		// longest common substring(s)
		// could be a list?
		{
			// expects Geeks
			String[] strs = new String[] {};
			// Set<String> subStrs = getLongestSubstring_OnlyTwoStrings(strs);
			// // nah.
			Set<String> subStrs = getLongestSubstrings(strs);
			debugln("longest common substring(s) " + subStrs);

			// other test scnearios :
			// "abab", "babai", "dbabx", "zbay" -- [ba]
			// "axoxobab", "bxoabai", "dbabxo", "zxobay" --> [xo, ba]
			// "GeeksforGeeks", "GeeksQuiz"
			// "abcdxyz", "xyzabcd" --> abcd
			// "abcxyz", "xyzabc" --> [abc, xyz]
			// "zxabcdezy" "yzabcdezx" --> abcdez
			// "GeeksQorGeeks", "GeeksQuiz", "JustGeekThatsAll" --> [Geek]
			// "GeeksQorAllGeeks", "GeeksQuizAll", "JustGeeqThatsAll" --> [All,
			// Gee]

		}

		// longest substring without repeating chars
		// Given "abcabcbb", the answer is "abc", which the length is 3.
		//
		// Given "bbbbb", the answer is "b", with the length of 1.
		//
		// Given "pwwkew", the answer is "wke", with the length of 3. Note that
		// the answer must be a substring, "pwke" is a subsequence and not a
		// substring.
		// "ABDEFGABEF" are ABDEFG and DEFGAB
		// could be a list
		{
			// String str = "bbbbb";
			// Set<String> subStrs = getLongestSubstringsInString(str);
			// debugln("longest substring(s) in string " + subStrs);
		}

		// TODO
		// number of countries. This one could be tackled by dfs ?
		// In a 2D array of integers, adjacent (vertically or horizontally)
		// positions in the array are considered part of the same "country" if
		// they contain the same integer. Given a 2D array of integers, compute
		// the number of distinct countries in the array.
		if (false) {
			// Reduce the problem to a undirected graph G=(V,E) where V=all
			// cells of the array and E=two adjacent countries with same colors,
			// all is left to run algorithm to find number of connected
			// components in that graph (simple DFS)

			// List<List<Character>> grid = new ArrayList<>();
			int nbRows = 10;
			int nbCols = 12;
			int[][] grid = new int[nbRows][nbCols]; // ROW COL

			List<String> gridStr = new ArrayList<>();
			gridStr.add("...1.2.11...");
			gridStr.add("..2112.1....");
			gridStr.add("..221111....");
			gridStr.add("............");
			gridStr.add("55.........1");
			gridStr.add("............");
			gridStr.add(".....333....");
			gridStr.add(".....343....");
			gridStr.add(".444434.....");
			gridStr.add("...33333....");

			init(grid, gridStr);
			disp(grid, null);

			boolean[][] isScanned = new boolean[nbRows][nbCols];

			int nbOfCountries = 0;
			for (int row = 0; row < nbRows; row++) {
				for (int col = 0; col < nbCols; col++) {
					if (grid[row][col] != 0 && !isScanned[row][col]) {
						debugln("determine country from pt " + row + "," + col);
						runDFS(grid, row, col, grid[row][col], isScanned);
						nbOfCountries++;
						disp(grid, isScanned);
					}
				}
			}

			debugln("nb of countries " + nbOfCountries);

		}

		// TODO
		// You are given 3 arrays A, B and C. All 3 of the arrays are sorted.
		// Find i, j, k such that :
		// max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is
		// minimized.
		// Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] -
		// A[i]))
		if (false) {
			Integer[] a = { 1, 4, 10 };
			Integer[] b = { 2, 15, 20 };
			Integer[] c = { 10, 12 };

			class ARRAY3PTR {
				// - 1: invalid call
				public int minimize(final List<Integer> a, final List<Integer> b, final List<Integer> c) {
					int aSiz = a.size() - 1;
					int bSiz = b.size() - 1;
					int cSiz = c.size() - 1;

					if (aSiz == -1 || bSiz == -1 || cSiz == -1)
						return -1;

					int i = 0, j = 0, k = 0;

					int minInt = Integer.MAX_VALUE;
					do {
						int curA = a.get(i);
						int curB = b.get(j);
						int curC = c.get(k);

						int min = Math.min(Math.min(curA, curB), curC);
						int max = Math.max(Math.max(curA, curB), curC); // needed?
						debugln("min " + min);
						debugln("max " + max);

						// define interval
						int curInt = Math.max(Math.max(Math.abs(curA - curB), Math.abs(curB - curC)),
								Math.abs(curC - curA));
						debugln("curInt " + curInt);
						if (curInt < minInt) {
							minInt = curInt;
							debugln("min Interval so far " + minInt);
						}

						if (minInt == 0) {
							break; // can't find smaller one ;)
						}

						// move cur min up if possible
						if (curA == min && i < aSiz) {
							i++;
							debugln("move i to " + i + " at val " + a.get(i));
						} else if (curB == min && j < bSiz) {
							j++;
							debugln("move j to " + j + " at val " + b.get(j));
						} else if (curC == min && k < cSiz) {
							k++;
							debugln("move k to " + k + " at val " + c.get(k));
						} else {
							break; // reached end of all arrays
						}

						// stop as soon as we hit the end of at least one array
						// which is not the maximum
						int nbOfEndsReached = 0;
						if (i == aSiz && curA < max && max > curA + minInt) {
							nbOfEndsReached++;
						}
						if (j == bSiz && curB < max && max > curB + minInt) {
							nbOfEndsReached++;
						}
						if (k == cSiz && curC < max && max > curC + minInt) {
							nbOfEndsReached++;
						}

						if (nbOfEndsReached == 2)
							break;

					} while (true);

					return minInt;
				}
			}

			int res = new ARRAY3PTR().minimize(Arrays.asList(a), Arrays.asList(b), Arrays.asList(c));
			debugln("res " + res);
		}

		// TODO
		// From a binary tree, return all nodes which are visible (value greater
		// than that of all its ancestors.)
		{
			class Solution {

			}
		}

		// TODO
		// Find if two words are anagrams of each other
		{

		}

		// TODO
		// elevator algorithm - capacity of an elevator
		// People are waiting for a elevator in a hotel. A elevator has limited
		// capacity and Hotel has floor 0 to M. Elevator has a limit of X ppl
		// and weight of Y. There are N ppl standing at ground floor in a queue.
		// So there are two arrays A[N] B[N]. A is weight of each person and B
		// is the floor the person wants to go. The elector goes up and stops at
		// each selected level and comes Back to the ground floor. Write a
		// solution of how many times the elevator will stop.
		{

		}

		// TODO
		// valid password algo ?
		{

		}

		// others?
		// https://www.glassdoor.com.au/Interview/IMC-Financial-Markets-Software-Engineer-Interview-Questions-EI_IE278100.0,21_KO22,39.htm
		// https://www.glassdoor.com.au/Interview/IMC-Financial-Markets-Software-Development-Engineer-Interview-Questions-EI_IE278100.0,21_KO22,51.htm
		// https://www.glassdoor.com.au/Interview/IMC-Financial-Markets-Interview-Questions-E278100.htm?filter.jobTitleExact=IT+Developer%2FEngineer+Graduate
		// https://www.glassdoor.com.au/Interview/IMC-Financial-Markets-Graduate-Software-Developer-Interview-Questions-EI_IE278100.0,21_KO22,49.htm

		// TODO
		// reviser /implementer design patterns
		// creational, structural, behavioral
		// xxxx
		// decorator...

		// TODO
		// create REST API

		// TODO
		// Atlassian tests ? Find on glassdoor

		// - Convert a string into an integer without using the inbuilt
		// frameworks.
		// - Given a binary encoded string, find the range of all consecutive
		// 1's and use them to draw an arc.
		// - Given an array of numbers write a method that moves non-zero
		// elements to the front of the array and returns the count of non-zero
		// elements.
		// - Draw up and implement a linked list interface that prints out a
		// description recursively.�

		// Given a string of numbers, ie, 11, count the number of permutations
		// of this string if we were to the digits as array indexes of the
		// alphabet. Assume the A = 1.
		// perms('1') = 1 ('a')
		// perms('11') = 2 ('aa', 'j')

		// TODO
		// flatten tree with up to three nodes ("Tri-Tree" ?)

		// REST API example (server & client) - difference with SOAP?
		// XXXXXX

		// TODO
		// detect sumtree

		// TODO
		// find number in rotated sorted array
		{
			int[] arr = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11 };

			class FINDNB {
				int find(int[] arr, int i) {
					return find(arr, i, 0, arr.length - 1);
				}

				int find(int[] arr, int i, int beg, int end) {
					debugln("beg: " + beg + " ; end: " + end);
					if (end < beg)
						return -1;
					if (arr[beg] == i)
						return beg;
					if (arr[end] == i)
						return end;
					int mid = (beg + end) / 2;
					if (arr[mid] == i)
						return mid;

					// if array is sorted: normal binary search
					if (arr[beg] < arr[end]) {
						// wrong range?
						if (arr[beg] > i || arr[end] < i)
							return -1;
						// eg. search only within the right half
						if (mid > i) { // search in lower half
							return find(arr, i, beg, mid - 1);
						} else { // search in upper half
							return find(arr, i, mid + 1, end);
						}
					} else { // search both halves
						int res = find(arr, i, beg, mid - 1);
						if (res == -1) {
							return find(arr, i, mid + 1, end);
						} else
							return res;
					}
				}
			}

			int res = new FINDNB().find(arr, 9);
			debugln("found at index " + res);

		}

	}

	static class Date implements Comparable<Date> {
		int day;
		int month;
		int year;

		// year first, then month, then day
		@Override
		public int compareTo(Date o) {
			if (this.year > o.year)
				return 1;
			else if (this.year < o.year)
				return -1;
			else if (this.month > o.month)
				return 1;
			else if (this.month < o.month)
				return -1;
			else if (this.day > o.day)
				return 1;
			else if (this.day < o.day)
				return -1;
			return 0;
		}

		Date() {
		}

		Date(Date d) {
			this.day = d.day;
			this.month = d.month;
			this.year = d.year;
		}
	}

	// returns 0 : exactly one month appart
	// 1 : more than 1 month
	// -1 : less than 1 month
	// ! not assuming d1 < d2
	public static int monthDiff(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			throw new UnsupportedOperationException("At least one date is null");

		// ensure d2 > d1
		if (d1.compareTo(d2) > 0) {
			Date temp = new Date(d2);
			d2 = new Date(d1);
			d1 = new Date(temp);
		}

		if (d1.year == d2.year) {
			if (d1.month == d2.month) {
				return -1;
			} else { // different months
				if (d2.month - d1.month == 1) {
					if (d1.day == d2.day) {
						return 0;
					}
					// case 12MAR and 23FEB (note: we always have d2 >= d1)
					if (d2.day < d1.day) {
						return -1;
					}
				}
			}
		} else if (d2.year - d1.year == 1) { // only one year diff
			// year diff is = 1
			if (d2.month == 1 && d1.month == 12) {
				if (d2.day == d1.day) {
					return 0;
				}
				if (d2.day < d1.day) {
					return -1;
				}
			}
		}

		return 1; // all other cases: more than 1 month diff
	}

	static private int[][] dirs = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	static private void runDFS(int[][] grid, int row, int col, int color, boolean[][] isScanned) {
		if (!isValid(row, col, grid) || isScanned[row][col] || grid[row][col] != color)
			return;
		isScanned[row][col] = true;
		for (int[] dir : dirs) {
			runDFS(grid, row + dir[0], col + dir[1], color, isScanned);
		}
	}

	static private boolean isValid(int row, int col, int[][] grid) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
			return false;
		}
		return true;
	}

	static public void init(int[][] grid, List<String> strs) {
		int row = 0;
		for (String str : strs) {
			int col = 0;
			for (char c : str.toCharArray()) {
				if (c == '.') {
					grid[row][col] = 0;
				} else {
					grid[row][col] = Integer.parseInt(Character.toString(c));
				}

				col++;
			}
			row++;
		}
	}

	static public void disp(int[][] grid, boolean[][] isScanned) {
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				int val = grid[row][col];
				if (val == 0) {
					debug(".");
				} else {
					if (isScanned == null || !isScanned[row][col]) {
						debug(val);
					} else {
						debug("X");
					}
				}
			}
			debugln("");
		}
	}

	// pwwkew --> wke
	// abcabcbb --> abc
	// "ABDEFGABEF" --> BDEFGA and DEFGAB ---> NO !!
	static Set<String> getLongestSubstringsInString(String str) {
		Set<String> subStrs = new HashSet<>();

		Map<Character, Integer> counter = new HashMap<>();
		int beg = 0;
		int idx = 0;
		int maxLength = 0;
		while (idx < str.length()) {
			char c = str.charAt(idx);
			Integer count = counter.get(c);
			if (count == null) {
				counter.put(c, idx);
			} else { // reset
				beg = counter.get(c) + 1;
				counter.put(c, idx);
			}
			idx++;
			if (idx - beg > maxLength) {
				maxLength = idx - beg;
				subStrs.clear();
				debugln("new maxLength = from " + beg + " to " + (idx - 1));
			}
			if (idx - beg == maxLength) {
				subStrs.add(str.substring(beg, idx));
				debugln("subStrs = " + subStrs);
			}
		}

		return subStrs;
	}

	static class LetterNode {
		char _val = Character.UNASSIGNED;
		Set<Integer> _wordNbs = new HashSet<>();
		Map<Character, LetterNode> _children = new HashMap<>();
		LetterNode _parent;

		LetterNode(char val, int wordNb, LetterNode parent) {
			_val = val;
			_wordNbs.add(wordNb);
			_parent = parent;
		}

		@Override
		public String toString() {
			return new String(_val + "(" + _wordNbs + ")");

		}
	}

	// use suffix tree instead :
	// https://en.wikipedia.org/wiki/Generalized_suffix_tree
	public static Set<String> getLongestSubstrings(String[] strs) {
		Set<String> substrings = new HashSet<>();

		if (strs == null || strs.length == 0) {
			return substrings;
		}
		if (strs.length == 1) {
			substrings.add(strs[0]);
			return substrings;
		}

		// building tree
		Map<Character, LetterNode> letterToRoot = new HashMap<>();
		int wordNb = 0;
		int maxDepth = 0;
		List<LetterNode> endOfLongestSubString = new ArrayList<>();
		for (String str : strs) {
			wordNb++;
			for (int beg = 0; beg < str.length(); beg++) {
				char begC = str.charAt(beg);
				// debug("beg : " + begC + " ");
				LetterNode begRoot = letterToRoot.get(begC);
				if (begRoot == null) {
					begRoot = new LetterNode(begC, wordNb, null);
					letterToRoot.put(begC, begRoot);
				}
				begRoot._wordNbs.add(wordNb);
				LetterNode prev = begRoot;
				int curDepth = 0;
				LetterNode curBestEnd = null;
				for (int idx = beg + 1; idx < str.length(); idx++) {
					char c = str.charAt(idx);
					curDepth++;
					// attempt to retrieve children C
					LetterNode childNode = prev._children.get(c);
					if (childNode == null) {
						childNode = new LetterNode(c, wordNb, prev);
						prev._children.put(c, childNode);
					} else {
						childNode._wordNbs.add(wordNb);
						// letter is used by all strings
						if (childNode._wordNbs.size() == strs.length) {
							if (curDepth > maxDepth) {
								maxDepth = curDepth;
								endOfLongestSubString.clear();
							}
							if (curDepth == maxDepth) {
								endOfLongestSubString.add(childNode);
							}
						}
					}
					prev = childNode;
					// debug(c);
				}
				// debugln("# with endOfLongestSubString " +
				// endOfLongestSubString);
			}
			// debugln("-");
		}

		// DEBUG TEST
		// LetterNode bRoot = letterToRoot.get('b');
		// debugln("");
		// display(bRoot);

		// get words from endOfLongestSubString content
		for (LetterNode node : endOfLongestSubString) {
			StringBuilder revStr = new StringBuilder();
			do {
				revStr.append(node._val);
				node = node._parent;
			} while (node != null);
			revStr.reverse();
			substrings.add(revStr.toString());
		}

		return substrings;
	}

	private static void display(LetterNode node) {
		display(node, "");
	}

	private static void display(LetterNode node, String indent) {
		if (node == null)
			return;
		LetterNode cur = node;
		debugln(indent + node);
		String newIndent = "  " + indent;
		for (LetterNode child : node._children.values()) {
			display(child, newIndent);
		}

	}

	// using more than 2 strings... baaaah.
	public static Set<String> getLongestSubstring_OnlyTwoStrings(String[] strs) {
		// internally, keep all matches (attempt to use more than 2 strings).
		Map<Integer, Set<String>> allMatches = new HashMap<>();

		if (strs.length < 2) {
			return new HashSet<>();
		}

		// first string is reference
		String strRef = strs[0];
		Map<Character, List<Integer>> mapRefCharToInt = new HashMap<>();
		for (int i = 0; i < strRef.length(); i++) {
			Character c = strRef.charAt(i);
			List<Integer> idx = mapRefCharToInt.get(c);
			if (idx == null) {
				mapRefCharToInt.put(c, new ArrayList<Integer>());
			}
			mapRefCharToInt.get(c).add(i);
		}

		debugln(mapRefCharToInt);

		// str[1] for now
		int maxLen = 0;
		String strCmp = strs[1];
		for (int i = 0; i < strCmp.length(); i++) {
			Character c = strCmp.charAt(i);
			debugln(c);
			List<Integer> idxRefs = mapRefCharToInt.get(c);
			if (idxRefs != null) {
				for (int idxRef : idxRefs) {
					int j = i;
					do {
						j++;
						idxRef++;
					} while (j < strCmp.length() && idxRef < strRef.length()
							&& strRef.charAt(idxRef) == strCmp.charAt(j));
					debugln("j=" + j + " ; idxRef=" + idxRef + " len=" + (j - i));
					if (j - i > maxLen) {
						maxLen = j - i;
						// ret.clear();
					}
					// if (j - i == maxLen) {
					Set<String> stringsForThisLen = allMatches.get(j - i);
					if (stringsForThisLen == null) {
						allMatches.put(j - i, new HashSet<String>());
					}
					allMatches.get(j - i).add(strRef.substring(idxRef - j + i, idxRef));
					// }
				}
			}
		}

		debugln(allMatches);

		List<Integer> lenghts = new ArrayList<Integer>(allMatches.keySet());
		Collections.sort(lenghts);
		Collections.reverse(lenghts);

		if (lenghts.size() > 0) {
			return allMatches.get(lenghts.get(0));
		} else {
			return new HashSet<>();
		}

	}

	// better: linear - goes from biggest base first,
	// then best bet on the height of sides
	public static int maxArea(ArrayList<Integer> A) {

		if (A == null)
			return 0;

		int n = A.size();
		int first = 0;
		int last = n - 1;
		int area = 0;

		while (first < last) {
			int width = last - first;
			area = Math.max(area, Math.min(A.get(first), A.get(last)) * width);
			if (A.get(first).intValue() >= A.get(last).intValue())
				last--;
			else
				first++;
		}

		return area;

	}

	// working but not optimized
	public static int maxArea_unoptimized(ArrayList<Integer> a) {
		// debugln("array : " + a);
		Map<Integer, Integer> absToOrd = new HashMap<>();
		for (int i = 0; i < a.size(); i++) {
			absToOrd.put(i, a.get(i));
		}
		// add heuristics: sort by value?
		int maxArea = 0;
		Map<Integer, List<Integer>> OrdToAbs = new HashMap<>();
		for (int i = 0; i < a.size(); i++) {
			List<Integer> abs = OrdToAbs.get(a.get(i));
			if (abs == null) {
				abs = new ArrayList<>();
			}
			// if (abs.size() < 2) {
			abs.add(i);
			// }
			OrdToAbs.put(a.get(i), abs);
		}

		Collections.sort(a);
		Collections.reverse(a);
		// debugln(a);

		int minOrd = Math.min(a.get(0), a.get(a.size() - 1));
		for (int i = 0; i < a.size(); i++) {
			int ord1 = a.get(i);
			for (int abs : OrdToAbs.get(ord1)) { // at most two abs
				// debugln("area from " + abs + "," + ord1);
				for (int j = i + 1; j < a.size(); j++) {
					int ord2 = a.get(j);
					int ord = Math.min(ord1, ord2);
					if (ord < minOrd)
						continue;
					for (int abs2 : OrdToAbs.get(ord2)) {
						// if (abs2 <= abs)
						// continue;
						int curArea = ord * Math.abs(abs - abs2);
						if (curArea > maxArea) {
							maxArea = curArea;
						}
						// debugln(" to " + abs2 + "," + ord2 + " , area = " +
						// curArea);
					}
				}

			}

		}

		return maxArea;
	}

	// not working
	// public static int removeDuplicates(ArrayList<Integer> a) {
	// int readFrom = 0;
	// int writeTo = 0;
	// debugln("init " + a);
	//
	// while (readFrom < a.size() && writeTo < a.size()) {
	// // detect current reps
	// int val = a.get(readFrom);
	// int nextValIdx = readFrom + 1;
	// while (nextValIdx < a.size() && a.get(nextValIdx) == val) {
	// nextValIdx++;
	// }
	// // --nextValIdx;
	// debugln("last rep idx " + nextValIdx);
	// int nbOfRepets = nextValIdx - readFrom;
	// debugln("nb of rep " + nbOfRepets);
	// readFrom = nextValIdx - nbOfRepets;
	//
	// debugln("write to " + writeTo + " ; readFrom " + readFrom);
	// a.set(writeTo, readFrom);
	// debugln("new array " + a);
	// writeTo = readFrom + nbOfRepets;
	//
	// readFrom++;
	// }
	//
	// return 0;
	// }

	// new ArrayList(Arrays.asList(1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 3));
	public static int removeDuplicates(ArrayList<Integer> a) {
		int prevVal = a.get(0);
		int counter = 0;
		int writeAt = 0;
		for (int idx = 0; idx < a.size(); idx++) {
			int curVal = a.get(idx);
			a.set(writeAt, curVal);
			if (prevVal == curVal) {
				counter++;
				// debugln("counter at idx " + idx + " is " + counter);
			} else {
				counter = 1;
			}
			prevVal = a.get(idx);
			if (counter < 2) {
				writeAt++;
			}
		}
		return writeAt;
	}

	public static int lengthOfLastWord(final String a) {
		// edgy cases?
		// no space OK
		// only spaces OK
		// empty word OK
		// finishing by space OK

		int lastSpaceCandidate = -1;
		int lastValidSpace = -1;
		int lastLetter = -1;
		for (int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			if (isLetter(c)) {
				// last found space is now valid
				lastValidSpace = lastSpaceCandidate;
				lastLetter = i;
			}
			if (c == ' ') {
				lastSpaceCandidate = i;
				debugln("lastSpaceCandidate " + lastSpaceCandidate);
			}
		}

		debugln("lastValidSpace " + lastValidSpace);
		debugln("lastLetter " + lastLetter);

		// last letter - last space = length of last word
		return lastLetter - lastValidSpace;

	}

	public static boolean isLetter(char c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
	}

	public static String convert(String a, int b) {

		if (b <= 1)
			return a;

		String[] lines = new String[b];
		int baseStep = (b - 1) * 2;
		for (int line = 1; line <= b; line++) {
			int offsetA = baseStep - 2 * (line - 1);
			int offsetB = baseStep - offsetA;
			if (offsetA == 0) {
				offsetA = offsetB;
			}
			if (offsetB == 0) {
				offsetB = offsetA;
			}
			int curOffset = offsetA;
			lines[line - 1] = new String();
			for (int idx = line - 1; idx < a.length();) {
				lines[line - 1] += a.charAt(idx);
				idx += curOffset;
				debugln("current offset " + curOffset);
				curOffset = (curOffset == offsetA ? offsetB : offsetA);
			}
			debugln("line #" + line + " is " + lines[line - 1]);
		}

		String res = "";
		for (String line : lines) {
			res += line;
		}

		return new String(res);
	}

	public static ArrayList<String> restoreIpAddresses(String a) {

		Map<Integer, Set<String>> cache = new HashMap<>();
		ArrayList<String> ret = new ArrayList<>(restoreIpAddresses(a, 0, cache));
		Collections.sort(ret, new CompIP());
		return ret;
	}

	public static Set<String> restoreIpAddresses(String a, int idx, Map<Integer, Set<String>> cache) {

		if (cache.containsKey(idx)) {
			return cache.get(idx);
		}

		// base case
		if (idx >= a.length()) {
			Set<String> possibs = new HashSet<>();
			possibs.add("");
			cache.put(idx, possibs);
			return possibs;
		}

		// general
		Set<String> possibs = new HashSet<>();
		for (int k = 1; k <= 3; k++) {
			String valStr = a.substring(idx, Math.min(idx + k, a.length()));
			int val = Integer.MAX_VALUE;
			if (valStr.length() == 0 || hasALeadingZero(valStr))
				continue;
			val = Integer.parseInt(valStr);
			valStr = String.valueOf(val); // in case we have 00, 000, 027...
			if (val <= 255) {
				Set<String> subPossibs = restoreIpAddresses(a, idx + k, cache);
				for (String subPoss : subPossibs) {
					int nbDots = nbOfDotsIn(subPoss);
					// always max 2 dots and exactly 2 dots when initial call
					if (nbDots < 3 && (idx > 0 || nbDots == 2)) {
						if (subPoss.length() == 0) {
							possibs.add(valStr);
						} else
							possibs.add(valStr + "." + subPoss);
					}
				}
			}
		}

		cache.put(idx, possibs);
		return possibs;

	}

	private static class CompIP implements Comparator<String> {

		// Assuming they are IPs !!!
		@Override
		public int compare(String o1, String o2) {
			int idx1_1 = o1.indexOf(".");
			int idx1_2 = o1.indexOf(".", idx1_1 + 1);
			int idx1_3 = o1.indexOf(".", idx1_2 + 1);
			Integer val1_1 = Integer.valueOf(o1.substring(0, idx1_1));
			Integer val1_2 = Integer.valueOf(o1.substring(idx1_1 + 1, idx1_2));
			Integer val1_3 = Integer.valueOf(o1.substring(idx1_2 + 1, idx1_3));
			Integer val1_4 = Integer.valueOf(o1.substring(idx1_3 + 1, o1.length()));

			int idx2_1 = o2.indexOf(".");
			int idx2_2 = o2.indexOf(".", idx2_1 + 1);
			int idx2_3 = o2.indexOf(".", idx2_2 + 1);
			Integer val2_1 = Integer.valueOf(o2.substring(0, idx2_1));
			Integer val2_2 = Integer.valueOf(o2.substring(idx2_1 + 1, idx2_2));
			Integer val2_3 = Integer.valueOf(o2.substring(idx2_2 + 1, idx2_3));
			Integer val2_4 = Integer.valueOf(o2.substring(idx2_3 + 1, o2.length()));

			if (val1_1.compareTo(val2_1) == 0) {
				if (val1_2.compareTo(val2_2) == 0) {
					if (val1_3.compareTo(val2_3) == 0) {
						return val1_4.compareTo(val2_4);
					} else {
						return val1_3.compareTo(val2_3);
					}
				} else {
					return val1_2.compareTo(val2_2);
				}
			} else {
				return val1_1.compareTo(val2_1);
			}
		}

	}

	private static boolean hasALeadingZero(String str) {
		int val = Integer.parseInt(str);
		String valStr = String.valueOf(val);
		return (!str.equals(valStr));
	}

	private static int nbOfDotsIn(String str) {
		int n = 0;
		for (char c : str.toCharArray()) {
			if (c == '.') {
				n++;
			}
		}
		return n;
	}

	public static int compareVersion(String a, String b) {

		String[] aSplit = a.split("\\.");
		String[] bSplit = b.split("\\.");

		int minVer = Math.min(aSplit.length, bSplit.length);
		for (int i = 0; i < minVer; i++) {
			String valA = aSplit[i];
			String valB = bSplit[i];
			if (valA.length() != valB.length()) {
				int diff = valA.length() - valB.length();
				String padding = "";
				for (int k = 0; k < Math.abs(diff); k++) {
					padding += "0";
				}
				if (diff > 0) {
					valB = padding + valB;
				} else {
					valA = padding + valA;
				}
			}
			// debugln(valA + " " + valB);
			if (valA.compareTo(valB) > 0)
				return 1;
			if (valA.compareTo(valB) < 0)
				return -1;
		}
		// still equal? min nb of versions wins
		if (aSplit.length > bSplit.length) {
			if (!areAllVersionsZeros(aSplit, minVer))
				return 1;
		}
		if (aSplit.length < bSplit.length)
			if (!areAllVersionsZeros(bSplit, minVer))
				return -1;

		return 0;

	}

	private static boolean areAllVersionsZeros(String[] splitStr, int idxFrom) {
		for (int i = idxFrom; i < splitStr.length; i++) {
			String val = splitStr[i];
			if (!isOnlyZeros(val))
				return false;
		}
		return true;
	}

	private static boolean isOnlyZeros(String val) {
		// debugln("checking " + val);
		for (char c : val.toCharArray()) {
			if (c != '0') {
				return false;
			}
		}
		return true;
	}

	public static long reverse(long a) {

		long x = a;
		int order = -1;
		double log2 = Math.log(2);
		Set<Integer> bits = new HashSet<>();
		do {
			order = (int) (Math.log(x) / log2);
			bits.add(order);
			x = (long) (x - Math.pow(2, order));
			// debugln("x=" + x);
		} while (order > 0);

		debugln(bits);

		long res = 0;
		for (int i = 0; i <= 31; i++) {
			if (bits.contains(i)) {
				res += Math.pow(2, (31 - i));
			}
		}

		return res;
	}

	// **************** usual printers. **************************
	private static void debug(Object obj) {
		System.err.print(obj == null ? "null" : obj.toString());
	}

	private static void debugln(Object obj) {
		System.err.println(obj == null ? "null" : obj.toString());
	}

	private static void print(Object obj) {
		System.out.print(obj == null ? "null" : obj.toString());
	}

	private static void println(Object obj) {
		System.out.println(obj == null ? "null" : obj.toString());
	}

}
