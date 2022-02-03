package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17143Fish {
	static int R, C, M, answer = 0;
	static Shark[][] map, tempMap;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Shark[R][C];
		answer = 0;
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for (int col = 0; col < C; col++) {
			for (int row = 0; row < R; row++) {
				if (map[row][col] != null) {
					answer += map[row][col].z;
					map[row][col] = null;
					break;
				}
			}
			tempMap = new Shark[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (map[r][c] == null)
						continue;
					move(r, c);
				}
			}
			map = tempMap.clone();
		}
		System.out.println(answer);
	}

	public static class Shark {
		int s;
		int d;
		int z;
		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	public static void move(int row, int col) {
		int cnt = 0, nr = row, nc = col;
		Shark cur = map[row][col];
		while (cnt < cur.s) {
			switch(cur.d) {
			// 상
			case 1:
				nr--;
				if (nr == -1) {
					nr++;
					cur.d = 2;
					continue;
				}
				cnt++;
				break;
			// 하
			case 2:
				nr++;
				if (nr == R) {
					nr--;
					cur.d = 1;
					continue;
				}
				cnt++;
				break;
			// 우
			case 3:
				nc++;
				if (nc == C) {
					nc--;
					cur.d = 4;
					continue;
				}
				cnt++;
				break;
			// 좌
			case 4:
				nc--;
				if (nc == -1) {
					nc++;
					cur.d = 3;
					continue;
				}
				cnt++;
				break;
			}
		}
		if (tempMap[nr][nc] == null || tempMap[nr][nc].z < cur.z)
			tempMap[nr][nc] = cur;
	}
}
