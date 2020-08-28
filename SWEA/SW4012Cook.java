package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW4012Cook {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			int N = Integer.parseInt(br.readLine());
			int[][] s = new int[N][N];
			int[] groupComb = new int[N];
			int[] group1 = new int[N/2], group2 = new int[N/2];
			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = N-1; i >= N/2; i--) {
				groupComb[i] = 1;
			}
			
			do {
				int sum1 = 0, sum2 = 0;
				int idx1 = 0, idx2 = 0;
				for (int i = 0; i < N; i++) {
					if(groupComb[i] == 0) group1[idx1++] = i;
					else group2[idx2++] = i;
				}
				
				for (int i = 0; i < N/2; i++) {
					for (int j = 0; j < N/2; j++) {
						if(i!=j) {
							sum1 += s[group1[i]][group1[j]];
							sum2 += s[group2[i]][group2[j]];
						}
					}
				}
				
				int diff = Math.abs(sum1 - sum2);
				if(answer > diff) answer = diff;
			} while(np(groupComb));
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static boolean np(int[] comb) {
		int i = comb.length-1;
		while(i>0 && comb[i-1]>=comb[i]) i--;
		if(i == 0) return false;
		
		int j = comb.length-1;
		while(comb[i-1]>=comb[j]) j--;
		swap(i-1, j, comb);
		
		int k = comb.length-1;
		while(i<k) swap(i++, k--, comb);
		
		return true;
	}

	private static void swap(int i, int j, int[] comb) {
		int temp = comb[i];
		comb[i] = comb[j];
		comb[j] = temp;
	}
	
}
