package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15685Curve {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st;
		int N = Integer.parseInt(s);
		int[] dr = {0, -1, 0, 1};
		int[] dc = {1, 0, -1, 0};
		int[] dir = new int[(int) Math.pow(2, 10)];
		int[] tempDir = new int[(int) Math.pow(2, 9) + 1];
		int[][] map = new int[101][101];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			int col = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			dir[0] = d;
			map[row][col]++;
			row += dr[d];
			col += dc[d];
			map[row][col]++;
			for (int gen = 1; gen <= g; gen++) {
				int powNum = (int) Math.pow(2, gen - 1);
				for (int j = 0; j < powNum; j++) {
					tempDir[powNum - j - 1] = (dir[j] + 1) % 4;
				}
				for (int j = 0; j < powNum; j++) {
					dir[j + powNum] = tempDir[j];
				}
				for (int j = 0; j < powNum; j++) {
					row += dr[tempDir[j]];
					col += dc[tempDir[j]];
					map[row][col]++;
				}
			}
		}
		
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] > 0 && map[i + 1][j] > 0 && map[i][j + 1] > 0 && map[i + 1][j + 1] > 0)
					cnt++;
			}
		}
		System.out.println(cnt);
	}
}
