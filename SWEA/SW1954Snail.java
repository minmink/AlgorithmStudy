package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1954Snail {
	
	private static int N, map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			map = new int[N][N];
			int index = 1;
			
			if(N == 1) map[0][0] = index;
			
snail:		for (int x = 0; x < N-1; x++) {
				for (int i = x; i < N-x; i++) {
					map[x][i] = index++;
				}
				if(index > N*N) break snail;
				
				for (int j = x + 1; j < N-x; j++) {
					map[j][N-1-x] = index++;
				}
				
				for (int i = N-2-x; i >= x; i--) {
					map[N-1-x][i] = index++;
				}
				if(index > N*N) break snail;
				
				for (int j = N-2-x; j > x; j--) {
					map[j][x] = index++;
				}
			}
			
			sb.append('#').append(test_case).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]).append(' ');
				}
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}

	}

}
