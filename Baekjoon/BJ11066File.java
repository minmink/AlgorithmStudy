package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11066File {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < T; testCase++) {
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int[] cost = new int[K];
			int[][] dp = new int[K][K];
			int[][] size = new int[K][K];
			for (int i = 0; i < K; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
				for (int j = 0; j < K; j++) {
					dp[i][j] = Integer.MAX_VALUE;
				}
				dp[i][i] = size[i][i] = cost[i];
			}
			for (int i = 1; i < K; i++) {
				dp[i - 1][i] = size[i - 1][i] = cost[i - 1] + cost[i];
			}
			for (int d = 1; d < K - 1; d++) {
				for (int i = 1; i < K; i++) {
					if (i + d >= K)
						continue;
					size[i - 1][i + d] = size[i - 1][i + d - 1] + cost[i + d];
					for (int d2 = d + 1; d2 > 0; d2--) {
						int temp = dp[i - 1][i + d - d2] + dp[i + d - d2 + 1][i + d] + size[i - 1][i + d];
						if (i - 1 == i + d - d2)
							temp -= dp[i - 1][i + d - d2];
						else if (i + d - d2 + 1 == i + d)
							temp -= dp[i + d - d2 + 1][i + d];
						dp[i - 1][i + d] = Math.min(dp[i - 1][i + d], temp);
					}
				}
			}
			
			sb.append(dp[0][K - 1] + "\n");
		}
		System.out.print(sb.toString());
	}

}
