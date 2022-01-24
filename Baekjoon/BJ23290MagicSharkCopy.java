package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ23290MagicSharkCopy {
	static int M, S, shark[] = new int[2];
	static ArrayList<Integer>[][] map = new ArrayList[4][4], tempMap;
	static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] sdr = {-1, 0, 1, 0};
	static int[] sdc = {0, -1, 0, 1};
	static boolean[][] oddSmell = new boolean[4][4], evenSmell = new boolean[4][4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			if (map[r][c] == null)
				map[r][c] = new ArrayList<>();
			map[r][c].add(d);
		}
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		shark[0] = Integer.parseInt(st.nextToken()) - 1;
		shark[1] = Integer.parseInt(st.nextToken()) - 1;
		
		for (int n = 0; n < S; n++) {
			tempMap = new ArrayList[4][4];
			
			// 물고기 이동
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					if (map[row][col] == null)
						continue;
					for (int num = 0; num < map[row][col].size(); num++) {
						boolean isMoved = false;
						for (int dir = 0; dir < 8; dir++) {
							int nd = (map[row][col].get(num) - dir + 8) % 8;
							int nr = row + dr[nd];
							int nc = col + dc[nd];
							if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || oddSmell[nr][nc] || evenSmell[nr][nc] || (nr == shark[0] && nc == shark[1]))
								continue;
							isMoved = true;
							if (tempMap[nr][nc] == null)
								tempMap[nr][nc] = new ArrayList<>();
							tempMap[nr][nc].add(nd);
							break;
						}
						if(!isMoved) {
							if (tempMap[row][col] == null)
								tempMap[row][col] = new ArrayList<>();
							tempMap[row][col].add(map[row][col].get(num));
						}
					}
				}
			}
			
			// 상어 이동
			int max = -1;
			int[] sd = new int[3];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < 4; k++) {
						int cnt = 0;
						boolean[][] isVisited = new boolean[4][4];
						int nr = shark[0] + sdr[i];
						int nc = shark[1] + sdc[i];
						if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4)
							continue;
						isVisited[nr][nc] = true;
						if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
							cnt += tempMap[nr][nc].size();
						
						nr += sdr[j];
						nc += sdc[j];
						if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4)
							continue;
						if (!isVisited[nr][nc]) {
							isVisited[nr][nc] = true;
							if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
								cnt += tempMap[nr][nc].size();
						}
						
						nr += sdr[k];
						nc += sdc[k];
						if (nr < 0 || nr >= 4 || nc < 0 || nc >= 4)
							continue;
						if (!isVisited[nr][nc]) {
							isVisited[nr][nc] = true;
							if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
								cnt += tempMap[nr][nc].size();
						}
						
						if (cnt > max) {
							max = cnt;
							sd = new int[] {i, j, k};
						}
					}
				}
			}
			
			if (n % 2 == 0) {
				oddSmell = new boolean[4][4];
				int nr = shark[0] + sdr[sd[0]];
				int nc = shark[1] + sdc[sd[0]];
				if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
					oddSmell[nr][nc] = true;
				tempMap[nr][nc] = new ArrayList<>();
				nr += sdr[sd[1]];
				nc += sdc[sd[1]];
				if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
					oddSmell[nr][nc] = true;
				tempMap[nr][nc] = new ArrayList<>();
				nr += sdr[sd[2]];
				nc += sdc[sd[2]];
				if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
					oddSmell[nr][nc] = true;
				tempMap[nr][nc] = new ArrayList<>();
				shark = new int[] {nr, nc};
			} else {
				evenSmell = new boolean[4][4];
				int nr = shark[0] + sdr[sd[0]];
				int nc = shark[1] + sdc[sd[0]];
				if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
					evenSmell[nr][nc] = true;
				tempMap[nr][nc] = new ArrayList<>();
				nr += sdr[sd[1]];
				nc += sdc[sd[1]];
				if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
					evenSmell[nr][nc] = true;
				tempMap[nr][nc] = new ArrayList<>();
				nr += sdr[sd[2]];
				nc += sdc[sd[2]];
				if (tempMap[nr][nc] != null && tempMap[nr][nc].size() > 0)
					evenSmell[nr][nc] = true;
				tempMap[nr][nc] = new ArrayList<>();
				shark = new int[] {nr, nc};
			}
			
			// 물고기 복제
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (tempMap[i][j] == null)
						continue;
					if (map[i][j] == null)
						map[i][j] = new ArrayList<>();
					for (int k = 0; k < tempMap[i][j].size(); k++) {
						map[i][j].add(tempMap[i][j].get(k));
					}
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == null)
					continue;
				cnt += map[i][j].size();
			}
		}
		
		System.out.println(cnt);
	}
}
