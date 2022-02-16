package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ5373Cubing {
	static char[][] cube = new char[12][9];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		Integer tc = Integer.parseInt(s);
		StringBuilder sb = new StringBuilder();
		for (int testCase = 0; testCase < tc; testCase++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 3; j < 6; j++) {
					cube[i][j] = 'o';
				}
			}
			for (int i = 3; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					cube[i][j] = 'g';
				}
				for (int j = 3; j < 6; j++) {
					cube[i][j] = 'w';
				}
				for (int j = 6; j < 9; j++) {
					cube[i][j] = 'b';
				}
			}
			for (int i = 6; i < 9; i++) {
				for (int j = 3; j < 6; j++) {
					cube[i][j] = 'r';
				}
			}
			for (int i = 9; i < 12; i++) {
				for (int j = 3; j < 6; j++) {
					cube[i][j] = 'y';
				}
			}
			s = br.readLine();
			int n = Integer.parseInt(s);
			s = br.readLine();
			int idx = 0;
			char dest, dir;
			for (int N = 0; N < n; N++) {
				dest = s.charAt(idx++);
				dir = s.charAt(idx++);
				idx++;
				char[] temp = new char[3];
				switch(dest) {
				case 'U':
					if (dir == '+') {
						clockwise('U');
						temp[0] = cube[2][3]; temp[1] = cube[2][4]; temp[2] = cube[2][5];
						cube[2][3] = cube[5][2]; cube[2][4] = cube[4][2]; cube[2][5] = cube[3][2];
						cube[5][2] = cube[6][5]; cube[4][2] = cube[6][4]; cube[3][2] = cube[6][3];
						cube[6][5] = cube[3][6]; cube[6][4] = cube[4][6]; cube[6][3] = cube[5][6];
						cube[3][6] = temp[0]; cube[4][6] = temp[1]; cube[5][6] = temp[2];
					} else {
						counterclockwise('U');
						temp[0] = cube[2][3]; temp[1] = cube[2][4]; temp[2] = cube[2][5];
						cube[2][3] = cube[3][6]; cube[2][4] = cube[4][6]; cube[2][5] = cube[5][6];
						cube[3][6] = cube[6][5]; cube[4][6] = cube[6][4]; cube[5][6] = cube[6][3];
						cube[6][5] = cube[5][2]; cube[6][4] = cube[4][2]; cube[6][3] = cube[3][2];
						cube[5][2] = temp[0]; cube[4][2] = temp[1]; cube[3][2] = temp[2];
					}
					break;
				case 'B':
					if (dir == '+') {
						clockwise('B');
						temp[0] = cube[11][3]; temp[1] = cube[11][4]; temp[2] = cube[11][5];
						cube[11][3] = cube[3][2]; cube[11][4] = cube[3][1]; cube[11][5] = cube[3][0];
						cube[3][2] = cube[3][5]; cube[3][1] = cube[3][4]; cube[3][0] = cube[3][3];
						cube[3][5] = cube[3][8]; cube[3][4] = cube[3][7]; cube[3][3] = cube[3][6];
						cube[3][8] = temp[0]; cube[3][7] = temp[1]; cube[3][6] = temp[2];
					} else {
						counterclockwise('B');
						temp[0] = cube[11][3]; temp[1] = cube[11][4]; temp[2] = cube[11][5];
						cube[11][3] = cube[3][8]; cube[11][4] = cube[3][7]; cube[11][5] = cube[3][6];
						cube[3][8] = cube[3][5]; cube[3][7] = cube[3][4]; cube[3][6] = cube[3][3];
						cube[3][5] = cube[3][2]; cube[3][4] = cube[3][1]; cube[3][3] = cube[3][0];
						cube[3][2] = temp[0]; cube[3][1] = temp[1]; cube[3][0] = temp[2];
					}
					break;
				case 'F':
					if (dir == '+') {
						clockwise('F');
						temp[0] = cube[5][3]; temp[1] = cube[5][4]; temp[2] = cube[5][5];
						cube[5][3] = cube[5][0]; cube[5][4] = cube[5][1]; cube[5][5] = cube[5][2];
						cube[5][0] = cube[9][5]; cube[5][1] = cube[9][4]; cube[5][2] = cube[9][3];
						cube[9][5] = cube[5][6]; cube[9][4] = cube[5][7]; cube[9][3] = cube[5][8];
						cube[5][6] = temp[0]; cube[5][7] = temp[1]; cube[5][8] = temp[2];
					} else {
						counterclockwise('F');
						temp[0] = cube[5][3]; temp[1] = cube[5][4]; temp[2] = cube[5][5];
						cube[5][3] = cube[5][6]; cube[5][4] = cube[5][7]; cube[5][5] = cube[5][8];
						cube[5][6] = cube[9][5]; cube[5][7] = cube[9][4]; cube[5][8] = cube[9][3];
						cube[9][5] = cube[5][0]; cube[9][4] = cube[5][1]; cube[9][3] = cube[5][2];
						cube[5][0] = temp[0]; cube[5][1] = temp[1]; cube[5][2] = temp[2];
					}
					break;
				case 'D':
					if (dir == '+') {
						clockwise('D');
						temp[0] = cube[8][3]; temp[1] = cube[8][4]; temp[2] = cube[8][5];
						cube[8][3] = cube[3][0]; cube[8][4] = cube[4][0]; cube[8][5] = cube[5][0];
						cube[3][0] = cube[0][5]; cube[4][0] = cube[0][4]; cube[5][0] = cube[0][3];
						cube[0][5] = cube[5][8]; cube[0][4] = cube[4][8]; cube[0][3] = cube[3][8];
						cube[5][8] = temp[0]; cube[4][8] = temp[1]; cube[3][8] = temp[2];
					} else {
						counterclockwise('D');
						temp[0] = cube[8][3]; temp[1] = cube[8][4]; temp[2] = cube[8][5];
						cube[8][3] = cube[5][8]; cube[8][4] = cube[4][8]; cube[8][5] = cube[3][8];
						cube[5][8] = cube[0][5]; cube[4][8] = cube[0][4]; cube[3][8] = cube[0][3];
						cube[0][5] = cube[3][0]; cube[0][4] = cube[4][0]; cube[0][3] = cube[5][0];
						cube[3][0] = temp[0]; cube[4][0] = temp[1]; cube[5][0] = temp[2];
					}
					break;
				case 'L':
					if (dir == '+') {
						clockwise('L');
						temp[0] = cube[3][3]; temp[1] = cube[4][3]; temp[2] = cube[5][3];
						cube[3][3] = cube[0][3]; cube[4][3] = cube[1][3]; cube[5][3] = cube[2][3];
						cube[0][3] = cube[9][3]; cube[1][3] = cube[10][3]; cube[2][3] = cube[11][3];
						cube[9][3] = cube[6][3]; cube[10][3] = cube[7][3]; cube[11][3] = cube[8][3];
						cube[6][3] = temp[0]; cube[7][3] = temp[1]; cube[8][3] = temp[2];
					} else {
						counterclockwise('L');
						temp[0] = cube[3][3]; temp[1] = cube[4][3]; temp[2] = cube[5][3];
						cube[3][3] = cube[6][3]; cube[4][3] = cube[7][3]; cube[5][3] = cube[8][3];
						cube[6][3] = cube[9][3]; cube[7][3] = cube[10][3]; cube[8][3] = cube[11][3];
						cube[9][3] = cube[0][3]; cube[10][3] = cube[1][3]; cube[11][3] = cube[2][3];
						cube[0][3] = temp[0]; cube[1][3] = temp[1]; cube[2][3] = temp[2];
					}
					break;
				case 'R':
					if (dir == '+') {
						clockwise('R');
						temp[0] = cube[3][5]; temp[1] = cube[4][5]; temp[2] = cube[5][5];
						cube[3][5] = cube[6][5]; cube[4][5] = cube[7][5]; cube[5][5] = cube[8][5];
						cube[6][5] = cube[9][5]; cube[7][5] = cube[10][5]; cube[8][5] = cube[11][5];
						cube[9][5] = cube[0][5]; cube[10][5] = cube[1][5]; cube[11][5] = cube[2][5];
						cube[0][5] = temp[0]; cube[1][5] = temp[1]; cube[2][5] = temp[2];
					} else {
						counterclockwise('R');
						temp[0] = cube[3][5]; temp[1] = cube[4][5]; temp[2] = cube[5][5];
						cube[3][5] = cube[0][5]; cube[4][5] = cube[1][5]; cube[5][5] = cube[2][5];
						cube[0][5] = cube[9][5]; cube[1][5] = cube[10][5]; cube[2][5] = cube[11][5];
						cube[9][5] = cube[6][5]; cube[10][5] = cube[7][5]; cube[11][5] = cube[8][5];
						cube[6][5] = temp[0]; cube[7][5] = temp[1]; cube[8][5] = temp[2];
					}
					break;
				}
			}
			for (int i = 3; i < 6; i++) {
				for (int j = 3; j < 6; j++) {
					sb.append(cube[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void clockwise(char dest) {
		char[] temp = new char[3];
		int r = 0, c = 0;
		switch(dest) {
		case 'U':
			r = 3;
			c = 3;
			break;
		case 'B':
			c = 3;
			break;
		case 'F':
			r = 6;
			c = 3;
			break;
		case 'D':
			r = 9;
			c = 3;
			break;
		case 'L':
			r = 3;
			break;
		case 'R':
			r = 3;
			c = 6;
			break;
		}
		temp[0] = cube[r][c];
		temp[1] = cube[r + 1][c];
		temp[2] = cube[r + 2][c];
		cube[r][c] = cube[r + 2][c];
		cube[r + 1][c] = cube[r + 2][c + 1];
		cube[r + 2][c] = cube[r + 2][c + 2];
		cube[r + 2][c + 1] = cube [r + 1][c + 2];
		cube[r + 2][c + 2] = cube[r][c + 2];
		cube[r + 1][c + 2] = cube[r][c + 1];
		cube[r][c + 2] = temp[0];
		cube[r][c + 1] = temp[1];
	}
	
	public static void counterclockwise(char dest) {
		char[] temp = new char[3];
		int r = 0, c = 0;
		switch(dest) {
		case 'U':
			r = 3;
			c = 3;
			break;
		case 'B':
			c = 3;
			break;
		case 'F':
			r = 6;
			c = 3;
			break;
		case 'D':
			r = 9;
			c = 3;
			break;
		case 'L':
			r = 3;
			break;
		case 'R':
			r = 3;
			c = 6;
			break;
		}
		temp[0] = cube[r][c];
		temp[1] = cube[r + 1][c];
		temp[2] = cube[r + 2][c];
		cube[r][c] = cube[r][c + 2];
		cube[r + 1][c] = cube[r][c + 1];
		cube[r + 2][c] = temp[0];
		cube[r][c + 2] = cube[r + 2][c + 2];
		cube[r][c + 1] = cube[r + 1][c + 2];
		cube[r + 2][c + 2] = temp[2];
		cube[r + 1][c + 2] = cube[r + 2][c + 1];
		cube[r + 2][c + 1] = temp[1];
	}
}
