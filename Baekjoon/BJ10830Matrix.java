package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ10830Matrix {
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s, " ");
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		int[][] matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			st = new StringTokenizer(s, " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[][] result = squared(B, matrix);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(result[i][j] % 1000 + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	
	public static int[][] squared(long n, int[][] matrix) {
		if (n == 1)
			return matrix;
		int[][] ret = squared(n / 2, matrix);
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int k = 0; k < N; k++) {
					sum += (ret[i][k] * ret[k][j]);
				}
				temp[i][j] = sum % 1000;
			}
		}
		
		if (n % 2 != 0) {
			ret = temp.clone();
			temp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int sum = 0;
					for (int k = 0; k < N; k++) {
						sum += (ret[i][k] * matrix[k][j]);
					}
					temp[i][j] = sum % 1000;
				}
			}
		}
		return temp;
	}
}