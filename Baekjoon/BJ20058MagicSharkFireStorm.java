package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20058MagicSharkFireStorm {
	static int N, Q, expN = 1;
	static int[][] map;
	static int[] Ls;
	static boolean[][] isZero;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		Ls = new int[Q];
		
		for (int i = 0; i < N; i++) {
			expN *= 2;
		}
		
		map = new int[expN][expN];
		for (int i = 0; i < expN; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < expN; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		for (int i = 0; i < Q; i++) {
			Ls[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int L : Ls) {
			isZero = new boolean[expN][expN];
			
			// L X L 시계방향 회전
			int expL = 1;
			for (int i = 0; i < L; i++) {
				expL *= 2;
			}
			
			for (int row = 0; row < expN; row += expL) {
				for (int col = 0; col < expN; col += expL) {
					rotate(expL, row, col);
				}
			}
			
			// 인접칸에 얼음 있는지 체크
			for (int row = 0; row < expN; row++) {
				for (int col = 0; col < expN; col++) {
					if (map[row][col] == 0)
						continue;
					
					int cnt = 0;
					
					for (int dir = 0; dir < 4; dir++) {
						int nr = row + dr[dir];
						int nc = col + dc[dir];
						if (nr < 0 || nr >= expN ||
								nc < 0 || nc >= expN ||
								map[nr][nc] == 0)
							continue;
						cnt++;
					}
					
					if (cnt < 3)
						isZero[row][col] = true;
				}
			}
			
			// 얼음 없고 0보다 크면 1 줄어듦
			for (int row = 0; row < expN; row++) {
				for (int col = 0; col < expN; col++) {
					if (isZero[row][col] && map[row][col] > 0)
						map[row][col]--;
				}
			}
		}
		
		int sum = 0;
		for (int i = 0; i < expN; i++) {
			for (int j = 0; j < expN; j++) {
				sum += map[i][j];
			}
		}
		
		System.out.println(sum);
		
		int max = 0;
		boolean[][] isVisited = new boolean[expN][expN];
		int[][] queue = new int[expN * expN][2];
		int rear = -1, front = -1;
		
		for (int row = 0; row < expN; row++) {
			for (int col = 0; col < expN; col++) {
				if (isVisited[row][col])
					continue;
				
				isVisited[row][col] = true;
				if (map[row][col] == 0)
					continue;
				
				int cnt = 1;
				queue[++rear] = new int[] {row, col};
				
				while (rear != front) {
					int[] cur = queue[++front];
					for (int dir = 0; dir < 4; dir++) {
						int nr = cur[0] + dr[dir];
						int nc = cur[1] + dc[dir];
						if (nr < 0 || nr >= expN ||
								nc < 0 || nc >= expN ||
								isVisited[nr][nc])
							continue;
						isVisited[nr][nc] = true;
						if (map[nr][nc] == 0)
							continue;
						queue[++rear] = new int[] {nr, nc};
						cnt++;
					}
				}
				
				if (cnt > max)
					max = cnt;
			}
		}
		
		System.out.println(max);
	}

	public static void rotate (int dis, int rowS, int colS) {
		if (dis <= 1)
			return;
		
		int row = rowS;
		int col = colS;
		int[] pre = new int[dis];
		pre[0] = map[row][col];
		for (int i = 1; i < dis; i++) {
			row += dr[0];
			col += dc[0];
			pre[i] = map[row][col];
		}
		
		for (int dir = 1; dir <= 4; dir++) {
			dir %= 4;
			int[] temp = new int[dis];
			temp[0] = pre[dis - 1];
			map[row][col] = pre[0];
			pre[0] = temp[0];
			for (int n = 1; n < dis; n++) {
				row += dr[dir];
				col += dc[dir];
				temp[n] = map[row][col];
				map[row][col] = pre[n];
				pre[n] = temp[n];
			}
			if (dir == 0)
				break;
		}
		
		rotate(dis - 2, rowS + 1, colS + 1);
	}
}
