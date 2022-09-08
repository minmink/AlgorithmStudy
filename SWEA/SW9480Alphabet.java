package algorithm;

import java.io.*;

public class SW9480Alphabet {
	static int standard = 0, N, answer;
	static int[] bits;
	static int[][] combs = new int[16][16];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			standard = 1 + (standard << 1);
		}
		combs[0][0] = 1;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			answer = 0;
			bits = new int[N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				int[] cnt = new int[26];
				for (int j = 0; j < s.length(); j++) {
					cnt[s.charAt(j) - 'a']++;
				}
				int temp = 0;
				for (int j = 0; j < 26; j++) {
					temp = (cnt[j] > 0 ? 1 : 0) + (temp << 1);
				}
				bits[i] = temp;
			}
			
			comb(0, 0);
			sb.append(answer).append("\n");
		}

		System.out.println(sb.toString());
	}
	
	private static void comb(int cur, int sum) {
		if(cur == N)
			return;
		
		int tempSum = sum | bits[cur];
		if((tempSum & standard) == standard) {
			answer++;
			for (int i = 1; i < N - cur; i++) {
				answer += getComb(N - cur - 1, i);
			}
			
			comb(cur + 1, sum);
			
			return;
		}
		
		comb(cur + 1, tempSum);
		comb(cur + 1, sum);
	}
	
	private static int getComb(int n, int r) {
		if(combs[n][r] != 0)
			return combs[n][r];
		
		if(n == r || r == 0)
			return combs[n][r] = 1;
		else {
			combs[n][r] = getComb(n-1, r-1) + getComb(n-1, r);
			return combs[n][r];
		}
	}
}
