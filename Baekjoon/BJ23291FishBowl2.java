package algorithm;

import java.io.*;
import java.util.*;

public class BJ23291FishBowl2 {
	static int N, map[][];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			map[0][i] = Integer.parseInt(st.nextToken());
		}
		int cnt = 0;
		int min = 10000;
		int max = 1;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, map[0][i]);
			max = Math.max(max, map[0][i]);
		}
		if(max - min <= K) {
			System.out.println(cnt);
			return;
		}
		
		while(max - min > K) {
			cnt++;
			
			for (int i = 0; i < N; i++) {
				if(map[0][i] == min)
					map[0][i]++;
			}
			
			map[1][1] = map[0][0];
			map[0][0] = 0;
			while(true) {
				int startC = 0;
				int endC = N - 1;
				for (int i = 0; i < N; i++) {
					if(map[0][i] == 0)
						continue;
					if(map[1][i] == 0) {
						endC = i - 1;
						break;
					}
					if(startC == 0) {
						startC = i;
						continue;
					}
				}
				int row = 0;
				for (int r = 1; r < N; r++) {
					if(map[r][startC] == 0) {
						row = r - 1;
						break;
					}
				}
				if(row + 1 > N - 1 - endC)
					break;
				for (int c = endC; c >= startC; c--) {
					for (int r = 0; r <= row; r++) {
						map[endC - c + 1][endC + 1 + r] = map[r][c];
						map[r][c] = 0;
					}
				}
			}
			control();
			back();
			
			for (int i = 0; i < N / 4; i++) {
				map[1][N - 1 - i] = map[0][i];
				map[0][i] = 0;
				map[2][N / 4 * 3 + i] = map[0][N / 4 + i];
				map[0][N / 4 + i] = 0;
				map[3][N - 1 - i] = map[0][N / 2 + i];
				map[0][N / 2 + i] = 0;
			}
			control();
			back();
			
			min = 10000;
			max = 1;
			for (int i = 0; i < N; i++) {
				min = Math.min(min, map[0][i]);
				max = Math.max(max, map[0][i]);
			}
		}
		System.out.println(cnt);
	}
	
	public static void control() {
		boolean[][][] visited = new boolean[N][N][4];
		int[][] num = new int[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if(map[row][col] == 0)
					continue;
				for (int dir = 0; dir < 4; dir++) {
					visited[row][col][dir] = true;
					int nr = row + dr[dir];
					int nc = col + dc[dir];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || (visited[nr][nc][(dir + 2) % 4] && visited[row][col][dir]) || map[nr][nc] == 0)
						continue;
					visited[nr][nc][(dir + 2) % 4] = true;
					int d = (map[row][col] - map[nr][nc]) / 5;
					num[row][col] -= d;
					num[nr][nc] += d;
				}
			}
		}
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				map[row][col] += num[row][col];
			}
		}
	}
	
	public static void back() {
		int idx = 0;
		for (int col = 0; col < N; col++) {
			if(map[1][col] == 0)
				continue;
			for (int row = 0; row < N; row++) {
				if(map[row][col] == 0)
					break;
				map[0][idx++] = map[row][col];
				map[row][col] = 0;
			}
		}
	}
}
