package algorithm;

import java.io.*;
import java.util.*;

public class BJ23290MagicSharkCopy2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[][] smells = new int[4][4];
		int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
		int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] sharkR = {-1, 0, 1, 0};
		int[] sharkC = {0, -1, 0, 1};
		String s;
		int[][][] map = new int[4][4][8];
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			map[s.charAt(0) - '1'][s.charAt(2) - '1'][s.charAt(4) - '1']++;
		}
		s = br.readLine();
		int[] shark = {s.charAt(0) - '1', s.charAt(2) - '1'};
		
		int time = 0;
		while(time++ < S) {
			int[][][] copiedMap = new int[4][4][8];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < 8; k++) {
						copiedMap[i][j][k] = map[i][j][k];
					}
				}
			}
			int[][][] tempMap = new int[4][4][8];
			
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					for (int dir = 0; dir < 8; dir++) {
						if(map[row][col][dir] == 0)
							continue;
						
						boolean success = false;
						for (int j = 0; j < 8; j++) {
							int d = (dir - j + 8) % 8;
							int nr = row + dr[d];
							int nc = col + dc[d];
							if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || smells[nr][nc] > 0 || (shark[0] == nr && shark[1] == nc))
								continue;
							tempMap[nr][nc][d] += map[row][col][dir];
							success = true;
							break;
						}
						if(!success)
							tempMap[row][col][dir] += map[row][col][dir];
					}
				}
			}
			
			int max = -1;
			int[] dirs = new int[3];
			boolean[][] visited = new boolean[4][4];
			for (int dir1 = 0; dir1 < 4; dir1++) {
				int nr1 = shark[0] + sharkR[dir1];
				int nc1 = shark[1] + sharkC[dir1];
				if(nr1 < 0 || nr1 >= 4 || nc1 < 0 || nc1 >= 4)
					continue;
				int cnt1 = 0;
				for (int i = 0; i < 8; i++) {
					cnt1 += tempMap[nr1][nc1][i];
				}
				visited[nr1][nc1] = true;
				for (int dir2 = 0; dir2 < 4; dir2++) {
					int nr2 = nr1 + sharkR[dir2];
					int nc2 = nc1 + sharkC[dir2];
					if(nr2 < 0 || nr2 >= 4 || nc2 < 0 || nc2 >= 4)
						continue;
					int cnt2 = cnt1;
					for (int i = 0; i < 8; i++) {
						cnt2 += tempMap[nr2][nc2][i];
					}
					for (int dir3 = 0; dir3 < 4; dir3++) {
						int nr3 = nr2 + sharkR[dir3];
						int nc3 = nc2 + sharkC[dir3];
						if(nr3 < 0 || nr3 >= 4 || nc3 < 0 || nc3 >= 4)
							continue;
						int cnt3 = cnt2;
						if(!visited[nr3][nc3]) {
							for (int i = 0; i < 8; i++) {
								cnt3 += tempMap[nr3][nc3][i];
							}
						}
						if(max < cnt3) {
							max = cnt3;
							dirs = new int[] {dir1, dir2, dir3};
						}
					}
				}
				visited[nr1][nc1] = false;
			}
			
			visited = new boolean[4][4];
			shark[0] += sharkR[dirs[0]];
			shark[1] += sharkC[dirs[0]];
			visited[shark[0]][shark[1]] = true;
			for (int i = 0; i < 8; i++) {
				if(tempMap[shark[0]][shark[1]][i] > 0) {
					smells[shark[0]][shark[1]] = 3;
					tempMap[shark[0]][shark[1]][i] = 0;
				}
			}
			shark[0] += sharkR[dirs[1]];
			shark[1] += sharkC[dirs[1]];
			for (int i = 0; i < 8; i++) {
				if(tempMap[shark[0]][shark[1]][i] > 0) {
					smells[shark[0]][shark[1]] = 3;
					tempMap[shark[0]][shark[1]][i] = 0;
				}
			}
			shark[0] += sharkR[dirs[2]];
			shark[1] += sharkC[dirs[2]];
			if(!visited[shark[0]][shark[1]]) {
				for (int i = 0; i < 8; i++) {
					if(tempMap[shark[0]][shark[1]][i] > 0) {
						smells[shark[0]][shark[1]] = 3;
						tempMap[shark[0]][shark[1]][i] = 0;
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(smells[i][j] > 0)
						smells[i][j]--;
				}
			}
			
			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < 8; k++) {
						map[i][j][k] = tempMap[i][j][k] + copiedMap[i][j][k];
					}
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 8; k++) {
					cnt += map[i][j][k];
				}
			}
		}
		System.out.println(cnt);
	}
}
