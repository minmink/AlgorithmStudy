package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501Call {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st;
		int N = Integer.parseInt(s);
		int[] dp = new int[N + 1];
		// 시간, 이익
		int[][] queue = new int[N][2];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			queue[i][0] = Integer.parseInt(st.nextToken());
			queue[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int n = N - 1; n >= 0; n--) {
			dp[n] = dp[n + 1];
			if (n + queue[n][0] > N)
				continue;
			dp[n] = Math.max(dp[n], queue[n][1] + dp[n + queue[n][0]]);
		}
		
		System.out.println(dp[0]);
	}

}
