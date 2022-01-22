package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1074Z2 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		int cnt = 0;
		
		for (int i = 0; i < N - 1; i++) {
			if (row >= Math.pow(2, N - 1 - i)) {
				cnt += (int) Math.pow(2, 2 * N - 2 * i - 1);
				row -= (int) Math.pow(2, N - 1 - i);
			}
			if (col >= Math.pow(2, N - 1 - i)) {
				cnt += (int) Math.pow(2, 2 * N - 2 * i - 2);
				col -= (int) Math.pow(2, N - 1 - i);
			}
		}
		
		if (row == 0) {
			if (col == 0)
				System.out.println(cnt);
			else
				System.out.println(cnt + 1);
		} else {
			if (col == 0)
				System.out.println(cnt + 2);
			else
				System.out.println(cnt + 3);
		}
	}
}