package algorithm;

import java.io.*;
import java.util.*;

public class SW1952SwimmingPool {
	static int threeMonth, min, cost[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int oneDay = Integer.parseInt(st.nextToken());
			int oneMonth = Integer.parseInt(st.nextToken());
			threeMonth = Integer.parseInt(st.nextToken());
			min = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			int[] plan = new int[12];
			cost = new int[12];
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
				cost[i] = Math.min(oneDay * plan[i], oneMonth);
			}
			
			dfs(0, 0);
			
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void dfs(int idx, int total) {
		if (total >= min)
			return;
		if (idx >= 12) {
			min = Math.min(min, total);
			return;
		}
		dfs(idx + 1, total + cost[idx]);
		dfs(idx + 3, total + threeMonth);
	}
}
