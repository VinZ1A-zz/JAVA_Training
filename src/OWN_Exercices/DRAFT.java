package OWN_Exercices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class DRAFT {

	public static void main(String[] args) {
		// try bulbs
		ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(0, 1, 0, 1));
		debugln("bulbs : " + bulbs(a));

		int n = 100;// 16777214 --> [31, 16777183]
		// get list of primes up to n
		// Set<Integer> primes = listOfPrimes(n);
		// debugln(primes);
		ArrayList<Integer> ints = primesum(n);
		// debugln(ints);

		// for (int i = 2; i < 30; i++) {
		// debugln("i = " + i + " --> " + (2 ^ i));
		// }

		// int what = 2 ^ 2;
		int first = Integer.parseInt("10", 2);
		int what = first ^ first;
		// debugln(what);

		// ************ distance to move between points - expects 108
		ArrayList<Integer> X = new ArrayList<>(Arrays.asList(8, 4, 8, -7, -5, -13, 9, -7, 8));
		ArrayList<Integer> Y = new ArrayList<>(Arrays.asList(8, 4, -15, -10, -3, -13, 12, 8, -8));
		int nbMoves = coverPoints(X, Y);
		debugln("dist = " + nbMoves);

		// **************** display diagonals of matrix
		{
			ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
			matrix.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
			matrix.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
			matrix.add(new ArrayList<>(Arrays.asList(7, 8, 9)));

			ArrayList<ArrayList<Integer>> res = diagonal(matrix);
			// debugln(res);
		}

		// ********** stepping number
		ArrayList<Integer> nb = stepnum(10, 20);
		debugln("nb of steppingnbs = " + nb);

		// ********** find unique prefix for list of words
		// ArrayList<String> words = new ArrayList<>(Arrays.asList("zebra",
		// "dog",
		// "duck", "dove"));

		// got prefixes : [f, k, ku, u, v, d], expects f km ku u v d
		ArrayList<String> words = new ArrayList<>(
				Arrays.asList("fwkho", "kmcoqhnw", "kuewhsqmgb", "uqcljj", "vsw", "dkqtbxi"));

		// debugln("words : " + words);
		// debugln("wordsInit : " + wordsInit);
		// debugln(Collections.binarySearch(words, "dovz"));

		// ArrayList<String> prefixes = prefix(words);
		// debugln("prefixes : " + prefixes);

		// ******** colorful nb *********
		{
			int val = 3245;
			debugln("colorful ? " + colorful(val));
		}

		{
			String val = "1024"; // can have 64 chars
			debugln("power = " + power(val));
		}

		// ************** wave sort ********
		// a1 >= a2 <= a3 >= a4 <= a5.....
		{
			// ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(1,
			// 2, 3,
			// 4, 5, 6));
			ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(5, 1, 3, 2, 4));
			// ArrayList<Integer> arr = new ArrayList<Integer>(Arrays.asList(99,
			// 78,
			// 16, 35, 97, 26, 12, 67, 10, 33, 79, 49, 79,
			// 21, 67, 72, 93, 36, 85, 45, 28, 91, 94, 57, 1, 53, 8, 44, 68, 90,
			// 24,
			// 96, 30, 3));

			ArrayList<Integer> result = wave(arr);
			debugln(result);
		}

		{
			// ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 5,
			// -7, 2,
			// 3));
			// ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(-1, -1,
			// -1, -1,
			// -1));
			// ArrayList<Integer> arr = new ArrayList<>(
			// Arrays.asList(756898537, -1973594324, -2038664370, -184803526,
			// 1424268980));
			// expects 1101513929 1315634022
			ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(336465782, -278722862, -2145174067, 1101513929, 1315634022,
					-1369133069, 1059961393, 628175011, -1131176229, -859484421));

			ArrayList<Integer> res = maxset(arr);
			// debugln("maxset : " + res);
		}

		{
			TreeNode root = new TreeNode(3);
			TreeNode node9 = new TreeNode(9);
			TreeNode node20 = new TreeNode(20);
			TreeNode node15 = new TreeNode(15);
			TreeNode node7 = new TreeNode(7);
			TreeNode node3 = new TreeNode(3);
			TreeNode node4 = new TreeNode(4);

			root.left = node9;
			root.right = node20;
			node20.left = node15;
			node20.right = node7;
			node9.left = node3;
			node9.right = node4;

			ArrayList<ArrayList<Integer>> res = zigzagLevelOrder(root);
			// debugln(res);
		}

		// tree is symetric?
		if (false) {
			TreeNode root = new TreeNode(1);
			TreeNode node2L = new TreeNode(2);
			TreeNode node2R = new TreeNode(2);
			TreeNode node3R = new TreeNode(3);
			TreeNode node3L = new TreeNode(3);
			TreeNode node4R = new TreeNode(4);
			TreeNode node4L = new TreeNode(4);

			root.right = node2R;
			root.left = node2L;
			node2L.left = node3L;
			node2L.right = node4L;
			node2R.right = node3R;
			node2R.left = node4R;
			node3L.left = new TreeNode(5);
			node3L.right = new TreeNode(6);
			node4L.left = new TreeNode(7);
			node4L.right = new TreeNode(8);
			node4R.left = new TreeNode(8);
			node4R.right = new TreeNode(7);
			node3R.left = new TreeNode(6);
			node3R.right = new TreeNode(5);

			// debugln("isSymmetric = " + isSymmetric(root));

		}

		if (false) {
			TreeNode root = new TreeNode(3);
			TreeNode node9 = new TreeNode(9);
			TreeNode node2 = new TreeNode(2);
			TreeNode node5 = new TreeNode(5);
			TreeNode node7 = new TreeNode(7);
			TreeNode node3 = new TreeNode(3);
			TreeNode node4 = new TreeNode(4);

			root.left = node9;
			root.right = node2;
			node2.left = node5;
			node2.right = node7;
			node9.left = node3;
			node9.right = node4;

			// debugln("sumNumbers = " + sumNumbers(root));
		}

		if (false) {
			// 6,8
			// List<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 7,
			// 7, 8,
			// // 8, 8, 9, 10, 11, 12, 13, 14));
			// List<Integer> arr = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5,
			// 7, 7,
			// 8, 8, 8)); // 7,9
			// List<Integer> arr = new ArrayList<>(Arrays.asList(8, 8, 8, 9, 10,
			// 11,
			// 12, 13, 14));
			// List<Integer> arr = new ArrayList<>(Arrays.asList(1, 1));

			// String str = "1 1 1 1 1 1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 2 2 2
			// 2 2 2
			// 2 2 " //
			// + "2 2 2 2 2 2 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 4 4
			// 4 4 4
			// 4 4 4 4 4 4 " + //
			// "4 4 4 4 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 5 6 6 6 6 6 6 6
			// 6 6 6
			// 6 6 6 6 6 6 6 6 " + //
			// "6 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8
			// 8 8 8
			// 8 8 8 8 8 8 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 10 10 10 10 10 10
			// 10 10
			// 10 10 10 10 10 10 10 10 10 10 10 10 10 10";
			String str = "1 1 1 1 1 1 1 1 1 2 2 2 2 2 2 2 2 2 3 3 3 4 4 4 4 4 4 4 5 " + //
					"5 5 5 5 5 5 5 5 5 5 5 6 6 6 6 6 6 6 6 7 7 7 7 7 7 7 7 7 7 7 8 8 8 8 8 8 " + //
					"8 9 9 9 9 9 10 10 10 10 10 10 10 10 10 10 10 10 10 10 ";

			List<Integer> arr = new ArrayList<>();
			for (String s : str.split(" ")) {
				if (s.trim().isEmpty())
					continue;
				arr.add(Integer.valueOf(s));
			}

			Collections.sort(arr); // should be already sorted
			ArrayList<Integer> res = searchRange(arr, 10);
			debugln(res);

		}

		// build tree
		{
			// String desc = "3 9 20 -1 -1 15 7 1 2 3 4";
			String desc = "38 14 24 6 4 10 14 -1 -1 -1 4";
			// note: no initial size, no useless -1's at the end
			TreeNode root = buildTree(desc);
		}

	}

	// Woolies --- that was bad.. ? 62%
	public static int solution(int A, int B) {
		int limitLow = (int) Math.ceil(Math.sqrt(A));
		int limitHigh = (int) Math.sqrt(B);
		debugln(limitLow + " " + limitHigh);

		int result = limitHigh - limitLow;
		// if (limitLow * limitLow == A)
		// result++;

		return result;
	}

	// TREE BUILDER FROM STRING - BUILD TREE -
	// eg. "3 9 20 -1 -1 15 7";
	public static TreeNode buildTree(String desc) {
		String[] elems = desc.split(" ");
		// Map<Integer, TreeNode> idxToNode = new HashMap<>();
		TreeNode root = new TreeNode(Integer.parseInt(elems[0]));
		// idxToNode.put(0, root);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		int i = 1;
		while (i < elems.length) {
			TreeNode curNode = queue.poll();
			int valL = Integer.parseInt(elems[i]);
			if (valL != -1) {
				debugln("attaching left child of " + curNode.val + "(" + valL + ")");
				curNode.left = new TreeNode(valL);
				queue.add(curNode.left);
			}
			i++;
			if (i < elems.length) {
				int valR = Integer.parseInt(elems[i]);
				if (valR != -1) {
					debugln("attaching right child of " + curNode.val + "(" + valR + ")");
					curNode.right = new TreeNode(valR);
					queue.add(curNode.right);
				}
			}
			i++;

		}

		return root;

	}

	public static ArrayList<Integer> searchRange(final List<Integer> a, int b) {

		int leftBound = searchRange(a, b, true);
		int rightBound = searchRange(a, b, false);
		ArrayList<Integer> res = new ArrayList<>();
		res.add(leftBound);
		res.add(rightBound);
		return res;

	}

	public static int searchRange(final List<Integer> a, int b, boolean searchForLeftBound) {
		int low = 0;
		int high = a.size() - 1;
		int mid;

		while (low <= high) {
			mid = (low + high) / 2;
			boolean rightOfMidIsaB = (mid + 1 < a.size()) && a.get(mid + 1) == b;
			boolean leftOfMidIsaB = (mid - 1 >= 0) && a.get(mid - 1) == b;
			if (a.get(mid) < b || (!searchForLeftBound && a.get(mid) == b && rightOfMidIsaB)) {
				low = mid + 1;
			} else if (a.get(mid) > b || (searchForLeftBound && a.get(mid) == b && leftOfMidIsaB)) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	// [-1, -1] when not found
	public static ArrayList<Integer> searchRange2(final List<Integer> a, int b) {

		if (Collections.binarySearch(a, b) < 0) {
			ArrayList<Integer> res = new ArrayList<>();
			res.add(-1);
			res.add(-1);
			return res;
		}

		return searchRange2(a, b, 0, a.size() - 1);
	}

	// working but too slow. Try searchRange() for a better one
	public static ArrayList<Integer> searchRange2(final List<Integer> a, int b, int beg, int end) {

		ArrayList<Integer> res = new ArrayList<>();

		int midPt = beg + (end - beg) / 2;
		debugln("at midPt " + midPt + " , got " + a.get(midPt));
		if (a.get(midPt) < b) {
			debugln("search right : beg=" + midPt + " , end=" + end);
			return searchRange2(a, b, midPt, end);
		} else if (a.get(midPt) > b) {
			debugln("search left : beg=" + beg + " , end=" + midPt);
			return searchRange2(a, b, beg, midPt);
		} else if (a.get(midPt) == b) {
			// left bound
			int leftBound = beg;
			do {
				leftBound = leftBound + (midPt - leftBound) / 2;
			} while (a.get(leftBound) < b && midPt - leftBound > 1);
			debugln(" before leftBound : " + leftBound + " and after beg : " + beg);
			// adjust, go left step by step
			while (leftBound >= 0 && a.get(leftBound) == b) {
				leftBound--;
			}
			leftBound++;
			debugln(" leftBound adj : " + leftBound);
			// right bound
			int rightBound = midPt + (end - midPt) / 2;
			while (a.get(rightBound) > b && rightBound - midPt > 1) {
				// rightBound = midPt + (rightBound - midPt) / 2;
				debugln(" cur  rightBound : " + rightBound);
				rightBound = rightBound - (rightBound - midPt) / 2;
			}
			debugln(" after rightBound : " + rightBound + " and before end : " + end);
			// adjust, go right step by step
			while (rightBound < a.size() && a.get(rightBound) == b) {
				rightBound++;
			}
			rightBound--;
			debugln(" rightBound adj : " + rightBound);
			// not needed searchRange(a, b, leftBound, rightBound);
			res.add(leftBound);
			res.add(rightBound);
		}

		return res;
	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	static class NodeLevel {
		TreeNode node;
		int lev;

		NodeLevel(TreeNode n, int x) {
			node = n;
			lev = x;
		}
	}

	static class NodeWithPrevSum {
		TreeNode node;
		long sum;
		boolean isRoot = false;

		NodeWithPrevSum(TreeNode n, long s) {
			node = n;
			sum = s;
		}
	}

	public static int sumNumbers(TreeNode a) {

		if (a == null)
			return 0;

		Queue<NodeWithPrevSum> zeQ = new LinkedList<>();
		NodeWithPrevSum rootNodeSum = new NodeWithPrevSum(a, a.val);
		rootNodeSum.isRoot = true;
		zeQ.offer(rootNodeSum);
		int sum = 0;

		while (!zeQ.isEmpty()) {
			NodeWithPrevSum curNode = zeQ.poll();

			long addedVal = 0;
			if (!curNode.isRoot) {
				addedVal = curNode.sum * 10 + curNode.node.val;
			} else {
				addedVal = curNode.node.val;
			}

			// sum of root to leaf only
			if (curNode.node.left == null && curNode.node.right == null) {
				debugln(addedVal);
				sum += addedVal;
				sum = sum % 1003;
			}

			if (curNode.node.left != null) {
				zeQ.offer(new NodeWithPrevSum(curNode.node.left, addedVal % 1003));
			}
			if (curNode.node.right != null) {
				zeQ.offer(new NodeWithPrevSum(curNode.node.right, addedVal % 1003));
			}

		}

		return sum;

	}

	public static int isSymmetric(TreeNode a) {

		if (a == null)
			return 1;
		if (a.left == null && a.right == null)
			return 1;
		if (a.left == null && a.right != null || a.right == null && a.left != null)
			return 0;

		TreeNode left = a.left;
		TreeNode right = a.right;
		if (left.val != right.val)
			return 0;

		Queue<NodeLevel> leftQ = new LinkedList<>();
		Queue<NodeLevel> rightQ = new LinkedList<>();
		leftQ.offer(new NodeLevel(left, 0));
		rightQ.offer(new NodeLevel(right, 0));

		NodeLevel nullTerminalNode = new NodeLevel(new TreeNode(Integer.MIN_VALUE), -1);

		while (!leftQ.isEmpty() && !rightQ.isEmpty()) {
			NodeLevel curNodeL = leftQ.poll();
			NodeLevel curNodeR = rightQ.poll();
			debugln("next L val = " + curNodeL.node.val + " lev = " + curNodeL.lev);
			debugln("next R val = " + curNodeR.node.val + " lev = " + curNodeR.lev);
			if (curNodeL.node.val != curNodeR.node.val || curNodeL.lev != curNodeR.lev) {
				return 0;
			}
			if (curNodeL.node.right != null) {
				leftQ.offer(new NodeLevel(curNodeL.node.right, curNodeL.lev + 1));
			} else if (curNodeL.node.val != Integer.MIN_VALUE) {
				nullTerminalNode.lev = curNodeL.lev + 1;
				leftQ.offer(nullTerminalNode);
			}
			if (curNodeL.node.left != null) {
				leftQ.offer(new NodeLevel(curNodeL.node.left, curNodeL.lev + 1));
			} else if (curNodeL.node.val != Integer.MIN_VALUE) {
				nullTerminalNode.lev = curNodeL.lev + 1;
				leftQ.offer(nullTerminalNode);
			}
			if (curNodeR.node.left != null) {
				debugln("adding L " + curNodeR.node.left.val);
				rightQ.offer(new NodeLevel(curNodeR.node.left, curNodeR.lev + 1));
			} else if (curNodeR.node.val != Integer.MIN_VALUE) {
				nullTerminalNode.lev = curNodeR.lev + 1;
				rightQ.offer(nullTerminalNode);
			}
			if (curNodeR.node.right != null) {
				debugln("adding R " + curNodeR.node.right.val);
				rightQ.offer(new NodeLevel(curNodeR.node.right, curNodeR.lev + 1));
			} else if (curNodeR.node.val != Integer.MIN_VALUE) {
				nullTerminalNode.lev = curNodeR.lev + 1;
				rightQ.offer(nullTerminalNode);
			}
		}

		if (!leftQ.isEmpty() || !rightQ.isEmpty())
			return 0;

		return 1;
	}

	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode a) {
		ArrayList<ArrayList<Integer>> res = zigzagLevelOrder(a, true);

		ArrayList<ArrayList<Integer>> finalRes = new ArrayList<>();
		int i = 0;
		for (ArrayList<Integer> level : res) {
			if (i % 2 == 0) {
				Collections.reverse(level);
			}
			finalRes.add(level);
			i++;
		}

		return finalRes;
	}

	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode a, boolean rightFirst) {
		// base case
		if (a == null) {
			ArrayList<ArrayList<Integer>> res = new ArrayList<>();
			ArrayList<Integer> empty = new ArrayList<>();
			res.add(empty);
			return res;
		}

		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> topLev = new ArrayList<>();
		topLev.add(a.val);
		// debugln("adding " + a.val + " with rightFirst=" + rightFirst);
		res.add(topLev);

		// right then left first
		// if (rightFirst) {
		ArrayList<ArrayList<Integer>> resR = zigzagLevelOrder(a.right, !rightFirst);
		ArrayList<ArrayList<Integer>> resL = zigzagLevelOrder(a.left, rightFirst); // ok.
		for (int i = 0; i < Math.max(resR.size(), resL.size()); i++) {
			ArrayList<Integer> nextLev = new ArrayList<>();
			if (i < resR.size()) {
				nextLev.addAll(resR.get(i));
			}
			if (i < resL.size()) {
				nextLev.addAll(resL.get(i));
			}
			if (!nextLev.isEmpty())
				res.add(nextLev);
		}
		// } else {
		// ArrayList<ArrayList<Integer>> resL = zigzagLevelOrder(a.left,
		// rightFirst);
		// ArrayList<ArrayList<Integer>> resR = zigzagLevelOrder(a.right,
		// !rightFirst); // ok
		// for (int i = 0; i < Math.max(resR.size(), resL.size()); i++) {
		// ArrayList<Integer> nextLev = new ArrayList<>();
		//
		// if (i < resL.size()) {
		// nextLev.addAll(resL.get(i));
		// }
		// if (i < resR.size()) {
		// nextLev.addAll(resR.get(i));
		// }
		// if (!nextLev.isEmpty())
		// res.add(nextLev);
		// }
		// }

		return res;

	}

	public static ArrayList<Integer> maxset(ArrayList<Integer> a) {

		long maxSum = 0;
		long curSum = 0;
		ArrayList<Integer> bestSeq = new ArrayList<>();
		ArrayList<Integer> curSeq = new ArrayList<>();
		for (int i : a) {
			if (i >= 0) {
				curSum += i;
				curSeq.add(i);
			}
			if ((curSum > maxSum) || ((curSum == maxSum) &&

					(curSeq.size() > bestSeq.size()
							|| (curSeq.size() > 0 && curSeq.size() == bestSeq.size() && curSeq.get(0) < bestSeq.get(0))))) {
				maxSum = curSum;
				bestSeq = (ArrayList<Integer>) curSeq.clone();
			}
			if (i < 0) {
				curSum = 0;
				curSeq.clear();
			}

		}
		return bestSeq;

	}

	public static ArrayList<Integer> wave(ArrayList<Integer> a) {

		if (a == null || a.size() < 1)
			return null;

		Collections.sort(a);
		// all permutations of a
		// List<List<Integer>> perms = getEligiblePerm(a);
		// debugln(perms);

		ArrayList<Integer> sol = new ArrayList<>();
		for (int i = 0; i < a.size() - 1; i += 2) {
			sol.add(a.get(i + 1));
			sol.add(a.get(i));
		}
		if (a.size() % 2 > 0) {
			sol.add(a.get(a.size() - 1));
		}

		// return (ArrayList<Integer>) perms.get(perms.size() - 1);
		return sol;
	}

	// eligible when a0 >= a1 <= a2 >= a3 <= a4.....
	// horribly not efficient
	private static List<List<Integer>> getEligiblePerm(ArrayList<Integer> a) {
		debugln("called with " + a);
		if (a == null || a.size() == 0) {
			List<List<Integer>> perms = new ArrayList<>();
			perms.add(new ArrayList<Integer>());
			return perms;
		}

		List<List<Integer>> perms = new ArrayList<>();
		for (Integer i : a) {
			ArrayList<Integer> subList = (ArrayList<Integer>) a.clone();
			subList.remove(i);
			List<List<Integer>> subPerms = getEligiblePerm(subList);
			for (List<Integer> perm : subPerms) {
				perm.add(i);
				// check validity of last added elem in perm
				if (perm.size() >= 2) {
					if (perm.size() % 2 == 0) {
						// elem just added must be smaller than elem before
						if (perm.get(perm.size() - 1) > perm.get(perm.size() - 2)) {
							continue; // use next subPerm item
						}
					} else {
						// elem just added must be greater than elem before
						if (perm.get(perm.size() - 1) < perm.get(perm.size() - 2)) {
							continue; // use next subPerm item
						}
					}
				}
				perms.add(perm);
			}
		}
		return perms;

	}

	// is a power of 2?
	public static int power(String a) {
		String val = a;
		do {
			val = divideByTwo(val);
			// debugln("val = " + val);
		} while (!val.equals("1") && !val.endsWith(".5"));

		return val.equals("1") ? 1 : 0;
	}

	private static String divideByTwo(String str) {

		if (str == null)
			return null;

		int idx = 0;
		StringBuilder result = new StringBuilder();
		int remainder = 0;
		do {
			int digit = Integer.parseInt(String.valueOf(str.charAt(idx)));

			int res = (remainder * 10 + digit) / 2;
			remainder = (remainder * 10 + digit) % 2;
			if (!(res == 0 && idx == 0)) {
				result.append(res);
			}
			idx++;
		} while (idx < str.length());
		if (remainder > 0) {
			result.append(".5");
		}

		return result.toString();

	}

	public static int colorful(int a) {

		String str = String.valueOf(a);

		List<String> allSubSeq = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			List<String> subSeq = getSubsequences(str, i);
			allSubSeq.addAll(subSeq);
		}

		// debugln(allSubSeq);

		Map<Integer, String> prodToNb = new HashMap<>();
		for (String i : allSubSeq) {
			int sum = 1;
			for (int k = 0; k < i.length(); k++) {
				int digit = Integer.parseInt(String.valueOf(i.charAt(k)));
				sum = sum * digit;
			}
			if (prodToNb.containsKey(sum)) {
				return 0;
			}
			prodToNb.put(sum, i);
		}

		return 1;
	}

	private static List<String> getSubsequences(String str, int idx) {
		// base case
		if (idx == str.length()) {
			List<String> subSeqs = new ArrayList<>();
			// subSeqs.add(""); // NO.
			return subSeqs;
		}

		// general case
		List<String> subSeqs = new ArrayList<>();
		subSeqs.add(String.valueOf(str.charAt(idx)));
		List<String> prevSubs = getSubsequences(str, idx + 1);
		for (String prevStr : prevSubs) {
			subSeqs.add(str.charAt(idx) + prevStr);
		}
		return subSeqs;
	}

	public static ArrayList<String> prefix(ArrayList<String> a) {

		ArrayList<String> wordsInit = (ArrayList<String>) a.clone();
		Collections.sort(a);
		debugln("words : " + a);

		Map<String, String> wordToPref = new HashMap<>();
		ArrayList<String> prefixes = new ArrayList<>();
		String prev = null;
		for (int i = 0; i < a.size(); i++) {
			String str = a.get(i);
			String prefixCandidate = null;
			int k1 = -1;
			if (i < a.size() - 1) {
				String next = a.get(i + 1);
				k1 = 0;
				while (k1 < str.length() && k1 < next.length() && str.charAt(k1) == next.charAt(k1)) {
					k1++;
				}
				k1++; // k common letters, need k+1 to identify
				debugln("k=" + k1);
				prefixCandidate = str.substring(0, k1);

			}

			if (i > 0) {
				int k2 = 0;
				while (k2 < str.length() && k2 < prev.length() && str.charAt(k2) == prev.charAt(k2)) {
					k2++;
				}
				k2++;
				debugln("new k=" + Math.max(k1, k2));
				prefixCandidate = str.substring(0, Math.max(k1, k2));
			}

			debugln("pref = " + prefixCandidate);
			wordToPref.put(str, prefixCandidate);
			prev = prefixCandidate;
		}

		for (String str : wordsInit) {
			prefixes.add(wordToPref.get(str));
		}

		return prefixes;
	}

	public static ArrayList<Integer> stepnum(int a, int b) {
		// base case
		if (a < 10) {
			ArrayList<Integer> newSteps = new ArrayList<>();
			for (int i = a; i < 10; i++) {
				if (i <= b)
					newSteps.add(i);
			}
			if (b >= 10) {
				ArrayList<Integer> otherSteps = stepnum(10, b);
				newSteps.addAll(otherSteps);
			}
			Collections.sort(newSteps);
			return newSteps;
		}

		// general case
		int subA = a / 10;
		int subB = b / 10;
		ArrayList<Integer> subSteps = stepnum(subA, subB);
		ArrayList<Integer> newSteps = new ArrayList<>();
		for (int i : subSteps) {
			int lastDigit = i % 10;
			if (lastDigit <= 8) {
				int newVal = i * 10 + lastDigit + 1;
				if (newVal >= a && newVal <= b)
					newSteps.add(newVal);
			}
			if (lastDigit >= 1) {
				int newVal = i * 10 + lastDigit - 1;
				if (newVal >= a && newVal <= b)
					newSteps.add(newVal);
			}
		}

		Collections.sort(newSteps);
		return newSteps;

	}

	public static ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> a) {

		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		for (int i = 0; i < a.size(); i++) {
			ArrayList<Integer> subList = new ArrayList<>();
			for (int j = 0; j <= i; j++) {
				subList.add(a.get(j).get(i - j));
			}
			res.add(subList);
		}

		for (int i = a.size() - 2; i >= 0; i--) {
			ArrayList<Integer> subList = new ArrayList<>();
			for (int j = 0; j <= i; j++) {
				subList.add(a.get(a.size() - 1 - (i - j)).get(a.size() - j - 1));
			}
			res.add(subList);
		}

		return res;

	}

	// X and Y co-ordinates of the points in order.
	// Each point is represented by (X.get(i), Y.get(i))
	public static int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {

		int prevX = 0, prevY = 0;
		int dist = 0;
		for (int i = 0; i < X.size(); i++) {
			if (i == 0) {
				prevX = X.get(i);
				prevY = Y.get(i);
				continue;
			}
			int localDist = getDistance(prevX, prevY, X.get(i), Y.get(i));
			// debugln("from " + prevX + "," + prevY + " to " + X.get(i) + "," +
			// Y.get(i) + " ; dist = " + localDist);
			dist += localDist;
			prevX = X.get(i);
			prevY = Y.get(i);
		}
		return dist;

	}

	private static int getDistance(int fromX, int fromY, int toX, int toY) {

		int distX = Math.abs(fromX - toX);
		int distY = Math.abs(fromY - toY);

		int diagDist = Math.min(distX, distY);
		// int lineDist = Math.abs(distX - distY);
		int lineDist = Math.max(distX, distY) - diagDist;

		return diagDist + lineDist;

	}

	private static Set<Integer> listOfPrimes(int n) {

		Set<Integer> res = new TreeSet<Integer>();

		double upTo = n;

		res.add(2);
		res.add(3);
		for (int i = 3; i <= upTo; i = i + 2) {
			if (i % 3 == 0) {
				continue;
			}
			double maxSearch = Math.sqrt(i);
			boolean isPrime = true;
			for (double nb : res) {
				if (nb > maxSearch)
					break;
				// any non-prime would be divisible by a prime
				if (i % nb == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				res.add(i);
			}
		}

		return res;
	}

	public static boolean isPrime(int num) {
		for (int i = 2; i < num; i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

	public static ArrayList<Integer> primesum(int a) {
		Set<Integer> primes = listOfPrimes(a / 2);

		ArrayList<Integer> res = null;
		for (int prime : primes) {
			int comp = a - prime;
			// if (primes.contains(comp)) { // only works for small nbs
			if (isPrime(comp)) {
				debugln(prime);
				res = new ArrayList<>(Arrays.asList(prime, comp));
				break; // only smaller needed
			}
		}
		return res;
	}

	public static int bulbs(ArrayList<Integer> a) {

		int nbTries = 0;
		for (int i = 0; i < a.size(); i++) {
			if ((a.get(i) == 0 && nbTries % 2 == 0) || (a.get(i) == 1 && nbTries % 2 > 0)) {
				nbTries++;
			}
		}
		return nbTries;
	}

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
