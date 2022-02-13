package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ23291Fishbowl {
	static int N, K, map[][] = new int[100][100], control[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		s = br.readLine();
		st = new StringTokenizer(s, " ");
		for (int i = 0; i < N; i++) {
			map[99][i] = Integer.parseInt(st.nextToken());
		}
		int cnt = 1;
		while(true) {
			int min = 10000;
			for (int i = 0; i < N; i++) {
				min = Math.min(min, map[99][i]);
			}
			for (int i = 0; i < N; i++) {
				if (map[99][i] == min)
					map[99][i]++;
			}
			
			// 어항 쌓기
			map[98][1] = map[99][0];
			map[99][0] = 0;
			int rowNum = 0;
			int colNum = 0;
			while(true) {
				int[][] temp = new int[100][100];
				int tempNum = 0;
				for (int col = 0; col < N; col++) {
					if (map[99][col] == 0)
						continue;
					if (map[98][col] == 0)
						break;
					for (int row = 99; row >= 0; row--) {
						if (map[row][col] == 0)
							break;
						temp[tempNum][99 - row] = map[row][col];
						map[row][col] = 0;
					}
					tempNum++;
				}
				for (int i = 0; i < 100; i++) {
					if (temp[0][i] == 0) {
						colNum = i;
						break;
					}
				}
				for (int i = 0; i < 100; i++) {
					if (temp[i][0] == 0) {
						rowNum = i;
						break;
					}
				}
				int idx = 0;
				for (int i = 0; i < N; i++) {
					if (map[99][i] == 0)
						continue;
					idx = i;
					break;
				}
				if (idx + colNum > N) {
					idx -= rowNum;
					for (int row = 0; row < rowNum; row++) {
						for (int col = 0; col < colNum; col++) {
							map[99 - col][idx + row] = temp[row][col];
						}
					}
					break;
				}
				for (int row = 0; row < rowNum; row++) {
					for (int col = 0; col < colNum; col++) {
						map[99 - rowNum + row][idx + col] = temp[row][col];
					}
				}
				if (idx + colNum == N)
					break;
			}
			
			// 인접한 어항..
			controlFish();
			
			// 어항 놓기
			back();
			
			// 어항 쌓기
			{
				int[] temp = new int[N / 2];
				for (int i = 0; i < N / 2; i++) {
					temp[i] = map[99][i];
					map[99][i] = 0;
				}
				for (int i = 0; i < N / 2; i++) {
					map[98][i + N / 2] = temp[N / 2 - i - 1];
				}
			}
			{
				int[][] temp = new int[2][N / 4];
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < N / 4; j++) {
						temp[i][j] = map[99 - i][N / 4 * 3 - j - 1];
						map[99 - i][N / 4 * 3 - j - 1] = 0;
					}
				}
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < N / 4; j++) {
						map[96 + i][N / 4 * 3 + j] = temp[i][j];
					}
				}
			}
			
			// 인접한 어항..
			controlFish();
			
			// 어항 놓기
			back();
			
			min = Integer.MAX_VALUE;
			int max = 0;
			for (int i = 0; i < N; i++) {
				max = Math.max(max, map[99][i]);
				min = Math.min(min, map[99][i]);
			}
			if (max - min <= K) {
				System.out.println(cnt);
				break;
			}
			cnt++;
		}
	}

	public static void controlFish() {
		control = new int[100][100];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] != 0 && j < 99 && map[i][j + 1] != 0 && Math.abs(map[i][j] - map[i][j + 1]) / 5 > 0) {
					int d = Math.abs(map[i][j] - map[i][j + 1]) / 5;
					if (map[i][j] > map[i][j + 1]) {
						control[i][j] -= d;
						control[i][j + 1] += d;
					} else {
						control[i][j] += d;
						control[i][j + 1] -= d;
					}
				}
				if (map[i][j] != 0 && i < 99 && map[i + 1][j] != 0 && Math.abs(map[i][j] - map[i + 1][j]) / 5 > 0) {
					int d = Math.abs(map[i][j] - map[i + 1][j]) / 5;
					if (map[i][j] > map[i + 1][j]) {
						control[i][j] -= d;
						control[i + 1][j] += d;
					} else {
						control[i][j] += d;
						control[i + 1][j] -= d;
					}
				}
			}
		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				map[i][j] += control[i][j];
			}
		}
	}
	
	public static void back() {
		int tempCol = 0;
		for (int col = 0; col < N; col++) {
			if (map[99][col] == 0 || map[98][col] == 0)
				continue;
			for (int row = 99; row >= 0; row--) {
				if (map[row][col] == 0)
					break;
				map[99][tempCol++] = map[row][col];
				map[row][col] = 0;
			}
		}
	}
}
