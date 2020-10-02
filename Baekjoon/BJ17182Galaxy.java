package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17182Galaxy {

	static boolean[] visited;
	static int min = Integer.MAX_VALUE, N, matrix[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					matrix[j][k] = Math.min(matrix[j][k], matrix[j][i] + matrix[i][k]);
				}
			}
		}
		
		visited = new boolean[N];
		visited[K] = true;
		dfs(K, N-1, 0);
		
		System.out.println(min);
	}

	private static void dfs(int cur, int cnt, int total) {
		if(total >= min) return;
		
		if(cnt == 0) {
			min = Math.min(total, min);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, cnt-1, total+matrix[cur][i]);
				visited[i] = false;
			}
		}
	}

}
