package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15684Ladder {
	static int N, M, H, map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][2 * N - 1];
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			map[Integer.parseInt(st.nextToken()) - 1][(Integer.parseInt(st.nextToken()) - 1) * 2 + 1] = 1;
		}
		
		// 0개
		if (test()) {
			System.out.println(0);
			return;
		}
		
		// 1개
		for (int c = 1; c < 2 * N - 1; c += 2) {
			for (int r = 0; r < H; r++) {
				if (map[r][c] == 1)
					continue;
				if ((c == N * 2 - 3 || map[r][c + 2] != 1) &&
						(c == 1 || map[r][c - 2] != 1)) {
					map[r][c] = 1;
					if (test()) {
						System.out.println(1);
						return;
					}
					map[r][c] = 0;
				}
			}
		}
		
		// 2개
		for (int c1 = 1; c1 < 2 * N - 1; c1 += 2) {
			for (int r1 = 0; r1 < H; r1++) {
				if (map[r1][c1] == 1)
					continue;
				if ((c1 == N * 2 - 3 || map[r1][c1 + 2] != 1) &&
						(c1 == 1 || map[r1][c1 - 2] != 1)) {
					map[r1][c1] = 1;
					for (int c2 = c1; c2 < 2 * N - 1; c2 += 2) {
						for (int r2 = c2 == c1? r1 + 1 : 0; r2 < H; r2++) {
							if (map[r2][c2] == 1)
								continue;
							if ((c2 == N * 2 - 3 || map[r2][c2 + 2] != 1) &&
									(c2 == 1 || map[r2][c2 - 2] != 1)) {
								map[r2][c2] = 1;
								if (test()) {
									System.out.println(2);
									return;
								}
								map[r2][c2] = 0;
							}
						}
					}
					map[r1][c1] = 0;
				}
			}
		}
		
		// 3개
		for (int c1 = 1; c1 < 2 * N - 1; c1 += 2) {
			for (int r1 = 0; r1 < H; r1++) {
				if (map[r1][c1] == 1)
					continue;
				if ((c1 == N * 2 - 3 || map[r1][c1 + 2] != 1) &&
						(c1 == 1 || map[r1][c1 - 2] != 1)) {
					map[r1][c1] = 1;
					for (int c2 = c1; c2 < 2 * N - 1; c2 += 2) {
						for (int r2 = c2 == c1? r1 + 1 : 0; r2 < H; r2++) {
							if (map[r2][c2] == 1)
								continue;
							if ((c2 == N * 2 - 3 || map[r2][c2 + 2] != 1) &&
									(c2 == 1 || map[r2][c2 - 2] != 1)) {
								map[r2][c2] = 1;
								for (int c3 = c2; c3 < 2 * N - 1; c3 += 2) {
									for (int r3 = c3 == c2? r2 + 1 : 0; r3 < H; r3++) {
										if (map[r3][c3] == 1)
											continue;
										if ((c3 == N * 2 - 3 || map[r3][c3 + 2] != 1) &&
												(c3 == 1 || map[r3][c3 - 2] != 1)) {
											map[r3][c3] = 1;
											if (test()) {
												System.out.println(3);
												return;
											}
											map[r3][c3] = 0;
										}
									}
								}
								map[r2][c2] = 0;
							}
						}
					}
					map[r1][c1] = 0;
				}
			}
		}
		
		
		System.out.println(-1);
	}

	public static boolean test() {
		for (int c = 0; c < N * 2 - 1; c += 2) {
			int row = -1;
			int col = c;
			while (++row < H) {
				if (col < N * 2 - 2 && map[row][col + 1] == 1)
					col += 2;
				else if (col > 0 && map[row][col - 1] == 1)
					col -= 2;
			}
			if (c != col)
				return false;
		}
		
		return true;
	}
}
