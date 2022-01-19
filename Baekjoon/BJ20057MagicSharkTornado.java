package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20057MagicSharkTornado {
	static int N;
	static int[] dr = {-1, 0, 1, 1, 1, 0, -1, -1};
	static int[] dc = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[][] map;
	static double[][] leftSand = {{0, 0, 0.02, 0, 0}, {0, 0.1, 0.07, 0.01, 0}, {0.05, 0, 0, 0, 0}, {0, 0.1, 0.07, 0.01, 0}, {0, 0, 0.02, 0, 0}};
	static double[][] rightSand = {{0, 0, 0.02, 0, 0}, {0, 0.01, 0.07, 0.1, 0}, {0, 0, 0, 0, 0.05}, {0, 0.01, 0.07, 0.1, 0}, {0, 0, 0.02, 0, 0}};
	static double[][] upSand = {{0, 0, 0.05, 0, 0}, {0, 0.1, 0, 0.1, 0}, {0.02, 0.07, 0, 0.07, 0.02}, {0, 0.01, 0, 0.01, 0}, {0, 0, 0, 0, 0}};
	static double[][] downSand = {{0, 0, 0, 0, 0}, {0, 0.01, 0, 0.01, 0}, {0.02, 0.07, 0, 0.07, 0.02}, {0, 0.1, 0, 0.1, 0}, {0, 0, 0.05, 0, 0}};
	static int total = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int row = N/2, col = N/2 - 1;
		int dir = 0;
		int dis = 1;
		int moved = 0;
		boolean isDouble = false;
		while (true) {
			move(row, col, dir);
			moved++;
			
			if (row == 0 && col == 0)
				break;
			
			if (moved == dis) {
				dir = (dir + 1) % 4;
				
				if (isDouble) {
					isDouble = false;
					dis++;
				} else {
					isDouble = true;
				}
				
				moved = 0;
			}
			
			row = row + dr[2 * dir + 1];
			col = col + dc[2 * dir + 1];
		}
		
		System.out.println(total);
	}

	public static void move(int row, int col, int dir) {
		int sand = map[row][col];
		map[row][col] = 0;
		double[][] sandMap = new double[5][5];
		int remain = sand;
		switch (dir) {
		case 0:
			sandMap = leftSand.clone();
			break;
		case 1:
			sandMap = downSand.clone();
			break;
		case 2:
			sandMap = rightSand.clone();
			break;
		case 3:
			sandMap = upSand.clone();
			break;
		}
		
		int nr = 0, nc = 0, moveSand = 0;
		for (int r = -2; r <= 2; r++) {
			for (int c = -2; c <= 2; c++) {
				nr = row + r;
				nc = col + c;
				moveSand = (int) (sand * sandMap[r + 2][c + 2]);
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					total += moveSand;
					remain -= moveSand;
					continue;
				}
				map[nr][nc] += moveSand;
				remain -= moveSand;
			}
		}
		
		nr = row + dr[dir * 2 + 1];
		nc = col + dc[dir * 2 + 1];
		if (nr >= 0 && nr < N && nc >= 0 && nc < N)
			map[nr][nc] += remain;
		else
			total += remain;
	}
}
