package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1074Z {

	private static boolean flag = false;
	private static int map[][], index = -1, r, c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int n = (int) Math.pow(2, N);
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		z(n, 0, 0);
		
		System.out.println(index);
	}

	public static void z(int N, int row, int col) {
		if(flag) return;
		if(N == 0) {
			index++;
			if(row == r && col == c) {
				flag = true;
			}
			return;
		}
		if(!flag) z(N/2, row, col);
		if(!flag) z(N/2, row, col+N);
		if(!flag) z(N/2, row+N, col);
		if(!flag) z(N/2, row+N, col+N);
	}
	
}