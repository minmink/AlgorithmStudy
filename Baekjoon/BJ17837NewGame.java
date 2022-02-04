package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ17837NewGame {
	static int N, K, color[][], location[][], cnt = 0;
	static int[] dr = new int[] {0, 0, 0, -1, 1};
	static int[] dc = new int[] {0, 1, -1, 0, 0};
	static ArrayList<Integer>[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		color = new int[N][N];
		map = new ArrayList[N][N];
		location = new int[K][3];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < N; j++) {
				color[i][j] = s.charAt(j * 2) - '0';
				map[i][j] = new ArrayList<Integer>();
			}
		}
		for (int i = 0; i < K; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			map[r][c].add(i);
			location[i] = new int[] {r, c, dir};
		}
		while (++cnt <= 1000) {
			for (int k = 0; k < K; k++) {
				int[] cur = location[k];
				int nr = cur[0] + dr[cur[2]];
				int nc = cur[1] + dc[cur[2]];
				// 범위 밖이거나 파란색
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || color[nr][nc] == 2) {
					if (moveToBlue(k)) {
						System.out.println(cnt);
						return;
					}
				}
				// 빨간색
				else if (color[nr][nc] == 1) {
					if (moveToRed(k)) {
						System.out.println(cnt);
						return;
					}
				}
				// 흰색
				else {
					if (moveToWhite(k)) {
						System.out.println(cnt);
						return;
					}
				}
			}
		}
		System.out.println(-1);
	}
	
	public static boolean moveToBlue(int k) {
		int[] cur = location[k];
		int nr = cur[0] + dr[cur[2]];
		int nc = cur[1] + dc[cur[2]];
		switch (cur[2]) {
		case 1:
			cur[2] = 2;
			break;
		case 2:
			cur[2] = 1;
			break;
		case 3:
			cur[2] = 4;
			break;
		case 4:
			cur[2] = 3;
			break;
		}
		nr = cur[0] + dr[cur[2]];
		nc = cur[1] + dc[cur[2]];
		if (nr < 0 || nr >= N || nc < 0 || nc >= N || color[nr][nc] == 2)
			return false;
		if (color[nr][nc] == 1) {
			if (moveToRed(k))
				return true;
			return false;
		}
		if (moveToWhite(k))
			return true;
		return false;
	}
	
	public static boolean moveToRed(int k) {
		int[] cur = location[k];
		int nr = cur[0] + dr[cur[2]];
		int nc = cur[1] + dc[cur[2]];
		ArrayList<Integer> tempArr = new ArrayList<>();
		for (int i = map[cur[0]][cur[1]].size() - 1; i >= 0; i--) {
			int num = map[cur[0]][cur[1]].get(i);
			tempArr.add(num);
			map[cur[0]][cur[1]].remove(i);
			location[num][0] = nr;
			location[num][1] = nc;
			if (num == k)
				break;
		}
		for (int i = 0; i < tempArr.size(); i++) {
			map[nr][nc].add(tempArr.get(i));
		}
		if (map[nr][nc].size() >= 4)
			return true;
		return false;
	}
	
	public static boolean moveToWhite(int k) {
		int[] cur = location[k];
		int nr = cur[0] + dr[cur[2]];
		int nc = cur[1] + dc[cur[2]];
		ArrayList<Integer> tempArr = new ArrayList<>();
		for (int i = map[cur[0]][cur[1]].size() - 1; i >= 0; i--) {
			int num = map[cur[0]][cur[1]].get(i);
			tempArr.add(num);
			map[cur[0]][cur[1]].remove(i);
			location[num][0] = nr;
			location[num][1] = nc;
			if (num == k)
				break;
		}
		for (int i = tempArr.size() - 1; i >= 0; i--) {
			map[nr][nc].add(tempArr.get(i));
		}
		if (map[nr][nc].size() >= 4)
			return true;
		return false;
	}
}
