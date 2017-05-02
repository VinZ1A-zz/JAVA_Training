import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Sol_CodeSprint8_Roads_Solution {

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
		// aInput.add("6 6 2 5"); // 6 cities, 6 roads, clib=2, croad=5
		aInput.add("8 9 5 2"); // 6 cities, 6 roads, clib=5, croad=2
		aInput.add("1 3");
		aInput.add("3 4");
		aInput.add("2 4");
		aInput.add("7 8");
		aInput.add("1 2");
		aInput.add("2 3");
		aInput.add("5 6");
		aInput.add("7 5");
		aInput.add("2 7");

		return aInput;
	}

	public static class Edge {
		public int u, v;
		public long w;

		public Edge(int u, int v, long w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}

	public static class UnionFind {
		private int parent[];
		private int rank[];

		public UnionFind(int n) {
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int findSet(int x) {
			while (parent[x] != x) {
				parent[x] = parent[parent[x]];
				x = parent[x];
			}
			return x;
		}

		public void union(int a, int b) {
			int setA = findSet(a);
			int setB = findSet(b);

			if (rank[setA] > rank[setB]) {
				parent[setB] = setA;
			} else {
				parent[setA] = setB;
				if (rank[setA] == rank[setB]) {
					rank[setB] += 1;
				}
			}
		}

		public boolean connected(int a, int b) {
			return (findSet(a) == findSet(b));
		}
	}

	// took 32 secs
	private static void doIt() {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for (int a0 = 0; a0 < q; a0++) {
			long n = in.nextLong();
			long m = in.nextLong();
			long lib = in.nextLong();
			long road = in.nextLong();

			List<Edge> edges = new ArrayList<>();
			for (int a1 = 0; a1 < m; a1++) {
				int city_1 = in.nextInt();
				int city_2 = in.nextInt();
				edges.add(new Edge(city_1, city_2, road));
			}

			UnionFind uf = new UnionFind((int) (n + 1));
			long minCost = n * lib;
			long roadCosts = 0;
			long libCosts = n * lib;

			for (Edge edge : edges) {
				if (!uf.connected(edge.u, edge.v)) {
					uf.union(edge.u, edge.v);
					roadCosts += road;
					libCosts -= lib;
					minCost = Math.min(minCost, roadCosts + libCosts);
				}
			}

			System.out.println(minCost);
		}
		in.close();

	}
}
