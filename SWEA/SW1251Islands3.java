package algorithm;

import java.io.*;
import java.util.*;

public class SW1251Islands3 {
	static long[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][2];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				map[i][0] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				map[i][1] = Integer.parseInt(st.nextToken());
			}
			double E = Double.parseDouble(br.readLine());
			long answer = 0;
			long[][] graph = new long[N * (N - 1) / 2][];
			parent = new long[N];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					graph[cnt++] = new long[] {i, j, (long) (Math.pow(map[i][0] - map[j][0], 2) + Math.pow(map[i][1] - map[j][1], 2))};
				}
			}
			Arrays.sort(graph, (o1, o2) -> Long.compare(o1[2], o2[2]));
			
			for (int i = 1; i < N; i++) {
				parent[i] = i;
			}
			for (int i = 0; i < N * (N - 1) / 2; i++) {
				// 사이클이 존재하지 않는 경우에만 간선을 선택한다(여기서는 최종 비용만 고려하도록 하겠다).
				if (find((int)graph[i][0]) != find((int)graph[i][1])) {
					union((int)graph[i][0], (int)graph[i][1]);
					answer += graph[i][2];
				}
			}
			
			sb.append((long)Math.round(answer * E)).append("\n");
		}

		System.out.println(sb.toString());;
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		else
			return find((int)parent[x]);
	}
}
