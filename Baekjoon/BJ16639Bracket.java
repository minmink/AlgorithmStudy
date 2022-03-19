package algorithm;

import java.io.*;
import java.util.Arrays;

public class BJ16639Bracket {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		if (N == 1) {
			System.out.println(s);
			return;
		} else if (N == 3) {
			System.out.println(calc(s.charAt(0) - '0', s.charAt(2) - '0', s.charAt(1)));
			return;
		}
		
		int[][] dpMax = new int[N / 2 + 1][N / 2 + 1];
		int[][] dpMin = new int[N / 2 + 1][N / 2 + 1];
		for (int i = 0; i <= N / 2; i++) {
			Arrays.fill(dpMax[i], Integer.MIN_VALUE);
			Arrays.fill(dpMin[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i <= N / 2; i++) {
			dpMin[i][i] = dpMax[i][i] = s.charAt(i * 2) - '0';
			if (i == 0)
				continue;
			dpMin[i - 1][i] = dpMax[i - 1][i] = calc(dpMin[i - 1][i - 1], dpMin[i][i], s.charAt(i * 2 - 1));
		}
		
		for (int col = 2; col <= N / 2; col++) {
			for (int row = 0; row < N / 2; row++) {
				if (row + col > N / 2)
					break;
				for (int idx = 0; idx < col; idx++) {
					dpMin[row][row + col] = Math.min(dpMin[row][row + col],
							calc(dpMin[row][row + idx], dpMin[row + idx + 1][row + col], s.charAt((row + idx) * 2 + 1)));
					dpMin[row][row + col] = Math.min(dpMin[row][row + col],
							calc(dpMin[row][row + idx], dpMax[row + idx + 1][row + col], s.charAt((row + idx) * 2 + 1)));
					dpMin[row][row + col] = Math.min(dpMin[row][row + col],
							calc(dpMax[row][row + idx], dpMax[row + idx + 1][row + col], s.charAt((row + idx) * 2 + 1)));
					dpMin[row][row + col] = Math.min(dpMin[row][row + col],
							calc(dpMax[row][row + idx], dpMin[row + idx + 1][row + col], s.charAt((row + idx) * 2 + 1)));
					dpMax[row][row + col] = Math.max(dpMax[row][row + col],
								calc(dpMax[row][row + idx], dpMax[row + idx + 1][row + col], s.charAt((row + idx) * 2 + 1)));
					dpMax[row][row + col] = Math.max(dpMax[row][row + col],
							calc(dpMax[row][row + idx], dpMin[row + idx + 1][row + col], s.charAt((row + idx) * 2 + 1)));
					dpMax[row][row + col] = Math.max(dpMax[row][row + col],
							calc(dpMin[row][row + idx], dpMax[row + idx + 1][row + col], s.charAt((row + idx) * 2 + 1)));
					dpMax[row][row + col] = Math.max(dpMax[row][row + col],
							calc(dpMin[row][row + idx], dpMin[row + idx + 1][row + col], s.charAt((row + idx) * 2 + 1)));
				}
			}
		}
		
		System.out.println(dpMax[0][N / 2]);
	}

	private static int calc(int num1, int num2, char op) {
		switch(op) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		default:
			return num1 * num2;
		}
	}
}
