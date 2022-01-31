package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14499Tetromino {
	static int N, M, map[][], max = 0;
	static int[][] dr0 = new int[][] {{0, 0, 0, 0}, {0, 1, 2, 3}};
	static int[][] dc0 = new int[][] {{0, 1, 2, 3}, {0, 0, 0, 0}};
	static int[] dr1 = new int[] {0, 0, 1, 1};
	static int[] dc1 = new int[] {0, 1, 0, 1};
	static int[][] dr2 = new int[][] {{0, 0, 0, 1}, {0, 1, 1, 2}, {0, 1, 1, 2}, {0, 1, 1, 1}};
	static int[][] dc2 = new int[][] {{0, 1, 2, 1}, {0, 0, 1, 0}, {1, 0, 1, 1}, {1, 0, 1, 2}};
	static int[][] dr3 = new int[][] {{0, 1, 1, 2}, {0, 0, 1, 1}, {0, 0, 1, 1}, {0, 1, 1, 2}};
	static int[][] dc3 = new int[][] {{0, 0, 1, 1}, {1, 2, 0, 1}, {0, 1, 1, 2}, {1, 0, 1, 0}};
	static int[][] dr4 = new int[][] {{0, 1, 2, 2}, {0, 1, 1, 1}, {0, 0, 1, 2}, {0, 0, 0, 1}, {0, 1, 2, 2}, {0, 0, 0, 1}, {0, 0, 1, 2}, {0, 1, 1, 1}};
	static int[][] dc4 = new int[][] {{0, 0, 0, 1}, {2, 0, 1, 2}, {0, 1, 1, 1}, {0, 1, 2, 0}, {1, 1, 0, 1}, {0, 1, 2, 2}, {0, 1, 0, 0}, {0, 0, 1, 2}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				move0(i, j);
				move1(i, j);
				move2(i, j);
				move3(i, j);
				move4(i, j);
			}
		}
		
		System.out.println(max);
	}

	public static void move0 (int row, int col) {
		for (int i = 0; i < 2; i++) {
			int nr, nc;
			boolean can = true;
			int temp = 0;
			for (int j = 0; j < 4; j++) {
				nr = row + dr0[i][j];
				nc = col + dc0[i][j];
				if (nr >= N || nc >= M) {
					can = false;
					break;
				}
				temp += map[nr][nc];
			}
			if (can) {
				if (max < temp)
					max = temp;
			}
		}
	}
	
	public static void move1 (int row, int col) {
		int nr, nc;
		boolean can = true;
		int temp = 0;
		for (int j = 0; j < 4; j++) {
			nr = row + dr1[j];
			nc = col + dc1[j];
			if (nr >= N || nc >= M) {
				can = false;
				break;
			}
			temp += map[nr][nc];
		}
		if (can) {
			if (max < temp)
				max = temp;
		}
	}

	public static void move2 (int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nr, nc;
			boolean can = true;
			int temp = 0;
			for (int j = 0; j < 4; j++) {
				nr = row + dr2[i][j];
				nc = col + dc2[i][j];
				if (nr >= N || nc >= M) {
					can = false;
					break;
				}
				temp += map[nr][nc];
			}
			if (can) {
				if (max < temp)
					max = temp;
			}
		}
	}

	public static void move3 (int row, int col) {
		for (int i = 0; i < 4; i++) {
			int nr, nc;
			boolean can = true;
			int temp = 0;
			for (int j = 0; j < 4; j++) {
				nr = row + dr3[i][j];
				nc = col + dc3[i][j];
				if (nr >= N || nc >= M) {
					can = false;
					break;
				}
				temp += map[nr][nc];
			}
			if (can) {
				if (max < temp)
					max = temp;
			}
		}
	}

	public static void move4 (int row, int col) {
		for (int i = 0; i < 8; i++) {
			int nr, nc;
			boolean can = true;
			int temp = 0;
			for (int j = 0; j < 4; j++) {
				nr = row + dr4[i][j];
				nc = col + dc4[i][j];
				if (nr >= N || nc >= M) {
					can = false;
					break;
				}
				temp += map[nr][nc];
			}
			if (can) {
				if (max < temp)
					max = temp;
			}
		}
	}
}
