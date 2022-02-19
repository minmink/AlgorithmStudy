package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ12865Backpack {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] W = new int[N + 1];
		int[] V = new int[N + 1];
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (W[i] <= j)
					dp[i][j] = Math.max(dp[i - 1][j], V[i] + dp[i - 1][j - W[i]]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}
		System.out.println(dp[N][K]);
		br.close();
	}
}
