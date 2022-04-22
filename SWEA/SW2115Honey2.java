package algorithm;

import java.io.*;
import java.util.*;

public class SW2115Honey2 {
	static int firstMax, secondMax, map[][], C, first[], second[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j * 2) - '0';
				}
			}
			
			int max = 0;
			first = new int[M];
			second = new int[M];
			for (int row1 = 0; row1 < N; row1++) {
				for (int col1 = 0; col1 <= N - M; col1++) {
					for (int i = 0; i < M; i++) {
						first[i] = map[row1][col1 + i];
					}
					firstMax = 0;
					getFirstMax(0, 0, 0);
					
					for (int row2 = row1; row2 < N; row2++) {
						for (int col2 = 0; col2 <= N - M; col2++) {
							if(row1 == row2 && col2 == 0) {
								col2 = col1 + M - 1;
								continue;
							}
							for (int i = 0; i < M; i++) {
								second[i] = map[row2][col2 + i];
							}
							secondMax = 0;
							getSecondMax(0, 0, 0);
							max = Math.max(max, firstMax + secondMax);
						}
					}
					
				}
			}
			
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void getFirstMax(int idx, int sum, int total) {
		for (int i = idx; i < first.length; i++) {
			if(sum + first[i] > C)
				continue;
			firstMax = (int) Math.max(firstMax, total + Math.pow(first[i], 2));
			getFirstMax(i + 1, sum + first[i], (int) (total + Math.pow(first[i], 2)));
		}
	}

	public static void getSecondMax(int idx, int sum, int total) {
		for (int i = idx; i < second.length; i++) {
			if(sum + second[i] > C)
				continue;
			secondMax = (int) Math.max(secondMax, total + Math.pow(second[i], 2));
			getSecondMax(i + 1, sum + second[i], (int) (total + Math.pow(second[i], 2)));
		}
	}
}
