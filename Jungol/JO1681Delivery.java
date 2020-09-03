package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JO1681Delivery {

	static int[][] adjMatrix;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE, N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		adjMatrix = new int[N][N];
		visited = new boolean[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < N; i++) {
			if(adjMatrix[0][i] != 0) {
				visited[i] = true;
				dfs(i, adjMatrix[0][i], 1);
				visited[i] = false;
			}
		}
		
		System.out.println(min);
	}

	private static void dfs(int start, int sum, int cnt) {
		if(cnt != N && start == 0) return;
		if(cnt == N) {
			if(min > sum) min = sum;
			return;
		}
		if(sum >= min) return;
		for (int i = 0; i < N; i++) {
			if(!visited[i] && adjMatrix[start][i] > 0) {
				visited[i] = true;
				dfs(i, sum+adjMatrix[start][i], cnt+1);
				visited[i] = false;
			}
		}
	}
}
