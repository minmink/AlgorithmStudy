package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ20061Domino {
	static int t, x, y, answer = 0;
	static boolean[][] greenMap = new boolean[6][4], blueMap = new boolean[4][6];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int N = Integer.parseInt(s);
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			t = s.charAt(0) - '0';
			x = s.charAt(2) - '0';
			y = s.charAt(4) - '0';
			
			moveGreen();
			moveBlue();
		}
		
		System.out.println(answer);
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if (blueMap[i][j])
					cnt++;
				if (greenMap[j][i])
					cnt++;
			}
		}
		System.out.println(cnt);
	}

	public static void moveGreen() {
		int r1 = 0, r2 = 0;
		boolean[] removeIdx = new boolean[6];
		// 이동
		switch(t) {
		case 1:
			for (int row = 0; row < 6; row++) {
				if (greenMap[row][y]) {
					break;
				}
				r1 = row;
			}
			
			greenMap[r1][y] = true;
			break;
		case 2:
			for (int row = 0; row < 6; row++) {
				if (greenMap[row][y] || greenMap[row][y + 1]) {
					break;
				}
				r1 = row;
			}
			
			greenMap[r1][y] = true;
			greenMap[r1][y + 1] = true;
			break;
		case 3:
			for (int row = 0; row < 5; row++) {
				if (greenMap[row][y] || greenMap[row + 1][y]) {
					break;
				}
				r1 = row;
				r2 = row + 1;
			}
			
			greenMap[r1][y] = true;
			greenMap[r2][y] = true;
			break;
		}
		// 채워진 행 제거
		for (int i = 0; i < 6; i++) {
			boolean remove = true;
			for (int j = 0; j < 4; j++) {
				if (!greenMap[i][j]) {
					remove = false;
					break;
				}
			}
			if (remove) {
				removeIdx[i] = true;
				for (int j = 0; j < 4; j++) {
					greenMap[i][j] = false;
				}
				answer++;
			}
		}

		for (int i = 5; i >= 1; i--) {
			if (!removeIdx[i])
				continue;
			for (int row = i; row >= 1; row--) {
				for (int tempRow = row - 1; tempRow >= 0; tempRow--) {
					if (removeIdx[tempRow])
						continue;
					for (int col = 0; col < 4; col++) {
						greenMap[row][col] = greenMap[tempRow][col];
					}
					break;
				}
			}
			for (int col = 0; col < 4; col++) {
				greenMap[0][col] = false;
			}
		}
		// 0, 1행에 블록이 있으면 행 제거
		int cnt = 0;
		removeIdx = new boolean[6];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (greenMap[i][j]) {
					cnt++;
					break;
				}
			}
		}
		
		for (int i = 5; i > 5 - cnt; i--) {
			if (removeIdx[i])
				continue;
			removeIdx[i] = true;
			for (int j = 0; j < 4; j++) {
				greenMap[i][j] = false;
			}
		}
		// 비워진 행 있으면 내려오기
		for (int i = 5; i >= 1; i--) {
			if (!removeIdx[i])
				continue;
			for (int row = i; row >= 1; row--) {
				for (int tempRow = row - 1; tempRow >= 0; tempRow--) {
					if (removeIdx[tempRow])
						continue;
					for (int col = 0; col < 4; col++) {
						greenMap[row][col] = greenMap[tempRow][col];
					}
					break;
				}
			}
			for (int col = 0; col < 4; col++) {
				greenMap[0][col] = false;
			}
		}
	}
	
	public static void moveBlue() {
		int c1 = 0, c2 = 0;
		boolean[] removeIdx = new boolean[6];
		// 이동
		switch(t) {
		case 1:
			for (int col = 0; col < 6; col++) {
				if (blueMap[x][col]) {
					break;
				}
				c1 = col;
			}
			
			blueMap[x][c1] = true;
			break;
		case 2:
			for (int col = 0; col < 5; col++) {
				if (blueMap[x][col] || blueMap[x][col + 1]) {
					break;
				}
				c1 = col;
				c2 = col + 1;
			}
			
			blueMap[x][c1] = true;
			blueMap[x][c2] = true;
			break;
		case 3:
			for (int col = 0; col < 6; col++) {
				if (blueMap[x][col] || blueMap[x + 1][col]) {
					break;
				}
				c1 = col;
			}
			
			blueMap[x][c1] = true;
			blueMap[x + 1][c1] = true;
			break;
		}
		// 채워진 열 제거
		for (int i = 0; i < 6; i++) {
			boolean remove = true;
			for (int j = 0; j < 4; j++) {
				if (!blueMap[j][i]) {
					remove = false;
					break;
				}
			}
			if (remove) {
				removeIdx[i] = true;
				for (int j = 0; j < 4; j++) {
					blueMap[j][i] = false;
				}
				answer++;
			}
		}

		for (int i = 5; i >= 1; i--) {
			if (!removeIdx[i])
				continue;
			for (int col = i; col >= 1; col--) {
				for (int tempCol = col - 1; tempCol >= 0; tempCol--) {
					if (removeIdx[tempCol])
						continue;
					for (int row = 0; row < 4; row++) {
						blueMap[row][col] = blueMap[row][tempCol];
					}
					break;
				}
			}
			for (int row = 0; row < 4; row++) {
				blueMap[row][0] = false;
			}
		}
		// 0, 1열에 블록이 있으면 열 제거
		int cnt = 0;
		removeIdx = new boolean[6];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (blueMap[j][i]) {
					cnt++;
					break;
				}
			}
		}
		
		for (int i = 5; i > 5 - cnt; i--) {
			if (removeIdx[i])
				continue;
			removeIdx[i] = true;
			for (int j = 0; j < 4; j++) {
				blueMap[j][i] = false;
			}
		}
		// 비워진 열 있으면 오른쪽으로 오기
		for (int i = 5; i >= 1; i--) {
			if (!removeIdx[i])
				continue;
			for (int col = i; col >= 1; col--) {
				for (int tempCol = col - 1; tempCol >= 0; tempCol--) {
					if (removeIdx[tempCol])
						continue;
					for (int row = 0; row < 4; row++) {
						blueMap[row][col] = blueMap[row][tempCol];
					}
					break;
				}
			}
			for (int row = 0; row < 4; row++) {
				blueMap[row][0] = false;
			}
		}
	}
}
