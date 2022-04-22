package algorithm;

import java.io.*;
import java.util.*;

public class SW2105Desert {
	static int N, map[][], max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			max = -1;
			for (int side1 = 1; side1 <= N - 2; side1++) {
				for (int side2 = 1; side2 <= N - 1 - side1; side2++) {
					for (int row = side1; row < N - 1; row++) {
						for (int col = 0; col < N - side2; col++) {
							if(row - side1 < 0 || row + side2 >= N || col + side1 + side2 >= N)
								continue;
							check(side1, side2, row, col);
						}
					}	
				}
			}
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void check(int side1, int side2, int row, int col) {
		int cnt = 0;
		HashSet<Integer> set = new HashSet<>();
		int nr = row;
		int nc = col;
		for (int i = 0; i < side1; i++) {
			nr--;
			nc++;
			if(set.contains(map[nr][nc]))
				return;
			cnt++;
			set.add(map[nr][nc]);
		}
		for (int i = 0; i < side2; i++) {
			nr++;
			nc++;
			if(set.contains(map[nr][nc]))
				return;
			cnt++;
			set.add(map[nr][nc]);
		}
		for (int i = 0; i < side1; i++) {
			nr++;
			nc--;
			if(set.contains(map[nr][nc]))
				return;
			cnt++;
			set.add(map[nr][nc]);
		}
		for (int i = 0; i < side2; i++) {
			nr--;
			nc--;
			if(set.contains(map[nr][nc]))
				return;
			cnt++;
			set.add(map[nr][nc]);
		}
		max = Math.max(max, cnt);
	}
}
