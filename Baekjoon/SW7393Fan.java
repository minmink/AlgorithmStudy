package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW7393Fan {

	static long[][][] dp = new long[101][10][1<<10];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		calc();
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			long sum = 0;
			for (int i = 0; i < 10; i++) {
				sum = (sum + dp[N][i][(1<<10)-1]) % 1000000000;
			}
			sb.append("#").append(testCase).append(" ").append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void calc() {
		for (int i = 1; i < 10; i++) {
			dp[1][i][1<<i] = 1;
		}
		
		for (int i = 2; i <= 100; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < (1<<10); k++) {
					int bit = k | (1<<j);
					dp[i][j][bit] = (dp[i][j][bit] + ((0<j?dp[i-1][j-1][k]:0) + (j<9 ? dp[i-1][j+1][k]:0)) % 1000000000) % 1000000000;
				}
			}
		}
	}
}
