package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW4613Russia {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][3]; // W: 0, B: 1, R: 2
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < M; j++) {
					switch (s.charAt(j)) {
					case 'W':
						arr[i][1]++;
						arr[i][2]++;
						break;
					case 'B':
						arr[i][0]++;
						arr[i][2]++;
						break;
					case 'R':
						arr[i][0]++;
						arr[i][1]++;
						break;
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			for (int i = 0; i <= N-3; i++) {
				int temp = 0;
				for (int k = 0; k <= i; k++) {
					temp += arr[k][0];
				}
				for (int j = i+1; j <= N-2; j++) {
					for (int k = i+1; k <= j; k++) {
						temp += arr[k][1];
					}
					for (int k = j+1; k <= N-1; k++) {
						temp += arr[k][2];
					}
					min = Math.min(min, temp);
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(min).append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
