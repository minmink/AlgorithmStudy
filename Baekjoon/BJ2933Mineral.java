package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2933Mineral {
	static int R, C;
	static char[][] map;
	static int[][] isVisited;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {-1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		s = br.readLine();
		int N = Integer.parseInt(s);
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		action:
		for (int turn = 1; turn <= N; turn++) {
			int height = R - Integer.parseInt(st.nextToken());
			int c = 0;
			isVisited = new int[R][C];
			boolean isBreak = false;
			if (turn % 2 == 0) {
				for (int col = C - 1; col >= 0; col--) {
					if (map[height][col] == 'x') {
						map[height][col] = '.';
						isBreak = true;
						c = col;
						break;
					}
				}
			} else {
				for (int col = 0; col < C; col++) {
					if (map[height][col] == 'x') {
						map[height][col] = '.';
						isBreak = true;
						c = col;
						break;
					}
				}
			}
			if (!isBreak)
				continue action;
			int down = 0;
			if (height > 0 && map[height - 1][c] == 'x' && isVisited[height - 1][c] == 0) {
				down = search(height - 1, c);
				if (down != 0 && down != 100) {
					goDown(height - 1, c, down);
					continue;					
				}
			}
			if (height < R - 1 && map[height + 1][c] == 'x' && isVisited[height + 1][c] == 0) {
				down = search(height + 1, c);
				if (down != 0 && down != 100) {
					goDown(height + 1, c, down);
					continue;					
				}
			}
			if (c > 0 && map[height][c - 1] == 'x' && isVisited[height][c - 1] == 0) {
				down = search(height, c - 1);
				if (down != 0 && down != 100) {
					goDown(height, c - 1, down);
					continue;
				}
			}
			if (c < C - 1 && map[height][c + 1] == 'x' && isVisited[height][c + 1] == 0) {
				down = search(height, c + 1);
				if (down != 0 && down != 100) {
					goDown(height, c + 1, down);
					continue;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static int search(int row, int col) {
		int min = 100;
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {row, col});
		isVisited[row][col]++;
		boolean[][] sameGroup = new boolean[R][C];
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			sameGroup[cur[0]][cur[1]] = true;
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '.' || isVisited[nr][nc] > 0)
					continue;
				queue.offer(new int[] {nr, nc});
				isVisited[nr][nc]++;
			}
		}
		boolean[][] visited = new boolean[R][C];
		queue.offer(new int[] {row, col});
		visited[row][col] = true;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == '.' || visited[nr][nc])
					continue;
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
			int num = 0;
			boolean same = false;
			for (int r = cur[0] + 1; r < R; r++) {
				if (num >= min)
					break;
				if (sameGroup[r][cur[1]]) {
					same = true;
					break;
				}
				if (map[r][cur[1]] == 'x')
					break;
				num++;
			}
			if (!same)
				min = Math.min(min, num);
		}
		return min;
	}
	
	public static void goDown(int row, int col, int num) {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] change = new boolean[R][C];
		queue.offer(new int[] {row, col});
		isVisited[row][col]++;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				if (nr < 0 || nr >= R || nc < 0 || nc >= C || isVisited[nr][nc] != 1)
					continue;
				queue.offer(new int[] {nr, nc});
				isVisited[nr][nc]++;
			}
			map[cur[0]][cur[1]] = '.';
			change[cur[0] + num][cur[1]] = true;
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (change[i][j])
					map[i][j] = 'x';
			}
		}
	}
}
