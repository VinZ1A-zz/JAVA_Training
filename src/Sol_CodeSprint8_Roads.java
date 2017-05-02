import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_CodeSprint8_Roads {

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

		// expects 4 then 12
		aInput.add("2"); // 2 queries
		aInput.add("3 3 2 1"); // 3 cities, 3 roads, clib=2, croad=1
		aInput.add("1 2");
		aInput.add("3 1");
		aInput.add("2 3");
		aInput.add("6 6 2 5"); // 6 cities, 6 roads, clib=2, croad=5
		aInput.add("8 9 5 2"); // 8 cities, 9 roads, clib=5, croad=2
		aInput.add("1 3");
		aInput.add("3 4");
		aInput.add("2 4");
		aInput.add("7 8");
		aInput.add("1 2");
		aInput.add("2 3");
		aInput.add("5 6");// added vinz
		aInput.add("7 5");// added vinz
		aInput.add("2 7"); // added vinz

		// expected output
		// 805
		// 184
		// 80
		// 5
		// 204
		// aInput.add("5");
		// aInput.add("9 2 91 84");
		// aInput.add("8 2");
		// aInput.add("2 9");
		// aInput.add("5 9 92 23");
		// aInput.add("2 1");
		// aInput.add("5 3");
		// aInput.add("5 1");
		// aInput.add("3 4");
		// aInput.add("3 1");
		// aInput.add("5 4");
		// aInput.add("4 1");
		// aInput.add("5 2");
		// aInput.add("4 2");
		// aInput.add("8 3 10 55");
		// aInput.add("6 4");
		// aInput.add("3 2");
		// aInput.add("7 1");
		// aInput.add("1 0 5 3");
		// aInput.add("2 0 102 1");

		// read from file
		// doReadFromFile(aInput);

		return aInput;
	}

	private static void doReadFromFile(Vector<String> aInput) {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			// fr = new FileReader("CodeSprint8_Roads_UseCase_expected.txt");
			// br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader("CodeSprint8_Roads_UseCase.txt")); // CodeSprint8_Roads_UseCase
																						// //CodeSprint8_Roads_UseCase_short

			while ((sCurrentLine = br.readLine()) != null) {
				// System.out.println(sCurrentLine);
				aInput.add(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}

	/*--
	 * if cost(lib) <= cost (road)
	 *   min cost = nb of cities X cost(lib)
	 * else
	 * 	 min cost = MST of each group X cost(road) + nb of groups X cost(lib)
	 * 
	 */
	private static void doIt() {
		Scanner in = new Scanner(System.in);
		long q = in.nextInt();
		for (int queryNb = 0; queryNb < q; queryNb++) {
			long nbCities = in.nextInt(); // nb of cities
			// TreeMap not efficient
			Map<Long, Long> cityToGrp = new HashMap<Long, Long>((int) nbCities);
			Map<Long, ArrayList<Long>> grpToCities = new HashMap<Long, ArrayList<Long>>((int) (nbCities / 4));
			long nbRoads = in.nextInt();
			long costLib = in.nextInt();
			long costRoad = in.nextInt();

			// no point building roads if libs are cheaper
			if (costLib <= costRoad || nbRoads == 0) {
				// System.err.println(costLib + " * " + nbCities + " = " +
				// costLib * nbCities);
				System.out.println(costLib * nbCities);

				// flush remaining data
				for (int roadNb = 0; roadNb < nbRoads; roadNb++) {
					long cityNb1 = in.nextInt();
					long cityNb2 = in.nextInt();
				}

				continue; // next query
			}

			long curGrpNb = 0;
			long nbOfDeletedGroups = 0;
			long nbRoadsToBuild = 0;
			for (int roadNb = 0; roadNb < nbRoads; roadNb++) {
				long cityNb1 = in.nextInt();
				long cityNb2 = in.nextInt();

				// ***** SEARCHes (non ordered) // **********
				Long grpOfCity1 = cityToGrp.get(cityNb1);
				Long grpOfCity2 = cityToGrp.get(cityNb2);
				// System.err.println("cit" + cityNb1 + ":" + grpOfCity1 + " ;
				// cit" + cityNb2 + ":" + grpOfCity2);

				// determine nb of groups and MST ( minimum spanning tree with
				// equal weights)
				// if both cities not already connected --> new road in new
				// group
				if (grpOfCity1 == null && grpOfCity2 == null) {
					++curGrpNb;
					// add (not ordered )
					cityToGrp.put(cityNb1, curGrpNb);
					cityToGrp.put(cityNb2, curGrpNb);
					ArrayList<Long> listOfCities = new ArrayList<Long>();
					listOfCities.add(cityNb1);
					listOfCities.add(cityNb2);
					grpToCities.put(curGrpNb, listOfCities);
					nbRoadsToBuild++;
				}

				// if one city only not connected --> new road in existing group
				if ((grpOfCity1 != null && grpOfCity2 == null) || (grpOfCity2 != null && grpOfCity1 == null)) {
					nbRoadsToBuild++;
					if (grpOfCity1 != null) {
						// ADD (non ordered) **************
						cityToGrp.put(cityNb2, grpOfCity1);
						// if (grpToCities.containsKey(grpOfCity1)) {
						grpToCities.get(grpOfCity1).add(cityNb2);
						// } else {
						// ArrayList<Long> listOfCities = new ArrayList<Long>();
						// listOfCities.add(cityNb2);
						// grpToCities.put(grpOfCity1, listOfCities);
						// }
					} else {
						// ADD (non ordered) **************
						cityToGrp.put(cityNb1, grpOfCity2);
						// if (grpToCities.containsKey(grpOfCity2)) {
						grpToCities.get(grpOfCity2).add(cityNb1);
						// } else {
						// ArrayList<Long> listOfCities = new ArrayList<Long>();
						// listOfCities.add(cityNb1);
						// grpToCities.put(grpOfCity2, listOfCities);
						// }

					}
				}

				// if both cities connected already
				if (grpOfCity1 != null && grpOfCity2 != null) {
					// connected to different groups --> need a new road
					if (grpOfCity1.intValue() != grpOfCity2.intValue()) {
						// both cities connected to different groups -->
						// consolidate grp nb (take min (grp1,grp2))
						nbRoadsToBuild++;
						nbOfDeletedGroups++;
						long newGrpNb = Math.min(grpOfCity1, grpOfCity2);
						long oldGrpNb = Math.max(grpOfCity1, grpOfCity2);
						// SEARCH all cities, get current groupNb, change to
						// MIN(grp1,grp2) when max detected

						// get list of cities belonging to oldGrpNb
						ArrayList<Long> listOfCities = grpToCities.get(oldGrpNb);
						// add same list with different key newGrpNb
						grpToCities.get(newGrpNb).addAll(listOfCities);
						// for all cities in this list, go to map cityToGrp and
						// update
						for (long aCity : listOfCities) {
							cityToGrp.put(aCity, newGrpNb);
						}
						// remove oldGrpNb from map (remove does not work -- add
						// empty array instead)
						// grpToCities.put(oldGrpNb, new ArrayList<Long>());
						grpToCities.remove(oldGrpNb); // should be OK??

						// COSTS TOO MUCH!!!
						// for (Iterator<Integer> cityToGroupIter =
						// cityToGrp.keySet().iterator(); cityToGroupIter
						// .hasNext();) {
						// int curCity = cityToGroupIter.next();
						// if (cityToGrp.get(curCity) == oldGrpNb) {
						// cityToGrp.put(curCity, newGrpNb);
						// }
						// }
					}
				}

			}

			// debug
			// for (Entry<Integer, Integer> cityToGrpEntry :
			// cityToGrp.entrySet()) {
			// System.err.println(cityToGrpEntry.getKey() + " - " +
			// cityToGrpEntry.getValue());
			// }

			// add isolated cities in own group -- could add in main loop
			for (long i = 1; i <= nbCities; i++) {
				if (!cityToGrp.containsKey(i)) {
					curGrpNb++;
				}
			}

			// System.err.println("nbRoadsToBuild =" + nbRoadsToBuild);
			// System.err.println("nb of distinct groups =" + (curGrpNb -
			// nbOfDeletedGroups));
			// System.err.println("min cost = " + (nbRoadsToBuild * costRoad +
			// (curGrpNb - nbOfDeletedGroups) * costLib));
			System.err.println("nbRoadsToBuild = " + nbRoadsToBuild);
			System.err.println("costRoad = " + costRoad);
			System.err.println("curGrpNb = " + curGrpNb);
			System.err.println("nbOfDeletedGroups = " + nbOfDeletedGroups);
			System.err.println("costLib = " + costLib);
			System.err.println("grp size = " + grpToCities.size());
			System.out.println(nbRoadsToBuild * costRoad + (curGrpNb - nbOfDeletedGroups) * costLib);

			// System.err.println("-- next --");
		}

	}
}
