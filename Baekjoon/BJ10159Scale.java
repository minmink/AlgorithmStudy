package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10159Scale {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		boolean[][] graph = new boolean[N + 1][N + 1];
		boolean[][] reverse = new boolean[N + 1][N + 1];
		for (int m = 0; m < M; m++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			graph[i][j] = true;
			reverse[j][i] = true;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(i == k)
					continue;
				for (int j = 1; j <= N; j++) {
					if(i == j || k == j)
						continue;
					
					if(graph[i][k] && graph[k][j])
						graph[i][j] = true;
					if(reverse[i][k] && reverse[k][j])
						reverse[i][j] = true;
				}
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				graph[i][j] |= reverse[i][j];
			}
		}
		
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if(i == j)
					continue;
				if(!graph[i][j])
					cnt++;
			}
			sb.append(cnt).append("\n");
		}
		System.out.print(sb.toString());
	}
}
