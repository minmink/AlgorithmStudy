package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ21609MiddleSchool {
	static int N, M, map[][], total = 0, rainbowMax, cntMax;
	static boolean[][] isVisited, group;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (N == 1) {
			System.out.println(total);
			return;
		}
		
		while (true) {
			cntMax = 0;
			rainbowMax = 0;
			
			// 그룹 짓기
			isVisited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (isVisited[i][j] || map[i][j] < 1 || map[i][j] == 100)
						continue;
					isVisited[i][j] = true;
					grouping(i, j);
				}
			}
			if (cntMax == 0) {
				System.out.println(total);
				return;
			}
			
			// 그룹 제거
			total += cntMax * cntMax;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (group[i][j])
						map[i][j] = 100;
				}
			}
			
			// 중력 작용
			move();
			
			// 반시계 회전
			rotate();
			
			// 중력 작용
			move();
		}
	}

	public static void grouping (int row, int col) {
		int cnt = 1;
		int num = map[row][col];
		int rainbow = 0;
		boolean[][] tempGroup = new boolean[N][N];
		tempGroup[row][col] = true;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {row, col});
		while (queue.size() != 0) {
			int[] cur = queue.poll();
			int nr, nc;
			for (int dir = 0; dir < 4; dir++) {
				nr = cur[0] + dr[dir];
				nc = cur[1] + dc[dir];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == -1 || tempGroup[nr][nc] || map[nr][nc] == 100)
					continue;
				if (map[nr][nc] == 0)
					rainbow++;
				else if (map[nr][nc] == num)
					isVisited[nr][nc] = true;
				else
					continue;
				cnt++;
				tempGroup[nr][nc] = true;
				queue.offer(new int[] {nr, nc});
			}
		}
		
		if (cnt > 1 && cnt > cntMax) {
			cntMax = cnt;
			rainbowMax = rainbow;
			group = tempGroup.clone();
		} else if (cnt == cntMax && rainbow >= rainbowMax) {
			group = tempGroup.clone();
			rainbowMax = rainbow;
		}
	}
	
	public static void move() {
		for (int col = 0; col < N; col++) {
			int tempRow = N - 1;
			for (int row = N - 1; row >= 0; row--) {
				if (map[row][col] == -1) {
					tempRow = row - 1;
				} else if (map[row][col] != 100) {
					map[tempRow][col] = map[row][col];
					if (tempRow != row)
						map[row][col] = 100;
					tempRow--;
				}
			}
		}
	}
	
	public static void rotate() {
		int[][] tempMap = new int[N][N];
		for (int n = (N - 1) / 2; n >= 0; n--) {
			for (int col = n; col < N - n; col++) {
				 tempMap[N - 1 - col][n] = map[n][col];
				 tempMap[N - 1 - col][N - 1 - n] = map[N - 1 - n][col];
			}
			for (int row = n; row < N - n; row++) {
				tempMap[N - 1 - n][row] = map[row][n];
				tempMap[n][row] = map[row][N - 1 - n];
			}
		}
		
		map = tempMap.clone();
	}
}
