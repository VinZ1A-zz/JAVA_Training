import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collectors;

public class Sol_WeekCode28_ValueFriendship {

	static boolean _debug = false;
	static boolean _critical = false;

	public static void main(String[] args) {

		if (args.length != 0) {
			_debug = false;
			_critical = true;
			debugln("in debug");
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

		// 1 group, 1 cycle
		// aInput.add("1");
		// aInput.add("5 4");
		// aInput.add("1 2");
		// aInput.add("3 2");
		// aInput.add("4 2");
		// aInput.add("4 3");

		// 2 groups, 1 cycle each
		// aInput.add("1");
		// aInput.add("10 8");
		// aInput.add("1 2");
		// aInput.add("3 2");
		// aInput.add("4 2");
		// aInput.add("4 3");
		// aInput.add("6 7");
		// aInput.add("8 7");
		// aInput.add("9 7");
		// aInput.add("9 8");

		// should get (?)
		aInput.add("11"); // 11
		aInput.add("3 2"); // 4 ok
		aInput.add("1 2 2 1");
		aInput.add("5 3"); // 16 ok
		aInput.add("1 2 3 4 4 5");
		aInput.add("5 3"); // 20 ok
		aInput.add("1 2 4 5 2 4");
		aInput.add("5 4"); // 24 ok
		aInput.add("1 2 2 3 1 3 4 5");
		aInput.add("8 8");// 224 ok
		aInput.add("1 2 2 3 3 4 5 6 7 8 7 6 5 4 8 1");
		aInput.add("14 15");// 728 ok
		aInput.add("1 2 2 3 3 4 4 5 1 5 6 7 7 8 8 9 9 6 10 11 11 12 12 10 13 14 3 13 11 7");
		aInput.add("14 14"); // 352 ok
		aInput.add("7 8 1 2 2 3 1 3 4 5 4 6 5 6 10 11 13 12 12 11 13 11 13 10 12 10 12 9");
		aInput.add("4 2"); // 6
		aInput.add("1 2 3 4");
		aInput.add("15 16"); // 1540
		aInput.add("1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10 1 1 11 11 12 12 13 13 14 14 15 15 11");
		aInput.add("15 15"); // 950 ok
		aInput.add("1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10 1 11 12 12 13 13 14 14 15 15 11");
		aInput.add("9 9"); // 114 ok
		aInput.add("1 2 2 3 3 1 4 5 5 6 6 4 7 8 8 9 9 7");

		return aInput;
	}

	private static Integer getParentOf(Integer i, Map<Integer, Integer> grpToMainGrp) {
		if (i == null)
			return null;
		Integer parentOf = null;
		Integer lastKnownParentOf = i;
		parentOf = grpToMainGrp.get(i);
		while (parentOf != null) {
			lastKnownParentOf = parentOf;
			parentOf = grpToMainGrp.get(parentOf);
		}
		return lastKnownParentOf;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt(); // nb of queries

		for (int a0 = 0; a0 < t; a0++) {
			int nbStud = in.nextInt();
			int nbRel = in.nextInt();
			// Parent -> Nodes
			// Map<Integer, List<Integer>> parents = new HashMap<>(); // NOT OPTIMUM
			// Node -> Parent
			Map<Integer, Integer> nodes = new HashMap<>();
			// parent (eg. group ID) -> nb of cycles
			Map<Integer, Integer> grpCycles = new HashMap<>();
			// parent (eg. group ID) -> nb of non-cycling links
			Map<Integer, Integer> grpLinks = new HashMap<>();
			// group of groups, by groupID (faster)
			Map<Integer, Integer> grpToMainGrp = new HashMap<>();
			for (int a1 = 0; a1 < nbRel; a1++) {
				int x = in.nextInt();
				int y = in.nextInt();

				// goal: identify nb of groups & of cycles
				// eg. : what is the parent of each node at the end
				// how many distinct parents --> nb of groups and size of each group
				// cycles detection: link created towards exiting node with same parent
				// node

				// c'est parti.
				// your code goes here

				Integer knownX = nodes.get(x);
				Integer knownY = nodes.get(y);
				// vertex on two new nodes --> two new parents
				if (knownX == null && knownY == null) {
					nodes.put(x, x); // parent is x
					nodes.put(y, x);
					List<Integer> nodesForParentX = new ArrayList<>();
					nodesForParentX.add(x);
					nodesForParentX.add(y);
					// parents.put(x, nodesForParentX); // NOT OPTIMUM
					debugln("initializing grpLinks for x=" + x);
					grpLinks.put(x, 1);
				}
				// vertex on one new node only --> attach new node to parent
				else if (knownX != null ^ knownY != null) {
					if (knownX != null) {
						knownX = getParentOf(knownX, grpToMainGrp);
						nodes.put(y, knownX);
						// parents.get(knownX).add(y);// NOT OPTIMUM
						debugln("adding link for knownX=" + knownX + " and y=" + y);
						grpLinks.put(knownX, grpLinks.get(knownX) + 1);
					} else {
						knownY = getParentOf(knownY, grpToMainGrp);
						nodes.put(x, knownY);
						// parents.get(knownY).add(x);// NOT OPTIMUM
						debugln("adding link for knownY=" + knownY + " and x=" + x);
						grpLinks.put(knownY, grpLinks.get(knownY) + 1);
					}
				}

				// vertex on two known nodes --> either link two groups (update parent
				// of one) or cycle
				else {
					// cycle?
					// only compare parent groups
					Integer parentOfX = getParentOf(knownX, grpToMainGrp);
					// Integer lastKnownParentOfX = knownX;
					// parentOfX = grpToMainGrp.get(knownX);
					// while (parentOfX != null) {
					// lastKnownParentOfX = parentOfX;
					// parentOfX = grpToMainGrp.get(parentOfX);
					// }
					// parentOfX = lastKnownParentOfX;

					Integer parentOfY = getParentOf(knownY, grpToMainGrp);
					// Integer lastKnownParentOfY = knownY;
					// parentOfY = grpToMainGrp.get(knownY);
					// while (parentOfY != null) {
					// lastKnownParentOfY = parentOfY;
					// parentOfY = grpToMainGrp.get(parentOfY);
					// }
					// parentOfY = lastKnownParentOfY;

					debugln("Cycle or grp link? " + knownX + "(" + parentOfX + ") / " + knownY + "(" + parentOfY + ")");

					if (parentOfX.equals(parentOfY)) { // knownX.equals(knownY)
						grpLinks.put(parentOfX, grpLinks.get(parentOfX) + 1);
						Integer currentNbOfCycles = grpCycles.get(parentOfX);
						int currentXNbOfCycles = grpCycles.get(knownX) == null ? 0 : grpCycles.get(knownX);
						if (currentNbOfCycles == null || currentNbOfCycles == 0) {
							debugln("adding " + currentXNbOfCycles + " + 1 cycles to " + parentOfX);
							grpCycles.put(parentOfX, currentXNbOfCycles + 1);
						} else {
							debugln("adding " + currentNbOfCycles + " + " + currentXNbOfCycles + " + 1 cycles to " + parentOfX);
							if (!parentOfX.equals(knownX)) {
								grpCycles.put(parentOfX, currentNbOfCycles + currentXNbOfCycles + 1);
							} else {
								grpCycles.put(parentOfX, currentNbOfCycles + 1);
							}
						}
						if (!parentOfX.equals(knownX)) {
							grpCycles.put(knownX, 0);
							grpLinks.put(knownX, 0);
						}

						// grpLinks.put(knownX, grpLinks.get(knownX) + 1); // it is still
						// some
						// // kind of Link ;)
						// Integer currentNbOfCycles = grpCycles.get(knownX);
						// if (currentNbOfCycles == null || currentNbOfCycles == 0) {
						// grpCycles.put(knownX, 1);
						// } else {
						// grpCycles.put(knownX, currentNbOfCycles + 1);
						// }

					} else { // two groups just got linked together
						// Better: Keep groups separated but track a link between them

						debugln("linking " + knownY + "(" + parentOfY + ") and " + knownX + "(" + parentOfX + ")");
						// X is subGrp of either Y or parent of Y
						grpToMainGrp.put(knownX, parentOfY);
						// alert other impacted groups
						// for (Entry<Integer, Integer> elem : grpToMainGrp.entrySet()) {
						// if (elem.getValue().equals(knownX)) {
						// debugln("setting for key " + elem.getKey() + " from val " +
						// elem.getValue() + " to val " + parentOfY);
						// elem.setValue(parentOfY);
						// }
						// }

						debugln("merging " + parentOfY + " and " + knownX + " : " + grpLinks.get(parentOfY) + " + "
								+ grpLinks.get(knownX) + " links + 1");
						if (!knownX.equals(parentOfX)) {
							grpLinks.put(parentOfY, grpLinks.get(parentOfY) + grpLinks.get(parentOfX) + grpLinks.get(knownX) + 1);
							grpLinks.put(parentOfX, 0);
						} else {
							grpLinks.put(parentOfY, grpLinks.get(parentOfY) + grpLinks.get(knownX) + 1);
						}
						grpLinks.put(knownX, 0);

						// including nb of group cycles & nb non-cyclic links
						Integer currentYNbOfCycles = grpCycles.get(parentOfY);
						int currentXNbOfCycles = grpCycles.get(knownX) == null ? 0 : grpCycles.get(knownX);
						if (currentYNbOfCycles == null || currentYNbOfCycles == 0) {
							grpCycles.put(parentOfY, currentXNbOfCycles);
						} else {
							grpCycles.put(parentOfY, currentYNbOfCycles + currentXNbOfCycles);
						}
						grpCycles.put(knownX, 0);
						grpCycles.put(parentOfX, 0);

						// grpLinks.put(parentOfY, grpLinks.get(parentOfY) +
						// grpLinks.get(knownX) + 1);
						// grpLinks.put(knownX, 0);
						// // including nb of group cycles & nb non-cyclic links
						// Integer currentYNbOfCycles = grpCycles.get(parentOfY);
						// int currentXNbOfCycles = grpCycles.get(knownX) == null ? 0 :
						// grpCycles.get(knownX);
						// if (currentYNbOfCycles == null || currentYNbOfCycles == 0) {
						// grpCycles.put(parentOfY, currentXNbOfCycles);
						// } else {
						// grpCycles.put(parentOfY, currentYNbOfCycles +
						// currentXNbOfCycles);
						// }
						// grpCycles.put(knownX, 0);

						// ******* WORKS BUT TOO SLOW **************
						// transform one parent (X) to the other (Y)
						// List<Integer> nodesAttachedToX = parents.get(knownX);
						// parents.get(knownY).addAll(nodesAttachedToX);
						// parents.put(knownX, null);
						// grpLinks.put(knownY, grpLinks.get(knownY) + grpLinks.get(knownX)
						// + 1);
						// for (int oldXNode : nodesAttachedToX) {
						// nodes.put(oldXNode, knownY);
						// }
						// grpLinks.put(knownX, null);
						// // including nb of group cycles & nb non-cyclic links
						// Integer currentYNbOfCycles = grpCycles.get(knownY);
						// int currentXNbOfCycles = grpCycles.get(knownX) == null ? 0 :
						// grpCycles.get(knownX);
						// if (currentYNbOfCycles == null || currentYNbOfCycles == 0) {
						// grpCycles.put(knownY, currentXNbOfCycles);
						// } else {
						// grpCycles.put(knownY, currentYNbOfCycles + currentXNbOfCycles);
						// }
						// grpCycles.put(knownX, 0);
					}
				}

			} // end reading all links from scanner

			// reconstruct mapping to parents
			Map<Integer, List<Integer>> parents = new HashMap<>();
			for (Entry<Integer, Integer> elem : nodes.entrySet()) {
				Integer realParentOfNode = grpToMainGrp.get(elem.getValue()) == null ? elem.getValue()
						: grpToMainGrp.get(elem.getValue());
				List<Integer> nodesForParent = parents.get(realParentOfNode);
				if (nodesForParent == null) {
					List<Integer> newList = new ArrayList<>();
					newList.add(elem.getKey());
					parents.put(realParentOfNode, newList);
				} else {
					nodesForParent.add(elem.getKey());
				}
			}

			// ****** DEBUG stuff ***********
			// for (Integer parent : parents.keySet()) {
			// debug("group parent " + parent + ": ");
			// for (Integer node : parents.get(parent)) {
			// debug(node + ", ");
			// }
			// debugln("");
			// }
			// for (Entry<Integer, Integer> entry : grpCycles.entrySet()) {
			// debugln("group parent " + entry.getKey() + " has " + entry.getValue() +
			// " cycles");
			// Integer realParentOfNode = grpToMainGrp.get(entry.getKey()) == null ?
			// entry.getKey()
			// : grpToMainGrp.get(entry.getKey());
			// if (!realParentOfNode.equals(entry.getKey())) {
			// debugCritln("****** changing val *********");
			// grpCycles.put(realParentOfNode, grpCycles.get(realParentOfNode) +
			// entry.getValue());
			// grpCycles.put(entry.getKey(), 0);
			// }
			// }
			// for (Entry<Integer, Integer> entry : grpCycles.entrySet()) {
			// debugln("(2) group parent " + entry.getKey() + " has " +
			// entry.getValue() + " cycles");
			// }
			// for (Entry<Integer, Integer> entry : grpLinks.entrySet()) {
			// debugln("group parent " + entry.getKey() + " has " + entry.getValue() +
			// " links");
			// Integer realParentOfNode = grpToMainGrp.get(entry.getKey()) == null ?
			// entry.getKey()
			// : grpToMainGrp.get(entry.getKey());
			// if (!realParentOfNode.equals(entry.getKey())) {
			// debugCritln("****** changing val *********");
			// grpLinks.put(realParentOfNode, grpLinks.get(realParentOfNode) +
			// entry.getValue());
			// grpLinks.put(entry.getKey(), 0);
			// }
			// }
			// for (Entry<Integer, Integer> entry : grpLinks.entrySet()) {
			// debugln("(2) group parent " + entry.getKey() + " has " +
			// entry.getValue() + " links");
			// }

			// result is:
			// 1 group only : no links-no-cycle (2 + 6 + 12 + ... from series) + last
			// one from series * nbOfCycles

			long result = 0;
			int totNbOfCycles = 0;
			long overallLastNbOfLinks = 0;
			long lastNbOfLinks = 0;
			// do big groups first
			Map<Integer, Integer> orderedGroups = new HashMap<>();
			for (Integer parent : parents.keySet()) {
				int nbOfCycles = grpCycles.get(parent) == null ? 0 : grpCycles.get(parent);
				int nbOfLinks = grpLinks.get(parent) == null ? 0 : grpLinks.get(parent);
				if (nbOfLinks > 0) {
					int nbOfLinksWhichAreNotCycles = nbOfLinks - nbOfCycles;
					orderedGroups.put(parent, nbOfLinksWhichAreNotCycles);
				}
			}
			orderedGroups = sortByValue(orderedGroups);
			for (Integer parent : orderedGroups.keySet()) { // parents.keySet()
				int nbOfCycles = grpCycles.get(parent) == null ? 0 : grpCycles.get(parent);
				Integer nbOfLinks = grpLinks.get(parent);
				int nbOfLinksWhichAreNotCycles = nbOfLinks - nbOfCycles;
				debugln("group " + parent + " has nbOfLinksWhichAreNotCycles = " + nbOfLinksWhichAreNotCycles);

				for (int n = 1; n <= nbOfLinksWhichAreNotCycles; n++) {
					lastNbOfLinks = (n * (n + 1));
					result = result + overallLastNbOfLinks + lastNbOfLinks;
					debugln(
							"adding " + lastNbOfLinks + " into result with existing links in other groups " + overallLastNbOfLinks);
				}
				overallLastNbOfLinks += lastNbOfLinks;
				totNbOfCycles += nbOfCycles;
			} // loop on all group IDs
			debugln("result before adding " + totNbOfCycles + " cycles :" + result + " with currently " + overallLastNbOfLinks
					+ " links");
			// finally add the cycles links (just adds same nb of links again)
			result = result + (totNbOfCycles * overallLastNbOfLinks);
			debugln("result after cycles : " + result);

			System.out.println(result);

		} // end test case

	}

	// using Collections.reverseOrder() to sort decreasingly
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	@SuppressWarnings("unused")
	private static void debug(String iStr) {
		if (_debug) {
			System.err.print(iStr);
		}
	}

	private static void debugln(String iStr) {
		if (_debug) {
			System.err.println(iStr);
		}
	}

	@SuppressWarnings("unused")
	private static void debugCrit(String iStr) {
		if (_critical || _debug) {
			System.err.print(iStr);
		}
	}

	private static void debugCritln(String iStr) {
		if (_critical || _debug) {
			System.err.println(iStr);
		}
	}

	private static final int find(int[] parent, int i) {
		int p = i;
		while (parent[p] != p) {
			p = parent[p];
		}
		return parent[i] = p;
	}

	private static void solution() {
		try (Scanner scanner = new Scanner(System.in)) {
			int q = scanner.nextInt();
			while (q-- > 0) {
				int n = scanner.nextInt(), m = scanner.nextInt(), parent[] = new int[n], friends[] = new int[n],
						friendships[] = new int[n];
				for (int i = 0; i < n; i++) {
					parent[i] = i;
					friends[i] = 1;
				}
				for (int i = 0; i < m; i++) {
					int x = scanner.nextInt() - 1, y = scanner.nextInt() - 1;
					int px = find(parent, x), py = find(parent, y);
					if (px < py) {
						parent[py] = px;
						friends[px] += friends[py];
						friendships[px] += friendships[py] + 1;
					} else if (py < px) {
						parent[px] = py;
						friends[py] += friends[px];
						friendships[py] += friendships[px] + 1;
					} else {
						friendships[px]++;
					}
				}
				List<Integer> groups = new ArrayList<>(/*
																								 * todo add estimate, break early
																								 * when reached
																								 */);
				int leftover = 0;
				for (int i = 0; i < n; i++) {
					if (parent[i] == i && friends[i] > 1) {
						groups.add(friends[i]);
						leftover += friendships[i] - (friends[i] - 1);
					}
				}
				Collections.sort(groups, Collections.reverseOrder());
				long sum = 0L, state = 0L;
				for (int group : groups) {
					sum += state * (group - 1);
					sum += ((long) (group - 1)) * group * (group + 1) / 3;
					state += ((long) (group - 1)) * group;
				}
				sum += leftover * state;
				System.out.println(sum);
			}
		}
	}
}
