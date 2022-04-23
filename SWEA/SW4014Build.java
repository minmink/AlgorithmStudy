package algorithm;

import java.io.*;
import java.util.*;

public class SW4014Build {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j * 2) - '0';
				}
			}
			
			int answer = 0;
			for (int row = 0; row < N; row++) {
				boolean success = true;
				int pre = map[row][0];
				int cnt = 1;
				search:
				for (int col = 1; col < N; col++) {
					if(pre == map[row][col]) {
						cnt++;
					} else if(pre + 1 == map[row][col] && cnt >= X) {
						pre = map[row][col];
						cnt = 1;
					} else if(pre == map[row][col] + 1) {
						for (int c = col; c < col + X; c++) {
							if(c >= N || map[row][c] != map[row][col]) {
								success = false;
								break search;
							}
						}
						pre = map[row][col];
						cnt = 0;
						col += X - 1;
					} else {
						success = false;
						break search;
					}
				}
				if(success)
					answer++;
			}
			
			for (int col = 0; col < N; col++) {
				boolean success = true;
				int pre = map[0][col];
				int cnt = 1;
				search:
				for (int row = 1; row < N; row++) {
					if(pre == map[row][col]) {
						cnt++;
					} else if(pre + 1 == map[row][col] && cnt >= X) {
						pre = map[row][col];
						cnt = 1;
					} else if(pre == map[row][col] + 1) {
						for (int r = row; r < row + X; r++) {
							if(r >= N || map[r][col] != map[row][col]) {
								success = false;
								break search;
							}
						}
						pre = map[row][col];
						cnt = 0;
						row += X - 1;
					} else {
						success = false;
						break search;
					}
				}
				if(success)
					answer++;
			}
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
