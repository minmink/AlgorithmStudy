package algorithm;

import java.io.*;

public class SW1264Image {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine().trim());
			String X = br.readLine();
			String Y = br.readLine();
			int[][] board = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(X.charAt(i - 1) == Y.charAt(j - 1))
						board[i][j] = board[i - 1][j - 1] + 1;
					else
						board[i][j] = Math.max(board[i - 1][j], board[i][j - 1]);
				}
			}
			double answer = board[N][N] * 100 / N;
			sb.append(String.format("%.2f", answer)).append("\n");
		}

		System.out.println(sb.toString());
	}
}
