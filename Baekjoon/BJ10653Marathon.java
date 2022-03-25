package algorithm;

import java.io.*;
import java.util.*;

public class BJ10653Marathon {
	private static int dp[][], dis[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] points = new int[N][2];
		dis = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			points[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				dis[i][j] = dis[j][i] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
			}
		}
		dp = new int[N][K + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		System.out.println(dfs(N - 1, K));
	}
	
	private static int dfs(int idx, int k) {
		if(idx == 0)
			return 0;
		if(dp[idx][k] != -1)
			return dp[idx][k];
		
		int d = Integer.MAX_VALUE;
		
		for (int i = 0; i <= k; i++) {
			if(idx - i - 1 < 0)
				break;
			d = Math.min(d, dfs(idx - i - 1, k - i) + dis[idx - i - 1][idx]);
		}
		
		return dp[idx][k] = d;
	}
}
