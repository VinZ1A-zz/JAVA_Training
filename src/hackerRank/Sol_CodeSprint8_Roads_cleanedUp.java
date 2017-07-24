package hackerRank;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Sol_CodeSprint8_Roads_cleanedUp {

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

		return aInput;
	}

	private static void doIt() {
		Scanner in = new Scanner(System.in);
		long q = in.nextInt();
		for (int queryNb = 0; queryNb < q; queryNb++) {
			int nbCities = in.nextInt(); // nb of cities
			// Note: TreeMap not efficient
			Map<Integer, Integer> cityToGrp = new HashMap<Integer, Integer>((int) nbCities);
			// more efficient mapping for re-grouping
			Map<Integer, ArrayList<Integer>> grpToCities = new HashMap<Integer, ArrayList<Integer>>(
					(int) (nbCities / 4));
			long nbRoads = in.nextInt();
			long costLib = in.nextInt();
			long costRoad = in.nextInt();

			// no point building roads if libs are cheaper
			if (costLib <= costRoad || nbRoads == 0) {
				System.out.println(costLib * nbCities);

				// flush remaining data
				for (int roadNb = 0; roadNb < nbRoads; roadNb++) {
					int cityNb1 = in.nextInt();
					int cityNb2 = in.nextInt();
				}
				continue; // next query
			}

			int curGrpNb = 0;
			int nbOfDeletedGroups = 0;
			int nbRoadsToBuild = 0;
			for (int roadNb = 0; roadNb < nbRoads; roadNb++) {
				int cityNb1 = in.nextInt();
				int cityNb2 = in.nextInt();

				Integer grpOfCity1 = cityToGrp.get(cityNb1);
				Integer grpOfCity2 = cityToGrp.get(cityNb2);

				// if both cities not already connected --> new road in new
				// group
				if (grpOfCity1 == null && grpOfCity2 == null) {
					++curGrpNb;
					cityToGrp.put(cityNb1, curGrpNb);
					cityToGrp.put(cityNb2, curGrpNb);
					ArrayList<Integer> listOfCities = new ArrayList<Integer>();
					listOfCities.add(cityNb1);
					listOfCities.add(cityNb2);
					grpToCities.put(curGrpNb, listOfCities);
					nbRoadsToBuild++;
				}

				// if one city only not connected --> new road in existing group
				if ((grpOfCity1 != null && grpOfCity2 == null) || (grpOfCity2 != null && grpOfCity1 == null)) {
					nbRoadsToBuild++;
					if (grpOfCity1 != null) {
						cityToGrp.put(cityNb2, grpOfCity1);
						grpToCities.get(grpOfCity1).add(cityNb2);
					} else {
						cityToGrp.put(cityNb1, grpOfCity2);
						grpToCities.get(grpOfCity2).add(cityNb1);
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
						int newGrpNb = Math.min(grpOfCity1, grpOfCity2);
						int oldGrpNb = Math.max(grpOfCity1, grpOfCity2);
						// SEARCH all cities, get current groupNb, change to
						// MIN(grp1,grp2) when max detected
						// get list of cities belonging to oldGrpNb
						ArrayList<Integer> listOfCities = grpToCities.get(oldGrpNb);
						// add same list with different key newGrpNb
						grpToCities.get(newGrpNb).addAll(listOfCities);
						// for all cities in this list, go to map cityToGrp and
						// update
						for (int aCity : listOfCities) {
							cityToGrp.put(aCity, newGrpNb);
						}
						grpToCities.remove(oldGrpNb);

					}
				}

			}

			// add isolated cities in own group -- could add in main loop
			for (int i = 1; i <= nbCities; i++) {
				if (!cityToGrp.containsKey(i)) {
					curGrpNb++;
				}
			}

			System.out.println(nbRoadsToBuild * costRoad + (curGrpNb - nbOfDeletedGroups) * costLib);
		}

	}
}
