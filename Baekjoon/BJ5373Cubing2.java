package algorithm;

import java.io.*;

public class BJ5373Cubing2 {
	static char[][] cube;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < testCase; tc++) {
			cube = new char[][] {{' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' '},
				{' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' '},
				{' ', ' ', ' ', 'o', 'o', 'o', ' ', ' ', ' '},
				{'g', 'g', 'g', 'w', 'w', 'w', 'b', 'b', 'b'},
				{'g', 'g', 'g', 'w', 'w', 'w', 'b', 'b', 'b'},
				{'g', 'g', 'g', 'w', 'w', 'w', 'b', 'b', 'b'},
				{' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' '},
				{' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' '},
				{' ', ' ', ' ', 'r', 'r', 'r', ' ', ' ', ' '},
				{' ', ' ', ' ', 'y', 'y', 'y', ' ', ' ', ' '},
				{' ', ' ', ' ', 'y', 'y', 'y', ' ', ' ', ' '},
				{' ', ' ', ' ', 'y', 'y', 'y', ' ', ' ', ' '}};
			int n = Integer.parseInt(br.readLine());
			String order = br.readLine();
			for (int idx = 0; idx < order.length(); idx++) {
				char place = order.charAt(idx++);
				char dir = order.charAt(idx++);
				char temp1, temp2, temp3;
				switch(place) {
				case 'L':
					temp1 = cube[9][3];
					temp2 = cube[10][3];
					temp3 = cube[11][3];
					if(dir == '+') {
						clock(3, 0);
						cube[9][3] = cube[6][3];
						cube[10][3] = cube[7][3];
						cube[11][3] = cube[8][3];
						cube[6][3] = cube[3][3];
						cube[7][3] = cube[4][3];
						cube[8][3] = cube[5][3];
						cube[3][3] = cube[0][3];
						cube[4][3] = cube[1][3];
						cube[5][3] = cube[2][3];
						cube[0][3] = temp1;
						cube[1][3] = temp2;
						cube[2][3] = temp3;
					} else {
						counter(3, 0);
						cube[9][3] = cube[0][3];
						cube[10][3] = cube[1][3];
						cube[11][3] = cube[2][3];
						cube[0][3] = cube[3][3];
						cube[1][3] = cube[4][3];
						cube[2][3] = cube[5][3];
						cube[3][3] = cube[6][3];
						cube[4][3] = cube[7][3];
						cube[5][3] = cube[8][3];
						cube[6][3] = temp1;
						cube[7][3] = temp2;
						cube[8][3] = temp3;
					}
					break;
				case 'R':
					temp1 = cube[9][5];
					temp2 = cube[10][5];
					temp3 = cube[11][5];
					if(dir == '+') {
						clock(3, 6);
						cube[9][5] = cube[0][5];
						cube[10][5] = cube[1][5];
						cube[11][5] = cube[2][5];
						cube[0][5] = cube[3][5];
						cube[1][5] = cube[4][5];
						cube[2][5] = cube[5][5];
						cube[3][5] = cube[6][5];
						cube[4][5] = cube[7][5];
						cube[5][5] = cube[8][5];
						cube[6][5] = temp1;
						cube[7][5] = temp2;
						cube[8][5] = temp3;
					} else {
						counter(3, 6);
						cube[9][5] = cube[6][5];
						cube[10][5] = cube[7][5];
						cube[11][5] = cube[8][5];
						cube[6][5] = cube[3][5];
						cube[7][5] = cube[4][5];
						cube[8][5] = cube[5][5];
						cube[3][5] = cube[0][5];
						cube[4][5] = cube[1][5];
						cube[5][5] = cube[2][5];
						cube[0][5] = temp1;
						cube[1][5] = temp2;
						cube[2][5] = temp3;
					}
					break;
				case 'U':
					temp1 = cube[2][3];
					temp2 = cube[2][4];
					temp3 = cube[2][5];
					if(dir == '+') {
						clock(3, 3);
						cube[2][3] = cube[5][2];
						cube[2][4] = cube[4][2];
						cube[2][5] = cube[3][2];
						cube[5][2] = cube[6][5];
						cube[4][2] = cube[6][4];
						cube[3][2] = cube[6][3];
						cube[6][5] = cube[3][6];
						cube[6][4] = cube[4][6];
						cube[6][3] = cube[5][6];
						cube[3][6] = temp1;
						cube[4][6] = temp2;
						cube[5][6] = temp3;
					} else {
						counter(3, 3);
						cube[2][3] =  cube[3][6];
						cube[2][4] = cube[4][6];
						cube[2][5] = cube[5][6];
						cube[3][6] = cube[6][5];
						cube[4][6] = cube[6][4];
						cube[5][6] = cube[6][3];
						cube[6][5] = cube[5][2];
						cube[6][4] = cube[4][2];
						cube[6][3] = cube[3][2];
						cube[5][2] = temp1;
						cube[4][2] = temp2;
						cube[3][2] = temp3;
					}
					break;
				case 'D':
					temp1 = cube[8][3];
					temp2 = cube[8][4];
					temp3 = cube[8][5];
					if(dir == '+') {
						clock(9, 3);
						cube[8][3] = cube[3][0];
						cube[8][4] = cube[4][0];
						cube[8][5] = cube[5][0];
						cube[3][0] = cube[0][5];
						cube[4][0] = cube[0][4];
						cube[5][0] = cube[0][3];
						cube[0][5] = cube[5][8];
						cube[0][4] = cube[4][8];
						cube[0][3] = cube[3][8];
						cube[5][8] = temp1;
						cube[4][8] = temp2;
						cube[3][8] = temp3;
					} else {
						counter(9, 3);
						cube[8][3] = cube[5][8];
						cube[8][4] = cube[4][8];
						cube[8][5] = cube[3][8];
						cube[5][8] = cube[0][5];
						cube[4][8] = cube[0][4];
						cube[3][8] = cube[0][3];
						cube[0][5] = cube[3][0];
						cube[0][4] = cube[4][0];
						cube[0][3] = cube[5][0];
						cube[3][0] = temp1;
						cube[4][0] = temp2;
						cube[5][0] = temp3;
					}
					break;
				case 'F':
					temp1 = cube[5][3];
					temp2 = cube[5][4];
					temp3 = cube[5][5];
					if(dir == '+') {
						clock(6, 3);
						cube[5][3] = cube[5][0];
						cube[5][4] = cube[5][1];
						cube[5][5] = cube[5][2];
						cube[5][0] = cube[9][5];
						cube[5][1] = cube[9][4];
						cube[5][2] = cube[9][3];
						cube[9][5] = cube[5][6];
						cube[9][4] = cube[5][7];
						cube[9][3] = cube[5][8];
						cube[5][6] = temp1;
						cube[5][7] = temp2;
						cube[5][8] = temp3;
					} else {
						counter(6, 3);
						cube[5][3] = cube[5][6];
						cube[5][4] = cube[5][7];
						cube[5][5] = cube[5][8];
						cube[5][6] = cube[9][5];
						cube[5][7] = cube[9][4];
						cube[5][8] = cube[9][3];
						cube[9][5] = cube[5][0];
						cube[9][4] = cube[5][1];
						cube[9][3] = cube[5][2];
						cube[5][0] = temp1;
						cube[5][1] = temp2;
						cube[5][2] = temp3;
					}
					break;
				case 'B':
					temp1 = cube[3][3];
					temp2 = cube[3][4];
					temp3 = cube[3][5];
					if(dir == '+') {
						clock(0, 3);
						cube[3][3] = cube[3][6];
						cube[3][4] = cube[3][7];
						cube[3][5] = cube[3][8];
						cube[3][6] = cube[11][5];
						cube[3][7] = cube[11][4];
						cube[3][8] = cube[11][3];
						cube[11][5] = cube[3][0];
						cube[11][4] = cube[3][1];
						cube[11][3] = cube[3][2];
						cube[3][0] = temp1;
						cube[3][1] = temp2;
						cube[3][2] = temp3;
					} else {
						counter(0, 3);
						cube[3][3] = cube[3][0];
						cube[3][4] = cube[3][1];
						cube[3][5] = cube[3][2];
						cube[3][0] = cube[11][5];
						cube[3][1] = cube[11][4];
						cube[3][2] = cube[11][3];
						cube[11][5] = cube[3][6];
						cube[11][4] = cube[3][7];
						cube[11][3] = cube[3][8];
						cube[3][6] = temp1;
						cube[3][7] = temp2;
						cube[3][8] = temp3;
					}
					break;
				}
			}
			for (int row = 3; row < 6; row++) {
				for (int col = 3; col < 6; col++) {
					sb.append(cube[row][col]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb.toString());
	}

	public static void clock(int row, int col) {
		char temp1 = cube[row][col + 2];
		char temp2 = cube[row][col + 1];
		cube[row][col + 2] = cube[row][col];
		cube[row][col + 1] = cube[row + 1][col];
		cube[row][col] = cube[row + 2][col];
		cube[row + 1][col] = cube[row + 2][col + 1];
		cube[row + 2][col] = cube[row + 2][col + 2];
		cube[row + 2][col + 1] = cube[row + 1][col + 2];
		cube[row + 2][col + 2] = temp1;
		cube[row + 1][col + 2] = temp2;
	}
	
	public static void counter(int row, int col) {
		char temp1 = cube[row][col];
		char temp2 = cube[row][col + 1];
		cube[row][col] = cube[row][col + 2];
		cube[row][col + 1] = cube[row + 1][col + 2];
		cube[row][col + 2] = cube[row + 2][col + 2];
		cube[row + 1][col + 2] = cube[row + 2][col + 1];
		cube[row + 2][col + 2] = cube[row + 2][col];
		cube[row + 2][col + 1] = cube[row + 1][col];
		cube[row + 2][col] = temp1;
		cube[row + 1][col] = temp2;
	}
}
