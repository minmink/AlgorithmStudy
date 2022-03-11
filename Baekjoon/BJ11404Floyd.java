package algorithm;

import java.io.*;
import java.util.*;

public class BJ11404Floyd {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] cost = new long[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cost[i][j] = Long.MAX_VALUE;
			}
			cost[i][i] = 0;
		}
		int m = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			cost[start][end] = Math.min(cost[start][end], d);
		}
		StringBuilder sb = new StringBuilder();
		
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (i == k)
					continue;
				for (int j = 0; j < n; j++) {
					if (j == k || i == j)
						continue;
					if (cost[i][k] != Long.MAX_VALUE && cost[k][j] != Long.MAX_VALUE)
						cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (cost[i][j] == Long.MAX_VALUE)
					sb.append(0);
				else
					sb.append(cost[i][j]);
				if (j < n - 1)
					sb.append(" ");
				else
					sb.append("\n");
			}
		}
		
		System.out.print(sb.toString());
	}
}
