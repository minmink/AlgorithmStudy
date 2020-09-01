package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14889Startlink {

	static int N, min = Integer.MAX_VALUE;
	static int[][] matrix;
	static int[] comb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		comb = new int[N];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeComb(0, 0);
		
		System.out.println(min);
	}

	private static void makeComb(int idx, int cnt) {
		if(cnt == N/2) {
			calc();
			return;
		}
		for (int i = idx; i < N; i++) {
			comb[i] = 1;
			makeComb(i+1, cnt+1);
			comb[i] = 0;
		}
	}

	private static void calc() {
		int sum1 = 0, sum2 = 0, idx1 = 0, idx2 = 0;
		int[] team1 = new int[N/2], team2 = new int[N/2];
		for (int i = 0; i < N; i++) {
			if(comb[i]==0) team1[idx1++] = i;
			else team2[idx2++] = i;
		}
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < N/2; j++) {
				sum1 += matrix[team1[i]][team1[j]];
				sum2 += matrix[team2[i]][team2[j]];
			}
		}
		int diff = Math.abs(sum1-sum2);
		if(diff<min) min = diff;
	}

}
