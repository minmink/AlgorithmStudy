package algorithm;

import java.io.*;
import java.util.*;

public class SW5650PinBall {
	static int map[][], N, holes[][][];
	static boolean[][][] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			sb.append("#").append(tc + 1).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			holes = new int[5][2][2];
			visited = new boolean[N][N][4];
			for (int i = 0; i < 5; i++) {
				holes[i][0][0] = -1;
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >= 6) {
						if(holes[map[i][j] - 6][0][0] == -1)
							holes[map[i][j] - 6][0] = new int[] {i, j};
						else
							holes[map[i][j] - 6][1] = new int[] {i, j};
					}
				}
			}
			
			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != 0)
						continue;
					for (int dir = 0; dir < 4; dir++) {
						if(!visited[i][j][dir])
							max = Math.max(max, startGame(i, j, dir));
						visited[i][j][dir] = true;
					}
				}
			}
			
			sb.append(max).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static int startGame(int row, int col, int dir) {
		int score = 0;
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		int orgRow = row;
		int orgCol = col;
		int orgDir = dir;
		
		game:
		while(true) {
			if(row < 0 || row >= N || col < 0 || col >= N) {
				row -= dr[dir];
				col -= dc[dir];
				dir = (dir + 2) % 4;
				score++;
			} else if(map[row][col] == -1 || (orgDir != dir && row == orgRow && col == orgCol)) {
				break;
			} else if(map[row][col] >= 6) {
				int nr = row;
				int nc = col;
				if(holes[map[row][col] - 6][0][0] == row && holes[map[row][col] - 6][0][1] == col) {
					row = holes[map[nr][nc] - 6][1][0];
					col = holes[map[nr][nc] - 6][1][1];
				} else {
					row = holes[map[nr][nc] - 6][0][0];
					col = holes[map[nr][nc] - 6][0][1];
				}
				
				row += dr[dir];
				col += dc[dir];
				continue game;
			} else if(map[row][col] >= 1) {
				dir = getDir(row, col, dir);
				row += dr[dir];
				col += dc[dir];
				score++;
			} else {
				visited[row][col][dir] = true;
				row += dr[dir];
				col += dc[dir];
			}
		}
		
		return score;
	}
	
	public static int getDir(int row, int col, int dir) {
		if(dir == 2) {
			if(map[row][col] == 1)
				dir = 1;
			else if(map[row][col] == 4)
				dir = 3;
			else
				dir = (dir + 2) % 4;
		} else if(dir == 0) {
			if(map[row][col] == 2)
				dir = 1;
			else if(map[row][col] == 3)
				dir = 3;
			else
				dir = (dir + 2) % 4;
		} else if(dir == 1) {
			if(map[row][col] == 3)
				dir = 2;
			else if(map[row][col] == 4)
				dir = 0;
			else
				dir = (dir + 2) % 4;
		} else {
			if(map[row][col] == 1)
				dir = 0;
			else if(map[row][col] == 2)
				dir = 2;
			else
				dir = (dir + 2) % 4;
		}
		
		return dir;
	}
}
