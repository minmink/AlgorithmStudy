package algorithm;

import java.io.*;
import java.util.*;

public class BJ18500Mineral {
	static char[][] map;
	static int R, C, min;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[][] visited, group;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int H = Integer.parseInt(st.nextToken());
			if (i % 2 == 0) {
				// 왼쪽
				for (int c = 0; c < C; c++) {
					if (map[R - H][c] == 'x') {
						map[R - H][c] = '.';
						down(R - H, c);
						break;
					}
				}
			} else {
				// 오른쪽
				for (int c = C - 1; c >= 0; c--) {
					if (map[R - H][c] == 'x') {
						map[R - H][c] = '.';
						down(R - H, c);
						break;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			sb.append(map[i]);
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void down(int r, int c) {
		visited = new boolean[R][C];
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == '.')
				continue;
			
			if (check(nr, nc))
				return;
		}
	}
	
	private static boolean check(int r, int c) {
		if (r == R - 1)
			return false;
		boolean end = false;
		group = new boolean[R][C];
		visited[r][c] = true;
		group[r][c] = true;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {r, c});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == '.')
					continue;
				if (nr == R - 1)
					end = true;
				visited[nr][nc] = true;
				group[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
		if (end)
			return false;
		min = R;
		for (int j = 0; j < C; j++) {
			next:
			for (int i = R - 1; i >= 0; i--) {
				if (!group[i][j])
					continue;
				int k;
				for (k = 1; k < R; k++) {
					if (i + k < R && group[i + k][j])
						continue next;
					if (i + k >= R || map[i + k][j] == 'x') {
						k--;
						break;
					}
				}
				if (k == R)
					k--;
				min = Math.min(min, k);
			}
		}
		for (int row = R - 1; row >= 0; row--) {
			for (int col = 0; col < C; col++) {
				if (group[row][col]) {
					map[row][col] = '.';
					map[row + min][col] = 'x';
				}
			}
		}
		return true;
	}
}
