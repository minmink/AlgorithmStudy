package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14890Runway {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int answer = 0;
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int row = 0; row < N; row++) {
			int pre = map[row][0];
			int cnt = 1;
			boolean can = true;
			runway:
			for (int col = 1; col < N; col++) {
				if (pre == map[row][col]) {
					cnt++;
					continue;
				} else if (pre + 1 == map[row][col]) {
					if (cnt < L) {
						can = false;
						break runway;
					}
					pre = map[row][col];
					cnt = 1;
				} else if (map[row][col] + 1 == pre) {
					for (int c = col; c < col + L; c++) {
						if (c == N || map[row][c] != pre - 1) {
							can = false;
							break runway;
						}
						if (c == col + L - 1) {
							col += L - 1;
							pre = map[row][col];
							cnt = 0;
							break;
						}
					}
				} else {
					can = false;
					break runway;
				}
			}
			if (can)
				answer++;
		}
		for (int col = 0; col < N; col++) {
			int pre = map[0][col];
			int cnt = 1;
			boolean can = true;
			runway:
			for (int row = 1; row < N; row++) {
				if (pre == map[row][col]) {
					cnt++;
					continue;
				} else if (pre + 1 == map[row][col]) {
					if (cnt < L) {
						can = false;
						break runway;
					}
					pre = map[row][col];
					cnt = 1;
				} else if (map[row][col] + 1 == pre) {
					for (int r = row; r < row + L; r++) {
						if (r == N || map[r][col] != pre - 1) {
							can = false;
							break runway;
						}
						if (r == row + L - 1) {
							row += L - 1;
							pre = map[row][col];
							cnt = 0;
							break;
						}
					}
				} else {
					can = false;
					break runway;
				}
			}
			if (can)
				answer++;
		}
		System.out.println(answer);
	}
}