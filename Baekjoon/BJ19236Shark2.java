package algorithm;

import java.io.*;
import java.util.*;

public class BJ19236Shark2 {
	static int shark[], sharkDir, max = 0;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Fish[][] map = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				map[i][j] = new Fish(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
			}
		}
		shark = new int[] {0, 0};
		sharkDir = map[0][0].dir;
		int num = map[0][0].num;
		map[0][0] = null;
		
		fishMove(map);
		
		sharkMove(map, num);
		
		System.out.println(max);
	}
	
	public static void sharkMove(Fish[][] map, int total) {
		int[] sharkOrg = {shark[0], shark[1]};
		int sharkDirOrg = sharkDir;
		boolean success = false;
		Fish[][] tempMap = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(map[i][j] == null)
					continue;
				tempMap[i][j] = new Fish(map[i][j].num, map[i][j].dir);
			}
		}
		
		for (int d = 1; d <= 3; d++) {
			int nr = shark[0] + dr[sharkDir] * d;
			int nc = shark[1] + dc[sharkDir] * d;
			if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || map[nr][nc] == null)
				continue;
			success = true;
			Fish temp = tempMap[nr][nc];
			tempMap[nr][nc] = null;
			shark = new int[] {nr, nc};
			sharkDir = temp.dir;
			fishMove(tempMap);
			sharkMove(tempMap, total + temp.num);
			tempMap = new Fish[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(map[i][j] == null)
						continue;
					tempMap[i][j] = new Fish(map[i][j].num, map[i][j].dir);
				}
			}
			shark = sharkOrg;
			sharkDir = sharkDirOrg;
		}
		
		if(!success)
			max = Math.max(max, total);
	}
	
	public static void fishMove(Fish[][] map) {
		for (int num = 1; num <= 16; num++) {
			int row = -1;
			int col = -1;
			search:
			for (int r = 0; r < 4; r++) {
				for (int c = 0; c < 4; c++) {
					if(map[r][c] == null)
						continue;
					if(map[r][c].num == num) {
						row = r;
						col = c;
						break search;
					}
				}
			}
			if(row == -1)
				continue;
			
			Fish cur = map[row][col];
			for (int dir = 0; dir < 8; dir++) {
				int nd = (cur.dir + dir + 8) % 8;
				int nr = row + dr[nd];
				int nc = col + dc[nd];
				if(nr < 0 || nr >= 4 || nc < 0 || nc >= 4 || (nr == shark[0] && nc == shark[1]))
					continue;
				cur.dir = nd;
				if(map[nr][nc] != null) {
					Fish temp = map[nr][nc];
					map[row][col] = temp;
				} else {
					map[row][col] = null;
				}
				map[nr][nc] = cur;
				break;
			}
		}
	}

	public static class Fish {
		int num;
		int dir;
		public Fish(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
}
