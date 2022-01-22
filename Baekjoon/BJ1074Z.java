package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1074Z {

	private static boolean flag = false;
	private static int cnt = 0, r, c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int n = (int) Math.pow(2, N);
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		z(n, 0, 0);
		z(n, 0, n / 2);
		z(n, n / 2, 0);
		z(n, n / 2, n / 2);
		
		System.out.println(cnt);
	}

	public static void z(int N, int row, int col) {
		if(flag) return;
		if(N == 1) {
			cnt++;
			if(row == r && col == c) {
				cnt--;
				flag = true;
			}
			return;
		}
		if(!flag) z(N/2, row, col);
		if(!flag) z(N/2, row, col+N/2);
		if(!flag) z(N/2, row+N/2, col);
		if(!flag) z(N/2, row+N/2, col+N/2);
	}
	
}