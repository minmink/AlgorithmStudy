package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ23289Heater {
	static int R, C, K, W, heaterCnt = 0, searchCnt = 0, total = 0;
	static int[][] map, heater, control, searchArr;
	static boolean[][][] wall;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		heater = new int[R * C][3];
		searchArr = new int[R * C][2];
		for (int i = 0; i < R; i++) {
			s = br.readLine();
			for (int j = 0; j < C; j++) {
				int num = s.charAt(j * 2) - '0';
				if (num == 0)
					continue;
				if (num <= 4)
					heater[heaterCnt++] = new int[] {i, j, s.charAt(j * 2) - '0'};
				else if (num == 5)
					searchArr[searchCnt++] = new int[] {i, j};
			}
		}
		s = br.readLine();
		W = Integer.parseInt(s);
		wall = new boolean[R][C][2];
		for (int i = 0; i < W; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			wall[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken())] = true;
		}
		
		while (total <= 100) {
			for (int i = 0; i < heaterCnt; i++) {
				heat(heater[i][0], heater[i][1], heater[i][2]);
			}
			
			control = new int[R][C];
			search();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j] += control[i][j];
				}
			}
			
			for (int i = 0; i < C; i++) {
				if (map[0][i] > 0)
					map[0][i]--;
				if (map[R - 1][i] > 0)
					map[R - 1][i]--;
			}
			for (int i = 1; i < R - 1; i++) {
				if (map[i][0] > 0)
					map[i][0]--;
				if (map[i][C - 1] > 0)
					map[i][C - 1]--;
			}
			
			total++;
			
			boolean isFull = true;
			for (int i = 0; i < searchCnt; i++) {
				if (map[searchArr[i][0]][searchArr[i][1]] < K) {
					isFull = false;
					break;
				}
			}
			if (isFull)
				break;
		}
		
		System.out.println(total);
	}
	
	public static void heat(int r, int c, int dir) {
		Queue<int[]> queue = new LinkedList<int[]>();
		int val = 5, size, nr, nc;
		int[] cur;
		boolean[][] isVisited = new boolean[R][C];
		switch(dir) {
		case 1: // 우
			if (wall[r][c][1])
				break;
			queue.offer(new int[] {r, c + 1});
			map[r][c + 1] += val;
			val--;
			while (!queue.isEmpty() && val > 0) {
				size = queue.size();
				for (int i = 0; i < size; i++) {
					cur = queue.poll();
					nr = cur[0] - 1;
					nc = cur[1] + 1;
					if (nr >= 0 && nc < C && !wall[cur[0]][cur[1]][0] && !wall[cur[0] - 1][cur[1]][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
					nr = cur[0];
					if (nc < C && !wall[cur[0]][cur[1]][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
					nr = cur[0] + 1;
					if (nr < R && nc < C && !wall[cur[0] + 1][cur[1]][0] && !wall[cur[0] + 1][cur[1]][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
				}
				val--;
			}
			break;
		case 2: // 좌
			if (wall[r][c - 1][1])
				break;
			queue.offer(new int[] {r, c - 1});
			map[r][c - 1] += val;
			val--;
			while (!queue.isEmpty() && val > 0) {
				size = queue.size();
				for (int i = 0; i < size; i++) {
					cur = queue.poll();
					nr = cur[0] - 1;
					nc = cur[1] - 1;
					if (nr >= 0 && nc >= 0 && !wall[cur[0]][cur[1]][0] && !wall[cur[0] - 1][cur[1] - 1][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
					nr = cur[0];
					if (nc >= 0 && !wall[cur[0]][cur[1] - 1][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
					nr = cur[0] + 1;
					if (nr < R && nc >= 0 && !wall[cur[0] + 1][cur[1]][0] && !wall[cur[0] + 1][cur[1] - 1][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
				}
				val--;
			}
			break;
		case 3: // 상
			if (wall[r][c][0])
				break;
			queue.offer(new int[] {r - 1, c});
			map[r - 1][c] += val;
			val--;
			while (!queue.isEmpty() && val > 0) {
				size = queue.size();
				for (int i = 0; i < size; i++) {
					cur = queue.poll();
					nr = cur[0] - 1;
					nc = cur[1] - 1;
					if (nr >= 0 && nc >= 0 && !wall[cur[0]][cur[1] - 1][0] && !wall[cur[0]][cur[1] - 1][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
					nc = cur[1];
					if (nr >= 0 && !wall[cur[0]][cur[1]][0] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
					nc = cur[1] + 1;
					if (nr >= 0 && nc < C && !wall[cur[0]][cur[1] + 1][0] && !wall[cur[0]][cur[1]][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
				}
				val--;
			}
			break;
		case 4: // 하
			if (wall[r + 1][c][0])
				break;
			queue.offer(new int[] {r + 1, c});
			map[r + 1][c] += val;
			val--;
			while (!queue.isEmpty() && val > 0) {
				size = queue.size();
				for (int i = 0; i < size; i++) {
					cur = queue.poll();
					nr = cur[0] + 1;
					nc = cur[1] - 1;
					if (nr < R && nc >= 0 && !wall[cur[0] + 1][cur[1] - 1][0] && !wall[cur[0]][cur[1] - 1][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
					nc = cur[1];
					if (nr < R && !wall[cur[0] + 1][cur[1]][0] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
					nc = cur[1] + 1;
					if (nr < R && nc < C && !wall[cur[0] + 1][cur[1] + 1][0] && !wall[cur[0]][cur[1]][1] && !isVisited[nr][nc]) {
						queue.offer(new int[] {nr, nc});
						isVisited[nr][nc] = true;
						map[nr][nc] += val;
					}
				}
				val--;
			}
			break;
		}
	}
	
	public static void search() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int big, small, val;
				if (j + 1 < C && !wall[i][j][1]) {
					if (map[i][j] >= map[i][j + 1]) {
						big = j;
						small = j + 1;
					}
					else {
						big = j + 1;
						small = j;
					}
					val = (map[i][big] - map[i][small]) / 4;
					control[i][big] -= val;
					control[i][small] += val;
				}
				
				if (i + 1 < R && !wall[i + 1][j][0]) {
					if (map[i][j] >= map[i + 1][j]) {
						big = i;
						small = i + 1;
					}
					else {
						big = i + 1;
						small = i;
					}
					val = (map[big][j] - map[small][j]) / 4;
					control[big][j] -= val;
					control[small][j] += val;
				}
			}
		}
	}
}
