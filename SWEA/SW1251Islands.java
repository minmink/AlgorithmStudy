package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW1251Islands {

	static int[] root;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[][] islands = new int[N][2];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				islands[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				islands[i][1] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine().trim());
			Edge[] bridges = new Edge[N*(N-1)/2];
			int idx = 0;
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					long len1 = islands[i][0] - islands[j][0];
					long len12 = len1 * len1;
					long len2 = islands[i][1] - islands[j][1];
					long len22 = len2 *len2;
					double len = Math.sqrt(len12 + len22);

					bridges[idx++] = new Edge(i, j, len);
				}
			}
			Arrays.sort(bridges);
			root = new int[N];
			for (int i = 1; i < N; i++) {
				root[i] = i;
			}
			double len = 0;
			int cnt = 0;
			idx = 0;
			while(cnt < N-1) {
				Edge cur = bridges[idx++];
				if(union(cur.from, cur.to)) {
					len += cur.len * cur.len * E;
					cnt++;
				}
			}
			sb.append("#").append(testCase).append(" ").append(String.format("%.0f", len)).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static int findSet (int x) {
		if(root[x] == x) return x;
		return root[x] = findSet(root[x]);
	}
	
	public static boolean union (int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if(rootA == rootB) return false;
		root[rootB] = rootA;
		return true;
	}
	
	public static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double len;
		
		public Edge(int from, int to, double len) {
			super();
			this.from = from;
			this.to = to;
			this.len = len;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.len>o.len) return 1;
			else if (this.len == o.len) return 0;
			else return -1;
		}
		
	}
}
