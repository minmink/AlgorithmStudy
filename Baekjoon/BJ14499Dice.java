package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14499Dice {
	static int map[][], dice[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dice = new int[4][3];
		int[] dr = {0, 0, 0, -1, 1};
		int[] dc = {0, 1, -1, 0, 0};
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		s = br.readLine();
		StringBuilder sb = new StringBuilder();
		int dir = 0;
		for (int i = 0; i < s.length(); i = i + 2) {
			dir = s.charAt(i) - '0';
			int nr = row + dr[dir];
			int nc = col + dc[dir];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M)
				continue;
			row = nr;
			col = nc;
			rollDice(dir);
			if (map[row][col] == 0)
				map[row][col] = dice[3][1];
			else {
				dice[3][1] = map[row][col];
				map[row][col] = 0;
			}
			sb.append(dice[1][1] + "\n");
		}
		System.out.println(sb.toString());
	}

	public static void rollDice(int dir) {
		int temp;
		switch (dir) {
		case 1: // 동
			temp = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 2: // 서
			temp = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 3: // 북
			temp = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = dice[3][1];
			dice[3][1] = temp;
			break;
		case 4: // 남
			temp = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = temp;
			break;
		}
	}
}
